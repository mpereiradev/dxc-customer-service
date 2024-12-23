package dev.matheuspereira.dxc_customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@ComponentScan(basePackages = "dev.matheuspereira.dxc_customer_service")
@EnableJpaRepositories(basePackages = "dev.matheuspereira.dxc_customer_service.infrastructure.jpa")
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class DxcCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DxcCustomerServiceApplication.class, args);
	}

}
