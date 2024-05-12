package com.demo.controller;

import com.demo.dto.Item;
import com.demo.dto.ResponseBody;
import com.demo.model.Item_Status;
import com.demo.service.ActionService;
import com.demo.service.ItemService;
import com.demo.service.ListingService;
import com.demo.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/items")
public class ItemController {

  @Autowired
  private ActionService actionService;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserService userService;

  @Autowired
  ListingService listingService;

  @GetMapping("/list")
  public ResponseEntity<List<Item>> fetchAllItemsListed() {
    try {
      return new ResponseEntity<>(
          listingService.findListedItemsByStatus(Item_Status.FOR_SALE.name()), HttpStatus.OK);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          Arrays.asList(
              itemService.getItemListWithErrorMessage("No item found to be listed by user")),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // list a item
  @PostMapping("/list/{itemId}/{price}")
  public ResponseEntity<ResponseBody> listAnItem(@PathVariable Integer itemId,@PathVariable Integer price) {
    try {
      return new ResponseEntity<>(actionService.performListItem(itemId,price), HttpStatus.OK);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          actionService.fetchItemResponseWithErrorMessage("Could not list item " + itemId),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/{user_id}")
  public ResponseEntity<ResponseBody> fetchAllItemsByUser(@PathVariable Integer user_id) {
    try {
      return new ResponseEntity<>(
          actionService.performFetchItem(user_id),
          HttpStatus.OK);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          actionService.fetchItemResponseWithErrorMessage(
              "Unable to fetch details for userId " + user_id),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/purchase/{user_id}/{item_id}")
  public ResponseEntity<ResponseBody> purchaseItem(@PathVariable("user_id") Integer user_id,
      @PathVariable("item_id") Integer item_id) {
    try {
      return new ResponseEntity<>(
          actionService.performPurchaseItem(user_id, item_id),
          HttpStatus.OK);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          actionService.fetchItemResponseWithErrorMessage(
              "Unable to purchase item for userId " + user_id),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
