package com.hibernate.jpa.prac.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hibernate.jpa.prac.HibernateJpaPracApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = {
			@NamedQuery(name="find_all_course", query="select c from Course c"),
			@NamedQuery(name="find_course_where_Hib", query="select c from Course c where name like 'Hib%'")  
		     })
//@NamedQuery(name="find_all_course", query="select c from Course c")
//@NamedQuery(name="find_course_where_Hib", query="select c from Course c where name like 'Hib%'")
@Cacheable // by default second level ehcache is true
//performing 1 L2C puts - mean there is no data for id 10001 and hibernate put it to ehcache from L2C misses
//performing 1 L2C hits - mean there is data is already into ehcache and use from cache
//performing 0 L2C misses - mean there is no data for id 10001 and hibernate put it to ehcache L2C misses
//http://localhost:8080/courses/10001 - on first hit course will be fetch from db, second onward from ehcache
@SQLDelete(sql="update course set is_deleted = true where id = ?") //set true whenever delete by id will be fired
@Where(clause="is_deleted = false") //if is_deleted is set to false then only find by id and return record
//soft delete - @SQLDelete, @Where and isDeleted combination
//in case of soft delete - in case of native query we have to use where is_deleted=0 manually, 
//ex - select * from course where is_deleted=0
//in case of soft delete - if using entity manager to remove course then need to setIsDeleted as true 
//bcz in db value will be true but in entity value will not be updated
//second solution we can use preRemove cycle method which will be called before deletion
public class Course {

	private static final Logger logger = LoggerFactory.getLogger(Course.class);
	
	@Id
	@GeneratedValue
	private Integer id;
	 
	@Column(nullable = false) //restrict to insert null value to name column into table
	private String name;
	
	@OneToMany(mappedBy = "course") //by default OneToMany is lazy fetch type
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy = "courses") //student is owning side
	@JsonIgnore
	List<Student> students = new ArrayList<Student>();
	
	@UpdateTimestamp //hibernate annotation not jpa, use to set update time
	private LocalDateTime created;
	
	@CreationTimestamp //hibernate annotation not jpa, use to set when first time record will be create
	private LocalDateTime updated;
	
	private boolean isDeleted; //for soft deletion
	
	@PreRemove  //some of others hooks - @PostRemove, @PostLoad, @PostPersist, @PostUpdate same as @PreX
	public void preRemove() {
		logger.info("preRemove to set isDeleted as true");
		this.isDeleted = true;
	}
	
	public Course(String name) {
		this.name = name;
	}
	
	public void setReviews(List<Review> reviews) throws Throwable {
		throw new Throwable("You can set list of reviews");
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public void setStudents(List<Student> students) throws Throwable {
		throw new Throwable("You can set list of students");
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}

	@Override
	public String toString() {
		return "\nCourse [id=" + id + ", name=" + name + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
}
