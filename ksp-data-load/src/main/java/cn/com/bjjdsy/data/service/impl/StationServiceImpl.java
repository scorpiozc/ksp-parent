package cn.com.bjjdsy.data.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.db.ParamStation;
import cn.com.bjjdsy.data.mapper.ParamStationMapper;
import cn.com.bjjdsy.data.service.StationService;

@Service
public class StationServiceImpl implements StationService {

	private static final Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);
	@Autowired
	private ParamStationMapper paramStationMapper;

	public List<ParamStation> findParamStations(String versionCode) {
		return paramStationMapper.selectByVersionCode(versionCode);
	}

//	public static void main(String[] args) {
//		new ReadStationDb().createParamStationFile("", "01", null);
//		List<ParamStation> list = new ArrayList<>();
//		ParamStation s = new ParamStation();
//		s.setStationCode((short) 103);
//		s.setStationName("苹果园");
//		list.add(s);
//		list.add(s);
//		list.add(s);
//		try (FileOutputStream fos = new FileOutputStream("" + CalcConstant.STATION_BASE_INFO)) {
//			for (ParamStation ps : list) {
//				IOUtils.write(new StationServiceImpl().stationBaseInfoToString(ps), fos);
//			}
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//
//		try (FileOutputStream fos = new FileOutputStream("" + CalcConstant.STATION_BASE_INFO + ".txt")) {
//			for (ParamStation ps : list) {
////				IOUtils.write(new StationServiceImpl().stationBaseInfoToString(ps), fos);
//			}
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
}
