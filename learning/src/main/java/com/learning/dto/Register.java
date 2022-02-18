package com.learning.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
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
	private Long id;
	
	
	@NotBlank
	private String username;

	@Email
	private String email;
	
	
	@NotBlank
	private String name;

	
	@NotBlank
	private String password;

	@Size(min = 3, max = 20)
	@NotBlank
	private String address;

	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	//3rd  table
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="regid"),
	inverseJoinColumns = @JoinColumn(name="roleId"))//registered user(regid) and role(roleid)
	private Set<Role> roles = new HashSet<Role>();
	
	public Register(String username,String email, String password,String name, String address) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	
	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Login login;
}
