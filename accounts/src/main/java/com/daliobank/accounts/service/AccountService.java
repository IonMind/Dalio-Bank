package com.daliobank.accounts.service;

import org.springframework.stereotype.Service;

import com.daliobank.accounts.model.dto.AccountRequestDTO;
import com.daliobank.accounts.model.dto.AccountResponseDTO;

@Service
public interface AccountService {
    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO);
    public AccountResponseDTO getAccountInfo(Long accountNumber);
    public void debitAccount(Long accountNumber,Double amount);
    public void creditAccount(Long accountNumber,Double amount);
}
