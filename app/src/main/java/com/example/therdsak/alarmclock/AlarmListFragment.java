package com.example.therdsak.alarmclock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class AlarmListFragment extends Fragment {

    private static final String ALARM_ID = "AlarmListFragment.ID";
    private static final int REQUEST_TIME = 200;
    private static final String DIALOG_TIME = "AlarmListFragment";
    ;

    RecyclerView mRecyclerView;
    private static final String TAG = "AlarmListFragment";


    private Alarm alarm;
    private AlarmLab alarmLab;


    private Adapter _adapter;


    public static AlarmListFragment newInstance(UUID alarmID) {
        Bundle args = new Bundle();
        args.putSerializable(ALARM_ID, alarmID);
        AlarmListFragment alarmFragment = new AlarmListFragment();
        alarmFragment.setArguments(args);
        return alarmFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_list, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_add_item:

                alarm = new Alarm();
                AlarmLab.getInstance(getActivity()).addAlarm(alarm);
                FragmentManager fragmentManager = getFragmentManager();

                Log.i(TAG, "onOptionsItemSelected01: " + alarm);
                TimeDialog timeDialog = TimeDialog.newInstance(alarm.getId());
                timeDialog.setTargetFragment(AlarmListFragment.this, REQUEST_TIME);
                timeDialog.show(fragmentManager, DIALOG_TIME);

          //      UpdateUI();


                Log.i(TAG, "hello" + alarm.getAlarmDate());

                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_alarm_main, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.i(TAG, "onCreateView: " + mRecyclerView);

        UpdateUI();
        return v;
    }

    public String setFormatTime(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("hh.mm a ");
        String ss = formatDate.format(date);
        return ss;

    }


    private class Adapter extends RecyclerView.Adapter<Holder> {
        private List<Alarm> _alarms;
        private int viewCreatingCount;


        public Adapter(List<Alarm> alarms) {
            _alarms = alarms;
        }


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            viewCreatingCount++;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm_clock, parent, false);
            Log.i(TAG, "onCreateViewHolder: " + viewCreatingCount);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Alarm alarm = _alarms.get(position);

            Log.i(TAG, "onBindViewHolder: " + _alarms);
            holder.bind(alarm, position);
        }

        @Override
        public int getItemCount() {

            return _alarms.size();
        }


        public void setAlarms(List<Alarm> alarms) {
            _alarms = alarms;
        }
    }


    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView timeTextView;
        public TextView nameTextView;
        public Switch aSwitch;

        Alarm _alarm;
        int _position;

        public Holder(View itemView) {
            super(itemView);

            timeTextView = (TextView) itemView.findViewById(R.id.list_time);
            nameTextView = (TextView) itemView.findViewById(R.id.list_name);
            aSwitch = (Switch) itemView.findViewById(R.id.switch_name);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


//            Toast.makeText(getActivity(), "Add time for activity on every day and set up to start on activity", Toast.LENGTH_LONG).show();
        }


        public void bind(Alarm alarm, int position) {
            _alarm = alarm;
            _position = position;

                timeTextView.setText(setFormatTime(_alarm.getAlarmDate()));
                nameTextView.setText(_alarm.getTitle());
                Log.i(TAG, "bind: " + nameTextView.getText().toString());
                aSwitch.setChecked(_alarm.isSolve());
                Log.i(TAG, "bind: " + _alarm.isSolve());

                aSwitch.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            TimeDialog timeDialog = TimeDialog.newInstance(alarm.getId());
            timeDialog.setTargetFragment(AlarmListFragment.this, REQUEST_TIME);
            timeDialog.show(getActivity().getSupportFragmentManager(), "Edit Time");

        }


        @Override
        public boolean onLongClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirm Delete...");
            builder.setMessage("Are you sure you want delete this ?");
            builder.setIcon(R.drawable.images);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlarmLab.getInstance(getActivity()).deleteAlarm(_alarm.getId());
//                    getActivity().finish();
                    UpdateUI();
                    Log.i(TAG, "onLongClick: " + alarm.getId());

                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

            return true;
        }
    }


    public void UpdateUI() {
        AlarmLab alarmLab = AlarmLab.getInstance(getActivity());
        List<Alarm> alarms = alarmLab.getAlarm();
        Log.i(TAG, "UpdateUI22222: " + alarms);

        if (_adapter == null) {
            _adapter = new Adapter(alarms);
            _adapter.setAlarms(alarmLab.getAlarm());
        }
        mRecyclerView.setAdapter(_adapter);
        Log.i(TAG, "UpdateUI11111: " + _adapter);
        _adapter.notifyDataSetChanged();
        Log.i(TAG, "UpdateUI: " + _adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivitygolf: " + requestCode);
        Log.i(TAG, "onActivitygolf: " + resultCode);
        Log.i(TAG, "onActivitygolf: " + data);
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult1: " + REQUEST_TIME);
        if (requestCode == REQUEST_TIME) {
            Log.i(TAG, "onActivityResult11: " + REQUEST_TIME);
            if (resultCode == Activity.RESULT_OK) {
                UpdateUI();
                Log.i(TAG, "onActivityResult111: " + _adapter);
            }
        }
    }
}



