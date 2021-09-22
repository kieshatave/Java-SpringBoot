package com.kieshatave.studentRoster.repositories;

import java.util.*;
import org.springframework.data.repository.CrudRepository;
import com.kieshatave.studentRoster.models.Dorm;

public interface DormRepository extends CrudRepository<Dorm, Long>{
	List<Dorm> findAll();
}
