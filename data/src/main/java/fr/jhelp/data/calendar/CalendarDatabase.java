package fr.jhelp.data.calendar;

import android.content.Context;

import fr.jhelp.tools.injector.Injector;

public class CalendarDatabase {
    public static final CalendarDatabase INSTANCE = new CalendarDatabase();
    private final CalendarDatabaseHelper calendarDatabaseHelper;

    private CalendarDatabase() {
        this.calendarDatabaseHelper = new CalendarDatabaseHelper(Injector.instance(Context.class));
    }
}
