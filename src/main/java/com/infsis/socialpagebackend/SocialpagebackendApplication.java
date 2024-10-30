package com.infsis.socialpagebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SocialpagebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialpagebackendApplication.class, args);
	}

}
