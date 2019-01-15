package cn.com.bjjdsy.data.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;
import cn.com.bjjdsy.data.mapper.ParamOdRouteEffectiveMapper;
import cn.com.bjjdsy.data.service.ParamOdRouteEffectiveService;

@Service
public class ParamOdRouteEffectiveServiceImpl implements ParamOdRouteEffectiveService {

	private static final Logger logger = LoggerFactory.getLogger(ParamOdRouteEffectiveServiceImpl.class);
	@Autowired
	private ParamOdRouteEffectiveMapper paramOdRouteEffectiveMapper;
	@Autowired
	private CustomConfig customConfig;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findParamOdRouteEffectiveByVersionCode(String versionCode) {
		return paramOdRouteEffectiveMapper.selectParamOdRouteEffectiveByVersionCode(versionCode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatchParamOdRouteEffective(String versionCode, List<ParamOdRouteEffective> paramOdRouteEffectives) {
		final int BATCH_SIZE = customConfig.getBatchSize();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"insert into PARAM_OD_ROUTE_EFFECTIVE (VERSION_CODE, O_STATION_CODE,D_STATION_CODE, ROUTE_NO, ROUTE_LINE_CODE,ROUTE_STATION_CODE, ROUTE_STATION_TIME, COST_TIME, IMPEDANCE_TIME,ROUTE_TRANSFER_CODE, ROUTE_TRANSFER_TIME, ID) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?)");
		Stopwatch timer = new Stopwatch();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection();
				// 构造预处理statement
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			conn.setAutoCommit(false);

			// 1万次循环
			int i = 1;
			for (ParamOdRouteEffective odRoute : paramOdRouteEffectives) {
//				timer.start();
				pst.setString(1, versionCode);
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
				pst.setLong(12, odRoute.getId());

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

}
