package com.hibernate.jpa.prac.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hibernate.jpa.prac.entity.Course;
import com.hibernate.jpa.prac.entity.Student;


@SpringBootTest
class JPQLTests {

	private static final Logger logger = LoggerFactory.getLogger(JPQLTests.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	void findAllJPQLWithoutType() {
		//Query query = entityManager.createQuery("select c from Course c");
		Query query = entityManager.createNamedQuery("find_all_course");
		List courseList = query.getResultList();
		logger.info("findAllJPQLWithoutType :: courseList -> {}", courseList);
	}
	
	@Test
	void findAllJPQLTyped() {
		//TypedQuery<Course> query = entityManager.createQuery("select c from Course c", Course.class);
		TypedQuery<Course> query = entityManager.createNamedQuery("find_all_course", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("findAllJPQLTyped :: courseList -> {}", courseList);
	}
	
	@Test
	void findAllJPQLWhere() {
		//TypedQuery<Course> query = entityManager.createQuery("select c from Course c where name like 'Hib%'", Course.class);
		TypedQuery<Course> query = entityManager.createNamedQuery("find_course_where_Hib", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("findAllJPQLWhere :: courseList -> {}", courseList);
	}
	
	@Test
	@Transactional
	void retrieveCoursesWithoutStdent() {
		Query query = entityManager.createQuery("select c from Course c where c.students is empty", Course.class);
		logger.info("course which  don't have any students -> {}", query.getResultList());
	}
	
	@Test
	@Transactional
	void retrieveCoursesWithAtLeast2Student() {
		Query query = entityManager.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
		logger.info("retrieveCoursesWithAtLeast2Student -> {}", query.getResultList());
	}
	
	@Test
	@Transactional
	void retrieveCoursesWithStdentOrderBy() {
		//Query query = entityManager.createQuery("select c from Course c order by size(c.students)", Course.class);
		Query query = entityManager.createQuery("select c from Course c order by size(c.students) desc", Course.class);
		logger.info("retrieveCoursesWithStdentOrderBy -> {}", query.getResultList());
	}

	@Test
	@Transactional
	void retrieveStdentWithPassportPattern() {
		Query query = entityManager.createQuery("select s from Student s where s.passport.number like '%984%'",
				Student.class);
		logger.info("retrieveStdentWithPassportPattern -> {}", query.getResultList());
	}
	
	@Test
	void join() {
		Query query = entityManager.createQuery("select c, s from Course c JOIN c.students s");
		List<Object[]> results = query.getResultList();
		logger.info("join :: results size -> {}", results.size());
		
		for(Object[] result : results) {
			logger.info(result[0] + " " + result[1]);
		}
	}
	
	@Test
	void leftJoin() {
		Query query = entityManager.createQuery("select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> results = query.getResultList();
		logger.info("leftJoin :: results size -> {}", results.size());
		
		for(Object[] result : results) {
			logger.info(result[0] + " " + result[1]);
		}
	}
	
	@Test
	void crossJoin() {
		Query query = entityManager.createQuery("select c, s from Course c, Student s");
		List<Object[]> results = query.getResultList();
		logger.info("crossJoin :: results size -> {}", results.size());
		
		for(Object[] result : results) {
			logger.info(result[0] + " " + result[1]);
		}
	}
}
