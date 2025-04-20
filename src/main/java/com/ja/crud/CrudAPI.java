package com.ja.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CrudAPI {

	public static void main(String[] args) {
		SpringApplication.run(CrudAPI.class, args);
	}




}
