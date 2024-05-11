package com.demo.repository;

import com.demo.model.ActionDO;
import com.demo.model.User_Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ActionRepository extends JpaRepository<ActionDO, String> {

  List<ActionDO> findByActionAndActiveTrueOrderByExecutionOrderAsc(String actionType);

}
