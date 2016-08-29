package com.example.therdsak.alarmclock.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.therdsak.alarmclock.Database.AlarmDBSchema.AlarmTable;


/**
 * Created by Therdsak on 8/24/2016.
 */
public class AlarmBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "DatabaseAlarm";
    private static final int VERSION = 1;


    public AlarmBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE " + AlarmTable.NAMEDB
                + "("
                + "_id integer primary key autoincrement, "
                + AlarmTable.Cols.UUID + ","
                + AlarmTable.Cols.NAME + ","
                + AlarmTable.Cols.TIME + ","
                + AlarmTable.Cols.ISSolve + ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }
}
