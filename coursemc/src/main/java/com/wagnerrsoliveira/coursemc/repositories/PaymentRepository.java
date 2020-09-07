package com.wagnerrsoliveira.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerrsoliveira.coursemc.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
