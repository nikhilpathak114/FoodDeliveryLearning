package com.learning.dto;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;
	import javax.validation.constraints.Email;
	import javax.validation.constraints.NotBlank;
	import javax.validation.constraints.Size;

	import com.fasterxml.jackson.annotation.JsonIgnore;


	//import com.fasterxml.jackson.databind.annotation.JsonSerialize;

	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Data
	@Entity
	@NoArgsConstructor
	@Table(name="login")
	public class Login implements Comparable<Login> {

		@Id
		@Column(name="username")
		@Email
		private String username;
		
		@Size(max=100)
		@NotBlank
		private String password;
		
		@Override
		public int compareTo(Login o) {
			// TODO Auto-generated method stub
			return this.username.compareTo(o.getUsername());
		}
		
		@OneToOne(fetch=FetchType.LAZY)
		//@JsonSerialize(using = CustomListSerializer.class)
	    @JoinColumn(name = "regid")
//		@JsonProperty(access =Access.WRITE_ONLY)
		@JsonIgnore
		private Register register;
		
	}

