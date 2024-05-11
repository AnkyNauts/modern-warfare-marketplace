package com.demo.dto;

import com.demo.dto.User;

public class Player extends User {

  private int earnMargin;

  public void setEarnMargin(int earnMargin) {
    this.earnMargin = earnMargin;
  }

  public int getEarnMargin() {
    return earnMargin;
  }

}
