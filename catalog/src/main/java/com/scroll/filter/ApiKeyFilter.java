package com.scroll.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiKeyFilter extends OncePerRequestFilter {

	private static final String API_KEY_HEADER = "X-API-KEY";

	private String VALID_API_KEY;

	public ApiKeyFilter(@Value("${api.key}") String vALID_API_KEY) {
		super();
		VALID_API_KEY = vALID_API_KEY;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String apiKey = request.getHeader(API_KEY_HEADER);

		if (apiKey == null || !StringUtils.equals(apiKey, VALID_API_KEY)) {
			log.error("Provided API Key {} Does Not Match Valid Key {}", apiKey, VALID_API_KEY);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Unauthorized Request - Invalid API Key");
			return;
		}

		log.info("API KEY Validated. Processing Request...");
		filterChain.doFilter(request, response);

	}

}
