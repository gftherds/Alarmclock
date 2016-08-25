package com.example.therdsak.alarmclock;

import java.util.Date;
import java.util.UUID;

/**
 * Created by therdsak on 8/24/16.
 */
public class Alarm {
    private Date alarmDate;
    private UUID id;

    public Alarm(){
        this(UUID.randomUUID());
    }

    public Alarm(UUID uuid) {
        id = uuid;
        alarmDate = new Date();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }



    public Date getAlarmDate() {
        return alarmDate;
    }
}
