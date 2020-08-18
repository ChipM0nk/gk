package com.personal.exam.gk.entity;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="customer_ak",uniqueConstraints = @UniqueConstraint(columnNames = "customerID"))
public class Customer_AK {
	
	@Id
	@GeneratedValue
	@Column(name = "customerID", nullable = false)
	private Integer customerID;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "customerAge")
	private int customerAge;
	
	@Column(name = "customerAddress")
	private String customerAddress;
	
	@OneToMany(mappedBy = "customer")
	private List<Orders_AK> orders;
	

	
	
	
}
