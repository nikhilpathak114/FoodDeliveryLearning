package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.EROLE;
import com.learning.dto.Login;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.LoginRepo;
import com.learning.service.LoginServ;

@Service
public class LoginServImpl implements LoginServ {
	@Autowired
	private LoginRepo loginRepository;

	@Override
	public Login addCredentials(Login login) {
		// TODO Auto-generated method stub
		Login login2 = loginRepository.save(login);
		return login2;
	}

	@Override
	public String deleteCredentials(String email) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Login> optional = loginRepository.findById(email);
		if (optional.isEmpty())
			throw new IdNotFoundException("Record not found");
		else {
			loginRepository.deleteById(email);
			return "Success";
		}
	}

	@Override
	public String changePassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeRole(String email, EROLE role) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<List<Login>> getAllLoginDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(loginRepository.findAll());
	}
}