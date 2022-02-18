package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.EROLE;
import com.learning.dto.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRoleName(EROLE roleName);
}
