package fr.jhelp.models.calendar;

import java.util.Calendar;

public final class CalendarElement {
    public final Calendar date;
    public final String description;

    public CalendarElement(Calendar date, String description) {
        this.date = date;
        this.description = description;
    }
}
