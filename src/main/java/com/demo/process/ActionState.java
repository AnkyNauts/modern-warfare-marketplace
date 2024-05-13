package com.demo.process;

import com.demo.model.ActionType;
import java.util.Map;

public interface ActionState {

  void performAction(Integer userId, Integer itemId, Map<String, Object> propertiesMap);

  ActionType getActionType();

}
