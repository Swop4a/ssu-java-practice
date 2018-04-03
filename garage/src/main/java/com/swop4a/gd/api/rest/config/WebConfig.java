package com.swop4a.gd.api.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Aleksandr_Pavkin
 * @since 4/3/2018 9:11 PM
 */
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
