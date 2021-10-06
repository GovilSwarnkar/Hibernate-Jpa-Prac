package com.hibernate.jpa.prac.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hibernate.jpa.prac.entity.Course;
import com.hibernate.jpa.prac.entity.Student;


@SpringBootTest
class CriteriaQueryTests {

	private static final Logger logger = LoggerFactory.getLogger(CriteriaQueryTests.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	void select() {
		//1. Creating criteria builder to create query and returning expected object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define root for table which are involved in query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Build TypedQuery using entity manager and criteria query
		
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> results = query.getResultList();
		logger.info("criteriaQuerySelect :: results -> {}", results);
		
	}
	
	@Test
	void selectWhereLike() {
		//1. Creating criteria builder to create query and returning expected object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define root for table which are involved in query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3.Define predicate using criteria builder
		Predicate like = cb.like(courseRoot.get("name"), "Spring%");
		
		//4.Add Predicates to the Criteria Query
		cq.where(like);
		
		//5.Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> results = query.getResultList();
		logger.info("criteriaQuerySelectWhereLike :: results -> {}", results);
		
	}
	

	@Test
	void coursesWithoutStudent() {
		//Select c FROM Course c where c.students is empty
		
		//1. Creating criteria builder to create query and returning expected object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define root for table which are involved in query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3.Define predicate using criteria builder
		Predicate emptyStudent = cb.isEmpty(courseRoot.get("students"));
		
		//4.Add Predicates to the Criteria Query
		cq.where(emptyStudent);
		
		//5.Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> results = query.getResultList();
		logger.info("coursesWithoutStudentCriteria :: results -> {}", results);
		
	}
	
	@Test
	void join() {
		//Select c FROM Course c join c.students s
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		courseRoot.join("students");
		
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> results = query.getResultList();
		logger.info("join :: results -> {}", results);
		
	}
	

	@Test
	void leftJoin() {
		//Select c FROM Course c left join c.students s
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		courseRoot.join("students", JoinType.LEFT);
		
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> results = query.getResultList();
		logger.info("leftJoin :: results -> {}", results);
		
	}
}
