package com.moorthy.microservices.currencyexchangeservice.controller;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moorthy.microservices.currencyexchangeservice.dao.CurrencyExchangeDao;
import com.moorthy.microservices.currencyexchangeservice.entity.CurrencyLimits;
import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValueBean;

@RestController
public class CurrencyExchangeController {
	private long id = 1l;
	private String from;
	private String to;
	private BigDecimal exchangeMultiple;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeDao currencyExchangeDao;
		
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValueBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = currencyExchangeDao.findByFromAndTo(from, to);
		
		if (exchangeValue == null) {
			throw new RuntimeException("Exchange data for "+from+" to "+to+" does not exist");
		}

		int port = Integer.parseInt(environment.getProperty("local.server.port"));
		CurrencyLimits currencyLimits = getCurrencyLimits();
		ExchangeValueBean exchangeValueBean = 
				new ExchangeValueBean(exchangeValue.getId(), from, to, exchangeValue.getExchangeMultiple(), port, currencyLimits.getMinimum(), currencyLimits.getMaximum()); 
		return exchangeValueBean;
	}
	
	public CurrencyLimits getCurrencyLimits() {
		ResponseEntity<CurrencyLimits> response = new RestTemplate().getForEntity("http://localhost:8080/limits",CurrencyLimits.class);
		
		CurrencyLimits currencyLimits = response.getBody();
		
		return currencyLimits;
		
	}

}
