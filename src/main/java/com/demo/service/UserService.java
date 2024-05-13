package com.demo.service;

import com.demo.dto.UserInput;
import com.demo.model.UserAccount;
import com.demo.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  private Integer SUPAKI_ACCOUNT_ID = 1;


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
    UserAccount userAccount = findUserByUserId(SUPAKI_ACCOUNT_ID);
    userAccount.setPointsEarned(userAccount.getPointsEarned().add(earnedPoints));
    return userRepository.save(userAccount);
  }

  public UserAccount getPlayerUserAccount(BigDecimal earnedPoints, UserAccount userAccount) {
    userAccount.setPointsEarned(getPointsEarned(userAccount).add(earnedPoints));
    userAccount.setLastPurchaseDate(LocalDate.now());
    return updateItem(userAccount);
  }

  private BigDecimal getPointsEarned(UserAccount userAccount) {
    if (userAccount.getPointsEarned() != null) {
      return userAccount.getPointsEarned();
    } else {
      return BigDecimal.ZERO;
    }
  }

}
