package com.example.chartjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChartjsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChartjsApplication.class, args);
	}

}
