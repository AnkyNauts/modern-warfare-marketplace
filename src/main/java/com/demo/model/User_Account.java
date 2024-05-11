package com.demo.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
public class User_Account {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "EMAIL_ID")
  private String email;

  @Column(name = "POINTS_EARNED")
  private BigDecimal pointsEarned;

  @Column(name = "LAST_PURCHASE_DATE")
  private LocalDate lastPurchaseDate;

  @Column(name = "USER_NAME")
  private String userName;

/*  @OneToMany(mappedBy = "userAccount")
  private List<Item_Table> itemTableList;*/


  // for JPA only, no use
  public User_Account() {
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", email='" + email + '\'' +
        ", pointsEarned=" + pointsEarned +
        ", lastPurchaseDate=" + lastPurchaseDate +
        ", userName=" + userName +
        '}';
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getPointsEarned() {
    return pointsEarned;
  }

  public void setPointsEarned(BigDecimal pointsEarned) {
    this.pointsEarned = pointsEarned;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public BigDecimal getPrice() {
    return pointsEarned;
  }

  public void setPrice(BigDecimal pointsEarned) {
    this.pointsEarned = pointsEarned;
  }

  public LocalDate getLastPurchaseDate() {
    return lastPurchaseDate;
  }

  public void setLastPurchaseDate(LocalDate lastPurchaseDate) {
    this.lastPurchaseDate = lastPurchaseDate;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
