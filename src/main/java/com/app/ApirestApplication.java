package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"com.api.controllers"})
@EntityScan("com.api.entities")
@EnableJpaRepositories("com.api.repository")
@EnableSwagger2
public class ApirestApplication implements WebMvcConfigurer {



//	@Override
//	public void addViewControllers (ViewControllerRegistry registry) {
//		RedirectViewControllerRegistration r =
//				registry.addRedirectViewController("/", "/swagger-ui.html");
//	}

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}



}
