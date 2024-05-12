package com.demo.builder;

import com.demo.dto.Item;
import com.demo.dto.User;
import com.demo.model.User_Account;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

  public User buildUser(User_Account userAccount, List<Item> itemList) {
    User user = new User();
    BeanUtils.copyProperties(userAccount, user);
    user.setItemList(itemList);
    return user;
  }

}
