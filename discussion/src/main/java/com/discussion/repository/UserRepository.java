package com.discussion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.discussion.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findUserByEmail(String email);

}
