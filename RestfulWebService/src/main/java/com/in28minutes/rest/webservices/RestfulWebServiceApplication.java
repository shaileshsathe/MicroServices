package com.in28minutes.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		// used AcceptHeaderResolver instead of SessionLocalResolver as we are accepting
		// it in header
		// advantage of AcceptHeaderResolver is we would not need to configure Locale as
		// Request Parameter or RequestHeader in every controller method with LocaleContextHolder.getLocale
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;

	}

	// configured this in application.propertied instead of bean
	/*
	 * @Bean//Be careful about the name of the method -should be messageSource
	 * public ResourceBundleMessageSource bundleMessageSource() {
	 * ResourceBundleMessageSource messageSource = new
	 * ResourceBundleMessageSource(); messageSource.setBasename("messages"); return
	 * messageSource; }
	 */

}
