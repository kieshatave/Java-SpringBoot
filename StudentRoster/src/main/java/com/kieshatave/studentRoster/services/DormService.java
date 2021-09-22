package com.kieshatave.studentRoster.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.kieshatave.studentRoster.models.Dorm;
import com.kieshatave.studentRoster.repositories.DormRepository;

@Service
public class DormService {
	private final DormRepository repo;
	public DormService(DormRepository repo) {
		this.repo = repo;
	}
	
	public List<Dorm> allDorms() {
		return repo.findAll();
	}
	
	public Dorm createDorm(Dorm dorm) {
		return this.repo.save(dorm);
	}
	
	public Dorm findDorm(Long id) {
		Optional<Dorm> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void removeStudent(Long id) {
		this.repo.deleteById(id);
	}
}
