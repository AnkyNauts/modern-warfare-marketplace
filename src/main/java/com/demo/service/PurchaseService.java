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
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

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

  public Item purchaseItem(Integer userId, Integer itemId) {
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
  }
}
