package com.poc.moneytransfer.service;

import static org.hibernate.internal.util.StringHelper.isEmpty;

import com.poc.moneytransfer.entity.AccountEntity;
import com.poc.moneytransfer.entity.TransactionEntity;
import com.poc.moneytransfer.exception.AccountNotFoundException;
import com.poc.moneytransfer.model.Account;
import com.poc.moneytransfer.model.Transaction;
import com.poc.moneytransfer.repository.AccountRepository;
import com.poc.moneytransfer.repository.TransactionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

  @Autowired
  TransactionRepository repository;

  @Autowired
  AccountRepository accountRepository;

  public List<AccountEntity> getAllAccounts() {
    List<AccountEntity> accountList = accountRepository.findAll();

    if (accountList.size() > 0) {
      return accountList;
    } else {
      return new ArrayList<AccountEntity>();
    }
  }

  public AccountEntity getAccountById(Long id) throws AccountNotFoundException {
    Optional<AccountEntity> account = accountRepository.findById(id);

    if (account.isPresent()) {
      return account.get();
    } else {
      throw new AccountNotFoundException("No account record exist for given account number  "+id);
    }
  }

  public String createOrUpdateAccount(Account account) throws AccountNotFoundException {
    Optional<AccountEntity> userAccount = accountRepository.findById(account.getAccountNumber());
    AccountEntity accountEntity = null;
    if (userAccount.isPresent()) {
      accountEntity = userAccount.get();
      accountEntity.setAccountNumber(accountEntity.getAccountNumber());
      if (!isEmpty(account.getEmail())) {
        accountEntity.setEmail(account.getEmail());
      }
      if (!isEmpty(account.getName())) {
        accountEntity.setName(account.getName());
      }
      if (null != (account.getBalance())) {
        accountEntity.setBalance(accountEntity.getBalance());
      }
    } else {
      accountEntity = new AccountEntity();
      accountEntity.setAccountNumber(account.getAccountNumber());
      accountEntity.setBalance(account.getBalance());
      accountEntity.setEmail(account.getEmail());
      accountEntity.setName(account.getName());
    }
    accountRepository.save(accountEntity);
    return "Account with account number : "+account.getAccountNumber()+" is successfully created for customer "+account.getName();
  }

  public void deleteAccountById(Long id) throws AccountNotFoundException {
    Optional<AccountEntity> account = accountRepository.findById(id);

    if (account.isPresent()) {
      accountRepository.deleteById(id);
    } else {
      throw new AccountNotFoundException("No account record exist for given account Number "+id);
    }
  }

  @Transactional
  public String updateTransaction(Transaction transaction) throws AccountNotFoundException {
    TransactionEntity transactionEntity = null;
    Optional<AccountEntity> sourceAccount = accountRepository.findById(transaction.getSourceAccountNumber());

    if(!sourceAccount.isPresent()) {
      throw new AccountNotFoundException("No source account  exists with given account number " +transaction.getSourceAccountNumber());
    }

    Optional<AccountEntity> destinationAccount = accountRepository.findById(transaction.getDestinationAccountNumber());
    if(!destinationAccount.isPresent()) {
      throw new AccountNotFoundException("No destination account exists with given account number "+transaction.getDestinationAccountNumber());
    }
    BigDecimal availableBalance = sourceAccount.get().getBalance();
    if(availableBalance.compareTo(BigDecimal.ZERO) > 0  && availableBalance.compareTo(transaction.getAmount()) >= 0) {
      transactionEntity = new TransactionEntity();
      transactionEntity.setAmount(transaction.getAmount());
      transactionEntity.setSourceAccountNumber(transaction.getSourceAccountNumber());
      transactionEntity.setDestinationAccountNumber(transaction.getDestinationAccountNumber());
      transactionEntity = repository.save(transactionEntity);

      sourceAccount.get().setBalance(availableBalance.subtract(transaction.getAmount()));
      accountRepository.save(sourceAccount.get());

      destinationAccount.get().setBalance(destinationAccount.get().getBalance().add(transaction.getAmount()));
      accountRepository.save(destinationAccount.get());
    }else{
      throw new RuntimeException("Source Account has Insufficient funds !");
    }
    return "Amount "+transaction.getAmount()+" successfully transferred from "+transaction.getSourceAccountNumber()+"  to  "+transaction.getDestinationAccountNumber() ;
  }
}