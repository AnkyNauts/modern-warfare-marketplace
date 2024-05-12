package com.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInput {

  private String email;

  private BigDecimal pointsEarned;

  private LocalDate lastPurchaseDate;

  private String userName;

  private int earnMargin;

  private String errorMessage;

  @Override
  public String toString() {
    return "User{" +
        ", email='" + email + '\'' +
        ", pointsEarned=" + pointsEarned +
        ", lastPurchaseDate=" + lastPurchaseDate +
        ", userName=" + userName +
        ", earnMargin=" + earnMargin +
        '}';
  }
}
