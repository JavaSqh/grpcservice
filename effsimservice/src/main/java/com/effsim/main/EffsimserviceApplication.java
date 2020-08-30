package com.effsim.main;

import com.effsim.main.service.GrpcStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 *@description:  项目启动入口
 *@author: Shiqinghu
 *@time: 2020/8/30 18:40
 */
@SpringBootApplication
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
