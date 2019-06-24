package com.moorthy.microservices.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties("limits-service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LimitsConfiguration {

	private int minimum;
	private int maximum;
}
