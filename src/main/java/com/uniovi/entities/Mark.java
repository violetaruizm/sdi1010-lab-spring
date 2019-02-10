package com.uniovi.entities;
import javax.persistence.*;

@Entity
public class Mark {
	
	@Id//Clave primaria 
	@GeneratedValue//generar automaticamente
	private Long id;
	private String description;
	private Double score;

	public Mark(Long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.score = score;
	}

	public Mark() {
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + score + "]";
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
