package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

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
@ToString
@EqualsAndHashCode
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private Integer addressId;
	@NotNull(message = "Name cannot be null")
	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;
	@NotNull(message = "Address cannot be null")
	@Column(name = "STREET", nullable = false)
	private String street;
	@Column(name = "STREETTWO")
	private String streetTwo;
	@NotNull(message = "City cannot be null")
	@Column(name = "CITY", nullable = false)
	private String city;
	@NotNull(message = "State cannot be null")
	@Column(name = "STATE", nullable = false)
	private String state;
	@NotNull(message = "Zip code cannot be null")
	@Column(name = "ZIP", nullable = false)
	private String zip;
	@NotNull(message = "Country cannot be null")
	@Column(name = "COUNTRY", nullable = false)
	private String country;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<User> users = new ArrayList<>();

}
