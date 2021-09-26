package com.hibernate.jpa.prac.repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hibernate.jpa.prac.entity.Student;


@SpringBootTest
class StudentRepositoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	@Transactional //in case of lazy fetching :: using @Transactional bcz once find method of student will be called session will be end and at the time calling passport it throw no session
	void retrieveStudentAndPasswordTest() {
		Student student = entityManager.find(Student.class, 20001);
		logger.info("student name-> {}", student.getName());
		logger.info("student password -> {}", student.getPassport());
		
	}
	
	@Test
	void persistanceContextWithTransactionUnderstadTest() {
		studentRepository.persistanceContextWithTransactionUnderstad();
	}
	
	@Test
	@Transactional
	void retrieveStudentAndCourse() {
		Student student = entityManager.find(Student.class, 20001);
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses()); //student_course courses0_ inner join
	}
	
}
