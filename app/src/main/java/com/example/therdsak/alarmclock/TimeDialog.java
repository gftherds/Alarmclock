package com.example.therdsak.alarmclock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class TimeDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TAG = "TimeDialog1";


    private static final int REQUEST_CODE = 200;



    EditText textView;
    TimePicker timePicker;
    Switch aSwitch;
    AlarmLab mAlarmLab;
    Alarm mAlarm;
    Date date;
    UUID uuid;

    public static TimeDialog newInstance(UUID uuid) {
        TimeDialog timeDialog = new TimeDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ARG_DATE", uuid);
        Log.d("time dialog uuid ", uuid.toString());
        timeDialog.setArguments(bundle);
        return timeDialog;

    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        mAlarmLab = AlarmLab.getInstance(getActivity());
        uuid = (UUID) getArguments().getSerializable("ARG_DATE");
        mAlarm = mAlarmLab.getInstance(getActivity()).getAlarmById(uuid);


//        mAlarm = mAlarmLab.getAlarmById(uuid);
//        Log.i(TAG, "onCreateDialog: " + mAlarm.getId());
        Log.i(TAG, "onCreateDialog: " + uuid);

        timePicker = (TimePicker) v.findViewById(R.id.time_picker_dialog_1);
        textView = (EditText) v.findViewById(R.id.editText);
        aSwitch = (Switch) v.findViewById(R.id.switch_dialog_on_off);


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        if (mAlarm != null) {
            calendar.setTime(mAlarm.getAlarmDate());
            timePicker.setHour(hour);
            timePicker.setMinute(min);
        }
        mAlarmLab = AlarmLab.getInstance(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        builder.setPositiveButton("Save", this);
        builder.setNegativeButton("Cancel", null);
        return builder.create();


    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        mAlarm = new Alarm();
        Intent intent = new Intent();
        Calendar calendar = Calendar.getInstance();
        int hour = 0;
        int minute = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);


        mAlarm.setTitle(textView.getText().toString());



        mAlarm.setAlarmDate(calendar.getTime());
        mAlarm.setSolve(true);






        Log.i(TAG, "Check true/false " + mAlarm.getId() + "tf -> " + mAlarm.isSolve());

        mAlarmLab.addAlarm(mAlarm);
        Log.i(TAG, "onClick: " + mAlarm);



        getTargetFragment().onActivityResult(REQUEST_CODE, Activity.RESULT_OK, intent);





    }
}



