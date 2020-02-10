package com.demo;

import com.demo.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpreinteApplication implements CommandLineRunner {
@Autowired
private DevicesService devicesService;


	public static void main(String[] args) {SpringApplication.run(EmpreinteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		devicesService.InitDeviceList();

	};

}
