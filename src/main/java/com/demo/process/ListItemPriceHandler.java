package com.demo.process;

import com.demo.dto.PropertyConstant;
import com.demo.exception.InvalidItemException;
import com.demo.model.ActionType;
import com.demo.service.ItemService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ListItemPriceHandler implements ActionState {

  @Autowired
  private ItemService itemService;

  @Override
  public void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap) {
    String price = (String) propertiesMap.get(PropertyConstant.PRICE.name());
      Integer integerPrice = Integer.parseInt(price);
      if(integerPrice<10 || integerPrice>1000){
        throw new InvalidItemException("Price "+price+" range should be in between 10 to 1000 USD");
      }
  }

  @Override
  public ActionType getActionType() {
    return ActionType.LIST;
  }

}
