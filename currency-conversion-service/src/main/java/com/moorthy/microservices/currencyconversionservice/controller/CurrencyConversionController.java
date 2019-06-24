package com.moorthy.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moorthy.microservices.currencyconversionservice.CurrencyConversionBean;
import com.moorthy.microservices.currencyconversionservice.feignproxy.CurrencyExchangeServiceFeignProxy;
import com.moorthy.microservices.currencyexchangeservice.entity.CurrencyLimits;
import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValueBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeServiceFeignProxy proxy;
	
	@GetMapping("/currency-converter-nofeign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversion(@PathVariable String from, 
			                                       @PathVariable String to, 
			                                       @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		// tried with .getForObject... works too, but with different syntax

		ResponseEntity<CurrencyConversionBean> responseEntity = 
		            new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				                                    CurrencyConversionBean.class, 
				                                    uriVariables);
		
		CurrencyConversionBean response = responseEntity.getBody();

		CurrencyConversionBean currencyConversionBean = formatCurrencyConversionBean(from, to, quantity, response); 
		
		return currencyConversionBean;	
	}
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversionFeign(@PathVariable String from, 
			                                       @PathVariable String to, 
			                                       @PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		
		CurrencyConversionBean currencyConversionBean = formatCurrencyConversionBean(from, to, quantity, response); 

		System.out.println("The mango response is "+ response);
		
		return currencyConversionBean;	
	}

	private CurrencyConversionBean formatCurrencyConversionBean(String from, String to, BigDecimal quantity,
			CurrencyConversionBean response) {
		long id = response.getId(); 
		BigDecimal exchangeMultiple = response.getExchangeMultiple();
		BigDecimal totalCalculatedAmount = quantity.multiply(exchangeMultiple);
		int port = response.getPort();
		int minimum = response.getMinimum();
		int maximum = response.getMaximum();
		
		
		CurrencyConversionBean currencyConversionBean = 
				new CurrencyConversionBean(id, from, to, exchangeMultiple, port, minimum, maximum, quantity, totalCalculatedAmount);
		return currencyConversionBean;
	}
}
