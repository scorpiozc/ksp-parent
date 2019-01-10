package cn.com.bjjdsy.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.mapper.ParamOdRouteAccessibleMapper;
import cn.com.bjjdsy.data.service.ParamOdRouteAccessibleService;

@Service
public class ParamOdRouteAccessibleServiceImpl implements ParamOdRouteAccessibleService {

	private static final Logger logger = LoggerFactory.getLogger(ParamOdRouteAccessibleServiceImpl.class);
	@Autowired
	private ParamOdRouteAccessibleMapper paramOdRouteAccessibleMapper;
	@Autowired
	private CustomConfig customConfig;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveParamOdRouteAccessible(ParamOdRouteAccessible paramOdRouteAccessible) {
		paramOdRouteAccessibleMapper.insert(paramOdRouteAccessible);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveParamOdRouteAccessible(List<ParamOdRouteAccessible> paramOdRouteAccessibles) {
		final int BATCH_SIZE = customConfig.getBatchSize();
		Stopwatch timer = new Stopwatch();
		List<ParamOdRouteAccessible> records = new ArrayList<>();
		while (paramOdRouteAccessibles.size() > BATCH_SIZE) {
			records = paramOdRouteAccessibles.subList(0, BATCH_SIZE);
			timer.start();
			paramOdRouteAccessibleMapper.insertBatch(records);
			timer.stop();
			paramOdRouteAccessibles.removeAll(records);
			logger.info("insert batch odroute {} spend: {} seconds\n", BATCH_SIZE, String.format("%.2f", timer.time()));
		}
		paramOdRouteAccessibleMapper.insertBatch(paramOdRouteAccessibles);
	}

//	public static void main(String[] args) {
//		List<String> list = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g");
//		List<String> subList = list.subList(0, 2);
//		System.out.println(subList.toString());
//		list.removeAll(subList);
//		System.out.println(list.toString());
//		subList = list.subList(0, 2);
//		System.out.println(subList.toString());
//		list.removeAll(subList);
//		System.out.println(list.toString());
//
//	}

}
