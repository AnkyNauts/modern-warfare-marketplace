package com.demo.service;

import com.demo.builder.ItemBuilder;
import com.demo.dto.Item;
import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.ItemTable;
import com.demo.process.ActionState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;

  @Autowired
  private ItemBuilder itemBuilder;

  @Autowired(required = false)
  private List<ActionState> handlerList;


  public Item listItem(Integer itemId, Integer price) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put(PropertyConstant.PRICE.name(), price);
    try {
      for (ActionState state : handlerList) {
        if (state.getActionType() == ActionType.LIST) {
          state.performAction(null, itemId, resultMap);
        }
      }
      ItemTable itemTable = (ItemTable) resultMap.get(PropertyConstant.ITEM_TABLE.name());
      return itemBuilder.buildItem(itemTable);
    } catch (InvalidItemException exception) {
      return itemService.getItemListWithErrorMessage(exception.getMessage());
    } catch (Exception exception) {
      return itemService.getItemListWithErrorMessage("Unable to list item " + itemId);
    }
  }

  public List<Item> findListedItemsByStatus(String status) {
    return itemService.findListedItemsByStatus(status);
  }
}
