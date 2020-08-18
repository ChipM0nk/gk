package com.personal.exam.gk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "orders")
@Table(name = "orders_ak", uniqueConstraints = @UniqueConstraint(columnNames = "orderID"))
public class Orders_AK {

	@Id
	@GeneratedValue
	@Column(name = "orderID", nullable = false)
	private int orderID;
	
	
	@Column(name = "customerID", nullable = false, insertable = false, updatable = false)
	private int customerID;
	
	@Column(name = "orderDetail", nullable = false)
	private String orderDetail;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "orderDate", nullable = false)
	private Date orderDate;
	
	@Column(name = "orderAmount", nullable = false)
	private double orderAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerID")
	private Customer_AK customer;
	
	
	
}
