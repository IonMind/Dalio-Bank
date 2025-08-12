package com.daliobank.accountsservice.model.entity;

import com.daliobank.accountsservice.model.entity.interfaces.LoanFacility;
import com.daliobank.bankingcommons.enums.AccountType;
import com.daliobank.bankingcommons.exception.InsufficientFundsException;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("Savings Account")
public class SavingsAccount extends Account implements LoanFacility {

    // private final AccountType accountType = AccountType.SAVINGS;


    public SavingsAccount(String accountHolderName, Double balance) {
        super(accountHolderName, balance);
        if (balance < getMinimumBalance()) {
            throw new InsufficientFundsException("Initial balance must be at least " + getMinimumBalance());
        }
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    @Override
    public double getMinimumBalance() {
        return 1000.0;
    }

    @Override
    public int getTransactionLimit() {
        return 5;
    }
}
