package fr.jhelp.tools;

import java.util.Calendar;

public class Tools {
    public static Calendar createCalendar(final int year, final int month, final int day, final int hour, final int minute) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
