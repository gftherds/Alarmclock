package com.example.therdsak.alarmclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class AlarmMainActivity extends SingleFragmentActivity {


    private static final String ALARM_ID = "AlarmMainActivity";
    private UUID _alarmId;

    @Override
    protected Fragment onCreateFragment() {
        _alarmId = (UUID) getIntent().getSerializableExtra(ALARM_ID);
        return  AlarmListFragment.newInstance(_alarmId);
    }


    }










