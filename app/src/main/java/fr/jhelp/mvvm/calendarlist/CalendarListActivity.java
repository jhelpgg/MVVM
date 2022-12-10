package fr.jhelp.mvvm.calendarlist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.jhelp.models.Models;
import fr.jhelp.models.calendar.CalendarModel;
import fr.jhelp.mvvm.R;
import fr.jhelp.tools.Tools;
import fr.jhelp.tools.coroutine.Coroutines;
import fr.jhelp.tools.injector.Injector;

public class CalendarListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_calendar);

        Injector.inject(Context.class, this.getApplicationContext());

        final RecyclerView recyclerView = this.findViewById(R.id.calendarListRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CalendarListAdapter());
        this.findViewById(R.id.addElementFloatingButton).setOnClickListener(this::clickView);

        // For test we add manually some elements
        Coroutines.launch(
                () ->
                {
                    final CalendarModel calendarModel = Models.calendarModel();
                    calendarModel.set(Tools.createCalendar(1985, 1, 24, 16, 32), "Destiny encounter");
                    calendarModel.set(Tools.createCalendar(2000, 1, 1, 0, 0), "2000's bug");
                    calendarModel.set(Tools.createCalendar(2023, 1, 1, 0, 0), "Happy new year !");
                });
    }

    private void clickView(@NonNull final View view) {
        final int id = view.getId();

        if (id == R.id.addElementFloatingButton) {
            this.addElement();
        }
    }

    private void addElement() {
        // TODO
    }
}
