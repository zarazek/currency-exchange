package com.example.currencyexchange.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class ExchangeRateTable {
    private final String effectiveDate;
    private final ExchangeRate[] rates;

    @JsonCreator
    public ExchangeRateTable(final @JsonProperty("effectiveDate") String effectiveDate,
                             final @JsonProperty("rates") ExchangeRate[] rates) {
        this.effectiveDate = effectiveDate;
        this.rates = rates;
    }
}
