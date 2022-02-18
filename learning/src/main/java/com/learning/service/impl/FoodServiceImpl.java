package com.learning.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.dto.EFOOD;
import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidFoodType;
import com.learning.repository.FoodRepository;
import com.learning.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	@Transactional(rollbackFor = AlreadyExistsException.class)
	public Food addFood(Food food) throws SQLException, AlreadyExistsException {
		// TODO Auto-generated method stub
		if(foodRepository.existsByFoodName(food.getFoodName())) {
			throw new AlreadyExistsException("This record already exist.");
		}
		Food food2 = foodRepository.save(food);
		if(food2!=null) {
			return food2;
		}
		return null;
	}

	@Override
	public Food getFoodById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Food> optional = foodRepository.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException("it doesnt exist");
		}
		else {
			return optional.get();
		}
	}

	@Override
	public String deleteFoodById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Food optional = this.getFoodById(id);
			if(optional==null) {
				throw new IdNotFoundException("record not found");
			}
			else {
				foodRepository.deleteById(id);
				return "success";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Food updateFoodById(Integer foodId, Food food) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Food> optional = foodRepository.findById(foodId);
		if(optional.isEmpty()) {
			throw new IdNotFoundException("Id not found Exception.");
		}else {
			food.setFoodId(foodId);
			Food food2 = foodRepository.save(food);
			return food2;
		}
	}

	@Override
	public Optional<List<Food>> getAllFoodDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(foodRepository.findAll());
	}

	@Override
	public Optional<List<Food>> getFoodByFoodType(EFOOD foodType) throws InvalidFoodType {
		// TODO Auto-generated method stub
		Optional<Food> optional = foodRepository.findByFoodType(foodType);
		if(optional.isEmpty()) {
			throw new InvalidFoodType("Invalid Food Type");
		}
		else {
			return null;
		}
	}

}
