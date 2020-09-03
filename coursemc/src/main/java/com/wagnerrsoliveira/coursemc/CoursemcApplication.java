package com.wagnerrsoliveira.coursemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wagnerrsoliveira.coursemc.domain.Category;
import com.wagnerrsoliveira.coursemc.repositories.CategoryRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Computing");
		Category category2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(category1,category2));
		
		
	}

}
