package com.demo.controller;

import com.demo.dto.UserInput;
import com.demo.model.UserAccount;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping()
  public UserAccount addUser(UserInput user) {
    try {
      return userService.saveUser(user);
    } catch (Exception exception) {
      System.out.println("error " + exception);
    }
    return null;
  }


}
