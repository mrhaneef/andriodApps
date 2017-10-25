package com.example.mhaneef.myp.db;

import android.provider.BaseColumns;

/**
 * Created by mhaneef on 10/17/2017.
 */

public class PContract {

    private PContract(){}

    public static class PEntry implements BaseColumns {
        public static final String TABLE_NAME = "P";
        public static final String COLUMN_NAME_F = "F";
        public static final String COLUMN_NAME_Z = "Z";
        public static final String COLUMN_NAME_A = "A";
        public static final String COLUMN_NAME_M = "M";
        public static final String COLUMN_NAME_I = "I";
        public static final String COLUMN_NAME_MODIFIED = "MODIFIED";
    }



    public static class HistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "PHistory";
        public static final String COLUMN_NAME_F = "F";
        public static final String COLUMN_NAME_Z = "Z";
        public static final String COLUMN_NAME_A = "A";
        public static final String COLUMN_NAME_M = "M";
        public static final String COLUMN_NAME_I = "I";
        public static final String COLUMN_NAME_CHANNGED = "CHANGED_COLUMN_NAME";
        public static final String COLUMN_NAME_MODIFIED = "MODIFIED";
    }


}
