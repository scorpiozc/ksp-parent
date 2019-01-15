package cn.com.bjjdsy.path.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.com.bjjdsy.path.service.KspService;

@RestController
public class KspController {

	private static final Logger logger = LoggerFactory.getLogger(KspController.class);
	@Resource(name = "KspAccessibleServiceImpl")
	private KspService kspAccessibleService;
	@Resource(name = "KspEffectiveServiceImpl")
	private KspService kspEffectiveService;

	@GetMapping(value = "calcAccessiblePath/{taskJobId}")
	public String calcAccessiblePath(@PathVariable("taskJobId") String taskJobId) {
		return kspAccessibleService.calcPath(taskJobId);
	}

	@GetMapping(value = "calcEffectivePath/{taskJobId}")
	public String calcEffectivePath(@PathVariable("taskJobId") String taskJobId) {
		return kspEffectiveService.calcPath(taskJobId);
	}

	public static void main(String[] args) {
		SpringApplication.run(KspController.class, args);
	}
}
