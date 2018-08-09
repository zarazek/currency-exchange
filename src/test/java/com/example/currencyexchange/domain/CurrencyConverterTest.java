package com.example.currencyexchange.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Test
public class CurrencyConverterTest {
    private static final double USD_TO_PLN_RATE = 3.6846;

    private CurrencyConverter converter;

    @BeforeMethod
    public void setUp() {
        converter = new CurrencyConverter();
        final Map<String, Double> rates = new HashMap<>();
        rates.put("USD", USD_TO_PLN_RATE);
        rates.put("PLN", 1.0);
        converter.updateRates(rates);
    }

    @Test
    public void exchangeBetweenUsdAndUsd() {
        assertThat(converter.exchange("USD", "USD", 10.0)).isEqualTo(10.0);
    }

    @Test
    public void exchangeBetweenUsdAndPln() {
        assertThat(converter.exchange("USD", "PLN", 10)).isEqualTo(10.0 * USD_TO_PLN_RATE);
    }

    @Test
    public void exchangeBetweenPlnAndUsd() {
        assertThat(converter.exchange("PLN", "USD", 10)).isEqualTo(10.0 / USD_TO_PLN_RATE);
    }

    @Test
    public void exchangeFromUnknownCurrency() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> converter.exchange("EUR", "PLN", 10)
        );
    }

    @Test
    public void exchangeToUnknownCurrency() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> converter.exchange("PLN", "EUR", 10)
        );
    }

}