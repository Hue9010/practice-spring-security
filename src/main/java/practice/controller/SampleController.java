package practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);

	@GetMapping("/")
	public String index() {
		log.info("index");
		return "index";
	}
	
	@GetMapping("/guest")
	public String guest() {
		log.info("guest");
		return "guest";
	}
	
	@GetMapping("/manage")
	public String manage() {
		log.info("manage");
		return "manage";
	}
	
	@GetMapping("/admin")
	public String admin() {
		log.info("admin");
		return "admin";
	}
}
