package com.wagnerrsoliveira.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerrsoliveira.coursemc.domain.OrderMain;
import com.wagnerrsoliveira.coursemc.repositories.OrderRepository;
import com.wagnerrsoliveira.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public OrderMain find(Integer id) {
		Optional<OrderMain> orders = repository.findById(id);
		return orders.orElseThrow(()-> new ObjectNotFoundException("Object not found! id: "+id+" Type: "+OrderMain.class.getName()));
	}
}
