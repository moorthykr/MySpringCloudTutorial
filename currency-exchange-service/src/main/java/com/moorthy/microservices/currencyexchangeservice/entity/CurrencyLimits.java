package com.moorthy.microservices.currencyexchangeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyLimits {

	private int minimum;
	private int maximum;
}
