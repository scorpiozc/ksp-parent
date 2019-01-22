package cn.com.bjjdsy.data.loader.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.path.Line;
import cn.com.bjjdsy.data.entity.path.Station;
import cn.com.bjjdsy.data.file.r.path.ReadDepartFile;
import cn.com.bjjdsy.data.file.r.path.ReadLineFile;
import cn.com.bjjdsy.data.file.r.path.ReadParktimeFile;
import cn.com.bjjdsy.data.file.r.path.ReadSectionFile;
import cn.com.bjjdsy.data.file.r.path.ReadStationFile;
import cn.com.bjjdsy.data.file.r.path.ReadTransferFile;
import cn.com.bjjdsy.data.file.r.path.ReadTransferWalktimeFile;
import cn.com.bjjdsy.data.loader.LoadData;

@Service("loadKspDataFileImpl")
public class LoadKspDataFileImpl implements LoadData {

	private static final Logger logger = LoggerFactory.getLogger(LoadKspDataFileImpl.class);
	private String filepath;
	private String versionCode;
	private String localtion;
	@Autowired
	private ReadDepartFile readDepartFile;
	@Autowired
	private ReadLineFile readLineFile;
	@Autowired
	private ReadParktimeFile readParktimeFile;
	@Autowired
	private ReadSectionFile readSectionFile;
	@Autowired
	private ReadStationFile readStationFile;
	@Autowired
	private ReadTransferFile readTransferFile;
	@Autowired
	private ReadTransferWalktimeFile readTransferWalktimeFile;
	@Autowired
	private CustomConfig customConfig;

	@Override
	public void load(String versionCode) throws IOException {
		this.filepath = customConfig.getFilepath() + versionCode;
		loadParktime();
		loadDepart();
		loadTransfer();
		loadStation();
		loadLine();
		genLine();
		genStation();
//		fakeTransfer();
		loadSection();
		loadTransferWalktime();
	}

	private void loadDepart() throws IOException {
//		AbstractReadDataFile rdf = new ReadDepartFile();
		readDepartFile.read(filepath + CalcConstant.LINE_DEPART_INTERVAL_TIME);
	}

	private void loadLine() throws IOException {
//		AbstractReadDataFile rdf = new ReadLineFile();
		readLineFile.read(filepath + CalcConstant.LINE_BASE_INFO);
	}

	private void loadParktime() throws IOException {
//		AbstractReadDataFile rdf = new ReadParktimeFile();
		readParktimeFile.read(filepath + CalcConstant.STATION_PARKTIME);
	}

	private void loadSection() throws IOException {
//		AbstractReadDataFile rdf = new ReadSectionFile();
		readSectionFile.read(filepath + CalcConstant.SECTION_BASE_INFO);
	}

	private void loadStation() throws IOException {
//		AbstractReadDataFile rdf = new ReadStationFile();
		readStationFile.read(filepath + CalcConstant.STATION_BASE_INFO);
	}

	private void loadTransfer() throws IOException {
//		AbstractReadDataFile rdf = new ReadTransferFile();
		readTransferFile.read(filepath + CalcConstant.TRANSFER_BASE_INFO);
	}

	private void loadTransferWalktime() throws IOException {
//		AbstractReadDataFile rdf = new ReadTransferWalktimeFile();
		readTransferWalktimeFile.read(filepath + CalcConstant.TRANSFER_LINE_WALKTIME_INFO);
	}

	private void genLine() {
		for (Integer code : CalcConstant.lineDict.keySet()) {
			CalcConstant.lines[code] = new Line(code, CalcConstant.lineDict.get(code));
		}
	}

	private void genStation() {
		for (int i = 0; i < CalcConstant.MAX_STATION; i++) {
			CalcConstant.stationIndexes[i] = -1;
		}
		for (Integer code : CalcConstant.stationDict.keySet()) {
			CalcConstant.stationIndexes[code] = CalcConstant.stationCounts;
			CalcConstant.stationCodes[CalcConstant.stationCounts++] = code;
			CalcConstant.stations[code] = new Station(code, null, CalcConstant.stationDict.get(code));

		}
	}

	private void fakeTransfer() {
		try (InputStream is = new FileInputStream(this.getClass().getResource("/data/fake_transfer.json").getFile())) {
			CalcConstant.fakeTransferDict = JSON.parseObject(is, Map.class);
//			CalcConstant.fakeTransferDict.forEach((k, v) -> {
//				System.out.printf("fake:%s %s\n", k, v);
//			});
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void mainTest(String[] args) throws IOException {
		new LoadKspDataFileImpl().load("01");
	}
}
