package fr.jhelp.models.calendar;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;

import fr.jhelp.models.calendar.event.CalendarEvent;
import fr.jhelp.models.calendar.event.CalendarEventType;
import fr.jhelp.tools.coroutine.Coroutines;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

final class CalendarModelImplementation extends CalendarModel {
    private final MutableSharedFlow<CalendarEvent> events =
            SharedFlowKt.<CalendarEvent>MutableSharedFlow(0, 0, BufferOverflow.DROP_OLDEST);
    private final ArrayList<CalendarElement> elements = new ArrayList<CalendarElement>();

    @NonNull
    @Override
    public Flow<CalendarEvent> events() {
        return this.events;
    }

    @Override
    public void set(@NonNull Calendar date, @NonNull String description) {
        final long time = date.getTimeInMillis();
        int indexToInsert = -1;
        boolean override = false;
        final int size;

        synchronized (this.elements) {
            size = this.elements.size();

            for (int index = 0; index < size; index++) {
                final CalendarElement calendarElement = this.elements.get(index);
                final long timeTested = calendarElement.date.getTimeInMillis();

                if (time == timeTested) {
                    indexToInsert = index;
                    override = true;
                    break;
                }

                if (time > timeTested) {
                    indexToInsert = index;
                    break;
                }
            }

            if (override) {
                this.elements.set(indexToInsert, new CalendarElement(date, description));
            } else if (indexToInsert >= 0) {
                this.elements.add(indexToInsert, new CalendarElement(date, description));
            } else {
                this.elements.add(new CalendarElement(date, description));
            }
        }

        if (override) {
            this.dispatch(CalendarEventType.ELEMENT_CHANGED, indexToInsert, 1);
        } else if (indexToInsert >= 0) {
            this.dispatch(CalendarEventType.ELEMENT_ADD, indexToInsert, 1);
        } else {
            this.dispatch(CalendarEventType.ELEMENT_ADD, size, 1);
        }
    }

    @Override
    public void remove(@NonNull Calendar date) {
        final long time = date.getTimeInMillis();
        int indexToRemove = -1;

        synchronized (this.elements) {
            final int size = this.elements.size();

            for (int index = 0; index < size; index++) {
                if (time == this.elements.get(index).date.getTimeInMillis()) {
                    indexToRemove = index;
                    break;
                }
            }

            if (indexToRemove >= 0) {
                this.elements.remove(indexToRemove);
            }
        }

        if (indexToRemove >= 0) {
            this.dispatch(CalendarEventType.ELEMENT_REMOVED, indexToRemove, 1);
        }
    }

    @Override
    public int numberElements() {
        synchronized (this.elements) {
            return this.elements.size();
        }
    }

    @NonNull
    @Override
    public CalendarElement get(int index) {
        synchronized (this.elements) {
            return this.elements.get(index);
        }
    }

    private void dispatch(@NonNull final CalendarEventType calendarEventType, final int offset, final int number) {
        final CalendarEvent calendarEvent = new CalendarEvent(calendarEventType, offset, number);
        Coroutines.launch(() -> this.events.tryEmit(calendarEvent));
    }
}
