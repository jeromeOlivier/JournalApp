package com.example.journalApp.subD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class JournalController {

    @Autowired
    JournalRepository journalRepository;

    @GetMapping(value = "/add")
    public String addEntry(String title, String body, ModelMap model) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle(title);
        journalEntry.setDate(java.util.Date.from(Instant.now()));
        journalEntry.setBody(body);
        journalRepository.save(journalEntry);
        model.addAttribute("journalEntry", journalRepository.findAll(Sort.by("number").descending()));

        return "journalView";
    }

    @GetMapping("/startApp")
    public String listPreviousEntries(ModelMap model) {
        model.addAttribute("journalEntry", journalRepository.findAll(Sort.by("number").descending()));
        return "journalView";
    }

    @GetMapping(value = "/edit")
    public String editEntry(Integer entryNumber, ModelMap model) {
//        entryNumber = Integer.parseInt(entryString);
        Optional<JournalEntry> searchResult = journalRepository.findById(entryNumber);
        if (searchResult.isPresent()) {
            JournalEntry journalEntry = searchResult.get();
            model.addAttribute("entryToUpdate", journalEntry);
            return "journalEdit";
        } else {
            return "noEntryFound";
        }
    }
}

