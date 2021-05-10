package com.example.journalApp.subD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.Locale;
import java.util.Optional;

@Controller
public class JournalController {

    @Autowired
    JournalRepository journalRepository;

    @GetMapping("/startApp")
    public String listPreviousEntries(ModelMap model) {
        journalAppReset(model);
        return "journalView";
    }

    @GetMapping(value = "/add")
    public String addEntry(String title, String body, ModelMap model) {
        JournalEntry journalEntry = new JournalEntry();
        LocalDateTime now = LocalDateTime.now();
        String date = (DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                                        .format(now)).toUpperCase(Locale.ROOT);
        String time = (DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                                        .format(now));
        String dateTime = date + " @ " + time;
        journalEntry.setDate(dateTime);
        journalEntry.setTitle(title.toUpperCase(Locale.ROOT));
        journalEntry.setBody(body);
        journalRepository.save(journalEntry);
        journalAppReset(model);
        return "journalView";
    }

    @GetMapping(value = "/edit")
    public String editEntry(Integer entryNumber, ModelMap model) {
        Optional<JournalEntry> searchResult = journalRepository.findById(entryNumber);
        if (searchResult.isPresent()) {
            JournalEntry journalEntry = searchResult.get();
            Object entryList = journalRepository.findAll(Sort.by("number").descending());
            model.addAttribute("entryTitle", journalEntry.title)
                 .addAttribute("entryBody", journalEntry.body)
                 .addAttribute("entryNumber", journalEntry.number)
                 .addAttribute("entryList", entryList)
                 .addAttribute("journalAction", "/update");
            return "journalView";
        } else {
            return "noEntryFound";
        }
    }

    @GetMapping(value = "/update")
    public String updateEntry(Integer entryNumber, String title,
                              String body, ModelMap model) {

        Optional<JournalEntry> searchResult = journalRepository.findById(entryNumber);
        if (searchResult.isPresent()) {
            JournalEntry journalEntry = searchResult.get();
            journalEntry.setTitle(title.toUpperCase(Locale.ROOT));
            journalEntry.setBody(body);
            journalRepository.save(journalEntry);
        } else {
            return "noEntryFound";
        }
        journalAppReset(model);
        return "journalView";
    }

    @GetMapping(value = "/delete")
    public String deleteEntry(Integer entryNumber, ModelMap model) {
        Optional<JournalEntry> searchResult = journalRepository.findById(entryNumber);
        if (searchResult.isPresent()) {
            JournalEntry journalEntry = searchResult.get();
            journalRepository.delete(journalEntry);
        } else {
            return "noEntryFound";
        }
        journalAppReset(model);
        return "journalView";
    }

    public ModelMap journalAppReset(ModelMap model) {
        Object entryList = journalRepository.findAll(Sort.by("number").descending());
        model.addAttribute("entryList", entryList)
             .addAttribute("journalAction", "/add")
             .addAttribute("entryTitle", "")
             .addAttribute("entryBody", "");
        return model;
    }
}