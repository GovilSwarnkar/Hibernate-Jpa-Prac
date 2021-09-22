package com.hibernate.jpa.prac.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.hibernate.jpa.prac.entity.Course;


@SpringBootTest
class CourseRepositoryTest {//junit has lower priority than run method of CommandLineRunner

	private static final Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);
	
	private static final Integer ID = 10001;
	
	private static final String COURSE_NAME_1 = "Google Cloud";
	
	private static final String COURSE_NAME_2 = "Hibernate-Jpa";
	
	private static final String COURSE_NAME_3 = "Hibernate";
	
	private static final Course COURSE = new Course(COURSE_NAME_1);
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void findById_1() {
		logger.info("Find by Id : ");
		assertThat(courseRepository.findById(ID).getName()).isEqualTo(COURSE_NAME_2);
	}
	
	@Test
	@DirtiesContext //spring will reset the data after test ran
	void deleteById_1() {
		logger.info("Delete by Id : ");
		courseRepository.deleteById(ID);
		assertThat(courseRepository.findById(ID)).isNull();
	}
	
	@Test
	@DirtiesContext //spring will reset the data after test ran
	void save_insert() {
		logger.info("insert : ");
		Course course = courseRepository.save(COURSE);
		assertThat(courseRepository.findById(course.getId()).getName()).isEqualTo(COURSE_NAME_1);
	}
	

	@Test
	@DirtiesContext
	void save_update() {
		logger.info("update : ");
		Course course = courseRepository.findById(ID);
		course.setName(COURSE_NAME_3);
		courseRepository.save(course);
		assertThat(courseRepository.findById(course.getId()).getName()).isEqualTo(COURSE_NAME_3);
	}

	
	@Test
	@DirtiesContext
	void emTracksUnderTransactionTest() {
		logger.info("entity manager tracker entity operation : ");
		courseRepository.emTracksUnderTransaction();
	}

}