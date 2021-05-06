package com.example.journalApp.subD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int number; // auto generate increment
    Date date;
    String title;
    String body;

    public JournalEntry(String title, Date date, String body) {
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public JournalEntry() {
    }

    public int getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}