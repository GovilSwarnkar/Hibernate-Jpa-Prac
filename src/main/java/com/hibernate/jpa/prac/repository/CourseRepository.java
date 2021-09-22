package com.hibernate.jpa.prac.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.jpa.prac.entity.Course;

@Repository
@Transactional
public class CourseRepository {//for performing update operation transaction should be

	@Autowired
	EntityManager entityManager;
	
	public Course findById(Integer id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteById(Integer id) {
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId() == null) {
			entityManager.persist(course); // insert
		}else {
			entityManager.merge(course); //update
		}
		return course;
	}

	public void emTracksUnderTransaction() { //entity manager tracks all operation within the transaction
		Course course = new Course("Google Data Store");
		entityManager.persist(course);
		//here updating name after calling persist but then also it will be reflect into db bcz of transaction 
		course.setName("Google Data Store - updated");
	}
}
