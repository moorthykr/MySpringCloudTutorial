package com.moorthy.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@ConfigurationProperties("currency-conversion-service")
@EnableFeignClients("com.moorthy.microservices.currencyconversionservice")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	// Moorthy
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
}
