package com.demo.builder;

import com.demo.dto.Item;
import com.demo.model.Item_Table;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ItemBuilder {

  public List<Item> buildItemList(List<Item_Table> itemTableList) {
    List<Item> itemList = new ArrayList<>();
    for (Item_Table itemTable :
        itemTableList) {
      Item item = new Item();
      BeanUtils.copyProperties(itemTable, item);
      itemList.add(item);
    }
    return itemList;
  }

  public Item buildItem(Item_Table itemTable) {
    Item item = new Item();
    BeanUtils.copyProperties(itemTable, item);
    return item;
  }

}
