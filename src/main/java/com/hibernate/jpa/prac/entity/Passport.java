package com.hibernate.jpa.prac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String number;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport") //student is owning side among student and password
	private Student student;

	public Passport(String number) {
		super();
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "\nPassport [id=" + id + ", number=" + number + "]";
	}

}
