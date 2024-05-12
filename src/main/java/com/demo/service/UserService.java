package com.demo.service;

import com.demo.builder.UserBuilder;
import com.demo.dto.Item;
import com.demo.dto.User;
import com.demo.dto.UserInput;
import com.demo.model.Item_Status;
import com.demo.model.User_Account;
import com.demo.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserBuilder userBuilder;

  public User_Account findUserByUserId(Integer userId) {
    return userRepository.findUserByUserId(userId);
  }

  public List<Item> findAllItemsByUserId(Integer userId) {
    if (userId!=null) {
      List<Item> items = itemService.findListedItemsByUserIdAndStatus(userId,
          Item_Status.PURCHASED.name());
      if (CollectionUtils.isEmpty(items)) {
        return Arrays.asList(
            itemService.getItemListWithErrorMessage("User doesn't have any items purchased"));
      }
      return items;
    }
    return Arrays.asList(itemService.getItemListWithErrorMessage("UserId not found"));
  }

  public User fetchAllItemsByUser(Integer userId) {
    User user = new User();
    User_Account userAccount = findUserByUserId(userId);
    if (userAccount != null) {
      List<Item> items = findAllItemsByUserId(userId);
      user = userBuilder.buildUser(userAccount, items);
      return user;
    }
    return user;
  }

  public User_Account updateItem(User_Account userAccount) {
    return userRepository.save(userAccount);
  }

  public User_Account saveUser(UserInput user) {
    User_Account userAccount= new User_Account();
    BeanUtils.copyProperties(user,userAccount);
    return userRepository.save(userAccount);
  }

}
