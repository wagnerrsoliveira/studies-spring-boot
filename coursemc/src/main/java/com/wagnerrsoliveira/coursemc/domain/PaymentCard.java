package com.wagnerrsoliveira.coursemc.domain;

import javax.persistence.Entity;

import com.wagnerrsoliveira.coursemc.domain.enums.PaymentStatus;

@Entity
public class PaymentCard extends Payment{

	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public PaymentCard() {		
	}

	public PaymentCard(Integer id, PaymentStatus status, OrderMain order,Integer numberOfInstallments) {
		super(id, status, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
	
	
}
