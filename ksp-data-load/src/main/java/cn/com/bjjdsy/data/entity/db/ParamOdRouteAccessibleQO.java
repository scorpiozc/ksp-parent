package cn.com.bjjdsy.data.entity.db;

public class ParamOdRouteAccessibleQO {

	private String odRouteAccessibleVersionCode;

	private String ruleEffectiveVersionCode;

	public String getOdRouteAccessibleVersionCode() {
		return odRouteAccessibleVersionCode;
	}

	public void setOdRouteAccessibleVersionCode(String odRouteAccessibleVersionCode) {
		this.odRouteAccessibleVersionCode = odRouteAccessibleVersionCode;
	}

	public String getRuleEffectiveVersionCode() {
		return ruleEffectiveVersionCode;
	}

	public void setRuleEffectiveVersionCode(String ruleEffectiveVersionCode) {
		this.ruleEffectiveVersionCode = ruleEffectiveVersionCode;
	}

	public ParamOdRouteAccessibleQO(String odRouteAccessibleVersionCode, String ruleEffectiveVersionCode) {
		this.odRouteAccessibleVersionCode = odRouteAccessibleVersionCode;
		this.ruleEffectiveVersionCode = ruleEffectiveVersionCode;
	}

}
