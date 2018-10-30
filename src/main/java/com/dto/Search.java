package com.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "search")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Search {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer searchId;
	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	@Column(length = 1500)
	private String keywords;
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
}
