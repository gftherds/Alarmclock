package com.example.therdsak.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Therdsak on 8/25/2016.
 */
public class AlarmEditFragment extends Fragment {

    private static final String TAG = "AlarmEditFragment";
    private static final int REQUEST_TIME = 1234;
    private static final String DIALOG_TIME = "AlarmListFragment";
    private static final String ALARM_ID = "AlarmEditFragment.ID";
    private static final String CRIME_ID = "AlarmEditFragment.ID.1";


    public static Intent newIntent(Context activity, UUID id){
        Intent intent = new Intent(activity, AlarmEditFragment.class);
        intent.putExtra(ALARM_ID, id);
        return intent;


    }

    public static AlarmEditFragment newInstance(FragmentActivity activity, UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(CRIME_ID, crimeId);
        AlarmEditFragment crimeFragment = new AlarmEditFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    private Button AlarmAddButton;
    private Alarm alarm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_time,container,false);


            AlarmAddButton = (Button) v.findViewById(R.id.button_add);
           if(alarm == null){
               alarm = new Alarm();
           }
            AlarmAddButton.setText(getFormattedTime(alarm.getAlarmDate()));
            AlarmAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    if (alarm == null ){
                        alarm = new Alarm();
                    }
                    TimeDialog timeDialog = TimeDialog.newInstance(alarm.getAlarmDate());
                    Log.i(TAG, "onOptionsItemSelected: " + alarm);
                    timeDialog.setTargetFragment(AlarmEditFragment.this, REQUEST_TIME);
                    timeDialog.show(fragmentManager, DIALOG_TIME);
                }
            });

        return v;
    }

    private String getFormattedTime(Date date) {
       return new SimpleDateFormat("hh:mm").format(date);
    }





}
