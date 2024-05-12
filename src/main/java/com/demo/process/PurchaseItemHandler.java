package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.ItemStatus;
import com.demo.model.ItemTable;
import com.demo.service.ItemService;
import com.demo.service.UserService;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class PurchaseItemHandler implements ActionState {

  @Autowired
  private UserService userService;

  @Autowired
  private ItemService itemService;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    ItemTable fetchedItem = itemService.findListedItemsByItemId(itemId);

    if (fetchedItem == null) {
      throw new InvalidItemException("Could not find item " + itemId);
    }
    if (StringUtils.equalsIgnoreCase(fetchedItem.getStatus(), ItemStatus.PURCHASED.name())) {
      throw new InvalidItemException("Item " + itemId + " is already purchased");
    }

    propertiesMap.put(PropertyConstant.ITEM_TABLE.name(), fetchedItem);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.BUY;
  }
}
