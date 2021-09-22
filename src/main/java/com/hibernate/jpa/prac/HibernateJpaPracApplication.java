package com.hibernate.jpa.prac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.jpa.prac.entity.Course;
import com.hibernate.jpa.prac.repository.CourseRepository;

@SpringBootApplication
public class HibernateJpaPracApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(HibernateJpaPracApplication.class);

	@Autowired
	CourseRepository courseRepository;
	 
	public static void main(String[] args) {
		SpringApplication.run(HibernateJpaPracApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*logger.info("Course : find by Id -> {}", courseRepository.findById(10001));
		
		logger.info("Course : delete by Id :");
		courseRepository.deleteById(10002);
		
		courseRepository.save(new Course("React JS"));*/
		
		courseRepository.emTracksUnderTransaction();
		
	}

	
}
