package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "propertyValue")
@Table(name = "PROPERTY_VALUE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PropertyValue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propertyValueId;
	private String propertyValueName;
}
