package com.hibernate.jpa.prac.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
