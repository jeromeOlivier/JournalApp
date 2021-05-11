package com.example.journalApp.subD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class JournalDefaults {

    @Autowired
    JournalRepository journalRepository;

    public static String currentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String date = (DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                                        .format(now)).toUpperCase(Locale.ROOT);
        String time = (DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                                        .format(now));
        return date + " @ " + time;
    }

}
