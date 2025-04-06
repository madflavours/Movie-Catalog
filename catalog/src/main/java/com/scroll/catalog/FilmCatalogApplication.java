package com.scroll.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.scroll")
@EnableMongoRepositories(basePackages = "com.scroll.repository")
@ConfigurationPropertiesScan(basePackages = "com.scroll.config")
@EnableReactiveMongoRepositories(basePackages = "com.scroll.repository.reactive")
@PropertySource("classpath:application.properties")
public class FilmCatalogApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FilmCatalogApplication.class, args);
	}

}
