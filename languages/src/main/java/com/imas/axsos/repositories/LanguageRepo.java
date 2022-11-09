package com.imas.axsos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imas.axsos.models.Language;

@Repository
public interface LanguageRepo extends CrudRepository<Language, Long> {
	List<Language> findAll();
}