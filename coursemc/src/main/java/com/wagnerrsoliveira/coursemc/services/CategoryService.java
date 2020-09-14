package com.wagnerrsoliveira.coursemc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.wagnerrsoliveira.coursemc.domain.Category;
import com.wagnerrsoliveira.coursemc.repositories.CategoryRepository;
import com.wagnerrsoliveira.coursemc.services.exceptions.DataIntegrityException;
import com.wagnerrsoliveira.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category find(Integer id) {
		Optional<Category> categories = repository.findById(id);
		return categories.orElseThrow(()-> new ObjectNotFoundException("Object not found! id: "+id+" Type: "+Category.class.getName()));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return repository.save(category);
	}
	
	public Category update(Category category) {
		find(category.getId());
		return repository.save(category);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException exception) {
			throw new DataIntegrityException("it is not possible to exclude a category that has products.");
		}
	}
	
	public List<Category> findAll() {		
		return repository.findAll();
	}
}
