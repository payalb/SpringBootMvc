package com.dto;

import java.sql.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "SHOPPING_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Integer orderId;
	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	@ManyToOne(optional = false)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	private Address address;
	@ManyToOne(optional = false)
	@JoinColumn(name = "CARD_ID", nullable = false)
	private Card card;
	@ElementCollection(fetch = FetchType.EAGER)
//	@MapKeyJoinColumn(name = "ORDER_ID")
//	@MapKeyColumn(name = "PRODUCT_ID")
//	@MapKeyClass(value = Product.class)
//	@Column(name = "QUANTITY", columnDefinition = "INTEGER")
	private Map<Product, Integer> products;
	@Column(name = "ORDER_TIME")
	private Date orderTime;
	private Integer amount;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Order(User user, Address address, Card card, Map<Product, Integer> products, Date orderTime, Integer amount,
			OrderStatus status) {
		super();
		this.user = user;
		this.address = address;
		this.card = card;
		this.products = products;
		this.orderTime = orderTime;
		this.amount = amount;
		this.status = status;
	}

}
