package com.felipecarvalho.projetoMangasBR.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipecarvalho.projetoMangasBR.domain.Title;

public class VolumeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@JsonIgnore
	private Title title;
	
	@JsonFormat(pattern="MM/yyyy")
	private Date date;
	
	private Double price;
	
	public VolumeDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
