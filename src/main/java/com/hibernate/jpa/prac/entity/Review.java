package com.hibernate.jpa.prac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue
	private int id;
	
	private String rating;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToOne
	private Course course; //by default ManyToOne is eager fetching 
	
	public Review(String rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	@Override
	public String toString() {
		return "\nReview [id=" + id + ", description=" + description + "]";
	}

}
