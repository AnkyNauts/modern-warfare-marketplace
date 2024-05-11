package com.demo.service;

import com.demo.builder.ItemBuilder;
import com.demo.builder.UserBuilder;
import com.demo.dto.Item;
import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.Item_Table;
import com.demo.process.ActionState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  @Value("${max.list.price.limit}")
  private int maximumLimit;

  @Value("${min.list.price.limit}")
  private int minLimit;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserBuilder userBuilder;

  @Autowired
  private ItemBuilder itemBuilder;

  @Autowired(required = false)
  private List<ActionState> handlerList;

  public Item purchaseItem(String userId, String itemId) {
    Map<String, Object> resultMap = new HashMap<>();
    try {
      for (ActionState state : handlerList) {
        if (state.getActionType() == ActionType.BUY) {
          state.performAction(userId, itemId, resultMap);
        }
      }
      Item_Table itemTable = (Item_Table) resultMap.get(PropertyConstant.ITEM_TABLE.name());
      return itemBuilder.buildItem(itemTable);
    } catch (InvalidItemException exception) {
      return itemService.getItemListWithErrorMessage(exception.getMessage());
    } catch (Exception exception) {
      return itemService.getItemListWithErrorMessage("Unable to purchase item " + itemId);
    }
   /* Item purchasedItem = null;
    User_Account userAccount = null;
    Item_Table itemTable = null;
    if (!StringUtils.isEmpty(userId) || !StringUtils.isEmpty(itemId)) {
      userAccount = userService.findUserByUserId(userId);

      Item_Table fetchedItem = itemService.findListedItemsByItemId(itemId);
      if (userAccount == null) {
        purchasedItem = itemService.getItemListWithErrorMessage("Could not find user " + userId);
        return purchasedItem;
      }
      if (fetchedItem == null) {
        purchasedItem = itemService.getItemListWithErrorMessage("Could not find item " + itemId);
        return purchasedItem;
      }
      if (StringUtils.equalsIgnoreCase(fetchedItem.getStatus(), Item_Status.PURCHASED.name())) {
        purchasedItem = itemService.getItemListWithErrorMessage(
            "Item_Table " + itemId + " is already purchased");
        return purchasedItem;
      }
      if (userAccount.getLastPurchaseDate().equals(LocalDate.now())) {
        purchasedItem = itemService.getItemListWithErrorMessage(
            "Limit for this month is crossed, Cannot buy item " + itemId);
        return purchasedItem;
      }

      itemTable = fetchedItem;
      itemTable.setUserId(userId);
      itemTable.setStatus(Item_Status.PURCHASED.name());
      itemTable = itemService.updateItem(itemTable);

      userAccount.setLastPurchaseDate(LocalDate.now());
      userService.updateItem(userAccount);

    } else {
      itemTable.setStatus("ItemId / UserId cannot be blank");
    }

    return itemBuilder.buildItem(itemTable);*/
  }
}
