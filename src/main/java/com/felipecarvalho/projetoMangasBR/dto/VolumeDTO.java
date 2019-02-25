package com.felipecarvalho.projetoMangasBR.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.Volume;

@Entity
public class VolumeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	private Date date;
	
	@ManyToOne
	@JoinColumns({
		  @JoinColumn(name = "collection_id"),
		  @JoinColumn(name = "title_id")
		})
	private CollectionTitle collectionTitle;
	
	public VolumeDTO() {
	}
	
	public VolumeDTO(Volume obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.price = obj.getPrice();
		this.date = obj.getDate();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
