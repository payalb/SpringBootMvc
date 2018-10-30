package com.dto;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "property")
@Table(name = "PROPERTY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propertyId;
	private String propertyName;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROPERTY_ID")
	private Set<PropertyValue> propertyValues;
}
