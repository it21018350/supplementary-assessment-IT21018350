package com.example.supplementaryassessmentit21018350.Database;

import android.provider.BaseColumns;

public final class User {

    private User() {}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_1 = "name";
        public static final String COLUMN_2 = "username";
        public static final String COLUMN_3 = "gender";
        public static final String COLUMN_4 = "email";
        public static final String COLUMN_5 = "password";
    }
}
