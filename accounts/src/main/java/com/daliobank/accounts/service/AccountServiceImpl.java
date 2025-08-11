package com.daliobank.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daliobank.accounts.exception.AccountNotFoundException;
import com.daliobank.accounts.exception.InvalidAccountDetails;
import com.daliobank.accounts.model.dto.AccountRequestDTO;
import com.daliobank.accounts.model.dto.AccountResponseDTO;
import com.daliobank.accounts.model.entity.Account;
import com.daliobank.accounts.model.entity.CurrentAccount;
import com.daliobank.accounts.model.entity.NRIAccount;
import com.daliobank.accounts.model.entity.SavingsAccount;
import com.daliobank.accounts.model.enums.AccountType;
import com.daliobank.accounts.repository.AccountRepo;

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
        if (accountDto.accountType().toLowerCase().equals(AccountType.SAVINGS.toString().toLowerCase()) ) {
            return new SavingsAccount(accountDto.accountHolderName(), accountDto.initialBalance());
        } else if (accountDto.accountType().toLowerCase().equals(AccountType.CURRENT.toString().toLowerCase())) {
            return new CurrentAccount(accountDto.accountHolderName(), accountDto.initialBalance(),
                    accountDto.accountHolderName());
        } else if (accountDto.accountType().toLowerCase().equals(AccountType.NRI.toString().toLowerCase())) {
            return new NRIAccount(accountDto.accountHolderName(), accountDto.initialBalance(),
                    accountDto.accountHolderName());
        } else {
            throw new InvalidAccountDetails("accountType must be one of the following:  SAVINGS, CURRENT, NRI");
        }
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
