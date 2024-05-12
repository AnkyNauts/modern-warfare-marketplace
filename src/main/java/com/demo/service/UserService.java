package com.demo.service;

import com.demo.dto.UserInput;
import com.demo.model.UserAccount;
import com.demo.repository.UserRepository;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  private Double SUPAKI_COMMISSION = 0.1;

  public UserAccount findUserByUserId(Integer userId) {
    return userRepository.findUserByUserId(userId);
  }

  public UserAccount updateItem(UserAccount userAccount) {
    return userRepository.save(userAccount);
  }

  public UserAccount saveUser(UserInput user) {
    UserAccount userAccount = new UserAccount();
    BeanUtils.copyProperties(user, userAccount);
    //userAccount.setPointsEarned(new BigDecimal(10));
    return userRepository.save(userAccount);
  }

  public UserAccount updateSupakiAccount(BigDecimal earnedPoints) {
    UserAccount userAccount = findUserByUserId(1);
    userAccount.setPointsEarned(userAccount.getPointsEarned().add(earnedPoints.multiply(
        BigDecimal.valueOf(SUPAKI_COMMISSION))));
    return userRepository.save(userAccount);
  }

}
