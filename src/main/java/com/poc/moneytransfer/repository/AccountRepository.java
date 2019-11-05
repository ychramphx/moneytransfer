package com.poc.moneytransfer.repository;

import com.poc.moneytransfer.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository
    extends JpaRepository<AccountEntity, Long> {

}
