package com.hibernate.jpa.prac.repository;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hibernate.jpa.prac.entity.Course;


@SpringBootTest
class PerformanceTuning {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceTuning.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional
	public void creatingNPlusOneProblem() { 
		//executing 11 JDBC statements, first query to fetch course then to fetch individual student query will be fired
		TypedQuery<Course> query = entityManager.createNamedQuery("find_all_course", Course.class);
		List<Course> courses = query.getResultList();
		for(Course course : courses) {
			logger.info("Course -> {}, Student -> {}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblemUsingEntityGraph() { 
		//executing 1 JDBC statements
		EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
		entityGraph.addSubgraph("students");
		//now student will be loaded along with course at the time after query execution
		List<Course> courses = entityManager.createNamedQuery("find_all_course", Course.class)
											.setHint("javax.persistence.loadgraph", entityGraph)
		                                    .getResultList();
		for(Course course : courses) {
			logger.info("Course -> {}, Student -> {}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblemUsingJoinFetch() {  
		//only one join query will be fired, executing 1 JDBC statements
		List<Course> courses = entityManager.createNamedQuery("find_all_course_join_fetch", Course.class)
		                                    .getResultList();
		for(Course course : courses) {
			logger.info("Course -> {}, Student -> {}", course, course.getStudents());
		}
	}
}
