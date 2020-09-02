package com.wagnerrsoliveira.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerrsoliveira.coursemc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
