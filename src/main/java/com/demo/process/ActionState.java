package com.demo.process;

import com.demo.model.ActionType;
import java.util.Map;

public interface ActionState {

  void performAction(String userId, String itemId, Map<String, Object> propertiesMap);

  ActionType getActionType();

}
