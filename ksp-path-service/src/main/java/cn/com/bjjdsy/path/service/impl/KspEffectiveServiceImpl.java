package cn.com.bjjdsy.path.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.calc.effective.EffectiveCalcEngine;
import cn.com.bjjdsy.common.constant.CalcPathEnum;
import cn.com.bjjdsy.common.constant.ParamDictEnum;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessibleQO;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;
import cn.com.bjjdsy.data.entity.db.ParamRuleEffective;
import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;
import cn.com.bjjdsy.data.entity.db.ParamVersionRely;
import cn.com.bjjdsy.data.entity.db.ParamVersionTask;
import cn.com.bjjdsy.data.service.ParamOdRouteEffectiveService;
import cn.com.bjjdsy.data.service.ParamRuleEffectiveService;
import cn.com.bjjdsy.data.service.ParamVersionInfoService;
import cn.com.bjjdsy.data.service.ParamVersionRelyService;
import cn.com.bjjdsy.data.service.ParamVersionTaskService;
import cn.com.bjjdsy.path.service.KspService;

@Service("kspEffectiveServiceImpl")
public class KspEffectiveServiceImpl implements KspService {

	private static final Logger logger = LoggerFactory.getLogger(KspEffectiveServiceImpl.class);
	@Autowired
	private ParamVersionInfoService paramVersionInfoService;
	@Autowired
	private ParamVersionRelyService paramVersionRelyService;
	@Autowired
	private EffectiveCalcEngine effectiveCalcEngine;
	@Autowired
	private ParamOdRouteEffectiveService paramOdRouteEffectiveService;
	@Autowired
	private ParamVersionTaskService paramVersionTaskService;
	@Autowired
	private ParamRuleEffectiveService paramRuleEffectiveService;

	@Override
	public int calcPath(String taskJobId) {
//		ParamVersionInfo paramVersionInfo = this.getParamVersionInfo(taskJobId);
		ParamVersionTask task = this.getParamVersionTask(taskJobId);
		if (task == null) {
			logger.warn("taskJobId is not exists");
			return 0;
		}
		if (task.getTaskJobStatue() != 0) {
			logger.warn("task statue is {}:[1]executing[2]complete", task.getTaskJobStatue());
			return 0;
		}
		String versionCode = task.getVersionCode();
		if (this.checkPath(versionCode) == 0) {
			logger.warn("od route is inserted already");
			return 0;
		}

		logger.info("task start at {}:", LocalDateTime.now());
		paramVersionTaskService.updateParamVersionInfoStatueToExecute(task);
		this.init(versionCode);
		Stopwatch timer = new Stopwatch();
		timer.start();
		ParamOdRouteAccessibleQO paramOdRouteAccessibleQO = this.getParamOdRouteAccessibleQO(versionCode);
		List<ParamOdRouteEffective> paramOdRouteEffectiveList = effectiveCalcEngine.calc(paramOdRouteAccessibleQO);
		timer.stop();
		logger.info("calc effective path spend: {} seconds\n", String.format("%.2f", timer.time()));

		timer.start();
		this.printOut(versionCode, paramOdRouteEffectiveList);
		timer.stop();
		logger.info("print effective path spend: {} seconds\n", String.format("%.2f", timer.time()));
		logger.info("task end at {}:", LocalDateTime.now());
		paramVersionTaskService.updateParamVersionInfoStatueToComplete(task);
		return 1;
	}

	private void init(String versionCode) {
		this.initEffectiveRule(versionCode);
	}

	private void initEffectiveRule(String versionCode) {
		ParamRuleEffective paramRuleEffective = this.getEffectiveRule(versionCode);
		CalcPathEnum.RULE.setM(paramRuleEffective.getImpePropLimit().doubleValue() - 1);
		CalcPathEnum.RULE.setU(paramRuleEffective.getImpeValueLimit());
	}

	private ParamVersionInfo getParamVersionInfo(String taskJobId) {
		return paramVersionInfoService.getParamVersionInfoByTaskJobId(taskJobId);
	}

	private ParamRuleEffective getEffectiveRule(String versionCode) {
		List<ParamVersionRely> paramVersionRelyList = this.getParamVersionRely(versionCode);
		return paramRuleEffectiveService.getParamRuleEffective(this.getParamRuleEffectiveVersionCode(paramVersionRelyList));
	}

	private ParamVersionTask getParamVersionTask(String taskJobId) {
		return paramVersionTaskService.getParamVersionTaskByTaskJobId(taskJobId);
	}

	private ParamOdRouteAccessibleQO getParamOdRouteAccessibleQO(String versionCode) {
		List<ParamVersionRely> paramVersionRelyList = this.getParamVersionRely(versionCode);
		return new ParamOdRouteAccessibleQO(this.getParamOdRouteAccessibleVersionCode(paramVersionRelyList), this.getParamRuleEffectiveVersionCode(paramVersionRelyList));
	}

	private List<ParamVersionRely> getParamVersionRely(String versionCode) {
		return paramVersionRelyService.getParamVersionRelyByVersionCode(versionCode);
	}

	private String getParamOdRouteAccessibleVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.KL.name());
	}

	private String getParamRuleEffectiveVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.YG.name());
	}

	private String getVersionCode(List<ParamVersionRely> list, String paramCode) {
		Optional<ParamVersionRely> paramVersionRely = list.stream().filter(rely -> paramCode.equals(rely.getParamCodeDepend())).findFirst();
		return paramVersionRely.get().getVersionCodeDepend();
	}

	private void printOut(String versionCode, List<ParamOdRouteEffective> paramOdRouteEffectiveList) {
		final int LIST_SIZE = paramOdRouteEffectiveList.size();
		Stopwatch timer = new Stopwatch();
		timer.start();
		paramOdRouteEffectiveService.saveBatchParamOdRouteEffective(versionCode, paramOdRouteEffectiveList);
		timer.stop();
		logger.info("save odroute {} spend: {} seconds\n", LIST_SIZE, String.format("%.2f", timer.time()));
	}

	@Override
	public int checkPath(String versionCode) {
		int rows = paramOdRouteEffectiveService.findParamOdRouteEffectiveByVersionCode(versionCode);
		if (rows == 1) {
			return 0;
		}
		return 1;
	}
}
