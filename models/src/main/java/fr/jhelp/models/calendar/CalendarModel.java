package fr.jhelp.models.calendar;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

import fr.jhelp.models.calendar.event.CalendarEvent;
import fr.jhelp.tools.injector.Injector;
import kotlinx.coroutines.flow.Flow;

/**
 * Calendar model
 */
public abstract class CalendarModel extends ViewModel {
    /**
     * Initialize the default model
     */
    public static void initialization() {
        Injector.inject(CalendarModel.class, new CalendarModelImplementation());
    }

    /**
     * Events flow, plug to it to receive changes
     *
     * @return Events flow
     */
    @NonNull
    public abstract Flow<CalendarEvent> events();

    /**
     * Define an element for a given date.<br>
     * Created if not exists<br>
     * Updated if exists
     *
     * @param date        Event date
     * @param description Event description
     */
    public abstract void set(@NonNull final Calendar date, @NonNull final String description);

    /**
     * Remove element link to given date
     *
     * @param date Date to remove
     */
    public abstract void remove(@NonNull final Calendar date);

    /**
     * Number of elements
     *
     * @return Number of elements
     */
    public abstract int numberElements();

    /**
     * Obtain an element by it's index
     *
     * @param index Element's index
     * @return Corresponding element
     */
    @NonNull
    public abstract CalendarElement get(int index);
}
