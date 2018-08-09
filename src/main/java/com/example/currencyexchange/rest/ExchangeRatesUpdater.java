package com.example.currencyexchange.rest;

import com.example.currencyexchange.client.NbpClient;
import com.example.currencyexchange.domain.CurrencyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRatesUpdater {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRatesUpdater.class);
    private static final long SCHEDULE_PERIOD = 60L * 1000L; // one minute
    private static final long REFRESH_PERIOD = 24L * 60L * 60L * 1000L; // 24 h

    private final NbpClient nbpClient;
    private final CurrencyConverter converter;
    private long lastSuccessfullRun = 0L;

    public ExchangeRatesUpdater(final NbpClient nbpClient,
                                final CurrencyConverter converter) {
        this.nbpClient = nbpClient;
        this.converter = converter;
    }

    @Scheduled(fixedRate = SCHEDULE_PERIOD)
    public void updateExchangeRates() {
        try {
            final long now = System.currentTimeMillis();
            if (now - lastSuccessfullRun < REFRESH_PERIOD) {
                return;
            }

            converter.updateRates(nbpClient.retrieveExchangeRates());

            lastSuccessfullRun = now;
            LOGGER.info("Successfuly updated exchange rates");
        } catch (final Throwable e) {
            LOGGER.warn("Error updating exchange rates, next retry after {} milliseconds", SCHEDULE_PERIOD, e);
        }
    }
}
