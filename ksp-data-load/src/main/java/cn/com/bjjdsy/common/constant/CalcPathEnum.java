package cn.com.bjjdsy.common.constant;

public enum CalcPathEnum {
	RULE(5, 1.5);

	private CalcPathEnum(int kNum, double transCoeff) {
		this.kNum = kNum;
		this.transCoeff = transCoeff;
	}

	private int kNum;
	private double transCoeff;

	public int getkNum() {
		return kNum;
	}

	public void setkNum(int kNum) {
		this.kNum = kNum;
	}

	public double getTransCoeff() {
		return transCoeff;
	}

	public void setTransCoeff(double transCoeff) {
		this.transCoeff = transCoeff;
	}

}
