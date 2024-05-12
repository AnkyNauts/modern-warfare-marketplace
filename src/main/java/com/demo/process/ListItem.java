package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.model.ActionType;
import com.demo.model.Item_Status;
import com.demo.model.Item_Table;
import com.demo.service.ItemService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ListItem implements ActionState {

  @Autowired
  private ItemService itemService;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    Item_Table itemTable = (Item_Table) propertiesMap.get(PropertyConstant.ITEM_TABLE.name());

    itemTable.setUserId(null);
    itemTable.setStatus(Item_Status.FOR_SALE.name());
    itemTable = itemService.updateItem(itemTable);

    propertiesMap.put(PropertyConstant.ITEM_TABLE.name(), itemTable);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.LIST;
  }

}
