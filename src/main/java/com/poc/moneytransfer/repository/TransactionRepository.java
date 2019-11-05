package com.poc.moneytransfer.repository;

import com.poc.moneytransfer.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.moneytransfer.entity.AccountEntity;
 
@Repository
public interface TransactionRepository
        extends JpaRepository<TransactionEntity, Long> {
 
}
