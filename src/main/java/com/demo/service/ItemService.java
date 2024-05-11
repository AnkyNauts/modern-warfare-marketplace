package com.demo.service;

import com.demo.builder.ItemBuilder;
import com.demo.dto.Item;
import com.demo.model.Item_Table;
import com.demo.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ItemBuilder itemBuilder;

  public List<Item> findListedItemsByStatus(String status) {
    List<Item_Table> itemTableList = itemRepository.findByStatus(status);
    return itemBuilder.buildItemList(itemTableList);
  }

  public Item_Table findListedItemsByItemId(String itemId) {
    return itemRepository.findByItemId(itemId);
  }

  public List<Item> findListedItemsByUserIdAndStatus(String userId, String status) {
    List<Item_Table> itemTableList = itemRepository.findByUserIdAndStatus(userId, status);
    return itemBuilder.buildItemList(itemTableList);
  }

  public Item getItemListWithErrorMessage(String message) {
    return getItem(message);
  }

  private Item getItem(String message) {
    Item item = new Item();
    item.setErrorMessage(message);
    return item;
  }

  public Item_Table updateItem(Item_Table item) {
    return itemRepository.save(item);
  }
}
