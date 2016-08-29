package com.example.therdsak.alarmclock;

import android.content.Context;
import android.util.Log;

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


    public AlarmLab(Context context) {
        mAlarmList = new ArrayList<>();
        int size = mAlarmList.size();
        for (int i = 0; i < size; i++) {
            mAlarmList.get(i);
        }
    }

    public static AlarmLab getInstance(Context context) {
        if (instance == null) {
            instance = new AlarmLab(context);
        }
        return instance;
    }

    public Alarm getAlarmById(UUID uuid) {
        for (Alarm alarm : mAlarmList) {
            if (alarm.getId().equals(uuid)) {
                return alarm;
            }
        }
        return null;
    }




    public List<Alarm> getAlarm() {

        if (mAlarmList==null) {

            mAlarmList = new ArrayList<>();
        }
        return mAlarmList;

    }

    public void addAlarm(Alarm alarm) {
        mAlarmList.add(alarm);
    }


    public void deleteAlarm(UUID uuid){
        int i= 0;
        Log.i(TAG, "deleteAlarm11111: " + uuid);
        for (Alarm alarm : mAlarmList){
            Log.i(TAG, "deleteAlarm: "+ alarm.getId());
            if(alarm.getId().equals(uuid)){
                mAlarmList.remove(i);
                Log.i(TAG, "deleteAlarm: " + mAlarmList);
            }
            i++;
        }
    }

    public void updateAlarm(Alarm alarm){
        String uuidStr = alarm.getId().toString();
    }

}

