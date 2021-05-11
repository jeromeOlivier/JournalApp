package com.example.journalApp.subD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class JournalTimeStamp {

    // this is the function that generates the date and time stamp
    public static String currentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String date = (DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                                        .format(now)).toUpperCase(Locale.ROOT);
        String time = (DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                                        .format(now));
        return date + " @ " + time;
    }

}
