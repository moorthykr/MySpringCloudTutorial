package com.moorthy.microservices.currencyconversionservice;

import java.math.BigDecimal;

import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValueBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyConversionBean {

	private long id;
	private String from;
	private String to;
	private BigDecimal exchangeMultiple;
	private int port;
	private int minimum;
	private int maximum;
	private BigDecimal quantity;
	private BigDecimal totalCalculatedAmount;
		
}
