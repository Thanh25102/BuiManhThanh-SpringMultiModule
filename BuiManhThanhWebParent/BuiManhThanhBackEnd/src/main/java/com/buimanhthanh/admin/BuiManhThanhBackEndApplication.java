package com.buimanhthanh.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.buimanhthanh.common.entity"})
public class BuiManhThanhBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuiManhThanhBackEndApplication.class, args);
	}

}
