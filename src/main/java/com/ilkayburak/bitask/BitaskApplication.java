package com.ilkayburak.bitask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BitaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitaskApplication.class, args);
	}

}
