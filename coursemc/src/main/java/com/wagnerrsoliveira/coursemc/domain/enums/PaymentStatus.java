package com.wagnerrsoliveira.coursemc.domain.enums;

public enum PaymentStatus {

PENDING(1,"Pending"),
PAID(2,"Paid"),
CANCELED(2,"Canceled");
	
	private int id;
	private String description;
	
	private PaymentStatus(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static PaymentStatus toEnum(Integer id) {
		if(id==null) {
			return null;
		}
		
		for(PaymentStatus paymentStatus : PaymentStatus.values()) {
			if(id.equals(paymentStatus.getId())) {
				return paymentStatus;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: "+id);
	}
}
