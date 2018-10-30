package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private Integer productId;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	private String brand;
	@Column(length = 1000)
	private String description;
	private Float price;
	private Integer stock;
	private String imagePath;
	@Version
	private Long version;
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	private List<Property> properties = new ArrayList<>();
}
