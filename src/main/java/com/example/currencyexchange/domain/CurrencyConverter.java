package com.example.currencyexchange.domain;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private volatile Map<String, Double> rates = new HashMap<>();

    public double exchange(final String fromSymbol, final String toSymbol, final double amount) {
        // copy reference in case it changes between gets;
        final Map<String, Double> ratesCopy = rates;

        final Double fromRate = ratesCopy.get(fromSymbol);
        if (fromRate == null) {
            throw new IllegalArgumentException(String.format("Unknown from symbol: %s", fromSymbol));
        }
        final Double toRate = ratesCopy.get(toSymbol);
        if (toRate == null) {
            throw new IllegalArgumentException(String.format("Unknown to symbol: %s", toSymbol));
        }

        return amount * fromRate / toRate;
    }

    public void updateRates(final Map<String, Double> newRates) {
        rates = newRates;

    }

}
