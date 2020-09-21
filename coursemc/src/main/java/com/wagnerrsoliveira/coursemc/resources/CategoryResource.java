package com.wagnerrsoliveira.coursemc.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wagnerrsoliveira.coursemc.domain.Category;
import com.wagnerrsoliveira.coursemc.dtos.CategoryDTO;
import com.wagnerrsoliveira.coursemc.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category category = service.find(id);
		return ResponseEntity.ok().body(category);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO){
		Category category = service.fromDTO(categoryDTO);
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Integer id){
		Category category = service.fromDTO(categoryDTO);
		category.setId(id);
		category = service.update(category);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = service.findAll();
		List<CategoryDTO> categoriesDTO = categories.stream()
				.map(category->new CategoryDTO(category))
				.collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(categoriesDTO);
	}
	
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value = "page",defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction",defaultValue = "ASC") String direction
			) {
		Page<Category> categories = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> categoriesDTO = categories.map(category->new CategoryDTO(category));
		
		return ResponseEntity.ok().body(categoriesDTO);
	}
}
