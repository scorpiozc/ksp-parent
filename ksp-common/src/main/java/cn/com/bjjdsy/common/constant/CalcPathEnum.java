package cn.com.bjjdsy.common.constant;

public enum CalcPathEnum {
	RULE(5, 1.5, 0.6, 10);

	private CalcPathEnum(int kNum, double transCoeff, double m, double u) {
		this.kNum = kNum;
		this.transCoeff = transCoeff;
		this.m = m;
		this.u = u;
	}

	private int kNum;
	private double transCoeff;
	private double m;
	private double u;

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

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

}
