package com.hibernate.jpa.prac;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hibernate.jpa.prac.entity.Course;


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

}
