package com.demo.exception;

import javax.validation.ValidationException;

public class InvalidItemException extends ValidationException {

  public InvalidItemException(String message) {
    super(message);
  }
}
