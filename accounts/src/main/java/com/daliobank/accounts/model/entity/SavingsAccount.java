package com.daliobank.accounts.model.entity;

import com.daliobank.accounts.model.entity.interfaces.LoanFacility;
import com.daliobank.accounts.model.enums.AccountType;

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

    private final AccountType accountType = AccountType.SAVINGS;
    private final int transactionLimit = 5;

    public SavingsAccount(String accountHolderName, Double balance) {
        super(accountHolderName, balance, 1000.0);
        if (balance < minimumBalance) {
            throw new IllegalArgumentException("Initial balance must be at least " + minimumBalance);
        }
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return super.toString() + ", SavingsAccount{" +
                ", accountType='" + accountType.toString() + '\'' +
                ", transactionLimit=" + transactionLimit +
                '}';
    }
}
