package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.User_Account;
import com.demo.service.UserService;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PurchaseUserAccountHandler implements ActionState {

  @Autowired
  private UserService userService;

  @Override
  public void performAction(String userId, String itemId, Map<String, Object> propertiesMap) {
    if (!StringUtils.isEmpty(userId) || !StringUtils.isEmpty(itemId)) {
      User_Account userAccount = userService.findUserByUserId(userId);
      if (userAccount == null) {
        throw new InvalidItemException("Could not find user " + userId);
      }
      propertiesMap.put(PropertyConstant.USER_ACCOUNT.name(), userAccount);
    } else {
      throw new InvalidItemException("ItemId / UserId cannot be blank");
    }
  }

  @Override
  public ActionType getActionType() {
    return ActionType.BUY;
  }

}
