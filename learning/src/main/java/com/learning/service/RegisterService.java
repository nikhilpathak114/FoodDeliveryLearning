package com.learning.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface RegisterService {
	
	//declaring all the methods for registration
	public Register addUser(Register register) throws SQLException, AlreadyExistsException;
	public boolean checkAuthentication(String email, String password);
	public Register getUserById(Long id) throws IdNotFoundException;
	public String deleteUserById(Long id) throws IdNotFoundException;
	public Optional<List<Register>> getAllUserDetails();
	Register updateUserById(Long id, Register register) throws IdNotFoundException;
	
}
