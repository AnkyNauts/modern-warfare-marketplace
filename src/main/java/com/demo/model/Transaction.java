package com.demo.model;


import java.math.BigDecimal;
import java.time.LocalDate;


public class Transaction {
  private Long id;
  private String type;
  private BigDecimal price;
  private LocalDate lastTransactionDate;
  private String name;
  private String status;
  private int quantity;

  // for JPA only, no use
  public Transaction() {
  }

  @Override
  public String toString() {
    return "ItemTable{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", price=" + price +
        ", lastTransactionDate=" + lastTransactionDate +
        ", name=" + name +
        ", status=" + status +
        ", quantity=" + quantity +
        '}';
  }
}
