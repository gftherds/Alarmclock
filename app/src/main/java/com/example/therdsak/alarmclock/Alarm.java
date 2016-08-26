package com.example.therdsak.alarmclock;

import java.util.Date;
import java.util.UUID;

/**
 * Created by therdsak on 8/24/16.
 */
public class Alarm {
    private Date alarmDate;
    private boolean slove;

    public void setTitle(String title) {
        this.title = title;
    }

    private UUID id;
    private String title;

    public Alarm() {
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

    public String getTitle() {
        return title;
    }

    public boolean isSolve() {
        return slove;
    }
    public void setSolve(boolean solve){
        this.isSolve();
    }
}
