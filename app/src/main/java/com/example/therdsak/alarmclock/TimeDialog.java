package com.example.therdsak.alarmclock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class TimeDialog extends DialogFragment implements DialogInterface.OnClickListener{
        private static final String TAG ="TimeDialog1";


    public static final String EXTRA_TIME = "TimeDialog";



    TimePicker timePicker;
    Date date;


    public static TimeDialog newInstance(Date date) {
        TimeDialog timeDialog = new TimeDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ARG_DATE", date);
        timeDialog.setArguments(bundle);
        return timeDialog;

    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        date = (Date) getArguments().getSerializable("ARG_DATE");
        Log.i(TAG, "onCreateDialog: " + date);



        Calendar calendar = Calendar.getInstance();
        if(date == null){
            date  = new Date();
        }
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        timePicker = (TimePicker) v.findViewById(R.id.time_picker_dialog_1);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        }
        builder.setView(v);
        builder.setPositiveButton(android.R.string.ok, this);
        return builder.show();




//        Bundle bundle = getArguments();
//        timeHour = bundle.getInt(MyConstants.HOUR);
//        timeMinute = bundle.getInt(MyConstants.MINUTE);
//        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener(){

//            @Override
//            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
//                timeHour = hourOfDay;
//                timeMinute = minute;
//                Bundle b = new Bundle();
//                b.putInt(MyConstants.HOUR ,timeHour);
//                b.putInt(MyConstants.MINUTE , timeMinute);
//                Message msg = new Message();
//                msg.setData(b);
//              handler.sendMessage(msg);
//            }
//        };
//
//            return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
//    }
    }



    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        int hour = 0;
        int minute = 0;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE,minute);
        sendResult(Activity.RESULT_OK,calendar.getTime());






//        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode ,intent);
    }

    private void sendResult(int resultCode, Date date) {
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME , date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode , intent);
    }
}
