package cn.com.bjjdsy.calc;

import java.util.List;

import com.google.common.collect.Lists;

import cn.com.bjjdsy.calc.effective.EffectiveCalcEngine;
import cn.com.bjjdsy.calc.effective.PassengerDistributeRatioCalcUnit;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;

public class Tester {

	public static void main(String[] args) {
		// 18.25-1095|20.5-1230,18.25-1095|19-1140,17-1020|20.25-1215,13.75-825|24.5-1470,24-1440|30.5-1830,16.25-975|22-1320
		// Map<String, List<RunMapKey>> keys = CalcConstant.runMapKeyMap;
		ParamOdRouteEffective e1 = new ParamOdRouteEffective();
		e1.setoStationCode(new Short("101"));
		e1.setdStationCode(new Short("201"));
		e1.setImpedanceTime(975);
		ParamOdRouteEffective e2 = new ParamOdRouteEffective();
		e2.setoStationCode(new Short("101"));
		e2.setdStationCode(new Short("201"));
		e2.setImpedanceTime(1320);
		ParamOdRouteEffective e3 = new ParamOdRouteEffective();
		e3.setoStationCode(new Short("101"));
		e3.setdStationCode(new Short("202"));
		e3.setImpedanceTime(1440);
		List<ParamOdRouteEffective> list = Lists.newArrayList(e1, e2, e3);
		new EffectiveCalcEngine().calcPassengerDistributeRatio(list);

//		PassengerDistributeRatioCalcUnit.calcRatio(list);
		list.forEach(odRoute -> {
			System.out.println("p:" + odRoute.getPassFlowRatio());
		});
	}

}
