package com.demo.service;

import com.demo.model.ActionDO;
import com.demo.repository.ActionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionTableService {


  @Autowired
  private ActionRepository actionRepository;


  public List<ActionDO> fetchActionOnActionType(String actionType) {
    return actionRepository.findByActionAndActiveTrueOrderByExecutionOrderAsc(actionType);
  }

  public ActionDO fetchBuyActionOnActionRules(String actionType, String itemId, String userId) {
    Optional<ActionDO> actionDO = fetchActionOnActionType(actionType).stream()
        .filter(x -> checkBuyActionRule(x.getActionRule(), itemId, userId)).findFirst();
    if (actionDO.isPresent()) {
      return actionDO.get();
    }
    return null;
  }

  public ActionDO fetchSellActionOnActionRules(String actionType, String itemId, String userId) {
    Optional<ActionDO> actionDO = fetchActionOnActionType(actionType).stream()
        .filter(x -> checkSellActionRule(x.getActionRule(), itemId, userId)).findFirst();
    if (actionDO.isPresent()) {
      return actionDO.get();
    }
    return null;
  }

  private boolean checkBuyActionRule(String actionRule, String itemId, String userId) {
    if (actionRule == null) {
      return true;
    }

    Map<String, Object> context = new HashMap<>();
    context.put("itemId", itemId);
    context.put("userId", userId);
    VariableResolverFactory functionFactory = new MapVariableResolverFactory(context);
    return MVEL.evalToBoolean(actionRule, functionFactory);
  }

  private boolean checkSellActionRule(String actionRule, String itemId, String userId) {
    if (actionRule == null) {
      return true;
    }

    Map<String, Object> context = new HashMap<>();
    context.put("itemId", itemId);
    context.put("userId", userId);
    VariableResolverFactory functionFactory = new MapVariableResolverFactory(context);
    return MVEL.evalToBoolean(actionRule, functionFactory);
  }


}
