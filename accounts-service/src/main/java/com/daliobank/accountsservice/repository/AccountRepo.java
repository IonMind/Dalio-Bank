package com.daliobank.accountsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daliobank.accountsservice.model.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    
}
