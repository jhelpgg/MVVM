package fr.jhelp.models;

import java.util.concurrent.atomic.AtomicBoolean;

import fr.jhelp.models.calendar.CalendarModel;
import fr.jhelp.tools.injector.Injector;

public class Models {
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    private static void initialize() {
        if (Models.initialized.compareAndSet(false, true)) {
            CalendarModel.initialization();
        }
    }

    public static CalendarModel calendarModel() {
        Models.initialize();
        return Injector.instance(CalendarModel.class);
    }
}
