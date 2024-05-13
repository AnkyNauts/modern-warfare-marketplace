package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.ItemTable;
import com.demo.service.ItemService;
import java.time.LocalDate;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ListItemLastUpdateDateHandler implements ActionState {

  @Autowired
  private ItemService itemService;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    ItemTable fetchedItem = (ItemTable) propertiesMap.get(PropertyConstant.ITEM_TABLE.name());

    if (fetchedItem.getLastTransactionDate() != null && StringUtils.equalsIgnoreCase(
        fetchedItem.getLastTransactionDate().toString(), LocalDate.now().toString())) {
      throw new InvalidItemException(
          "Cannot list item " + itemId + " before 24 hours");
    }
  }

  @Override
  public ActionType getActionType() {
    return ActionType.LIST;
  }

}
