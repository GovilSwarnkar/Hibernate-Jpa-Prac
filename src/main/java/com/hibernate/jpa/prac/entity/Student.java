package com.hibernate.jpa.prac.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY) //lazy fetching when password will be fetch then only it will fetch from db, by default one to one mapping is eager fetching
	private Passport passport;

	@ManyToMany //by default ManyToMany is lazy fetching
	@JoinTable(name = "STUDENT_COURSE",
			   joinColumns = @JoinColumn(name = "STUDENT_ID"),
			   inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))  //JoinTable use at owning side
	List<Course> courses = new ArrayList<Course>();
	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public void setCourses(List<Course>  courses) throws Throwable  {
		throw new Throwable("You can not set list of courses");
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
	
	@Override
	public String toString() {
		return "\nStudent [id=" + id + ", name=" + name + "]";
	}

}
