package com.example.therdsak.alarmclock.Database;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Therdsak on 8/24/2016.
 */
public class AlarmDBSchema {
    public static final class AlarmTable {
        public static final String NAMEDB = "alarms";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String TIME = "time";
            public static final String ISSolve = "IsSolve";

        }

    }
}
