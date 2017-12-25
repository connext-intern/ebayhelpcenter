package com.connext.ebayhelpcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.connext.ebayhelpcenter.dao")
public class EbayHelpcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbayHelpcenterApplication.class, args);
	}
}
