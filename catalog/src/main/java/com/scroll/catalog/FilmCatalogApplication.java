package com.scroll.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = "com.scroll")
@EnableMongoRepositories(basePackages = "com.scroll.repository")
@ConfigurationPropertiesScan(basePackages = "com.scroll.config")
@EnableReactiveMongoRepositories(basePackages = "com.scroll.repository.reactive")
@Slf4j
public class FilmCatalogApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FilmCatalogApplication.class, args);
		log.info("Reached Main");
		
		log.info("#####################################################");
		
	}

}
