package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ACTION_ITEM")
@Data
public class ActionDO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACTION_ID")
  private String actionId;

  @Column(name = "ACTION_NAME")
  private String action;

  @Column(name = "ACTION_DESCRIPTION")
  private String actionDescription;

  @Column(name = "ACTION_RULE")
  private String actionRule;

  @Column(name = "EXECUTION_ORDER")
  private int executionOrder;

  @Column(name = "ACTIVE")
  private boolean active;


}
