package com.demo.controller;

import com.demo.builder.UserBuilder;
import com.demo.dto.UserInput;
import com.demo.model.User_Account;
import com.demo.service.ItemService;
import com.demo.service.PurchaseService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
public class UserController {

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;

  @Autowired
  private PurchaseService purchaseService;

  @Autowired
  UserBuilder userBuilder;

  @PostMapping()
  public User_Account addUser(UserInput user) {
    try {
      return userService.saveUser(user);
    } catch (Exception exception) {
      System.out.println("error " + exception);
    }
    return null;
  }


}
