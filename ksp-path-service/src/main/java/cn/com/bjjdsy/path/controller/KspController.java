package cn.com.bjjdsy.path.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.com.bjjdsy.path.entity.PathQO;
import cn.com.bjjdsy.path.service.KspService;

@RestController
public class KspController {

	private static final Logger logger = LoggerFactory.getLogger(KspController.class);
	@Autowired
	private KspService kspService;

	@GetMapping(value = "calcPath/{from}/{to}/{k}")
	public String calcPath(@PathVariable("from") int fromStation, @PathVariable("to") int toStation,
			@PathVariable("k") int k, PathQO qo) {
		kspService.calcPath(1);
		// return fromStation + "-" + toStation + " reach path k=" + k + " calc is
		// complete " + list.size();
		return "ok";
	}

	public static void main(String[] args) {
		SpringApplication.run(KspController.class, args);
	}
}
