package com.noubug.rentrepo.infrastructure.web.controllers;

import com.noubug.rentrepo.usecases.UpdateCurrenciesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/public/cron")
public class CronController {
    private final UpdateCurrenciesUseCase updateCurrenciesUseCase;

    public CronController(UpdateCurrenciesUseCase updateCurrenciesUseCase) {
        this.updateCurrenciesUseCase = updateCurrenciesUseCase;
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> createCurrencies() {
        updateCurrenciesUseCase.loadCurrencies();
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/currencies/rates/update")
    public ResponseEntity<?> updateCurrencyExchangeRate() {
        updateCurrenciesUseCase.updateCurrenciesExchangeRate();
        return ResponseEntity.ok("ok");
    }
}
