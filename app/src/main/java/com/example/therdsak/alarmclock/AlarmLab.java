package com.example.therdsak.alarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.therdsak.alarmclock.Database.AlarmBaseHelper;
import com.example.therdsak.alarmclock.Database.AlarmCursorWrapper;
import com.example.therdsak.alarmclock.Database.AlarmDBSchema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Therdsak on 8/25/2016.
 */
public class AlarmLab {

    private static final String TAG = "AlarmLab";
    private static AlarmLab instance;
    List<Alarm> mAlarmList;
    SQLiteDatabase database;
    public AlarmLab(Context context) {
        context = context.getApplicationContext();
        AlarmBaseHelper alarmBaseHelper = new AlarmBaseHelper(context);
        database = alarmBaseHelper.getWritableDatabase();
    }

    private static ContentValues getContentValues(Alarm alarm){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AlarmDBSchema.AlarmTable.Cols.UUID , alarm.getId().toString());
        contentValues.put(AlarmDBSchema.AlarmTable.Cols.NAME , alarm.getTitle());
        contentValues.put(AlarmDBSchema.AlarmTable.Cols.TIME , alarm.getAlarmDate().getTime());
        contentValues.put(AlarmDBSchema.AlarmTable.Cols.ISSolve , (alarm.isSolve()) ? 1 : 0 );
        return contentValues;
    }


    public static AlarmLab getInstance(Context context) {
        if (instance == null) {
            instance = new AlarmLab(context);
        }
        return instance;
    }

    public Alarm getAlarmById(UUID uuid) {
        AlarmCursorWrapper cursor = queryAlarms(AlarmDBSchema.AlarmTable.Cols.UUID + " = ? " , new String[] { uuid.toString() });
        try {
            if(cursor.getCount() == 0 ){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAlarm();
        }finally {
            cursor.close();
        }
    }




    public List<Alarm> getAlarm() {

        List<Alarm> alarms = new ArrayList<>();

        AlarmCursorWrapper cursor = queryAlarms(null, null);

        try {
            cursor.moveToFirst();
            while ( !cursor.isAfterLast() ){
                alarms.add(cursor.getAlarm());
                cursor.moveToNext();

            }
        } finally {
            cursor.close();
        }

        return alarms;
    }

    private AlarmCursorWrapper queryAlarms(String whereCause,String[] whereArgs) {
        Cursor cursor = database.query(AlarmDBSchema.AlarmTable.NAMEDB,
                null,
                whereCause,
                whereArgs,
                null,
                null,
                null);

        return new AlarmCursorWrapper(cursor);
    }

    public void addAlarm(Alarm alarm) {
        ContentValues contentValues = getContentValues(alarm);
        database.insert(AlarmDBSchema.AlarmTable.NAMEDB,null,contentValues);
    }


    public void deleteAlarm(UUID uuid){
        database.delete(AlarmDBSchema.AlarmTable.NAMEDB, AlarmDBSchema.AlarmTable.Cols.UUID + " = ?", new String[]{ uuid.toString() });
        }
    }




