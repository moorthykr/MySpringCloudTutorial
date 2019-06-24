package com.moorthy.microservices.currencyexchangeservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.moorthy.microservices.currencyexchangeservice.entity.ExchangeValueBean;

@Repository
public interface CurrencyExchangeDao extends JpaRepository<ExchangeValue, Long> {
	
	ExchangeValue findByFromAndTo(String from, String to);
	
}
