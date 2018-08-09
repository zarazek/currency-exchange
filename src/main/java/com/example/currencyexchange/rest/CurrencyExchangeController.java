package com.example.currencyexchange.rest;

import com.example.currencyexchange.domain.CurrencyConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private final CurrencyConverter converter;

    public CurrencyExchangeController(final CurrencyConverter converter) {
        this.converter = converter;
    }

    @RequestMapping("/exchange/{source}/{target}/{amount}")
    public double exchange(final @PathVariable("source") String source,
                           final @PathVariable("target") String target,
                           final @PathVariable("amount") double amount) {
        return converter.exchange(source, target, amount);
    }
}
