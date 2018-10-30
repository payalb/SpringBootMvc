package com.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CARD_ID")
	private Integer cardId;	
	@NotNull
	@Column(name = "CARD_NAME", nullable = false)
	private String cardName;
	@NotNull
	@Column(name = "CARD_NUM", nullable = false, unique = true)
	private String cardNum;
	@NotNull
//	@isValidExpiration(message = "Invalid expiration format.")
	@Column(name = "EXPIRATION", nullable = false)
	private Date expiration;
	@NotNull
	private Integer cvv;
	@ManyToOne
	private User user;
}
