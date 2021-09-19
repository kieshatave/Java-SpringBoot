package com.kieshatave.grouplang.repositories;

import java.util.*;
import org.springframework.data.repository.*;
import com.kieshatave.grouplang.models.Language;

public interface LanguageRepository extends CrudRepository<Language, Long>{
	List<Language> findAll();
}
