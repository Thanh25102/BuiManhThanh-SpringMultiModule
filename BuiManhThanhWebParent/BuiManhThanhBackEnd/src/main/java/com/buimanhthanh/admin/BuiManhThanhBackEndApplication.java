package com.buimanhthanh.admin;

import com.buimanhthanh.admin.util.FileUploadUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan({"com.buimanhthanh.common.entity"})
public class BuiManhThanhBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuiManhThanhBackEndApplication.class, args);
	}
	@Bean
	public FileUploadUtil fileUploadUtil(){
		return new FileUploadUtil();
	}
}
