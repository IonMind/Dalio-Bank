package com.daliobank.accounts.service;


import com.daliobank.accounts.model.dto.AccountRequestDTO;
import com.daliobank.accounts.model.dto.AccountResponseDTO;

public interface AccountService {
    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO);
    public AccountResponseDTO getAccountInfo(Long accountNumber);
    public String withdraw(Long accountNumber,Double amount);
    public String deposit(Long accountNumber,Double amount);
}
