package com.felipecarvalho.projetoMangasBR.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VolumeUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Volume volume;
	
	@ManyToOne
	@JoinColumns({
		  @JoinColumn(name = "collection_id", referencedColumnName = "collection_id"),
		  @JoinColumn(name = "title_id", referencedColumnName = "title_id")
		})
	private CollectionTitle collectionTitle;
	
	private boolean doesHave;
	
	public VolumeUser() {
	}
	
	public VolumeUser(CollectionTitle collectionTitle, Volume obj) {
		this.collectionTitle = collectionTitle;
		this.volume = obj;
		this.doesHave = false;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.volume.getName();
	}
	
	public Integer getCollectionId(CollectionTitle obj) {
		return obj.getCollection().getId();
	}
	
	public Integer getTitleId(CollectionTitle obj) {
		return obj.getTitle().getId();
	}

	public boolean getDoesHave() {
		return doesHave;
	}

	public void setDoesHave(boolean doesHave) {
		this.doesHave = doesHave;
	}
	
}
