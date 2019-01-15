package cn.com.bjjdsy.calc.effective;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return paramOdRouteEffectiveList;
	}

	private List<ParamOdRouteEffective> filterParamOdRouteAccessibleByImpedance(ParamOdRouteAccessibleQO qo) {
		return paramOdRouteAccessibleService.findParamOdRouteAccessible(qo);
	}
}
