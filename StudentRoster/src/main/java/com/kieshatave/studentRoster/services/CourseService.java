package com.kieshatave.studentRoster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kieshatave.studentRoster.models.Course;
import com.kieshatave.studentRoster.repositories.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository repo;
	public CourseService(CourseRepository repo) {
		this.repo = repo;
	}
	
	public List<Course> allCourses() {
		return repo.findAll();
	}
	
	public Course find(Long id) {
		Optional<Course> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Course addCourse(Course course) {
		return repo.save(course);
	}
	
	public void saveCourse(Course course) {
		this.repo.save(course);
	}
	
	public void removeCourse(Long id) {
		this.repo.deleteById(id);
	}
}
