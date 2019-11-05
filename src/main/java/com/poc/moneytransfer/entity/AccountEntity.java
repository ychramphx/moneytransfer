package com.poc.moneytransfer.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class AccountEntity {

  @Id
  private Long accountNumber;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "name")
  private String name;

  @Column(name = "email", nullable = false, length = 200)
  private String email;

  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public String toString() {
    return "AccountEntity [id=" + accountNumber + ", balance=" + balance +
        ", name=" + name + ", email=" + email + "]";
  }
}