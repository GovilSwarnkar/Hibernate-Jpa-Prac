package com.hibernate.jpa.prac.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.jpa.prac.entity.Course;


@SpringBootTest
class CourseSpringDataRepositoryTest {

	private static final Logger logger = LoggerFactory.getLogger(CourseSpringDataRepositoryTest.class);
	
	
	@Autowired
	private CourseSpringDataRepository courseRepository;
	
	@Test
	void findById() {
		logger.info("Find by Id : ");
		Optional<Course> courseOptional = courseRepository.findById(10001);
		assertThat(courseOptional.isPresent()).isEqualTo(true);
	}
	
	@Test
	void findByIdNotPresent() {
		logger.info("Find by Id : ");
		Optional<Course> courseOptional = courseRepository.findById(90001);
		assertThat(courseOptional.isPresent()).isEqualTo(false);
	}
	
	@Test
	void saveOrUpdate() {
		Course course = new Course("React Native");
		courseRepository.save(course);
		
		//update
		course.setName("React Native - updated");
		courseRepository.save(course);
	}
	
	@Test
	void findAll() {
		logger.info("courses -> {}", courseRepository.findAll());
		logger.info("courses count -> {}", courseRepository.count());
	}
	
	@Test
	void sort() {
		Sort sort = Sort.by(Sort.Direction.ASC, "name").and(Sort.by("updated"));
		logger.info("sorted courses -> {}", courseRepository.findAll(sort));
	}
	
	@Test
	void pagination() {
		logger.info("pagination");
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = courseRepository.findAll(pageRequest);
		logger.info("first page -> {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = courseRepository.findAll(secondPageable);
		logger.info("second page -> {}", secondPage.getContent());
	}
}
