package com.demo.repository;

import com.demo.model.Item_Table;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ItemRepository extends JpaRepository<Item_Table, Integer> {

    List<Item_Table> findByStatus(String status);

    List<Item_Table> findByUserIdAndStatus(Integer userId,String status);

    Item_Table findByItemId(Integer itemId);

}
