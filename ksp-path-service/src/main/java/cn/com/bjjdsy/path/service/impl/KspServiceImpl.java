package cn.com.bjjdsy.path.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.calc.CalcEngine;
import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.common.constant.CalcPathEnum;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.entity.db.ParamRuleAccessible;
import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;
import cn.com.bjjdsy.data.loader.LoadData;
import cn.com.bjjdsy.data.service.ParamOdRouteAccessibleService;
import cn.com.bjjdsy.data.service.ParamRuleAccessibleService;
import cn.com.bjjdsy.data.service.ParamVersionInfoService;
import cn.com.bjjdsy.path.service.KspService;

@Service
public class KspServiceImpl implements KspService {

	private static final Logger logger = LoggerFactory.getLogger(KspServiceImpl.class);
	private String filepath;
	@Resource(name = "loadKspDataFileImpl")
	private LoadData loadDataFile;
	@Resource(name = "loadKspDataDbImpl")
	private LoadData loadDataDb;
	@Autowired
	private ParamVersionInfoService paramVersionInfoService;
	@Autowired
	private CalcEngine calcEngine;
	@Autowired
	private CustomConfig customConfig;
	@Autowired
	private ParamRuleAccessibleService paramRuleAccessibleService;
	@Autowired
	private ParamOdRouteAccessibleService paramOdRouteAccessibleService;

	private void initParam(String versionCode) {
		filepath = customConfig.getFilepath() + versionCode;
		ParamRuleAccessible paramRuleAccessible = paramRuleAccessibleService.findParamRuleAccessible(versionCode);
		CalcPathEnum.RULE.setkNum(paramRuleAccessible.getkNum());
		CalcPathEnum.RULE.setTransCoeff(paramRuleAccessible.getTransCoeff().doubleValue());
	}

	private void initCons() {
		CalcConstant.sectionId = 0;
		CalcConstant.stationCounts = 0;
		CalcConstant.stationDict.clear();
		CalcConstant.lineDict.clear();
		CalcConstant.transferDict.clear();
		CalcConstant.parktimesDict.clear();
		CalcConstant.departIntervalTimesDict.clear();
		CalcConstant.fakeTransferDict.clear();
//		CalcConstant.stations = new Station[MAX_STATION];
//		CalcConstant.lines = new Line[MAX_LINE];
//		CalcConstant.stationCodes = new int[MAX_STATION];
//		CalcConstant.stationIndexes = new int[MAX_STATION];
	}

	private void loadBaseData(String versionCode) throws IOException {
		this.initCons();
		File dataFolder = new File(filepath);
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
			loadDataDb.load(versionCode);
		}
		loadDataFile.load(versionCode);
	}

	private void calc() {
		calcEngine.start(CalcPathEnum.RULE.getkNum());
	}

	private String printOut(String versionCode) {
		int rows = paramOdRouteAccessibleService.findParamOdRouteAccessibleByVersionCode(versionCode);
		if (rows == 1) {
			return "0";
		} else {
			List<ParamOdRouteAccessible> list = calcEngine.printPath(versionCode);// 9067, 1003,
			final int LIST_SIZE = list.size();
			Stopwatch timer = new Stopwatch();
			timer.start();
			paramOdRouteAccessibleService.saveBatcheParamOdRouteAccessible(list);
			timer.stop();
			logger.info("save odroute {} spend: {} seconds\n", LIST_SIZE, String.format("%.2f", timer.time()));
			return "1";
		}
//		list.forEach(odRoute -> {
//			 paramOdRouteAccessibleService.saveParamOdRouteAccessible(odRoute);
//		});

//		list.forEach(p -> {
//			logger.info("{},{},{},{},{},{},{},{}", p.getFromStation(), p.getToStation(), p.getSn(), p.getPathLine(),
//					p.getPathStation(), p.getTime(), p.getInpedance(), p.getPathTransfer());
//		});
	}

	@Override
	public String calcPath(String taskJobId) {
		ParamVersionInfo paramVersionInfo = paramVersionInfoService.getParamVersionInfoByTaskJobId(taskJobId);
		if (paramVersionInfo == null) {
			return "taskJobId is not exists";
		}
		String versionCode = paramVersionInfo.getVersionCode();

		Stopwatch timer = new Stopwatch();
		timer.start();
		try {
			this.initParam(versionCode);
			this.loadBaseData(versionCode);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return "0";
		}
		timer.stop();
		logger.info("load data spend: {} seconds\n", String.format("%.2f", timer.time()));

		timer.start();
		this.calc();
		timer.stop();
		logger.info("calc path spend: {} seconds\n", String.format("%.2f", timer.time()));

		timer.start();
		if ("0".equals(this.printOut(versionCode))) {
			return "od route is inserted already ";
		}
		timer.stop();
		logger.info("print path spend: {} seconds\n", String.format("%.2f", timer.time()));
		return "ok";
	}

	@Override
	public String calcTimeForward(String taskJobId) {
		return null;
	}

	public static void main(String[] args) {
		System.out.println("KNum:" + CalcPathEnum.RULE.getkNum() + " " + CalcPathEnum.RULE.getTransCoeff());
		CalcPathEnum.RULE.setkNum(10);
		CalcPathEnum.RULE.setTransCoeff(1.8);
		System.out.println("KNum:" + CalcPathEnum.RULE.getkNum() + " " + CalcPathEnum.RULE.getTransCoeff());
	}
}
