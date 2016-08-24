package com.example.therdsak.alarmclock;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Date;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class TimeDialog extends DialogFragment {

    private int timeHour;
    private int timeMinute;
    private Handler handler;



    public static TimeDialog newInstance(Date date){
        TimeDialog timeDialog = new TimeDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ARG_DATE",date);
        timeDialog.setArguments(bundle);
        return timeDialog;

    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        timeHour = bundle.getInt(MyConstants.HOUR);
        timeMinute = bundle.getInt(MyConstants.MINUTE);
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener(){

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                timeHour = hourOfDay;
                timeMinute = minute;
                Bundle b = new Bundle();
                b.putInt(MyConstants.HOUR ,timeHour);
                b.putInt(MyConstants.MINUTE , timeMinute);
                Message msg = new Message();
                msg.setData(b);
              handler.sendMessage(msg);
            }
        };

            return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
    }
}
