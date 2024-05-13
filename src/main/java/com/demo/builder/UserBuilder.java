package com.demo.builder;

import com.demo.dto.Item;
import com.demo.dto.User;
import com.demo.model.UserAccount;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

  public User buildUser(UserAccount userAccount, List<Item> itemList) {
    User user = new User();
    BeanUtils.copyProperties(userAccount, user);
    user.setItemList(itemList);
    return user;
  }

  public User buildUserWithErrorMessage(String message) {
    User user = new User();
   user.setErrorMessage(message);
    return user;
  }

}
