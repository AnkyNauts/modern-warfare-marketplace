package com.demo.dto;

import lombok.Data;

@Data
public class Player extends User {

  private int earnMargin;

  public void setEarnMargin(int earnMargin) {
    this.earnMargin = earnMargin;
  }

  public int getEarnMargin() {
    return earnMargin;
  }

}
