package fr.jhelp.models.calendar.event;

/**
 * Event may happen in calendar
 */
public final class CalendarEvent {
    public final CalendarEventType calendarEventType;
    public final int offset;
    public final int number;

    /**
     * Event may happen in calendar
     * @param calendarEventType Event type
     * @param offset Start element offset
     * @param number Number of elements implies in the change
     */
    public CalendarEvent(CalendarEventType calendarEventType, int offset, int number) {
        this.calendarEventType = calendarEventType;
        this.offset = offset;
        this.number = number;
    }
}
