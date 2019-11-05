package com.poc.moneytransfer.controller;

import com.poc.moneytransfer.entity.AccountEntity;
import com.poc.moneytransfer.exception.AccountNotFoundException;
import com.poc.moneytransfer.model.Account;
import com.poc.moneytransfer.model.Transaction;
import com.poc.moneytransfer.service.TransactionService;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    @Autowired
    TransactionService service;
 
    @GetMapping
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        List<AccountEntity> list = service.getAllAccounts();
 
        return new ResponseEntity<List<AccountEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{number}")
    public ResponseEntity<AccountEntity> getAccountByNumber(@PathVariable("number") Long number)
                                                    throws AccountNotFoundException {
        AccountEntity entity = service.getAccountById(number);
 
        return new ResponseEntity<AccountEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping("/createAccount")
    public ResponseEntity<String> createOrUpdateAccount(@Valid @RequestBody Account account)
                                                    throws AccountNotFoundException {
        String  status = service.createOrUpdateAccount(account);
        return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<String> createOrUpdateTransaction(@Valid @RequestBody Transaction transaction)
        throws AccountNotFoundException {
        String status = service.updateTransaction(transaction);
        return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{number}")
    public HttpStatus deleteAccount(@PathVariable("number") Long number)
                                                    throws AccountNotFoundException {
        service.deleteAccountById(number);
        return HttpStatus.OK;
    }
 
}