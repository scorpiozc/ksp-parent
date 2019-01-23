package cn.com.bjjdsy.calc.effective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessibleQO;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;
import cn.com.bjjdsy.data.service.ParamOdRouteAccessibleService;

@Service
public class EffectiveCalcEngine {

	@Autowired
	private ParamOdRouteAccessibleService paramOdRouteAccessibleService;

	public List<ParamOdRouteEffective> calc(ParamOdRouteAccessibleQO qo) {
		List<ParamOdRouteEffective> paramOdRouteEffectiveList = this.filterParamOdRouteAccessibleByImpedance(qo);
		// calc effective
		List<ParamOdRouteEffective> list = this.calcPassengerDistributeRatio(paramOdRouteEffectiveList);
		return list;
	}

	private List<ParamOdRouteEffective> filterParamOdRouteAccessibleByImpedance(ParamOdRouteAccessibleQO qo) {
		return paramOdRouteAccessibleService.findParamOdRouteAccessible(qo);
	}

	public List<ParamOdRouteEffective> calcPassengerDistributeRatio(List<ParamOdRouteEffective> list) {
		Map<String, List<ParamOdRouteEffective>> map = new HashMap<>();
		list.forEach(odRoute -> {
			String key = odRoute.getoStationCode() + "-" + odRoute.getdStationCode();
			if (map.get(key) == null) {
				map.put(key, new ArrayList<ParamOdRouteEffective>());
			}
			map.get(key).add(odRoute);
		});
		List<ParamOdRouteEffective> effectiveList = Lists.newArrayList();
		map.forEach((k, v) -> {
			PassengerDistributeRatioCalcUnit.calcRatio(v);
			effectiveList.addAll(v);
		});
		return effectiveList;
	}

}
