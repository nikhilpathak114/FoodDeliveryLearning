package com.learning.service;

import com.learning.dto.Login;

import java.util.List;
import java.util.Optional;

import com.learning.dto.EROLE;
import com.learning.exception.IdNotFoundException;

public interface LoginServ {
	
	public Login addCredentials(Login login);
	public String deleteCredentials(String email) throws IdNotFoundException;
	public String changePassword(String email,String password);
	public String changeRole(String email, EROLE role);
	public Optional<List<Login>> getAllLoginDetails();

}
