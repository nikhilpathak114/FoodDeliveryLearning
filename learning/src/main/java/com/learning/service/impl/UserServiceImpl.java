package com.learning.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.UserRepository;
import com.learning.service.RegisterService;

@Service
public class UserServiceImpl implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addUser(Register register) throws SQLException, AlreadyExistsException {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmailAndId(register.getEmail(), register.getId())) {
			throw new AlreadyExistsException("This record already exist.");
		}
			Register register2 = userRepository.save(register);
			if(register2!=null) {
				return register2;
			}
		return null;
	}

	@Override
	public Register getUserById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException("it doesnt exist");
		}
		else {
			return optional.get();
		}
	}

	@Override
	@Transactional(rollbackFor = IdNotFoundException.class)
	public String deleteUserById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Register optional = this.getUserById(id);
			if(optional==null) {
				throw new IdNotFoundException("record not found");
			}
			else {
				userRepository.deleteById(id);
				return "success";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Register updateUserById(Integer id,Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException("Id not found Exception.");
		}else {
			register.setId(id);
			Register register2 = userRepository.save(register);
			return register2;
		}
		
		
	}

	@Override
	public Optional<List<Register>> getAllUserDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public boolean checkAuthentication(String email,String password) {
		// TODO Auto-generated method stub
		boolean check = userRepository.existsByEmailAndPassword(email, password);
		return check;
	}

}
