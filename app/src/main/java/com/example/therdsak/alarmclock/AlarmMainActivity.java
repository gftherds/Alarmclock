package com.example.therdsak.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class AlarmMainActivity extends SingleFragmentActivity {

private static final String TAG= "AlarmMainActivity";
    private static final String ALARM_ID = "AlarmMainActivity.ID";
    private UUID _alarmId;





    @Override
    protected Fragment onCreateFragment() {
        Log.i(TAG, "onCreateFragment: " + _alarmId);
        _alarmId = (UUID) getIntent().getSerializableExtra(ALARM_ID);
        return  AlarmListFragment.newInstance(_alarmId);
    }




    }










