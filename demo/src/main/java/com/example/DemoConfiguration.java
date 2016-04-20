package com.example;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean( new WebServlet() );

		registration.addUrlMappings( "/console/*" );
		return registration;
	}

}
