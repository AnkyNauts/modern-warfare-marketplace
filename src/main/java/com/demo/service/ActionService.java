package com.demo.service;

import com.demo.builder.ResponseBodyBuilder;
import com.demo.dto.Item;
import com.demo.dto.ResponseBody;
import com.demo.dto.User;
import com.demo.model.Item_Status;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

  @Autowired
  private PurchaseService purchaseService;


  @Autowired
  private ListingService listingService;

  @Autowired
  private ResponseBodyBuilder responseBodyBuilder;

  @Autowired
  private FetchService fetchService;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;


  public ResponseBody performPurchaseItem(String userId, String itemId) {
    Item item = purchaseService.purchaseItem(userId, itemId);
    return responseBodyBuilder.buildResponse(Arrays.asList(item));
  }

  public ResponseBody performListItem(String itemId,String price) {
    Item item = listingService.listItem(itemId,price);
    return responseBodyBuilder.buildResponse(Arrays.asList(item));
  }

  public ResponseBody performFetchItem(String userId) {
    User user = fetchService.fetchAllItemsByUser(userId);
    return responseBodyBuilder.buildResponse(user);
  }

  public ResponseBody fetchAllListedItem() {
    List<Item> item = listingService.findListedItemsByStatus(Item_Status.FOR_SALE.name());
    return responseBodyBuilder.buildResponse(item);
  }

  public ResponseBody fetchItemResponseWithErrorMessage(String message) {
    Item item = itemService.getItemListWithErrorMessage(message);
    return responseBodyBuilder.buildErrorResponse(item.getErrorMessage());
  }

  /*public ResponseBody fetchUserResponseWithErrorMessage(String message) {
    User user = userService.getUserWithErrorMessage(message);
    return responseBodyBuilder.buildResponse(user);
  }*/


}
