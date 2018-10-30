package com.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile ListAttribute<User, Address> addresses;
	public static volatile ListAttribute<User, Card> cards;
	public static volatile SingularAttribute<User, Gender> gender;
	public static volatile SingularAttribute<User, String> mobile;
	public static volatile ListAttribute<User, Order> orders;
	public static volatile ListAttribute<User, CartItem> cartItems;
	public static volatile SingularAttribute<User, Integer> userId;

}

