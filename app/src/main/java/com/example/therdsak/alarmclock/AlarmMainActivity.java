package com.example.therdsak.alarmclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class AlarmMainActivity extends SingleFragmentActivity {


    private int timeMinute;
    private int timeHour;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected Fragment onCreateFragment() {
        return new AlarmListFragment();
    }

    class Myhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            timeHour = bundle.getInt(MyConstants.HOUR);
            timeMinute = bundle.getInt(MyConstants.MINUTE);
            setAlarm();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,timeHour);
        calendar.set(Calendar.MINUTE,timeMinute);
     //   alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis());


    }

    private void cancelAlarm(){
        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}


