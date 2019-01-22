package cn.com.bjjdsy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "cn.com.bjjdsy.data.mapper")
@EnableTransactionManagement
public class KspPathServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger("log-path-service");

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(KspPathServiceApplication.class);
		logger.info("KspPathCalcApplication start");
//		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

}
