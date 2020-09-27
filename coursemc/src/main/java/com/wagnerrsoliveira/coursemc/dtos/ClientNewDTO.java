package com.wagnerrsoliveira.coursemc.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wagnerrsoliveira.coursemc.services.validations.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Required fill")
	@Length(min =5  ,max=120,message = "The length must have between 5 and 120 char")
	private String name;
	
	@NotEmpty(message = "Required fill")
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "Required fill")
	private String registryId;
	
	private Integer type;
	
	@NotEmpty(message = "Required fill")
	private String description;
	
	@NotEmpty(message = "Required fill")
	private String number;
	
	private String complement;
	private String neighborhood;
	
	@NotEmpty(message = "Required fill")
	private String zipCode;
	
	@NotEmpty(message = "Required fill")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
		
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

	public String getRegistryId() {
		return registryId;
	}

	public void setRegistryId(String registryId) {
		this.registryId = registryId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
