package org.sample.controller.pojos;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class CreateTeam {
	Long id;
	@NotNull
	String name;
	Date date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
