package com.hibernate.jpa.prac;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.jpa.prac.entity.Course;
import com.hibernate.jpa.prac.entity.FullTimeEmployee;
import com.hibernate.jpa.prac.entity.PartTimeEmployee;
import com.hibernate.jpa.prac.entity.Review;
import com.hibernate.jpa.prac.entity.Student;
import com.hibernate.jpa.prac.repository.CourseRepository;
import com.hibernate.jpa.prac.repository.EmployeeRepository;
import com.hibernate.jpa.prac.repository.StudentRepository;

@SpringBootApplication
public class HibernateJpaPracApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(HibernateJpaPracApplication.class);

	@Autowired
	private CourseRepository courseRepository;
	 
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HibernateJpaPracApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*logger.info("Course : find by Id -> {}", courseRepository.findById(10001));
		
		logger.info("Course : delete by Id :");
		courseRepository.deleteById(10002);
		
		courseRepository.save(new Course("React JS"));*/
		
		//courseRepository.emTracksUnderTransaction();
		
		//studentRepository.saveStudentWithPassport();
		
		//studentRepository.persistanceContextWithTransactionUnderstad();
		
		//courseRepository.addReviewToCourse();
		
		/*List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review("5", "Awosme course and easy to learn"));
		reviews.add(new Review("5", "Super."));
		courseRepository.addReviewToCourse(10003, reviews);*/
       
		//studentRepository.saveStudentAndCourse();
		
		//studentRepository.saveStudentAndCourse(new Student("Narayan"), new Course("Google cloud platform"));
		
		/*employeeRepository.insert(new PartTimeEmployee("Ganesh", new BigDecimal("300")));
		employeeRepository.insert(new FullTimeEmployee("Kartik", new BigDecimal("150000")));
		
		//logger.info("employees -> {}", employeeRepository.retrieveEmployee());
		
		logger.info("parttime employees -> {}", employeeRepository.retrievePartTimeEmployee());
		logger.info("\nfulltime employees -> {}", employeeRepository.retrieveFullTimeEmployee());*/
		
		//studentRepository.setAddressToStudentAndGetAddress();
	}
}
