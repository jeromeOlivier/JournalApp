package com.example.journalApp.subD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class JournalController {

    @Autowired
    JournalRepository journalRepository;

    @RequestMapping(value = "/startApp", method = RequestMethod.POST)
    public String listPreviousEntries(ModelMap model) {
        journalAppReset(model);
        return "journalView";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEntry(String title, String body, ModelMap model) {
        if (body.equals("")) {
            journalAppReset(model);
            return "journalView";
        } else {
            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setDate(JournalDefaults.currentDateTime());
            journalEntry.setTitle(title);
            journalEntry.setBody(body);
            journalRepository.save(journalEntry);
            journalAppReset(model);
            return "journalView";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEntry(Integer entryNumber, String title,
                              String body, ModelMap model) {
        if (body.equals("")) {
            journalAppReset(model);
            return "journalView";
        } else {
            Optional<JournalEntry> searchResult = journalRepository.findById(entryNumber);
            if (searchResult.isPresent()) {
                JournalEntry journalEntry = searchResult.get();
                journalEntry.setTitle(title);
                journalEntry.setBody(body);
                journalRepository.save(journalEntry);
            } else {
                return "noEntryFound";
            }
            journalAppReset(model);
            return "journalView";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
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

    public void journalAppReset(ModelMap model) { // generic reset of the interface
        Object entryList = journalRepository.findAll(Sort.by("number").descending());
        model.addAttribute("entryList", entryList)
             .addAttribute("journalAction", "/add")
             .addAttribute("entryTitle", "")
             .addAttribute("entryBody", "");
    }
}