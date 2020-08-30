package com.effsim.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableScheduling 定时任务
public class EffsimserviceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(EffsimserviceApplication.class, args);
		GrpcStart grpcStart = (GrpcStart) run.getBean("grpcStart");
		try {
			grpcStart.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
