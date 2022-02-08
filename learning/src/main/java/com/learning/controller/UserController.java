package com.learning.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.EFOOD;
import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidFoodType;
import com.learning.service.FoodService;
import com.learning.service.RegisterService;

@RestController
public class UserController {

	@Autowired
	RegisterService registerService;
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody Register register) throws SQLException, AlreadyExistsException{
		Register result = registerService.addUser(register);
		return ResponseEntity.status(201).body(result);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody HashMap<String, String> map) {
			boolean res = registerService.checkAuthentication(map.get("email"),map.get("password"));
			if(res) { 
				
				return ResponseEntity.status(201).body("Success");
			}
			else return ResponseEntity.badRequest().body("failed");	
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) throws IdNotFoundException{
		Register register = registerService.getUserById(id);
		return ResponseEntity.ok(register);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUserDetails() {
		Optional<List<Register>> optional = registerService.getAllUserDetails();
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message","no record found" );
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable("id") Integer id,@RequestBody Register register) throws IdNotFoundException, SQLException, AlreadyExistsException{
		Register register2;
		try {
			register2 = registerService.updateUserById(id,register);
			return ResponseEntity.status(201).body(register2);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Id not Found.");
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer id) throws IdNotFoundException {
		String register = registerService.deleteUserById(id);
		return ResponseEntity.ok(register);
	}
	
	//=====================================food======================================//
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/food")
	public ResponseEntity<?> addUser(@RequestBody Food food) throws SQLException, AlreadyExistsException{
		Food result = foodService.addFood(food);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/food/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Integer id) throws IdNotFoundException{
		Food food2 = foodService.getFoodById(id);
		return ResponseEntity.ok(food2);
	}
	
	@GetMapping("/food/")
	public ResponseEntity<?> getAllFoodDetails() {
		Optional<List<Food>> optional = foodService.getAllFoodDetails();
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message","no record found" );
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@GetMapping("/food/type/{foodType}")
	public ResponseEntity<?> getFoodByFoodType(@PathVariable("foodType") EFOOD foodType) throws InvalidFoodType{
		Food food2 = foodService.getFoodByFoodType(foodType);
		return ResponseEntity.ok(food2);
	}
	
	
	
	@PutMapping("/food/{id}")
	public ResponseEntity<?> updateFoodById(@PathVariable("id") Integer foodId,@RequestBody Food food) throws IdNotFoundException, SQLException, AlreadyExistsException{
		Food food2;
		try {
			food2 = foodService.updateFoodById(foodId,food);
			return ResponseEntity.status(201).body(food2);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Id not Found.");
		}
	}
	
	@DeleteMapping("/food/{id}")
	public ResponseEntity<?> deletefoodById(@PathVariable Integer id) throws IdNotFoundException {
		String register = foodService.deleteFoodById(id);
		return ResponseEntity.ok(register);
	}
}
