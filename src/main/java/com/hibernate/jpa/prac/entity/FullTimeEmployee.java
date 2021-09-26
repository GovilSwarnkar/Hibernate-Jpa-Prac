package com.hibernate.jpa.prac.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullTimeEmployee extends Employee {

	private BigDecimal salary;

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "\nFullTimeEmployee [salary=" + salary + "]";
	}

}
