package com.demo.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ITEMS")
public class Item_Table {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ITEM_ID")
  private Integer itemId;

  @Column(name = "ITEM_TYPE")
  private String type;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "LAST_TRANSACTION_DATE")
  private LocalDate lastTransactionDate;

  @Column(name = "ITEM_NAME")
  private String name;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "USER_ID")
  private Integer userId;

 /* @ManyToOne
  @JoinColumn(name = "user_id",nullable = false,referencedColumnName="user_id")
  private User_Account userAccount;
*/
  @Override
  public String toString() {
    return "Item_Table{" +
        "itemId=" + itemId +
        ", type='" + type + '\'' +
        ", price=" + price +
        ", lastTransactionDate=" + lastTransactionDate +
        ", name=" + name +
        ", status=" + status +
        ", user_id=" + userId +
        '}';
  }

}
