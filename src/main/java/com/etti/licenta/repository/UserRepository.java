package com.akhianand.springrolejwt.repository;

import com.akhianand.springrolejwt.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	UserEntity findByUsername(String username);

	@Query("select u from UserEntity u")
	List<UserEntity> getAll();
}
