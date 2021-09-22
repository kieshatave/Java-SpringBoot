package com.kieshatave.studentRoster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.kieshatave.studentRoster.models.*;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Long>{
	List<StudentCourse> findAll();
}
