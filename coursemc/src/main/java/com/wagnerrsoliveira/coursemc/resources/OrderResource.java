package com.wagnerrsoliveira.coursemc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerrsoliveira.coursemc.domain.OrderMain;
import com.wagnerrsoliveira.coursemc.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<OrderMain> find(@PathVariable Integer id) {
		
		OrderMain order = service.find(id);
		
		return ResponseEntity.ok().body(order);
	}
}
