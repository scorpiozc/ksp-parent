package cn.com.bjjdsy.data.loader.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;
import cn.com.bjjdsy.data.file.r.time.ReadAccseDateAttributeFile;
import cn.com.bjjdsy.data.file.r.time.ReadAccseTimeAttributeFile;
import cn.com.bjjdsy.data.file.r.time.ReadAccseWalkTimeFile;
import cn.com.bjjdsy.data.file.r.time.ReadKPathFile;
import cn.com.bjjdsy.data.file.r.time.ReadParamSectionTccFile;
import cn.com.bjjdsy.data.file.r.time.ReadRunMapFile;
import cn.com.bjjdsy.data.file.r.time.ReadSectionFile;
import cn.com.bjjdsy.data.loader.LoadData;

public class LoadTimeDataFileImpl implements LoadData {

	static final Logger logger = LoggerFactory.getLogger(LoadTimeDataFileImpl.class);
	private String filepath;
	private String versionCode;

	public static void main(String[] args) {
		new LoadTimeDataFileImpl().load("", "01");
	}

	public void load(String filepath, String versionCode) {
		this.versionCode = versionCode;
		this.filepath = filepath;
		Stopwatch timer = new Stopwatch();
		timer.start();
		this.loadDateTypeMap();
		this.loadAccseDateAttribute();
		this.loadAccseTimeAttribute();
		this.loadAccseWalkTime();
		this.loadParamSectionTcc();
		this.loadKPath();
		this.loadRunMap();
		this.loadSection();
//		
		timer.stop();
		logger.info("load data spend: {} seconds\n", String.format("%f", timer.time()));

	}

	private void loadDateTypeMap() {
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				CalcConstant.dateTypeMap.put(i + ":" + j, k);
				k++;
			}
		}
//		CalcConstant.dateTypeMap.forEach((key, value) -> {
//			System.out.println(key + ":" + value);
//		});
	}

	private void loadAccseDateAttribute() {
		AbstractReadDataFile rdf = new ReadAccseDateAttributeFile();
		rdf.read(CalcConstant.ACCSE_DATE_ATTRIBUTE);
	}

	private void loadAccseTimeAttribute() {
		AbstractReadDataFile rdf = new ReadAccseTimeAttributeFile();
		rdf.read(CalcConstant.ACCSE_TIME_ATTRIBUTE);
	}

	private void loadAccseWalkTime() {
		AbstractReadDataFile rdf = new ReadAccseWalkTimeFile();
		rdf.read(CalcConstant.ACCSE_WALK_TIME);
	}

	private void loadKPath() {
		AbstractReadDataFile rdf = new ReadKPathFile();
		rdf.read(CalcConstant.K_PATH);
	}

	private void loadRunMap() {
		AbstractReadDataFile rdf = new ReadRunMapFile();
		rdf.read(CalcConstant.PLAN_RUN_MAP);
//		CalcConstant.runMapMap.forEach((key, value) -> {
//			value.forEach((k, v) -> {
//				System.out.println(key + ":" + k + ":" + v.getDepTime());
//			});
//		});
	}

	private void loadParamSectionTcc() {
		AbstractReadDataFile rdf = new ReadParamSectionTccFile();
		rdf.read(CalcConstant.PARAM_SECTION_TCC);
	}

	private void loadSection() {
		AbstractReadDataFile rdf = new ReadSectionFile();
		rdf.read(CalcConstant.SECTION_BASE_INFO);
//		CalcConstant.sectionDict.forEach((k, v) -> {
//			logger.info("{} {}", k, v);
//		});
	}

}
