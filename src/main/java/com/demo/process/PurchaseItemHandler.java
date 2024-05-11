package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.Item_Status;
import com.demo.model.Item_Table;
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
  public void performAction(String userId, String itemId, Map<String, Object> propertiesMap) {
    Item_Table fetchedItem = itemService.findListedItemsByItemId(itemId);

    if (fetchedItem == null) {
      throw new InvalidItemException("Could not find item " + itemId);
    }
    if (StringUtils.equalsIgnoreCase(fetchedItem.getStatus(), Item_Status.PURCHASED.name())) {
      throw new InvalidItemException("Item_Table " + itemId + " is already purchased");
    }

    propertiesMap.put(PropertyConstant.ITEM_TABLE.name(), fetchedItem);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.BUY;
  }
}
