package com.felipecarvalho.projetoMangasBR.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Title implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="publisher_id")
	private Publisher publisher;
	
	private boolean isFinished;
	
	private Date start;
	private Date end;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;
	
	@JsonIgnore
	@OneToMany(mappedBy="title")
	private Set<Volume> volumes = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.title", cascade = CascadeType.ALL)
	private Set<CollectionTitle> collectionsTitle = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="title")
	private List<Review> reviews = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private Double score;
	
	private String synopsis;
	
	public Title() {
	}

	public Title(Integer id, String name, Publisher publisher, Author author, boolean isFinished, Date start, Date end) {
		super();
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.author = author;
		this.isFinished = isFinished;
		this.start = start;
		this.end = end;
	}
	
	@JsonIgnore
	public List<Collection> getCollections(){
		List<Collection> lista = new ArrayList<>();
		for(CollectionTitle x : collectionsTitle) {
			lista.add(x.getCollection());
		}
		return lista;
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public Set<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(Set<Volume> volumes) {
		this.volumes = volumes;
	}

	public Set<CollectionTitle> getCollectionsTitle() {
		return collectionsTitle;
	}

	public void setCollectionsTitle(Set<CollectionTitle> collectionsTitle) {
		this.collectionsTitle = collectionsTitle;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public Double getScore() {
		return calculateScore();
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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
		Title other = (Title) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	private Double calculateScore() {
		if(reviews.isEmpty()) {
			return null;
		}
		double soma = 0.0;
		for(Review x : reviews) { 
			soma = soma + x.getScore();
		}
		return soma/reviews.size();
	}

}
