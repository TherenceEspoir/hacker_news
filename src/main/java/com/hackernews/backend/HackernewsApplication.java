package com.hackernews.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HackernewsApplication {

	@RestController
	class Controller {

	}

	public static void main(String[] args) {
		SpringApplication.run(HackernewsApplication.class, args);
	}

}
