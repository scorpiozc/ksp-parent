package cn.com.bjjdsy.calc.effective;

import java.math.BigDecimal;
import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;

public class PassengerDistributeRatioCalcUnit {

	private static double sumS = 0;

	public static void calcRatio(List<ParamOdRouteEffective> list) {
		if (list.size() == 1) {
			list.get(0).setPassFlowRatio(new BigDecimal(1.0));
		} else {
			double tmin = list.get(0).getImpedanceTime();
			calcSumS(list);
			list.forEach(odRoute -> {
				double x = getX(tmin, odRoute.getImpedanceTime());
//				System.out.println("x:" + x);
				double s = getS(x);
//				System.out.println("s:" + s);
				double sumS = getSumS();
//				System.out.println("sumS:" + sumS);
				BigDecimal p = getP(s, sumS);
//				System.out.println("p:" + p);
				odRoute.setPassFlowRatio(p);
			});
		}
	}

	private static double getX(double tmin, double t) {
		double x = 0;
		double m = 0.6;
		double u = 10 * 60;
		double range = t * m;

		if (range > u) {
			range = u;
		}

		x = (t - tmin) / range;
		BigDecimal bd = new BigDecimal(x);
		return bd.doubleValue();
	}

	private static double getS(double x) {
		double e = 2.718;
		double a = 0;
		double sigma = 0.25;

//		double fz = new BigDecimal(Math.pow((x - a), 2)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//		double fm = new BigDecimal(2 * Math.pow(sigma, 2)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		double fz = new BigDecimal(Math.pow((x - a), 2)).doubleValue();
		double fm = new BigDecimal(2 * Math.pow(sigma, 2)).doubleValue();
//		System.out.println("fz:" + fz + " fm:" + fm);
		// double pfs = -(Math.pow((x - a), 2) / (2 * Math.pow(sigma, 2)));
		double pfs = -(fz / fm);
//		System.out.println("pfs:" + pfs);
		BigDecimal bd = new BigDecimal(Math.pow(e, pfs));
//		return bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		return Math.pow(e, pfs);
	}

	private static double getSumS() {
		return sumS;
	}

	private static void calcSumS(List<ParamOdRouteEffective> list) {
		double tmin = list.get(0).getImpedanceTime();
		sumS = 0;
		list.forEach(odRoute -> {
			double x = getX(tmin, odRoute.getImpedanceTime());
			double s = getS(x);
			add(s);
		});
	}

	private static BigDecimal getP(double s, double sumS) {
//		System.out.println(s + " " + sumS);
		// return s.divide(sumS, 4, BigDecimal.ROUND_HALF_UP);
		return new BigDecimal(s / sumS).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	private static void add(double s) {
		// this.sumS = this.sumS.add(sumS);
		sumS += s;
	}
}
