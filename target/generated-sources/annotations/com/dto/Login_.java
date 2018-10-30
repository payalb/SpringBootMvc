package com.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Login.class)
public abstract class Login_ {

	public static volatile SingularAttribute<Login, String> password;
	public static volatile SingularAttribute<Login, Integer> loginId;
	public static volatile SingularAttribute<Login, UserRole> role;
	public static volatile SingularAttribute<Login, String> name;
	public static volatile SingularAttribute<Login, User> user;
	public static volatile SingularAttribute<Login, String> email;

}

