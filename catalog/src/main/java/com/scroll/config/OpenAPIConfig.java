package com.scroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@Slf4j
public class OpenAPIConfig {

	@Bean
	OpenAPI customOpenAPI() {
		log.info("Configuring Open API Swagger With API-KEY Authorization");
		return new OpenAPI()
				.info(
						new Info()
						.title("Movie API")
						.version("1.0")
						.description("This is a simple API to manage movies")
						)
				.components(new io.swagger.v3.oas.models.Components()
				        .addSecuritySchemes("API-KEY", new SecurityScheme()
				                .type(SecurityScheme.Type.APIKEY)
				                .in(SecurityScheme.In.HEADER)
				                .name("API-KEY")))
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("API-KEY"));
	}
}