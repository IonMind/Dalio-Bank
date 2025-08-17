package com.daliobank.transaction_service.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import com.daliobank.bankingcommons.exception.AccountNotFoundException;
import com.daliobank.bankingcommons.exception.InvalidTransactionType;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestClientConfig {
    @Value("${accounts.service.base-url}")
    private String accountsServiceBaseUrl;

    @Bean
    public RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(accountsServiceBaseUrl)
                .defaultStatusHandler(statusCode -> statusCode.isSameCodeAs(HttpStatusCode.valueOf(404)),
                        (request, response) -> {
                            String errorResponse = new String(response.getBody().readAllBytes());
                            log.error("REQUEST-> " + request.getURI().toString() + " Response-> "
                                    + response.getStatusCode().value() + errorResponse);
                            throw new AccountNotFoundException(errorResponse);
                        })
                .defaultStatusHandler(statusCode -> statusCode.isSameCodeAs(HttpStatusCode.valueOf(400)),
                        (request, response) -> {
                            String errorResponse = new String(response.getBody().readAllBytes());
                            log.error("REQUEST-> " + request.getURI().toString() + " Response-> "
                                    + response.getStatusCode().value() + errorResponse);
                            throw new InvalidTransactionType(errorResponse); //errors like inscufficient balance
                        })
                .defaultStatusHandler(statusCode -> statusCode.isSameCodeAs(HttpStatusCode.valueOf(500)),
                        (request, response) -> {
                            String errorResponse = new String(response.getBody().readAllBytes());
                            log.error("REQUEST-> " + request.getURI().toString() + " Response-> "
                                    + response.getStatusCode().value() + errorResponse);
                            throw new InternalError();
                        })
                .requestFactory(customRequestFactory())
                .build();
    }

    private SimpleClientHttpRequestFactory customRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(10));
        return factory;
    }
}
