package com.personal.exam.gk.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

	private Integer customerID;
	private String customerName;
	private int customerAge;
	private String customerAddress;
	
	private List<OrderDTO> orders;
	
}
