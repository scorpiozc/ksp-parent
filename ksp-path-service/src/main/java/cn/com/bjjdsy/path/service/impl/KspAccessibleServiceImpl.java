package cn.com.bjjdsy.path.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.calc.accessible.AccessibleCalcEngine;
import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.common.constant.CalcPathEnum;
import cn.com.bjjdsy.common.constant.ParamDictEnum;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.entity.db.ParamRuleAccessible;
import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;
import cn.com.bjjdsy.data.entity.db.ParamVersionRely;
import cn.com.bjjdsy.data.entity.db.ParamVersionTask;
import cn.com.bjjdsy.data.entity.path.Line;
import cn.com.bjjdsy.data.entity.path.Station;
import cn.com.bjjdsy.data.loader.LoadData;
import cn.com.bjjdsy.data.service.ParamOdRouteAccessibleService;
import cn.com.bjjdsy.data.service.ParamRuleAccessibleService;
import cn.com.bjjdsy.data.service.ParamVersionInfoService;
import cn.com.bjjdsy.data.service.ParamVersionRelyService;
import cn.com.bjjdsy.data.service.ParamVersionTaskService;
import cn.com.bjjdsy.path.service.KspService;

@Service("kspAccessibleServiceImpl")
public class KspAccessibleServiceImpl implements KspService {

	private static final Logger logger = LoggerFactory.getLogger(KspAccessibleServiceImpl.class);
	private String filepath;
	@Resource(name = "loadKspDataFileImpl")
	private LoadData loadDataFile;
	@Resource(name = "loadKspDataDbImpl")
	private LoadData loadDataDb;
	@Autowired
	private ParamVersionInfoService paramVersionInfoService;
	@Autowired
	private AccessibleCalcEngine calcEngine;
	@Autowired
	private CustomConfig customConfig;
	@Autowired
	private ParamRuleAccessibleService paramRuleAccessibleService;
	@Autowired
	private ParamOdRouteAccessibleService paramOdRouteAccessibleService;
	@Autowired
	private ParamVersionRelyService paramVersionRelyService;
	@Autowired
	private ParamVersionTaskService paramVersionTaskService;

	private void init(String versionCode) throws IOException {
		filepath = customConfig.getFilepath() + versionCode;
		this.initAccessibleRule(versionCode);
		this.resetCons();
		this.loadBaseData(versionCode);
	}

	private void loadBaseData(String versionCode) throws IOException {
		File dataFolder = new File(filepath);
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
			loadDataDb.load(versionCode);
		}
		loadDataFile.load(versionCode);
	}

	private void printOut(String versionCode) {
		List<ParamOdRouteAccessible> list = calcEngine.printPath(versionCode);// 9067, 1003,
		final int LIST_SIZE = list.size();
		Stopwatch timer = new Stopwatch();
		timer.start();
		paramOdRouteAccessibleService.saveBatcheParamOdRouteAccessible(list);
		timer.stop();
		logger.info("save odroute {} spend: {} seconds\n", LIST_SIZE, String.format("%.2f", timer.time()));
	}

	@Override
	public int calcPath(String taskJobId) {
//		String versionCode = this.getParamVersionInfoCode(taskJobId);
		ParamVersionTask task = this.getParamVersionTask(taskJobId);
		if (task == null) {
			logger.warn("taskJobId is not exists");
			return 0;
		}
		if (task.getTaskJobStatue() != 0) {
			logger.warn("task is executing");
			return 0;
		}
		String versionCode = task.getVersionCode();
		if (this.checkPath(versionCode) == 0) {
			logger.warn("od route is inserted already");
			return 0;
		}

		logger.info("task start at {}:", Instant.now());
		paramVersionTaskService.updateParamVersionInfoStatueToExecute(task);
		Stopwatch timer = new Stopwatch();
		timer.start();
		try {
			this.init(versionCode);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
		timer.stop();
		logger.info("load data spend: {} seconds\n", String.format("%.2f", timer.time()));

		timer.start();
		this.calc();
		timer.stop();
		logger.info("calc accessible path spend: {} seconds\n", String.format("%.2f", timer.time()));

		timer.start();
		this.printOut(versionCode);
		timer.stop();
		logger.info("print accessible path spend: {} seconds\n", String.format("%.2f", timer.time()));
		logger.info("task end at {}:", Instant.now());
		paramVersionTaskService.updateParamVersionInfoStatueToComplete(task);
		return 1;
	}

	private String getParamVersionInfoCode(String taskJobId) {
		ParamVersionInfo paramVersionInfo = paramVersionInfoService.getParamVersionInfoByTaskJobId(taskJobId);
		if (paramVersionInfo == null) {
			return null;
		} else {
			return paramVersionInfo.getVersionCode();
		}
	}

	private ParamVersionTask getParamVersionTask(String taskJobId) {
		return paramVersionTaskService.getParamVersionTaskByTaskJobId(taskJobId);
	}

	private void initAccessibleRule(String versionCode) {
		ParamRuleAccessible paramRuleAccessible = this.getAccessibleRule(versionCode);
		CalcPathEnum.RULE.setkNum(paramRuleAccessible.getkNum());
		CalcPathEnum.RULE.setTransCoeff(paramRuleAccessible.getTransCoeff().doubleValue());
	}

	private ParamRuleAccessible getAccessibleRule(String versionCode) {
		List<ParamVersionRely> paramVersionRelyList = this.getParamVersionRely(versionCode);
		return paramRuleAccessibleService.findParamRuleAccessible(this.getParamRuleAccessibleVersionCode(paramVersionRelyList));
	}

	private List<ParamVersionRely> getParamVersionRely(String versionCode) {
		return paramVersionRelyService.getParamVersionRelyByVersionCode(versionCode);
	}

	private String getParamRuleAccessibleVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.KG.name());
	}

	private String getVersionCode(List<ParamVersionRely> list, String paramCode) {
		Optional<ParamVersionRely> paramVersionRely = list.stream().filter(rely -> paramCode.equals(rely.getParamCodeDepend())).findFirst();
		return paramVersionRely.get().getVersionCodeDepend();
	}

	private void resetCons() {
		CalcConstant.sectionId = 0;
		CalcConstant.stationCounts = 0;
		CalcConstant.stationDict.clear();
		CalcConstant.lineDict.clear();
		CalcConstant.transferDict.clear();
		CalcConstant.parktimesDict.clear();
		CalcConstant.departIntervalTimesDict.clear();
		CalcConstant.fakeTransferDict.clear();
		CalcConstant.stations = new Station[CalcConstant.MAX_STATION];
		CalcConstant.lines = new Line[CalcConstant.MAX_LINE];
		CalcConstant.stationCodes = new int[CalcConstant.MAX_STATION];
		CalcConstant.stationIndexes = new int[CalcConstant.MAX_STATION];
	}

	private void calc() {
//		calcEngine.start(CalcPathEnum.RULE.getkNum());
		calcEngine.start(5);
	}

	public static void main(String[] args) {
		System.out.println("KNum:" + CalcPathEnum.RULE.getkNum() + " " + CalcPathEnum.RULE.getTransCoeff());
		CalcPathEnum.RULE.setkNum(10);
		CalcPathEnum.RULE.setTransCoeff(1.8);
		System.out.println("KNum:" + CalcPathEnum.RULE.getkNum() + " " + CalcPathEnum.RULE.getTransCoeff());
	}

	@Override
	public int checkPath(String versionCode) {
		int rows = paramOdRouteAccessibleService.findParamOdRouteAccessibleByVersionCode(versionCode);
		if (rows == 1) {
			return 0;
		}
		return 1;
	}
}
