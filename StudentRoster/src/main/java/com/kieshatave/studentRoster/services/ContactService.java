package com.kieshatave.studentRoster.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.kieshatave.studentRoster.models.Contact;
import com.kieshatave.studentRoster.repositories.ContactRepository;

@Service
public class ContactService {
	public final ContactRepository repo;
	
	public ContactService(ContactRepository repo) {
		this.repo = repo;
	}
	
	public void addContact(Contact contact) {
		repo.save(contact);
	}
	
	public List<Contact> allContact() {
		return repo.findAll();
	}
	
	public Contact createContact(Contact contact) {
		return repo.save(contact);
	}
	
	public Contact findContact(Long id) {
		Optional<Contact> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
}
