package com.foodstore.entity;

import java.io.Serializable;

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String role;
    private String description;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
