package com.wagnerrsoliveira.coursemc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wagnerrsoliveira.coursemc.domain.Client;
import com.wagnerrsoliveira.coursemc.dtos.ClientDTO;
import com.wagnerrsoliveira.coursemc.repositories.ClientRepository;
import com.wagnerrsoliveira.coursemc.services.exceptions.DataIntegrityException;
import com.wagnerrsoliveira.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Client find(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(()-> new ObjectNotFoundException("Object not found! id: "+id+" Type: "+Client.class.getName()));
	}
	
	public Client update(Client client) {
		Client newClient = find(client.getId());
		updateData(newClient, client);
		return repository.save(newClient);
	}
	
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException exception) {
			throw new DataIntegrityException("it is not possible to exclude because it has related entities.");
		}
	}
	
	public List<Client> findAll() {		
		return repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(),clientDTO.getName(),clientDTO.getEmail(),null,null);
	}
}
