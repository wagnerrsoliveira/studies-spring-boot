package com.wagnerrsoliveira.coursemc.domain.enums;

public enum ClientType {
	INDIVIDUAL(1,"Individual"),
	LEGALENTITY(2,"Legal Entity");
	
	private int id;
	private String description;
	
	private ClientType(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static ClientType toEnum(Integer id) {
		if(id==null) {
			return null;
		}
		
		for(ClientType clientType : ClientType.values()) {
			if(id.equals(clientType.getId())) {
				return clientType;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: "+id);
	}
}
