package com.demo.service;

import com.demo.builder.ResponseBodyBuilder;
import com.demo.dto.Item;
import com.demo.dto.ResponseBody;
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

  public ResponseBody performPurchaseItem(Integer userId, Integer itemId) {
    Item item = purchaseService.purchaseItem(userId, itemId);
    return responseBodyBuilder.buildResponse(Arrays.asList(item));
  }

  public ResponseBody performListItem(Integer itemId,Integer price) {
    Item item = listingService.listItem(itemId,price);
    return responseBodyBuilder.buildResponse(Arrays.asList(item));
  }

  public ResponseBody performFetchItem(Integer userId) {
    List<Item> items = fetchService.fetchAllItemsByUser(userId);
    return responseBodyBuilder.buildResponse(items);
  }

  public ResponseBody fetchItemResponseWithErrorMessage(String message) {
    return responseBodyBuilder.buildErrorResponse(message);
  }

}
