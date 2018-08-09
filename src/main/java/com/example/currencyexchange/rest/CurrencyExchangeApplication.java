package com.example.currencyexchange.rest;

import com.example.currencyexchange.client.NbpClient;
import com.example.currencyexchange.domain.CurrencyConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyExchangeApplication {

	public static void main(String[] args) { ;
		SpringApplication.run(CurrencyExchangeApplication.class, args);
	}

	@Bean
	CurrencyConverter currencyConverter() {
		return new CurrencyConverter();
	}

	@Bean
    NbpClient nbpClient() {
	    return new NbpClient();
    }
}
