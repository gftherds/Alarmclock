package com.example.therdsak.alarmclock;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Therdsak on 8/25/2016.
 */
public class AlarmLab {
    private static AlarmLab instance;
    List<Alarm> mAlarmList;
    private List<Alarm> alarm;

    public AlarmLab() {
        mAlarmList = new ArrayList<>();
        int size = mAlarmList.size();
        for (int i = 0; i < size; i++) {
            mAlarmList.get(i);
        }
    }

    public static AlarmLab getInstance(Context context) {
        if (instance == null) {
            instance = new AlarmLab();
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
        List<Alarm> alarms = new ArrayList<>();
        return alarms;
    }
}

