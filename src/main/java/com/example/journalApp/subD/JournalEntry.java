package com.example.journalApp.subD;

import javax.persistence.*;
import java.util.Date;

@Entity
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int number; // auto generate increment
    String date;
    String title;
    @Column(length=5000)
    String body;

    public JournalEntry(String title, String date, String body) {
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public JournalEntry() {
    }

    public int getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}