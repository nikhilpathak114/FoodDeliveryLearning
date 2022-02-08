package com.learning.dto;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.learning.dto.*;

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
@Table(name = "register")
public class Register implements Comparable<Register> {

	@Id // it will consider this column as PK
	@Column(name = "regid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(max = 50)
	@Email
	private String email;
	
	@Size(max=50)
	@NotBlank
	private String name;

	@Size(max = 100)
	@NotBlank
	private String password;

	@Size(max=100)
	@NotBlank
	private String address;

	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
}
