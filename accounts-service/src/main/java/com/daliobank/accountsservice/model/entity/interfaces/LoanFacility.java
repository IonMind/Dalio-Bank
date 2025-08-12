package com.daliobank.accountsservice.model.entity.interfaces;

public interface LoanFacility {
    default Boolean LoanFacilityAvailable() {
        return true;
    };

}
