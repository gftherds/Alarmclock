package com.example.therdsak.alarmclock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class AlarmMainActivity extends SingleFragmentActivity {


    @Override
    protected Fragment onCreateFragment() {
        return new AlarmListFragment();
    }
}


