package com.kieshatave.studentRoster.repositories;

import java.util.*;
import org.springframework.data.repository.CrudRepository;
import com.kieshatave.studentRoster.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	List<Contact> findAll();
}
