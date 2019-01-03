package cn.com.bjjdsy.data.loader.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamLine;
import cn.com.bjjdsy.data.entity.db.ParamSection;
import cn.com.bjjdsy.data.entity.db.ParamStation;
import cn.com.bjjdsy.data.entity.db.ParamSwitch;
import cn.com.bjjdsy.data.file.w.path.WriteDepartFile;
import cn.com.bjjdsy.data.file.w.path.WriteLineFile;
import cn.com.bjjdsy.data.file.w.path.WriteParktimeFile;
import cn.com.bjjdsy.data.file.w.path.WriteSectionFile;
import cn.com.bjjdsy.data.file.w.path.WriteStationFile;
import cn.com.bjjdsy.data.file.w.path.WriteTransferFile;
import cn.com.bjjdsy.data.file.w.path.WriteTransferWalktimeFile;
import cn.com.bjjdsy.data.loader.LoadData;
import cn.com.bjjdsy.data.service.LineService;
import cn.com.bjjdsy.data.service.SectionService;
import cn.com.bjjdsy.data.service.StationService;
import cn.com.bjjdsy.data.service.SwitchService;

@Service("loadKspDataDbImpl")
public class LoadKspDataDbImpl implements LoadData {

	@Autowired
	private LineService lineService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private StationService stationService;
	@Autowired
	private SwitchService switchService;
	@Autowired
	private WriteLineFile writeLineFile;
	@Autowired
	private WriteDepartFile writeDepartFile;
	@Autowired
	private WriteSectionFile writeSectionFile;
	@Autowired
	private WriteParktimeFile writeParktimeFile;
	@Autowired
	private WriteStationFile writeStationFile;
	@Autowired
	private WriteTransferFile writeTransferFile;
	@Autowired
	private WriteTransferWalktimeFile writeTransferWalktimeFile;

	@Override
	public void load(String filepath, String versionCode) {

		// read
		List<ParamLine> lines = lineService.loadParamLine(filepath, versionCode);
		List<ParamSection> sections = sectionService.loadParamSection(filepath, versionCode);
		List<ParamStation> stations = stationService.loadParamStation(filepath, versionCode);
		List<ParamSwitch> switchs = switchService.loadParamSwitch(filepath, versionCode);
		// write
		writeLineFile.createLineBaseInfoFile(filepath, versionCode, lines);
		writeDepartFile.createLineDepartIntervalTimeFile(filepath, versionCode, lines);
		writeTransferFile.createTransferWalktimeFile(filepath, versionCode, switchs);
		writeTransferWalktimeFile.createTransferWalktimeFile(filepath, versionCode, switchs);
		writeSectionFile.createSectionBaseInfoFile(filepath, versionCode, sections);
		writeStationFile.createStationBaseInfoFile(filepath, versionCode, stations);
		writeParktimeFile.createStationParktimeFile(filepath, versionCode, sections);
	}

	private String getStationVersionCode() {
		return "";
	}
}
