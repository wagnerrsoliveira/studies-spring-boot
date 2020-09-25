package com.wagnerrsoliveira.coursemc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wagnerrsoliveira.coursemc.domain.Category;
import com.wagnerrsoliveira.coursemc.dtos.CategoryDTO;
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
	
	@Transactional
	public Category insert(Category category) {
		category.setId(null);
		return repository.save(category);
	}
	
	public Category update(Category category) {
		Category newCategory = find(category.getId());
		updateData(newCategory, category);
		return repository.save(newCategory);
	}
	
	private void updateData(Category newCategory, Category category) {
		newCategory.setName(category.getName());
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
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}
}
