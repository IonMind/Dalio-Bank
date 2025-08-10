package com.daliobank.accounts.model.entity;

import com.daliobank.accounts.model.entity.interfaces.NRIInterface;
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
@DiscriminatorValue("NRI Account")
public class NRIAccount extends Account implements NRIInterface {

    private final AccountType accountType = AccountType.NRI;
    private final int transactionLimit = 2;
    private String residingCountry;

    public NRIAccount(Long accountNumber, String accountHolderName, Double balance, String residingCountry) {
        super(accountNumber, accountHolderName, balance, 5000.0);
        if (balance < minimumBalance) {
            throw new IllegalArgumentException("Initial balance must be at least " + minimumBalance);
        }
        this.residingCountry = residingCountry;
    }

    @Override
    public String getAccountType() {
        return accountType.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ", NRIAccount{" +
                ", accountType='" + accountType.toString() + '\'' +
                ", transactionLimit=" + transactionLimit +
                '}';
    }

    @Override
    public String getResidingCountry() {
        return residingCountry;
    }

}
