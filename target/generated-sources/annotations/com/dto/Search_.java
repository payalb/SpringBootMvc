package com.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Search.class)
public abstract class Search_ {

	public static volatile SingularAttribute<Search, Product> product;
	public static volatile SingularAttribute<Search, Integer> searchId;
	public static volatile SingularAttribute<Search, String> keywords;
	public static volatile SingularAttribute<Search, Category> category;

}

