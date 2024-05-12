package com.demo.process;

import com.demo.builder.ItemBuilder;
import com.demo.dto.PropertyConstant;
import com.demo.model.ActionType;
import com.demo.model.Item_Status;
import com.demo.model.Item_Table;
import com.demo.model.User_Account;
import com.demo.service.ItemService;
import com.demo.service.UserService;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class PurchaseItem implements ActionState {

  @Autowired
  private UserService userService;

  @Autowired
  private ItemService itemService;

  @Autowired
  private ItemBuilder itemBuilder;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    Item_Table fetchedItem = (Item_Table) propertiesMap.get(PropertyConstant.ITEM_TABLE.name());

    User_Account userAccount = (User_Account) propertiesMap.get(
        PropertyConstant.USER_ACCOUNT.name());

    fetchedItem.setUserId(userAccount.getUserId());
    fetchedItem.setStatus(Item_Status.PURCHASED.name());
    fetchedItem.setLastTransactionDate(LocalDate.now());
    fetchedItem = itemService.updateItem(fetchedItem);

    userAccount.setLastPurchaseDate(LocalDate.now());
    userService.updateItem(userAccount);

    propertiesMap.put(PropertyConstant.ITEM_TABLE.name(), fetchedItem);

  }

  @Override
  public ActionType getActionType() {
    return ActionType.BUY;
  }
}
