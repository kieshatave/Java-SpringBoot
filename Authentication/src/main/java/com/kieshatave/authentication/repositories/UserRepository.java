package com.kieshatave.authentication.repositories;

import org.springframework.data.repository.CrudRepository;
import com.kieshatave.authentication.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
