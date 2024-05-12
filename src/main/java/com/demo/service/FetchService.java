package com.demo.service;

import com.demo.dto.Item;
import com.demo.model.UserAccount;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchService {

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;

  public List<Item> fetchAllItemsByUser(Integer userId){
    UserAccount userAccount = userService.findUserByUserId(userId);
    if(userAccount==null){
      return Arrays.asList(itemService.getItemListWithErrorMessage("User "+userId+" does not exist"));
    }
    return itemService.findAllItemsByUserId(userId);
  }

}
