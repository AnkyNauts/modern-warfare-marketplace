package com.demo.repository;

import com.demo.model.ItemTable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ItemRepository extends JpaRepository<ItemTable, Integer> {

    List<ItemTable> findByStatus(String status);

    List<ItemTable> findByUserIdAndStatus(Integer userId,String status);

    ItemTable findByItemId(Integer itemId);

}
