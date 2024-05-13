package com.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private String userId;

  private String email;

  private BigDecimal pointsEarned;

  private LocalDate lastPurchaseDate;

  private String userName;

  private int earnMargin;

  private List<Item> itemList;

  private String errorMessage;

  @Override
  public String toString() {
    return "User{" +
        "id=" + userId +
        ", email='" + email + '\'' +
        ", pointsEarned=" + pointsEarned +
        ", lastPurchaseDate=" + lastPurchaseDate +
        ", userName=" + userName +
        ", earnMargin=" + earnMargin +
        ", itemList=" + itemList +
        '}';
  }
}
