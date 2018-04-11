package com.swop4a.gd.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("POST", "GET", "OPTIONS", "DELETE")
			.maxAge(3600)
			.exposedHeaders("x-requested-with");
	}
}
