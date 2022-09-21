package com.poc.CustomerService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.poc.CustomerService.util.AfterLoadEventListener;
import com.poc.CustomerService.util.BeforeSaveEventListener;

@PropertySource(value = {"classpath:pi-data.properties"})
@Configuration
public class EncryptionConfiguration {

	@Bean
	public BeforeSaveEventListener beforeSaveEventListener() {
		return new BeforeSaveEventListener();
	}
	
	@Bean
	public AfterLoadEventListener afterLoadEventListener() {
		return new AfterLoadEventListener();
	}
	
}
