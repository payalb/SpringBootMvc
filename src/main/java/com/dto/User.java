package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "SHOPPING_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"addresses", "cards"})
@EqualsAndHashCode(exclude = {"addresses", "cards"})
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String mobile;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Card> cards = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CartItem> cartItems = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Address> addresses = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Order> orders = new ArrayList<>();
}
