package com.daliobank.accountsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daliobank.accountsservice.model.entity.Account;
import com.daliobank.accountsservice.model.entity.CurrentAccount;
import com.daliobank.accountsservice.model.entity.NRIAccount;
import com.daliobank.accountsservice.model.entity.SavingsAccount;
import com.daliobank.accountsservice.repository.AccountRepo;
import com.daliobank.bankingcommons.dto.AccountRequestDTO;
import com.daliobank.bankingcommons.dto.AccountResponseDTO;
import com.daliobank.bankingcommons.exception.AccountNotFoundException;
import com.daliobank.bankingcommons.exception.InvalidAccountDetails;

import jakarta.transaction.Transactional;

@Service
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
    @Transactional
    public String withdraw(Long accountNumber,Double amount) {
       return "Updated Balance :" + accountRepo.save(searchAccount(accountNumber).withdraw(amount)).getBalance().toString();
    }

    @Override
    @Transactional
    public String deposit(Long accountNumber, Double amount) {
        return "Updated Balance :" + accountRepo.save(searchAccount(accountNumber).deposit(amount)).getBalance().toString();
    }

    private Account createAccount(AccountRequestDTO accountDto) {
        String type = accountDto.accountType().toUpperCase();
        // Using switch expression for cleaner code
        return switch (type) {
            case "SAVINGS" -> new SavingsAccount(accountDto.accountHolderName(), accountDto.initialBalance());
            case "CURRENT" -> new CurrentAccount(accountDto.accountHolderName(), accountDto.initialBalance(), accountDto.accountHolderName());
            case "NRI" -> new NRIAccount(accountDto.accountHolderName(), accountDto.initialBalance(), accountDto.accountHolderName());
            default -> throw new InvalidAccountDetails("accountType must be one of the following: SAVINGS, CURRENT, NRI");
        };
    }

    private AccountResponseDTO createResponse(Account account) {
        return new AccountResponseDTO(account.getAccountNumber(), account.getAccountType(),
                account.getAccountHolderName(), account.getBalance());
    }

    private Account searchAccount(Long accountNumber) {
        return accountRepo.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("No account found with number: " + accountNumber));
    }
}
