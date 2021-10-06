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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Course {

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
