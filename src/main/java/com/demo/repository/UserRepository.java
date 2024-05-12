package com.demo.repository;

import com.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserRepository extends JpaRepository<UserAccount, Integer> {

  UserAccount findUserByUserId(Integer userId);

}
