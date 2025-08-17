package com.daliobank.transaction_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import com.daliobank.bankingcommons.dto.AccountResponseDTO;
import com.daliobank.bankingcommons.exception.DepositFailedException;
import com.daliobank.bankingcommons.exception.WithdrawFailedException;
import com.daliobank.transaction_service.config.RestClientConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountsServiceClient {

    @Autowired
    RestClientConfig restClientConfig;

    public AccountResponseDTO getAccountDetails(Long accountNumber) {
        return restClientConfig.getRestClient().get()
                .uri(uriBuiler -> uriBuiler.queryParam("accountNumber", accountNumber)
                        .path("/getAccountInfo")
                        .build())
                .retrieve()
                .body(AccountResponseDTO.class);

    }

    public void withdrawMoney(Long accountNumber, Double amount) throws WithdrawFailedException {
        if (!restClientConfig.getRestClient().put()
                .uri(uriBuilder -> uriBuilder.queryParam("accountNumber", accountNumber)
                        .queryParam("amount", amount).path("/withdraw").build())
                .retrieve().toBodilessEntity().getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
            throw new WithdrawFailedException("Withdraw Not Sucessful");
        }

    }

    public void depositMoney(Long accountNumber, Double amount) throws DepositFailedException {
        if (!restClientConfig.getRestClient().put()
                .uri(uriBuilder -> uriBuilder.queryParam("accountNumber", accountNumber)
                        .queryParam("amount", amount).path("/deposit").build())
                .retrieve().toBodilessEntity().getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
            throw new DepositFailedException("Deposit Not Sucessful");
        }
    }

}
