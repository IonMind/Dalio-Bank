package com.daliobank.accounts.model.entity.interfaces;

public interface LoanFacility {
    default Boolean LoanFacilityAvailable() {
        return true;
    };

}
