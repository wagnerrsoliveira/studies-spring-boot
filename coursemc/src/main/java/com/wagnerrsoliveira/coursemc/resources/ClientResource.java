package com.wagnerrsoliveira.coursemc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerrsoliveira.coursemc.domain.Client;
import com.wagnerrsoliveira.coursemc.dtos.ClientDTO;
import com.wagnerrsoliveira.coursemc.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		
		Client client = service.find(id);
		
		return ResponseEntity.ok().body(client);
	}
	

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO,@PathVariable Integer id){
		Client client = service.fromDTO(clientDTO);
		client.setId(id);
		client = service.update(client);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> clients = service.findAll();
		List<ClientDTO> clientsDTO = clients.stream()
				.map(client->new ClientDTO(client))
				.collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(clientsDTO);
	}
	
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page",defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction",defaultValue = "ASC") String direction
			) {
		Page<Client> clients = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> clientsDTO = clients.map(client->new ClientDTO(client));
		
		return ResponseEntity.ok().body(clientsDTO);
	}
}
