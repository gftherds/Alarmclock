package com.example.therdsak.alarmclock;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class AlarmEditFragment extends Fragment {

        public static AlarmEditFragment newInstance(Context context){
        Bundle args = new Bundle();
        AlarmEditFragment fragment = new AlarmEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_time,container,false);

        return v;

    }


    public void addTime() {



    }
}
