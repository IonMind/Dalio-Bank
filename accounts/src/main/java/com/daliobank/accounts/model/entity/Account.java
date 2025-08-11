package com.daliobank.accounts.model.entity;

import com.daliobank.accounts.model.enums.AccountType;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
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
    private Long accountNumber;
    private String accountHolderName;
    private Double balance;
    protected final Double minimumBalance;

    public Account(String accountHolderName, Double balance, Double minimumBalance) {
        this.minimumBalance = null;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // define common methods for all account types
    public abstract AccountType getAccountType();

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withdraw(Double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}
