package com.wagnerrsoliveira.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerrsoliveira.coursemc.domain.Client;
import com.wagnerrsoliveira.coursemc.repositories.ClientRepository;
import com.wagnerrsoliveira.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Client search(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(()-> new ObjectNotFoundException("Object not found! id: "+id+" Type: "+Client.class.getName()));
	}
}
