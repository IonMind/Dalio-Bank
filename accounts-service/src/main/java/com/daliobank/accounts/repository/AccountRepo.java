package com.daliobank.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daliobank.accounts.model.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    
}
