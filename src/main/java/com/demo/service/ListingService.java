package com.demo.service;

import com.demo.builder.ItemBuilder;
import com.demo.dto.Item;
import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.model.Item_Table;
import com.demo.model.Item_Status;
import com.demo.process.ActionState;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

  @Value("${max.list.price.limit}")
  private int maximumLimit;

  @Value("${min.list.price.limit}")
  private int minLimit;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;

  @Autowired
  private ItemBuilder itemBuilder;

  @Autowired(required = false)
  private List<ActionState> handlerList;


  public Item listItem(String itemId, String price) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put(PropertyConstant.PRICE.name(), price);
    try {
      for (ActionState state : handlerList) {
        if (state.getActionType() == ActionType.LIST) {
          state.performAction(null, itemId, resultMap);
        }
      }
      Item_Table itemTable = (Item_Table) resultMap.get(PropertyConstant.ITEM_TABLE.name());
      return itemBuilder.buildItem(itemTable);
    } catch (InvalidItemException exception) {
      return itemService.getItemListWithErrorMessage(exception.getMessage());
    } catch (Exception exception) {
      return itemService.getItemListWithErrorMessage("Unable to list item " + itemId);
    }

    /*Item item = null;
    Item_Table updatedItem = null;
    if (!StringUtils.isEmpty(item_Id)) {

      Item_Table fetchedItem = itemService.findListedItemsByItemId(item_Id);
      if (fetchedItem == null) {
        item = itemService.getItemListWithErrorMessage("Could not find item " + item_Id);
        return item;
      }
      if (StringUtils.equalsIgnoreCase(fetchedItem.getStatus(), Item_Status.FOR_SALE.name())) {
        item = itemService.getItemListWithErrorMessage(
            "Item_Table " + item_Id + " is already listed");
        return item;
      }
      if (fetchedItem.getLastTransactionDate() != null && StringUtils.equalsIgnoreCase(
          fetchedItem.getLastTransactionDate().toString(), LocalDate.now().toString())) {
        item = itemService.getItemListWithErrorMessage(
            "Cannot list Item_Table " + item_Id + " before 24 hours");
        return item;
      }
      updatedItem = fetchedItem;
      updatedItem.setUserId(null);
      updatedItem.setStatus(Item_Status.FOR_SALE.name());
      updatedItem = itemService.updateItem(updatedItem);
      return itemBuilder.buildItem(updatedItem);

    } else {
      item.setStatus("ItemId cannot be blank");
    }
*/
    //return item;
  }

  public List<Item> findListedItemsByStatus(String status) {
    return itemService.findListedItemsByStatus(status);
  }
}
