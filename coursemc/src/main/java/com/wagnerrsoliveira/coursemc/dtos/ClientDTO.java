package com.wagnerrsoliveira.coursemc.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wagnerrsoliveira.coursemc.domain.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	
	@NotEmpty(message = "Required fill")
	@Length(min =5  ,max=120,message = "The length must have between 5 and 120 char")
	private String name;
	
	@NotEmpty(message = "Required fill")
	@Email(message = "Invalid email")
	private String email;

	public ClientDTO() {

	}

	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.email = client.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
