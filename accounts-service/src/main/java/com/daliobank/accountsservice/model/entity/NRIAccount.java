package com.daliobank.accountsservice.model.entity;

import com.daliobank.accountsservice.model.entity.interfaces.NRIInterface;
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
@DiscriminatorValue("NRI Account")
public class NRIAccount extends Account implements NRIInterface {

    // private final AccountType accountType = AccountType.NRI;
    // private final int transactionLimit = 2;
    private String residingCountry;

    public NRIAccount(String accountHolderName, Double balance, String residingCountry) {
        super(accountHolderName, balance);
        if (balance < getMinimumBalance()) {
            throw new InsufficientFundsException("Initial balance must be at least " + getMinimumBalance());
        }
        this.residingCountry = residingCountry;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.NRI;
    }

    @Override
    public String getResidingCountry() {
        return residingCountry;
    }

    @Override
    public double getMinimumBalance() {
        return 5000.0;
    }

    @Override
    public int getTransactionLimit() {
        return 2;
    }

}
