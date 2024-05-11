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
    return "Item_Table{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", price=" + price +
        ", lastTransactionDate=" + lastTransactionDate +
        ", name=" + name +
        ", status=" + status +
        ", quantity=" + quantity +
        '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public LocalDate getLastTransactionDate() {
    return lastTransactionDate;
  }

  public void setLastTransactionDate(LocalDate lastTransactionDate) {
    this.lastTransactionDate = lastTransactionDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
