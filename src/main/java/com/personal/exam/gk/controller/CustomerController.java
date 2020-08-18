package com.personal.exam.gk.controller;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.personal.exam.gk.entity.Customer_AK;
import com.personal.exam.gk.entity.Orders_AK;
import com.personal.exam.gk.model.CustomerDTO;
import com.personal.exam.gk.model.OrderDTO;
import com.personal.exam.gk.repository.CustomerRepository;


@RestController
public class CustomerController {

	private static final Gson GSON = new Gson();
	@Autowired
	CustomerRepository customerRepository;
	
	@RequestMapping(value = "/customer/new", method = RequestMethod.POST )
	public ResponseEntity<String> insertCustomerRecord(@RequestBody CustomerDTO customerDTO ) {
		
		Customer_AK customer = new Customer_AK();
		try {
			BeanUtils.copyProperties(customer, customerDTO);
			Customer_AK retVal = customerRepository.save(customer);
			
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	@RequestMapping(value = "/getrecords", produces ="application/json")
	public ResponseEntity<String> getCustomerRecords(){
		
		List<Customer_AK> customers = customerRepository.findAllByOrderByCustomerNameAsc();
		
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
	
		try {

			for(Customer_AK customer : customers) {
				CustomerDTO customerDTO = new CustomerDTO();
				BeanUtils.copyProperties(customerDTO, customer);
				
				List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
				for(Orders_AK order : customer.getOrders()) {
					OrderDTO orderDTO = new OrderDTO();
					BeanUtils.copyProperties(orderDTO, order);
					//date
					orderDTOs.add(orderDTO);
					
				}
				customerDTO.setOrders(orderDTOs);
				customerDTOs.add(customerDTO);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(GSON.toJson(customerDTOs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		
	}
}
