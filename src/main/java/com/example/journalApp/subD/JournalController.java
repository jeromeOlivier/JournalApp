package com.example.journalApp.subD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class JournalController {

    @Autowired
    JournalRepository journalRepository;

    @GetMapping(value = "/add")
    public String addEntry(String title, Date date, String body) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle(title);
        journalEntry.setDate(java.util.Calendar.getInstance().getTime());
        journalEntry.setBody(body);
        journalRepository.save(journalEntry);

        return "journalView";
    }

    @GetMapping("/startApp")
    public String listPreviousEntries(ModelMap model) {
        model.addAttribute("journalEntry", journalRepository.findAll());
        return "journalView";
    }
}

