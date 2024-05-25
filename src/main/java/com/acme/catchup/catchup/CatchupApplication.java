package com.acme.catchup.catchup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	// Enable JPA Auditing
public class CatchupApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatchupApplication.class, args);
	}

}
