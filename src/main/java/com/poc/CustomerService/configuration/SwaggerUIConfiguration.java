package com.poc.CustomerService.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
 * Swagger UI Configuration is used to customize Swagger UI
 */

@Configuration
public class SwaggerUIConfiguration {

	@Bean
	public Docket configuration() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.poc.CustomerService")).build().apiInfo(applicationDetails());
		
		docket.useDefaultResponseMessages(false);
		return docket;
	}

	/*
	 *  Method is used to add the Application details on Swagger UI 
	 *  
	 *  @return Application Details
	 */
	private ApiInfo applicationDetails() {
		
		return new ApiInfo("Customer Service", // Title
				"My POC Application", // Description
				"1.0", // Version
				null, // Terms of service URL
				null, // contact
				null, // License
				null, // License URL
				Collections.emptyList()); // Vendor Extensions
	}
	
}
