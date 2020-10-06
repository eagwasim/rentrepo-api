package com.noubug.rentrepo.usecases.impl;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.noubug.rentrepo.domain.CurrencyDomain;
import com.noubug.rentrepo.domain.gateway.CurrencyDomainGateway;
import com.noubug.rentrepo.domain.services.EnvironmentService;
import com.noubug.rentrepo.usecases.UpdateCurrenciesUseCase;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.util.ResourceUtils;

import javax.inject.Named;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;

@Named
public class UpdateCurrenciesUseCaseImpl implements UpdateCurrenciesUseCase {
    private final Gson gson;
    private final CurrencyDomainGateway currencyDomainGateway;
    private final OkHttpClient client = new OkHttpClient();
    private final EnvironmentService environmentService;

    public UpdateCurrenciesUseCaseImpl(Gson gson, CurrencyDomainGateway currencyDomainGateway, EnvironmentService environmentService) {
        this.gson = gson;
        this.currencyDomainGateway = currencyDomainGateway;
        this.environmentService = environmentService;
    }

    @Override
    public void loadCurrencies() {
        try (InputStream inputStream = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "data/currencies.json").openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            String dataString = CharStreams.toString(inputStreamReader);
            Map<String, String> currencyMap = gson.fromJson(dataString, new TypeToken<Map<String, String>>() {
            }.getType());

            for (Map.Entry<String, String> s : currencyMap.entrySet()) {
                currencyDomainGateway.create(CurrencyDomain.builder().code(s.getKey()).name(s.getValue()).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCurrenciesExchangeRate() {
        Request request = new Request.Builder()
                .url(String
                        .format("https://openexchangerates.org/api/latest.json?app_id=%s&base=USD",
                                environmentService.getVariable("openexchange.appid")))
                .build();
        String body;

        try (Response response = client.newCall(request).execute()) {
            body = response.body().string();
            Map<String, Object> data = gson.fromJson(body, new TypeToken<Map<String, Object>>() {
            }.getType());
            Map<String, Double> rates = (Map<String, Double>) data.get("rates");
            for (Map.Entry<String, Double> s : rates.entrySet()) {
                currencyDomainGateway
                        .updateBaseCurrencyExchangeRate(CurrencyDomain.builder().code(s.getKey())
                                .baseCurrencyExchangeRate(BigDecimal.valueOf(s.getValue()))
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
