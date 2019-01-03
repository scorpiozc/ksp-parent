package cn.com.bjjdsy.path.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.calc.CalcEngine;
import cn.com.bjjdsy.calc.entity.ReachPath;
import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;
import cn.com.bjjdsy.data.loader.LoadData;
import cn.com.bjjdsy.data.service.ParamVersionInfoService;
import cn.com.bjjdsy.path.service.KspService;

@Service
public class KspServiceImpl implements KspService {

	private static final Logger logger = LoggerFactory.getLogger(KspServiceImpl.class);
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

	private void loadBaseData(int taskJobId) {
//		try {
//			File filepath = new File(ResourceUtils.getConfigProperties().getString("filepath") + versionCode);
		ParamVersionInfo paramVersionInfo = paramVersionInfoService.getParamVersionInfoByTaskJobId(taskJobId);
		String filepath = customConfig.getFilepath() + paramVersionInfo.getVersionCode();
		File dataFolder = new File(filepath);
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
			loadDataDb.load(filepath, paramVersionInfo.getVersionCode());
		}
		loadDataFile.load(filepath, paramVersionInfo.getVersionCode());
//		} catch (ConfigurationException e) {
//			e.printStackTrace();
//		}
	}

	private void calc() {
		calcEngine.start(5);
	}

	private void printOut() {
		List<ReachPath> list = calcEngine.printPath(9067, 1003);

//		list.forEach(p -> {
//			logger.info("{},{},{},{},{},{},{},{}", p.getFromStation(), p.getToStation(), p.getSn(), p.getPathLine(),
//					p.getPathStation(), p.getTime(), p.getInpedance(), p.getPathTransfer());
//		});
	}

	@Override
	public void calcPath(int taskJobId) {
		Stopwatch timer = new Stopwatch();

		timer.start();
		this.loadBaseData(taskJobId);
		timer.stop();
		logger.info("load data spend: {} seconds\n", String.format("%f", timer.time()));

		timer.start();
		this.calc();
		timer.stop();
		logger.info("calc path spend: {} seconds\n", String.format("%f", timer.time()));

		timer.start();
		this.printOut();
		timer.stop();
		logger.info("print path spend: {} seconds\n", String.format("%f", timer.time()));
	}

	@Override
	public void calcTimeForward(int taskJobId) {
		// TODO Auto-generated method stub

	}
}
