package cn.com.bjjdsy.data.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessibleQO;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;
import cn.com.bjjdsy.data.mapper.ParamOdRouteAccessibleMapper;
import cn.com.bjjdsy.data.service.ParamOdRouteAccessibleService;

@Service
public class ParamOdRouteAccessibleServiceImpl implements ParamOdRouteAccessibleService {

	private static final Logger logger = LoggerFactory.getLogger(ParamOdRouteAccessibleServiceImpl.class);
	@Autowired
	private ParamOdRouteAccessibleMapper paramOdRouteAccessibleMapper;
	@Autowired
	private CustomConfig customConfig;
	@Autowired
	private JdbcTemplate jdbcTemplate;

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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatcheParamOdRouteAccessible(List<ParamOdRouteAccessible> paramOdRouteAccessibles) {
		final int BATCH_SIZE = customConfig.getBatchSize();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"insert into PARAM_OD_ROUTE_ACCESSIBLE (ID, VERSION_CODE, O_STATION_CODE,D_STATION_CODE, ROUTE_NO, ROUTE_LINE_CODE,ROUTE_STATION_CODE, ROUTE_STATION_TIME, COST_TIME, IMPEDANCE_TIME,ROUTE_TRANSFER_CODE, ROUTE_TRANSFER_TIME) ");
		sql.append("values (SEQ_OD_ROUTE_ACCES.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)");
		Stopwatch timer = new Stopwatch();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection();
				// 构造预处理statement
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			conn.setAutoCommit(false);

			// 1万次循环
			int i = 1;
			for (ParamOdRouteAccessible odRoute : paramOdRouteAccessibles) {
//				timer.start();
				pst.setString(1, odRoute.getVersionCode());
				pst.setShort(2, odRoute.getoStationCode());
				pst.setShort(3, odRoute.getdStationCode());
				pst.setShort(4, odRoute.getRouteNo());
				pst.setString(5, odRoute.getRouteLineCode());
				pst.setString(6, odRoute.getRouteStationCode());
				pst.setString(7, odRoute.getRouteStationTime());
//				pst.setLong(8, odRoute.getRouteDistance());
//				pst.setLong(9, odRoute.getRouteStationNo());
				pst.setInt(8, odRoute.getCostTime());
				pst.setInt(9, odRoute.getImpedanceTime());
				pst.setString(10, odRoute.getRouteTransferCode());
				pst.setString(11, odRoute.getRouteTransferTime());
//				pst.setShort(14, odRoute.getRouteTransferFrequency());

				pst.addBatch();
				// 每5000次提交一次
				if (i % BATCH_SIZE == 0) {// 可以设置不同的大小；如50，100，500，1000等等
					pst.executeBatch();
					conn.commit();
					pst.clearBatch();
				}
				i++;
//				timer.stop();
//				logger.info("executeBatch method odroute {} spend: {} seconds\n", BATCH_SIZE,
//						String.format("%f", timer.time()));
			}
			pst.executeBatch();
			conn.commit();
			pst.clearBatch();

			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int findParamOdRouteAccessibleByVersionCode(String versionCode) {
		return paramOdRouteAccessibleMapper.selectParamOdRouteAccessibleByVersionCode(versionCode);
	}

	@Override
	public List<ParamOdRouteEffective> findParamOdRouteAccessible(ParamOdRouteAccessibleQO qo) {
		return paramOdRouteAccessibleMapper.selectParamOdRouteAccessible(qo);
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
