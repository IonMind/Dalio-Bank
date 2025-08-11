package com.daliobank.accounts.model.entity;

import com.daliobank.accounts.model.entity.interfaces.CurrentAccountInterface;
import com.daliobank.accounts.model.entity.interfaces.LoanFacility;
import com.daliobank.accounts.model.enums.AccountType;

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

    private final AccountType accountType = AccountType.CURRENT;
    private final int transactionLimit = 10;
    private String companyName;

    public CurrentAccount(String accountHolderName, Double balance, String companyName) {
        super(accountHolderName, balance, 10000.0);
        if (balance < minimumBalance) {
            throw new IllegalArgumentException("Initial balance must be at least " + minimumBalance);
        }
        this.companyName = companyName;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return super.toString() + ", CurrentAccount{" +
                ", accountType='" + accountType.toString() + '\'' +
                ", transactionLimit=" + transactionLimit +
                '}';
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

}
