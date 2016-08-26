package com.example.therdsak.alarmclock;

import android.support.v4.app.Fragment;

/**
 * Created by Therdsak on 8/26/2016.
 */
public class AlarmListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment onCreateFragment() {
        return new AlarmListFragment();
    }
}
