package com.soft.crawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui.html
@EnableSwagger2
@SpringBootApplication
public class CrawlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlApplication.class, args);
	}

}
