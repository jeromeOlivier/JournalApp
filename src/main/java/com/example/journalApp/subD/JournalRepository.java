package com.example.journalApp.subD;

import com.example.journalApp.subD.JournalEntry;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<JournalEntry, Integer> {
    Object findAll(Sort number);
}
