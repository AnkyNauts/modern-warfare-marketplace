package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.UserAccount;
import com.demo.service.ItemService;
import com.demo.service.UserService;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UserLastPurchaseDateHandler implements ActionState {

  @Autowired
  private UserService userService;

  @Autowired
  private ItemService itemService;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    UserAccount userAccount = (UserAccount) propertiesMap.get(PropertyConstant.USER_ACCOUNT.name());
    if (userAccount.getLastPurchaseDate().equals(LocalDate.now())) {
      throw new InvalidItemException("Limit for this month is crossed, Cannot buy item " + itemId);

    }
  }

  @Override
  public ActionType getActionType() {
    return ActionType.BUY;
  }
}
