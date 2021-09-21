package com.kieshatave.studentRoster.repositories;

import java.util.*;
import org.springframework.data.repository.CrudRepository;
import com.kieshatave.studentRoster.models.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	List<Student> findAll();
}
