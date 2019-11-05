package com.poc.moneytransfer.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  @Column(name = "source_account")
  private Long sourceAccountNumber;
  @Column(name = "destination_account")
  private Long destinationAccountNumber;
  @Column(name = "ammount")
  private BigDecimal amount;



  public Long getSourceAccountNumber() {
    return sourceAccountNumber;
  }

  public void setSourceAccountNumber(Long sourceAccountNumber) {
    this.sourceAccountNumber = sourceAccountNumber;
  }

  public Long getDestinationAccountNumber() {
    return destinationAccountNumber;
  }

  public void setDestinationAccountNumber(Long destinationAccountNumber) {
    this.destinationAccountNumber = destinationAccountNumber;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "TransactionEntity [sourceAccountNumber=" + sourceAccountNumber + ", destinationAccountNumber=" + destinationAccountNumber +
        ", amount=" + amount +" ]";
  }

}
