package com.kieshatave.studentRoster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kieshatave.studentRoster.models.StudentCourse;
import com.kieshatave.studentRoster.repositories.StudentCourseRepository;

@Service
public class StudentCourseService {
	private final StudentCourseRepository repo;
	public StudentCourseService(StudentCourseRepository repo) {
		this.repo = repo;
	}
	
	public List<StudentCourse> allStudentCourses() {
		return repo.findAll();
	}
	
	public StudentCourse find(Long id) {
		Optional<StudentCourse> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public StudentCourse addCourse(StudentCourse course) {
		return repo.save(course);
	}
	
	public void saveCourse(StudentCourse course) {
		this.repo.save(course);
	}
	
	public void removeCourse(Long id) {
		this.repo.deleteById(id);
	}
}
