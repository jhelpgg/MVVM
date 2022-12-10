package fr.jhelp.mvvm.calendarlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.jhelp.models.Models;
import fr.jhelp.models.calendar.event.CalendarEvent;
import fr.jhelp.mvvm.R;
import fr.jhelp.tools.coroutine.Coroutines;

class CalendarListAdapter extends RecyclerView.Adapter<CalendarListViewHolder> {
    CalendarListAdapter() {
        Coroutines.collectFlowMain(Models.calendarModel().events(), this::event);
    }

    @NonNull
    @Override
    public CalendarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CalendarListViewHolder(layoutInflater.inflate(R.layout.element_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarListViewHolder holder, int position) {
        holder.calendarElement(Models.calendarModel().get(position));
    }

    @Override
    public int getItemCount() {
        return Models.calendarModel().numberElements();
    }

    private void event(@NonNull final CalendarEvent calendarEvent) {
        switch (calendarEvent.calendarEventType) {
            case ELEMENT_ADD:
                if (calendarEvent.number == 1) {
                    this.notifyItemInserted(calendarEvent.offset);
                } else {
                    this.notifyItemRangeInserted(calendarEvent.offset, calendarEvent.number);
                }
                break;
            case ELEMENT_CHANGED:
                if (calendarEvent.number == 1) {
                    this.notifyItemChanged(calendarEvent.offset);
                } else {
                    this.notifyItemRangeChanged(calendarEvent.offset, calendarEvent.number);
                }
                break;
            case ELEMENT_REMOVED:
                if (calendarEvent.number == 1) {
                    this.notifyItemRemoved(calendarEvent.offset);
                } else {
                    this.notifyItemRangeRemoved(calendarEvent.offset, calendarEvent.number);
                }
                break;
        }
    }
}
