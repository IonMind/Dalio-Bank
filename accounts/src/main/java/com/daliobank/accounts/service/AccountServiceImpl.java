package com.daliobank.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.daliobank.accounts.model.dto.AccountRequestDTO;
import com.daliobank.accounts.model.dto.AccountResponseDTO;
import com.daliobank.accounts.model.entity.Account;
import com.daliobank.accounts.model.entity.CurrentAccount;
import com.daliobank.accounts.model.entity.NRIAccount;
import com.daliobank.accounts.model.entity.SavingsAccount;
import com.daliobank.accounts.model.enums.AccountType;
import com.daliobank.accounts.repository.AccountRepo;

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO) {
        return createResponse(accountRepo.save(createAccount(accountRequestDTO)));
    }

    @Override
    public AccountResponseDTO getAccountInfo(Long accountNumber) {
        return createResponse(searchAccount(accountNumber));
    }

    @Override
    public void debitAccount(Long accountNumber,Double amount) {
        searchAccount(accountNumber).withdraw(amount);
    }

    @Override
    public void creditAccount(Long accountNumber, Double amount) {
        searchAccount(accountNumber).deposit(amount);
    }

    private Account createAccount(AccountRequestDTO accountDto) {
        if (accountDto.accountType() == AccountType.SAVINGS) {
            return new SavingsAccount(accountDto.accountHolderName(), accountDto.initialBalance());
        } else if (accountDto.accountType() == AccountType.CURRENT) {
            return new CurrentAccount(accountDto.accountHolderName(), accountDto.initialBalance(),
                    accountDto.accountHolderName());
        } else if (accountDto.accountType() == AccountType.NRI) {
            return new NRIAccount(accountDto.accountHolderName(), accountDto.initialBalance(),
                    accountDto.accountHolderName());
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
    }

    private AccountResponseDTO createResponse(Account account) {
        return new AccountResponseDTO(account.getAccountNumber(), account.getAccountType(),
                account.getAccountHolderName(), account.getBalance());
    }

    private Account searchAccount(Long accountNumber) {
        return accountRepo.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account type"));
    }
}
