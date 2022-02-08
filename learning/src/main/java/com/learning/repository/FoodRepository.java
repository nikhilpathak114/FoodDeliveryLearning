package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.EFOOD;
import com.learning.dto.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
	
	Boolean existsByFoodIdAndFoodName(Integer foodId, String foodName);

	Optional<Food> findByFoodType(EFOOD foodType);

	boolean existsByFoodName(String foodName);

	//String deleteById(Integer id);

	//Optional<Food> findById(Integer id);
}
