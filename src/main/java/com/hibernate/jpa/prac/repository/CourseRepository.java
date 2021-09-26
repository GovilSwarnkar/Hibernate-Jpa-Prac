package com.hibernate.jpa.prac.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.jpa.prac.entity.Course;
import com.hibernate.jpa.prac.entity.Review;

@Repository
@Transactional
public class CourseRepository {//for performing update operation transaction should be
	
	private static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);

	@Autowired
	EntityManager entityManager; //entity manager to persistence context keep track of every db operation for specific transaction
	
	public Course findById(Integer id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteById(Integer id) {
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId() == null) {
			entityManager.persist(course); // insert
		}else {
			entityManager.merge(course); //update
		}
		return course;
	}
	
	public void addReviewToCourse() {
		//get course
		Course course = findById(10003);
		logger.info("review -> {}", course.getReviews());
		
		//for adding review to course
		Review review1 = new Review("5", "Awosme course and easy to learn");
		Review review2 = new Review("5", "Super.");
		
		//setting relationship btw course and review
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to database
		entityManager.persist(review1);
		entityManager.persist(review2);
	}
	
	
	public void addReviewToCourse(Integer courseId, List<Review> reviews) {
		Course course = findById(courseId);
		for(Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			entityManager.persist(review);
		}
	}
	
	public void emTracksUnderTransaction() { //entity manager tracks all operation within the transaction
		/*Course course = new Course("Google Data Store");
		entityManager.persist(course);
		//here updating name after calling persist but then also it will be reflect into db bcz of transaction 
		course.setName("Google Data Store - updated");*/
		
		Course course1 = new Course("Google Data Store");
		Course course2 = new Course("Content Stack");
		
		entityManager.persist(course1);
		entityManager.persist(course2);
		
		entityManager.flush(); // make change into database
		
		//entityManager.detach(course1); //we can detach course from entity manager and it will no more track by em
		//entityManager.detach(course2);
		
		//or there is alternative way to clear every tracking information from em use clear
		//entityManager.clear(); //em will not track any courses
		
		//whatever changes after clear method nothing will be reflect to db and in case of detach same for specific
		
		/*course1.setName("Google Data Store - updated");
		entityManager.flush();
		
		course2.setName("Content Stack - updated");
		entityManager.flush();*/
		
		course1.setName("Google Data Store - updated");
		course2.setName("Content Stack - updated");
		
		logger.info("before refresh course1 -> {}", course1 );
		
		/*course1 data will be refreshed with old data which persisted first
		and new data will not be in db as well in course1 object after refreshed*/
		//entityManager.refresh(course1); 
		
		logger.info("after refresh course1 -> {}", course1 );
		
		entityManager.flush();
	}
}
