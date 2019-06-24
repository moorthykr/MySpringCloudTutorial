package com.moorthy.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moorthy.microservices.limitsservice.configuration.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {
//	@Value("${limits-service.maximum}")
//	private int maximum;
//	
//	@Value("${limits-service.minimum}")	
//	private int minimum;

	@Autowired
	private LimitsConfiguration limitsConfiguration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsConfigurations() {
		
		return new LimitsConfiguration (limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
		
	}
}
