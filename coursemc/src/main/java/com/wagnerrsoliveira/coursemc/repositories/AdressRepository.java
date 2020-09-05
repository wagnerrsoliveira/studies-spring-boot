package com.wagnerrsoliveira.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerrsoliveira.coursemc.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>{

}
