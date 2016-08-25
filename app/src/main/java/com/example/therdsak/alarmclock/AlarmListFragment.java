package com.example.therdsak.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
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
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class AlarmListFragment extends Fragment {

    private static final String ALARM_ID = "AlarmListFragment.ID";
    RecyclerView mRecyclerView;


    private Alarm alarm;


    private static final String TAG = "AlarmListFragment";
    private Adapter _adapter;

    public static  AlarmListFragment newInstance(UUID alarmId){
        Bundle args = new Bundle();
        args.putSerializable(ALARM_ID, alarmId);

        AlarmListFragment alarmListfragment  = new AlarmListFragment();
        alarmListfragment.setArguments(args);
        return alarmListfragment;
    }

    public static Intent newIntent(Context activity, UUID id) {
        Intent intent = new Intent(activity, AlarmListFragment.class);
        intent.putExtra(ALARM_ID , id);


        return intent;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);



        AlarmLab alarmLab = AlarmLab.getInstance(getActivity());
        UUID alarmId = (UUID) getArguments().getSerializable(ALARM_ID);
        alarm = AlarmLab.getInstance(getActivity()).getAlarmById(alarmId);
        Log.i(TAG, "onCreate:  " + alarm);



    }
    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        super.onCreateOptionsMenu(menu , inflater);
        inflater.inflate(R.menu.menu_add_list, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_add_item:
                if(alarm == null){
                    alarm = new Alarm();
                }

              if(getActivity().findViewById(R.id.fragment_container) == null) {
                Intent intent = AlarmEditFragment.newIntent(getActivity(), alarm.getId());

                startActivity(intent);
            }else{
                  Fragment newDetailFragment = AlarmEditFragment.newInstance(getActivity(),alarm.getId());
                  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newDetailFragment).commit();


        }


                UpdateUI();

                return true;
        }
        return super.onOptionsItemSelected(item);

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_alarm_main, container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mRecyclerView.setAdapter(new AlarmMainActivity());
        return v;
    }



    private class Adapter extends RecyclerView.Adapter<Holder> {
            private List<Alarm> _alarms;
            private int viewCreatingCount;
//
//        @Override
//        public long getItemId(int position) {
//            return super.getItemId(position);
//        }
//        //  private List<Alarm> alarms;

        public Adapter(List<Alarm> alarms){
                _alarms = alarms;
            }
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            viewCreatingCount++;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm_clock , parent, false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Alarm alarm = _alarms.get(position);
            holder.bind(alarm,position);
        }

        @Override
        public int getItemCount() {

            return _alarms.size();
        }

     //   public void setAlarms(List<Alarm> alarms) {
      //      this.alarms = alarms;
     //   }
    }


    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public TextView timeTextView;
        public TextView nameTextView;

        Alarm _alarm;
        int _position;

        public Holder(View itemView) {
            super(itemView);

            timeTextView = (TextView) itemView.findViewById(R.id.list_time);
            nameTextView = (TextView) itemView.findViewById(R.id.list_name);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }


        public void bind(Alarm alarm, int position) {
            _alarm = alarm;
            _position = position;
            timeTextView.setText(_alarm.getAlarmDate().toString());
            nameTextView.setText(_alarm.getTitle());
        }


        @Override
        public void onClick(View view) {

        }


        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }


    public void UpdateUI() {
        AlarmLab alarmLab = AlarmLab.getInstance(getActivity());
        List<Alarm> alarms = alarmLab.getAlarm();


        if (_adapter == null) {
            _adapter = new Adapter(alarms);
            mRecyclerView.setAdapter(_adapter);

        } else {
     //       _adapter.setAlarms(alarmLab.getAlarm());
            _adapter.notifyDataSetChanged();
        }

    }


}
