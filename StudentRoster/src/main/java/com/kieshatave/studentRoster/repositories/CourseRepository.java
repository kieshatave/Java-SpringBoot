package com.kieshatave.studentRoster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.kieshatave.studentRoster.models.*;

public interface CourseRepository extends CrudRepository<Course, Long>{
	List<Course> findAll();
}
