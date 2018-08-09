package com.example.currencyexchange.client;

import com.example.currencyexchange.client.ExchangeRate;
import com.example.currencyexchange.rest.ExchangeRatesUpdater;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class NbpClient {
    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/tables/a";

    public Map<String, Double> retrieveExchangeRates() {
        final RestTemplate restTemplate = new RestTemplate();
        final ExchangeRateTable[] tables = restTemplate.getForObject(NBP_API_URL, ExchangeRateTable[].class);
        return toExchangeRateMap(tables[0].getRates());
    }

    private static Map<String, Double> toExchangeRateMap(ExchangeRate[] ratesList) {
        final Map<String, Double> ratesMap = new HashMap<>();
        for (final ExchangeRate rate : ratesList) {
            addExchangeRate(ratesMap, rate.getSymbol(), rate.getRate());
        }

        // special case for PLN
        addExchangeRate(ratesMap, "PLN", 1.0);

        return ratesMap;
    }

    private static void addExchangeRate(Map<String, Double> ratesMap, final String symbol, final double rate) {
        if (rate < 0) {
            throw new IllegalArgumentException(String.format("Invalid rate for %s: %s", symbol, rate));
        }
        final Double prevRate = ratesMap.put(symbol, rate);
        if (prevRate != null) {
            throw new IllegalArgumentException(String.format("Duplicate rate for %s", symbol));
        }
    }

}
