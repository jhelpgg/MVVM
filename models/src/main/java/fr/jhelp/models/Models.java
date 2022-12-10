package fr.jhelp.models;

import fr.jhelp.models.calendar.CalendarModel;
import fr.jhelp.tools.injector.Injector;

public class Models {
    static {
        CalendarModel.initialization();
    }

    public static CalendarModel calendarModel() {
        return Injector.instance(CalendarModel.class);
    }
}
