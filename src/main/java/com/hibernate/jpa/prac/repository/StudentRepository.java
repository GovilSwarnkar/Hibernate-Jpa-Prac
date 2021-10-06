package com.hibernate.jpa.prac.repository;

import javax.persistence.EntityManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.jpa.prac.entity.Address;
import com.hibernate.jpa.prac.entity.Course;
import com.hibernate.jpa.prac.entity.Passport;
import com.hibernate.jpa.prac.entity.Student;
//javax.transaction.Transactional - jpa transaction only use with one database
//and for working with multi database at one transaction then should use spring transaction 
//- org.springframework.transaction.annotation.Transactional
@Repository
@Transactional
public class StudentRepository {//for performing update operation transaction should be
	
	private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

	@Autowired
	EntityManager entityManager; //entity manager to persistence context keep track of every db operation for specific transaction
	
	public Student findById(Integer id) {
		return entityManager.find(Student.class, id);
	}

	public void deleteById(Integer id) {
		Student student = findById(id);
		entityManager.remove(student);
	}
	
	public Student save(Student student) {
		if(student.getId() == null) {
			entityManager.persist(student); // insert
		}else {
			entityManager.merge(student); //update
		}
		return student;
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("F463927");
		entityManager.persist(passport);
		
		Student student = new Student("Arjun");
		student.setPassport(passport);
		entityManager.persist(student);
	}
	
	public void saveStudentAndCourse() {
		Student student = new Student("Narayan");
		Course course = new Course("Google cloud platform");
		entityManager.persist(student);
		entityManager.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		
		entityManager.persist(student);  //persist owning side of relation
	}
	
	public void saveStudentAndCourse(Student student, Course course) {
		entityManager.persist(student);
		entityManager.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		
		entityManager.persist(student);  //persist owning side of relation
	}
	
	public void persistanceContextWithTransactionUnderstad() {//PersistanceContaxt tracking every operation under transaction
		//Database operation - retrieve student
		Student student = entityManager.find(Student.class, 20001);
		//PersitanceContext(student) 
		
		//Dababase operation 2 - retrieve passport
		Passport passport = student.getPassport();
		//PersitanceContext(passport)
		
		//Dababase operation 3 - update passport
		passport.setNumber("E129845");
		
		//Dababase operation 4 - update student
		student.setName("Shiva - Updated");
	}
	
	public void setAddressToStudentAndGetAddress() {
		Student student = entityManager.find(Student.class, 20001);
		student.setAddress(new Address("Amphitheatre Parkway", "Muntain View", "California"));
		entityManager.flush();
		
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
		logger.info("address -> {}", student.getAddress());
	}
}
