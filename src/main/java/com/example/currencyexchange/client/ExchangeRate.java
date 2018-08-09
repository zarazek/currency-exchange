package com.example.currencyexchange.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ExchangeRate {
    private final String symbol;
    private final double rate;

    @JsonCreator
    public ExchangeRate(final @JsonProperty("code") String symbol,
                        final @JsonProperty("mid") double rate) {
        this.symbol = symbol;
        this.rate = rate;
    }
}
