package com.kieshatave.grouplang.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.kieshatave.grouplang.models.Language;
import com.kieshatave.grouplang.repositories.LanguageRepository;

@Service
public class LanguageService {
	
	private final LanguageRepository repository;
	
	public LanguageService(LanguageRepository repository) {
		this.repository = repository;
	}
	
	public List<Language> all() {
		return this.repository.findAll();
	}
	
	public Language create(Language newLang) {
		return this.repository.save(newLang);
	}
	
	public void save(Language language) {
		this.repository.save(language);
	}
	
	public Language findById(Long id) {
		Optional<Language> optional = this.repository.findById(id);
		if( optional.isPresent() ) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Language update(Long id, Language langUpdate) {
		
		Language language = this.findById(id);
		
		if( language != null ) {
			language.setName(langUpdate.getName());
			language.setCreator(langUpdate.getCreator());
			language.setCurrentVersion(langUpdate.getCurrentVersion());
			this.save(language);
			return language;
		}
		
		return null;
	}
	
	public void delete(Language language) {
		this.repository.delete(language);
	}
}
