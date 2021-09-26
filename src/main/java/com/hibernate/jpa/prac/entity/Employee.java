package com.hibernate.jpa.prac.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //in this case all sub class data will be stored into one table, d_type column part time and full time all data will be in single table
//@DiscriminatorColumn(name = "EmployeeType") //to rename d_type to specific column name
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //in this case for every concrete class new table will be created, PartTime and FullTime two table
//@Inheritance(strategy = InheritanceType.JOINED) //in this for all subclass and parent class table will be created and at the time of fetching record join will perform for all table
@MappedSuperclass //In this case employee will be no longer entity and there will no inheritance between employee and its sub class, along with that we will no use @Entity
public abstract class Employee {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;

	public Employee(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "\nEmployee [id=" + id + ", name=" + name + "]";
	}

}
