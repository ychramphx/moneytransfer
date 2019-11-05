package com.poc.moneytransfer.model;

import java.math.BigDecimal;

public class Transaction {

  private Long sourceAccountNumber;
  private Long destinationAccountNumber;
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


}
