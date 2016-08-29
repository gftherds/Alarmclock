package com.example.therdsak.alarmclock.Database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.therdsak.alarmclock.Database.AlarmDBSchema.AlarmTable;


import com.example.therdsak.alarmclock.Alarm;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class AlarmCursorWrapper extends CursorWrapper{

    public AlarmCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Alarm getAlarm(){
        String uuid = getString(getColumnIndex(AlarmTable.Cols.UUID));
        String name = getString(getColumnIndex(AlarmTable.Cols.NAME));
        long time = getInt(getColumnIndex(AlarmTable.Cols.TIME));
        int isSolve = getInt(getColumnIndex(AlarmTable.Cols.ISSolve));


        Alarm alarm = new Alarm(UUID.fromString(uuid));
        alarm.setTitle(name);
        alarm.setAlarmDate(new Time(time));
        alarm.setSolve(isSolve != 0);
        return alarm;




    }
}
