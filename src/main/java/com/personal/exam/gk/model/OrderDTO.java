package com.personal.exam.gk.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	private int orderID;
	private int customerID;
	private int orderDetail;
	private String orderDte;
	private double orderAmount;

}
