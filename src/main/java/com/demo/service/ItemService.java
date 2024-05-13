package com.demo.service;

import com.demo.builder.ItemBuilder;
import com.demo.dto.Item;
import com.demo.dto.ItemInput;
import com.demo.model.ItemTable;
import com.demo.model.ItemStatus;
import com.demo.repository.ItemRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ItemBuilder itemBuilder;

  public List<Item> findListedItemsByStatus(String status) {
    List<ItemTable> itemTableList = itemRepository.findByStatus(status);
    return itemBuilder.buildItemList(itemTableList);
  }

  public ItemTable findListedItemsByItemId(Integer itemId) {
    return itemRepository.findByItemId(itemId);
  }

  public List<Item> findListedItemsByUserIdAndStatus(Integer userId, String status) {
    List<ItemTable> itemTableList = itemRepository.findByUserIdAndStatus(userId, status);
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

  public ItemTable updateItem(ItemTable item) {
    return itemRepository.save(item);
  }

  public ItemTable saveItem(ItemInput itemInput) {
    ItemTable itemTable = new ItemTable();
    BeanUtils.copyProperties(itemInput, itemTable);
    itemTable.setStatus(ItemStatus.FOR_SALE.name());
    return itemRepository.save(itemTable);
  }

  public List<Item> findAllItemsByUserId(Integer userId) {
    if (userId!=null) {
      List<Item> items = findListedItemsByUserIdAndStatus(userId,
          ItemStatus.PURCHASED.name());
      if (CollectionUtils.isEmpty(items)) {
        return Arrays.asList(
            getItemListWithErrorMessage("User doesn't have any items purchased"));
      }
      return items;
    }
    return Arrays.asList(getItemListWithErrorMessage("UserId not found"));
  }
}
