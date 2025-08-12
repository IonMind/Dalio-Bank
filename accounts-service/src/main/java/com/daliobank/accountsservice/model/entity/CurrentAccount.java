package com.daliobank.accountsservice.model.entity;

import com.daliobank.accountsservice.model.entity.interfaces.CurrentAccountInterface;
import com.daliobank.accountsservice.model.entity.interfaces.LoanFacility;
import com.daliobank.bankingcommons.enums.AccountType;
import com.daliobank.bankingcommons.exception.InsufficientFundsException;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@Entity
@DiscriminatorValue("Current Account")
public class CurrentAccount extends Account implements CurrentAccountInterface, LoanFacility {

    // private final AccountType accountType = AccountType.CURRENT;
    // private final int transactionLimit = 10;
    private String companyName;

    public CurrentAccount(String accountHolderName, Double balance, String companyName) {
        super(accountHolderName, balance);
        if (balance < getMinimumBalance()) {
            throw new InsufficientFundsException("Initial balance must be at least " + getMinimumBalance());
        }
        this.companyName = companyName;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CURRENT;
    }

    @Override
    public double getMinimumBalance() {
        return 10000.0;
    }

    @Override
    public int getTransactionLimit() {
        return 10;
    }

}
