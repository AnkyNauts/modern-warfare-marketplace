package com.demo.service;

import com.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchService {

  @Autowired
  private UserService userService;

  public User fetchAllItemsByUser(Integer userId){
    return userService.fetchAllItemsByUser(userId);
  }

}
