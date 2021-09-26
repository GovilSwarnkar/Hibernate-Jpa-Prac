package com.hibernate.jpa.prac.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.jpa.prac.entity.Employee;
import com.hibernate.jpa.prac.entity.FullTimeEmployee;
import com.hibernate.jpa.prac.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	
	@Autowired
	EntityManager entityManager;
	
	public void insert(Employee employee) {
		entityManager.persist(employee);
	}
	
	public List<Employee> retrieveEmployee() {
		return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
	}

	public List<PartTimeEmployee> retrievePartTimeEmployee() {
		return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class)
				.getResultList();
	}

	public List<FullTimeEmployee> retrieveFullTimeEmployee() {
		return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class)
				.getResultList();
	}

}
