package com.hibernate.jpa.prac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hibernate.jpa.prac.entity.Course;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Integer>{
	
	public List<Course> findByName(String name);
	public List<Course> findByNameAndId(String name, Integer id);
	public List<Course> findByNameOrderByIdDesc(String name);
	public List<Course> deleteByName(String name);
	
	@Query("select c from Course c where name like 'Hib%'")  //jpql
	public List<Course> courseHibInName(String name);
	
	@Query(value = "select * from Course c where name like 'Hib%'", nativeQuery = true) //native query
	public List<Course> courseHibInNameUsingNativeQuery(String name);
	
	@Query(name = "find_all_course") //named query
	public List<Course> courseHibInNameUsingNamedQuery(String name);
}
