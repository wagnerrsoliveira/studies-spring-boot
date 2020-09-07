package com.wagnerrsoliveira.coursemc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.wagnerrsoliveira.coursemc.domain.enums.PaymentStatus;

@Entity
public class PaymentBankSlip extends Payment{

	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date paymentDate;

	public PaymentBankSlip() {		
	}

	public PaymentBankSlip(Integer id, PaymentStatus status, OrderMain order, Date dueDate, Date paymentDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
}
