package cn.com.bjjdsy.calc.entity;

import java.math.BigDecimal;
import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;

public class PassengerDistributeRatio {

	private double sumS = 0;

	public void calcRatio(List<ParamOdRouteEffective> list) {
		double tmin = list.get(0).getImpedanceTime();
		this.calcSumS(list);
		list.forEach(odRoute -> {
			double x = this.getX(tmin, odRoute.getImpedanceTime());
			System.out.println("x:" + x);
			double s = this.getS(x);
			System.out.println("s:" + s);
			double sumS = this.getSumS();
			System.out.println("sumS:" + sumS);
			double p = this.getP(s, sumS);
			System.out.println("p:" + p);
		});
	}

	private double getX(double tmin, double t) {
		double x = 0;
		double m = 0.6;
		double u = 10 * 60;
		double range = t * m;

		if (range > u) {
			range = u;
		}

		x = (t - tmin) / range;
		BigDecimal bd = new BigDecimal(x);
		return bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private double getS(double x) {
		double e = 2.718;
		double a = 0;
		double sigma = 0.25;
		System.out.println("fz:" + Math.pow((x - a), 2) + " fm:" + 2 * Math.pow(sigma, 2));
		double pfs = -(Math.pow((x - a), 2) / (2 * Math.pow(sigma, 2)));
		System.out.println("pfs:" + pfs);
		BigDecimal bd = new BigDecimal(Math.pow(e, pfs));
		return bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private double getSumS() {
		return this.sumS;
	}

	private void calcSumS(List<ParamOdRouteEffective> list) {
		double tmin = list.get(0).getImpedanceTime();
		list.forEach(odRoute -> {
			double x = this.getX(tmin, odRoute.getImpedanceTime());
			double s = this.getS(x);
			this.add(s);
		});
	}

	private double getP(double s, double sumS) {
		BigDecimal bd = new BigDecimal(s / sumS);
		return bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private void add(double sumS) {
		this.sumS += sumS;
	}
}
