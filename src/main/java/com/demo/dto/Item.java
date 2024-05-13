package com.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

  private String itemId;

  private String type;

  private BigDecimal price;

  private LocalDate lastTransactionDate;

  private String name;

  private String status;

  private String userId;

  private String errorMessage;

}
