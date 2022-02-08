package com.learning.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

//we use this method to override instead of other one used below because when we change anything later, it can handle on its own
@EqualsAndHashCode
@ToString

//ORM mapping purpose
@Entity
//entity class is used for ORM
//we can customize the table name
@Table(name = "Food")
public class Food implements Comparable<Food> {
	
	@Id //Id must be auto generated
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer foodId;
	
	@NotBlank
	private String foodName;
	
	@Range(min=5,max=200)
	@NotNull
	private Integer foodCost;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private EFOOD foodType;
	
	private String foodPic;

	@Override
	public int compareTo(Food o) {
		// TODO Auto-generated method stub
		return this.foodId.compareTo(o.getFoodId());
	}
	
}
