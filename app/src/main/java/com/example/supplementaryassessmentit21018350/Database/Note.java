package com.example.supplementaryassessmentit21018350.Database;

import android.provider.BaseColumns;

public final class Note {

    private Note() {}

    public static class Notes implements BaseColumns {
        public static final String TABLE_NAME = "Notes";
        public static final String COLUMN_1 = "title";
        public static final String COLUMN_2 = "note";
        public static final String COLUMN_3 = "createDate";
        public static final String COLUMN_4 = "modifiedDate";
    }
}
