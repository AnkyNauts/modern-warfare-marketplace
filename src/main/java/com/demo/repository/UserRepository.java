package com.demo.repository;

import com.demo.model.User_Account;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserRepository extends JpaRepository<User_Account, String> {

  User_Account findUserByUserId(String userId);

}
