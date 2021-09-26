package com.hibernate.jpa.prac.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.hibernate.jpa.prac.entity.Course;


@SpringBootTest
class NativeQueriesTest {//native queries can be use in case of jpa not supporting db related some operation and in case of mass update

	private static final Logger logger = LoggerFactory.getLogger(NativeQueriesTest.class);
	
	private static final Integer ID = 10001;
	
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	void findAllNativeQueries() {
		Query query = entityManager.createNativeQuery("select * from course", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("findAllNativeQueries :: courseList -> {}", courseList);
	}
	
	@Test
	void findByIdNativeQueries() {
		Query query = entityManager.createNativeQuery("select * from course where id = ?", Course.class);
		query.setParameter(1, ID);
		List<Course> courseList = query.getResultList();
		logger.info("findByIdNativeQueries :: courseList -> {}", courseList);
	}
	
	@Test
	void findByParameterNameNativeQueries() {
		Query query = entityManager.createNativeQuery("select * from course where id = :id", Course.class);
		query.setParameter("id", ID);
		List<Course> courseList = query.getResultList();
		logger.info("findByParameterNameNativeQueries :: courseList -> {}", courseList);
	}
	
	@Test
	void massUpdateNativeQueries() {
		Query query = entityManager.createNativeQuery("update course set updated = sysdate()");
		int rowsAffected = query.executeUpdate();
		logger.info("massUpdateNativeQueries :: rowsAffected -> {}", rowsAffected);
	}
	
}
