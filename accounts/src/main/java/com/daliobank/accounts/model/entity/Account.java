package com.daliobank.accounts.model.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class Account {
    

    @Setter(value = AccessLevel.NONE)
    @Id
    private Long accountNumber;
    private String accountHolderName;
    private Double balance;
    protected final Double minimumBalance;
 
    // define common methods for all account types
    public abstract String getAccountType();

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
    public String toString( ) {
        return "Account{" +
                ", accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}
