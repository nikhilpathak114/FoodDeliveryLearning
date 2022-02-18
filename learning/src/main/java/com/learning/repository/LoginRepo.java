package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Login;

@Repository
public interface LoginRepo extends JpaRepository<Login, String> {

	Boolean existsByUsername(String username);
}
