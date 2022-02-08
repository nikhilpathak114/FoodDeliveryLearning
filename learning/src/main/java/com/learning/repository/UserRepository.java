package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Register;

@Repository
public interface UserRepository extends JpaRepository<Register, Integer> {
	
	Boolean existsByEmailAndId(String email, Integer id);
	Boolean existsByEmailAndPassword(String email,String Password);
	//Optional<Register> findById(Integer id);
	//String deleteById(Integer id);
	
}
