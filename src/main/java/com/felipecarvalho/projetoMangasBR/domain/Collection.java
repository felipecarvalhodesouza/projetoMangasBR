package com.felipecarvalho.projetoMangasBR.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Collection implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private User owner;
	
	@JsonIgnore
	@OneToMany(mappedBy="id.collection")
	private Set<CollectionTitle> collectionTitle = new HashSet<>();
	
	public Collection() {
	}

	public Collection(Integer id, User owner) {
		super();
		this.id = id;
		this.owner = owner;
	}
	
	public List<Title> getTitles(){
		List<Title> lista = new ArrayList<>();
		for(CollectionTitle x : collectionTitle) {
			lista.add(x.getTitle());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<CollectionTitle> getCollectionTitle() {
		return collectionTitle;
	}

	public void setCollectionTitle(Set<CollectionTitle> collectionTitle) {
		this.collectionTitle = collectionTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collection other = (Collection) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
