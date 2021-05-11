package com.example.journalApp.subD;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface JournalRepository extends CrudRepository<JournalEntry, Integer> {
    Object findAll(Sort number);
}
