package fr.jhelp.data.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class CalendarDatabaseHelper extends SQLiteOpenHelper {
    private static final String NAME = "CalendarDatabase";
    private static final int VERSION = 1;

    public CalendarDatabaseHelper(@Nullable Context context) {
        super(context, CalendarDatabaseHelper.NAME, null, CalendarDatabaseHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // Nothing to do for now, since it is the first version
    }
}
