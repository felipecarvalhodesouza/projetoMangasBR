package com.felipecarvalho.projetoMangasBR.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipecarvalho.projetoMangasBR.dto.VolumeDTO;

@Entity
public class CollectionTitle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private CollectionTitlePK id = new CollectionTitlePK();
	
	@OneToMany(mappedBy="collectionTitle")
	private List<VolumeDTO> volumesUser;
	
	public CollectionTitle() {
	}

	public CollectionTitle(Collection collection, Title title) {
		super();
		id.setCollection(collection);
		id.setTitle(title);
	}
	
	public Collection getCollection() {
		return id.getCollection();
	}
	
	public Title getTitle() {
		return id.getTitle();
	}

	public CollectionTitlePK getId() {
		return id;
	}

	public void setId(CollectionTitlePK id) {
		this.id = id;
	}

	public List<VolumeDTO> getVolumesUser() {
		return volumesUser;
	}

	public void setVolumesUser(List<VolumeDTO> volumesUser) {
		this.volumesUser = volumesUser;
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
		CollectionTitle other = (CollectionTitle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
