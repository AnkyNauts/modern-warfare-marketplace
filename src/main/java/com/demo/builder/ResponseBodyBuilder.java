package com.demo.builder;

import com.demo.dto.Item;
import com.demo.dto.ResponseBody;
import com.demo.dto.User;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ResponseBodyBuilder {

  public ResponseBody buildErrorResponse(String message) {
    return ResponseBody.builder().errorMessage(message).build();
  }

  public ResponseBody buildResponse(List<Item> itemList) {
    String message = null;
    if (!CollectionUtils.isEmpty(itemList)) {
      message = itemList.get(0).getErrorMessage();
      itemList=null;
    }
    return ResponseBody.builder().item(itemList).errorMessage(message).build();
  }

  public ResponseBody buildResponse(User user) {
    return ResponseBody.builder().user(user).build();
  }

}
