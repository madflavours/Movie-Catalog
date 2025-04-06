package com.scroll.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scroll.filter.ApiKeyFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApiKeyConfig {

	private ApiKeyFilter apiKeyFilter;

	public ApiKeyConfig(ApiKeyFilter apiKeyFilter) {
		super();
		this.apiKeyFilter = apiKeyFilter;
	}

	@Bean
	FilterRegistrationBean<ApiKeyFilter> apiKeyFilterBean() {
		log.info("Configuring API KEY Bean Configuration...");
		FilterRegistrationBean<ApiKeyFilter> apiKeyFilterBean = new FilterRegistrationBean<ApiKeyFilter>();
		apiKeyFilterBean.setFilter(apiKeyFilter);
		apiKeyFilterBean.addUrlPatterns("/api/*");
		return apiKeyFilterBean;
	}
}
