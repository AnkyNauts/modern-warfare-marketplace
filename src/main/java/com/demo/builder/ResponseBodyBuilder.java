package com.demo.builder;

import com.demo.dto.Item;
import com.demo.dto.ResponseBody;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ResponseBodyBuilder {

  public ResponseBody buildErrorResponse(String message) {
    return ResponseBody.builder().errorMessage(message).build();
  }

  public ResponseBody buildResponse(List<Item> itemList) {
    return ResponseBody.builder().item(itemList).build();
  }

}
