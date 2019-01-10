package cn.com.bjjdsy.data.loader.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.constant.ParamDictEnum;
import cn.com.bjjdsy.data.entity.db.ParamLine;
import cn.com.bjjdsy.data.entity.db.ParamSection;
import cn.com.bjjdsy.data.entity.db.ParamStation;
import cn.com.bjjdsy.data.entity.db.ParamSwitch;
import cn.com.bjjdsy.data.entity.db.ParamVersionRely;
import cn.com.bjjdsy.data.file.w.path.WriteDepartFile;
import cn.com.bjjdsy.data.file.w.path.WriteLineFile;
import cn.com.bjjdsy.data.file.w.path.WriteParktimeFile;
import cn.com.bjjdsy.data.file.w.path.WriteSectionFile;
import cn.com.bjjdsy.data.file.w.path.WriteStationFile;
import cn.com.bjjdsy.data.file.w.path.WriteTransferFile;
import cn.com.bjjdsy.data.file.w.path.WriteTransferWalktimeFile;
import cn.com.bjjdsy.data.loader.LoadData;
import cn.com.bjjdsy.data.service.LineService;
import cn.com.bjjdsy.data.service.ParamVersionRelyService;
import cn.com.bjjdsy.data.service.SectionService;
import cn.com.bjjdsy.data.service.StationService;
import cn.com.bjjdsy.data.service.SwitchService;

@Service("loadKspDataDbImpl")
public class LoadKspDataDbImpl implements LoadData {

	private static final Logger logger = LoggerFactory.getLogger(LoadKspDataDbImpl.class);
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
	@Autowired
	private ParamVersionRelyService paramVersionRelyService;
	@Autowired
	private CustomConfig customConfig;

	@Override
	public void load(String versionCode) {
		String filepath = customConfig.getFilepath() + versionCode;
		List<ParamVersionRely> paramVersionRelyList = this.getParamVersionRely(versionCode);

		// read
		List<ParamLine> lines = lineService.findParamLines(this.getParamLineVersionCode(paramVersionRelyList));
		List<ParamSection> sections = sectionService
				.findParamSections(this.getParamSectionVersionCode(paramVersionRelyList));
		List<ParamStation> stations = stationService
				.findParamStations(this.getParamStationVersionCode(paramVersionRelyList));
		List<ParamSwitch> switchs = switchService
				.findParamSwitchs(this.getParamSwitchVersionCode(paramVersionRelyList));
		// write
		writeLineFile.createLineBaseInfoFile(filepath, versionCode, lines);
		writeDepartFile.createLineDepartIntervalTimeFile(filepath, versionCode, lines);
		writeTransferFile.createTransferBaseInfoFile(filepath, versionCode, switchs);
		writeTransferWalktimeFile.createTransferWalktimeFile(filepath, versionCode, switchs);
		writeSectionFile.createSectionBaseInfoFile(filepath, versionCode, sections);
		writeStationFile.createStationBaseInfoFile(filepath, versionCode, stations);
		writeParktimeFile.createStationParktimeFile(filepath, versionCode, sections);
	}

	private List<ParamVersionRely> getParamVersionRely(String versionCode) {
		return paramVersionRelyService.getParamVersionRelyByVersionCode(versionCode);
	}

	private String getParamLineVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.XL.name());
	}

	private String getParamSectionVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.QJ.name());
	}

	private String getParamStationVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.CZ.name());
	}

	private String getParamSwitchVersionCode(List<ParamVersionRely> list) {
		return this.getVersionCode(list, ParamDictEnum.HC.name());
	}

	private String getVersionCode(List<ParamVersionRely> list, String paramCode) {
		Optional<ParamVersionRely> paramVersionRely = list.stream()
				.filter(rely -> paramCode.equals(rely.getParamCodeDepend())).findFirst();
		return paramVersionRely.get().getVersionCodeDepend();
	}
}
