package cn.com.bjjdsy.common.constant;

public enum ParamEnum {

	XL("01", 1), CZ("02", 2), QJ("03", 3), HC("04", 4), SD("05", 5), ZX("06", 6);

	private ParamEnum(String paramCode, int index) {
		this.paramCode = paramCode;
		this.index = index;
	}

	private String paramCode;

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private int index;

	public static String getParamCode(int index) {
		for (ParamEnum p : ParamEnum.values()) {
			if (p.getIndex() == index) {
				return p.getParamCode();
			}
		}
		return null;
	}
}
