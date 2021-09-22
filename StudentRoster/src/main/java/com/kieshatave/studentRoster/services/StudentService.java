package com.kieshatave.studentRoster.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.kieshatave.studentRoster.models.Student;
import com.kieshatave.studentRoster.repositories.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository repo;
	
	public StudentService(StudentRepository repo) {
		this.repo = repo;
	}
	
	public List<Student> allStudent() {
		return repo.findAll();
	}
	
	public Student createStudent(Student student) {
		return this.repo.save(student);
	}
	
	public Student findStudent(long id) {
		Optional<Student> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Student remove(Long id) {
		Student editDorm = findStudent(id);
		editDorm.setDorm(null);
		return repo.save(editDorm);
	}
}
