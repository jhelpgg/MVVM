package fr.jhelp.mvvm.calendarlist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;

import fr.jhelp.models.calendar.CalendarElement;
import fr.jhelp.mvvm.R;

class CalendarListViewHolder extends RecyclerView.ViewHolder {
    private static final DateFormat DATE_FORMAT = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.DEFAULT);

    private final TextView elementCalendarDate;
    private final TextView elementCalendarDescription;

    CalendarListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.elementCalendarDate = itemView.findViewById(R.id.elementCalendarDate);
        this.elementCalendarDescription = itemView.findViewById(R.id.elementCalendarDescription);
    }

    void calendarElement(@NonNull final CalendarElement calendarElement) {
        this.elementCalendarDate.setText(CalendarListViewHolder.DATE_FORMAT.format(new Date(calendarElement.date.getTimeInMillis())));
        this.elementCalendarDescription.setText(calendarElement.description);
    }
}
