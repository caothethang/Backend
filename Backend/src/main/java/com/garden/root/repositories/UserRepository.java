package com.garden.root.repositories;

import com.garden.root.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    User findUserByUserNameAndPassword(String username,String password);
    
    User findUserByUserName(String username);
}
