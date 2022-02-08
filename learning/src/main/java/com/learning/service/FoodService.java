package com.learning.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.learning.dto.EFOOD;
import com.learning.dto.Food;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidFoodType;

public interface FoodService {

	public Food addFood(Food food) throws SQLException, AlreadyExistsException;
	public Food getFoodById(Integer id) throws IdNotFoundException;
	public String deleteFoodById(Integer id) throws IdNotFoundException;
	public Food updateFoodById(Integer id, Food food2) throws IdNotFoundException;
	public Optional<List<Food>> getAllFoodDetails();
	public Food getFoodByFoodType(EFOOD foodType) throws InvalidFoodType;
}
