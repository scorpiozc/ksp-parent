package cn.com.bjjdsy.calc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import cn.com.bjjdsy.common.util.Stopwatch;
import cn.com.bjjdsy.data.loader.LoadData;
import cn.com.bjjdsy.data.loader.impl.LoadKspDataFileImpl;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class KspPathCalcApplication {

	private static final Logger logger = LoggerFactory.getLogger("log-calc");

	public static void main2(String[] args) {
		Stopwatch timer = new Stopwatch();
		timer.start();
		LoadData loadData = new LoadKspDataFileImpl();
		try {
			loadData.load("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		timer.stop();
		logger.info("load data spend: {} seconds\n", String.format("%f", timer.time()));
		timer.start();
		CalcEngine calcEngine = new CalcEngine();
		calcEngine.start(5);
		timer.stop();
		logger.info("calc path spend: {} seconds\n", String.format("%f", timer.time()));
		timer.start();
		calcEngine.printPath(201, 457, "");
		timer.stop();
		logger.info("print path spend: {} seconds\n", String.format("%f", timer.time()));
//		SpringApplication app = new SpringApplication(KspPathCalcApplication.class);
		logger.info("KspPathCalcApplication start");
//		app.setWebApplicationType(WebApplicationType.NONE);
//		app.run(args);
	}

}
