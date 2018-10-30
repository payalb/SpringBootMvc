package com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"user"})
@EqualsAndHashCode
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOGIN_ID")
	private Integer loginId;
	@NotNull(groups = { LoginValidation.class, RegisterValidation.class }, message = "Email cannot be null")
	@Email(message = "Email is invalid")
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;
	@NotNull(groups = { RegisterValidation.class }, message = "Name cannot be null")
	@Size(groups = {
			RegisterValidation.class }, min = 2, max = 200, message = "Name must be between 2 and 200 characters")
	@Column(name = "NAME", nullable = false)
	private String name;
	@NotNull(groups = { LoginValidation.class, RegisterValidation.class }, message = "Password cannot be null")
	@Size(groups = { LoginValidation.class,
			RegisterValidation.class }, min = 6, message = "Password must be more than 6 characters")
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE", nullable = false)
	private UserRole role;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;

	public interface LoginValidation {
	}

	public interface RegisterValidation {
	}

}
