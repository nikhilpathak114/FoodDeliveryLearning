package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class InvalidFoodType extends Exception{
	
	public InvalidFoodType(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
