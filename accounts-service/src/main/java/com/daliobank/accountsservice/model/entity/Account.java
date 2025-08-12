package com.daliobank.accountsservice.model.entity;

import com.daliobank.bankingcommons.enums.AccountType;
import com.daliobank.bankingcommons.exception.InsufficientFundsException;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
// @AllArgsConstructor
public abstract class Account {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;
    private String accountHolderName;
    private Double balance;


    public Account(String accountHolderName, Double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // define common methods for all account types
    public abstract AccountType getAccountType();
    public abstract double getMinimumBalance();
    public abstract int getTransactionLimit();

    public Account deposit(Double amount) {
        this.balance += amount;
        return this;
    }

    public Account withdraw(Double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new InsufficientFundsException("Insufficient balance, current balance is " + this.balance);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                ", minimumBalanceRequirement=" + getMinimumBalance() +
                ", transactionLimit=" + getTransactionLimit() +
                ", account type=" + getAccountType() +
                '}';
    }
}
