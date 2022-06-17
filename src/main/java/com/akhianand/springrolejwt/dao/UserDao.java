package com.akhianand.springrolejwt.dao;

import com.akhianand.springrolejwt.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
