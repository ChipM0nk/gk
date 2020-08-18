package com.personal.exam.gk.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personal.exam.gk.entity.Customer_AK;
import com.personal.exam.gk.entity.Orders_AK;
import com.personal.exam.gk.model.OrderDTO;
import com.personal.exam.gk.repository.CustomerRepository;
import com.personal.exam.gk.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	
	
	@Autowired
	CustomerRepository customerRepository;
	
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");
	
	
	@RequestMapping(value = "/order/new", method = RequestMethod.POST )
	public ResponseEntity<String> insertCustomerRecord(@RequestBody OrderDTO orderDTO ) {
		
		Orders_AK orders = new Orders_AK();
		try {
			
			Customer_AK customer = customerRepository.findOne(orderDTO.getCustomerID());
			
			if(customer == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Customer ID");
			}
			BeanUtils.copyProperties(orders, orderDTO);
			orders.setCustomer(customer);
			Date date = SIMPLE_DATE_FORMAT.parse(orderDTO.getOrderDte());
			orders.setOrderDate(date);
			
			Orders_AK retVal = orderRepository.save(orders);
			System.out.println(retVal.getCustomerID());
			
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
	}
}
