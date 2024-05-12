package com.demo.process;

import com.demo.builder.ItemBuilder;
import com.demo.dto.PropertyConstant;
import com.demo.model.ActionType;
import com.demo.model.ItemTable;
import com.demo.model.ItemStatus;
import com.demo.model.UserAccount;
import com.demo.service.ItemService;
import com.demo.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class PurchaseItem implements ActionState {

  private Double PLAYER_COMMISSION = 0.9;

  @Autowired
  private UserService userService;

  @Autowired
  private ItemService itemService;

  @Autowired
  private ItemBuilder itemBuilder;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    ItemTable fetchedItem = (ItemTable) propertiesMap.get(PropertyConstant.ITEM_TABLE.name());

    UserAccount userAccount = (UserAccount) propertiesMap.get(
        PropertyConstant.USER_ACCOUNT.name());

    fetchedItem = getItemTable(fetchedItem, userAccount);

    //update user account
    getPlayerUserAccount(fetchedItem, userAccount);

    //update SUPAKI account
    userService.updateSupakiAccount(fetchedItem.getPrice());

    propertiesMap.put(PropertyConstant.ITEM_TABLE.name(), fetchedItem);

  }

  private void getPlayerUserAccount(ItemTable fetchedItem, UserAccount userAccount) {
    userAccount.setPointsEarned(userAccount.getPointsEarned().add(
        BigDecimal.valueOf(PLAYER_COMMISSION).multiply(fetchedItem.getPrice())));
    userAccount.setLastPurchaseDate(LocalDate.now());
    userService.updateItem(userAccount);
  }

  private ItemTable getItemTable(ItemTable fetchedItem, UserAccount userAccount) {
    fetchedItem.setUserId(userAccount.getUserId());
    fetchedItem.setStatus(ItemStatus.PURCHASED.name());
    fetchedItem.setLastTransactionDate(LocalDate.now());
    fetchedItem = itemService.updateItem(fetchedItem);
    return fetchedItem;
  }

  @Override
  public ActionType getActionType() {
    return ActionType.BUY;
  }
}
