package com.example.supplementaryassessmentit21018350.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerUser extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DBHandlerUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES1);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES1);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.Users.TABLE_NAME + " (" +
                    User.Users._ID + " INTEGER PRIMARY KEY," +
                    User.Users.COLUMN_1 + " TEXT," +
                    User.Users.COLUMN_2 + " TEXT," +
                    User.Users.COLUMN_3 + " TEXT," +
                    User.Users.COLUMN_4 + " TEXT," +
                    User.Users.COLUMN_5 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.Users.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + Note.Notes.TABLE_NAME + " (" +
                    Note.Notes._ID + " INTEGER PRIMARY KEY," +
                    Note.Notes.COLUMN_1 + " TEXT," +
                    Note.Notes.COLUMN_2 + " TEXT," +
                    Note.Notes.COLUMN_3 + " TEXT," +
                    Note.Notes.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES1 =
            "DROP TABLE IF EXISTS " + Note.Notes.TABLE_NAME;


    public long addUser(String name,String username,String gender,String email,String password){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(User.Users.COLUMN_1, name);
        values.put(User.Users.COLUMN_2, username);
        values.put(User.Users.COLUMN_3, gender);
        values.put(User.Users.COLUMN_4, email);
        values.put(User.Users.COLUMN_5, password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(User.Users.TABLE_NAME, null, values);
        return newRowId;
    }

    public int fetchLoginUserByUsername(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                User.Users.COLUMN_2,
                User.Users.COLUMN_5
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = User.Users.COLUMN_2 + " = ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                User.Users.COLUMN_2 + " DESC";

        Cursor cursor = db.query(
                User.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<String> items = new ArrayList<String>();

        while (cursor.moveToNext()){
            items.add(cursor.getString(cursor.getColumnIndexOrThrow(User.Users._ID)));
            items.add(cursor.getString(cursor.getColumnIndexOrThrow(User.Users.COLUMN_5)));

        }

//        String string = cursor.getString(5);
//        long id = cursor.getLong(cursor.getColumnIndexOrThrow(User.Users._ID));
        cursor.close();

        int id = Integer.parseInt(items.get(0));
        String string123 = items.get(1).toString();

        if (string123.equals(password)){
            return id;
        }else{
            return -1;
        }
    }

    public List fetchAllUserById(Integer id) {
        SQLiteDatabase db = getReadableDatabase();

        String uId = id.toString();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                User.Users.COLUMN_1,
                User.Users.COLUMN_2,
                User.Users.COLUMN_3,
                User.Users.COLUMN_4,
                User.Users.COLUMN_5
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = User.Users._ID + " = ?";
        String[] selectionArgs = { uId };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                User.Users._ID + " DESC";

        Cursor cursor = db.query(
                User.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List items = new ArrayList<>();
        items.add(cursor.getLong(cursor.getColumnIndexOrThrow(User.Users._ID)));
        items.add(cursor.getLong(cursor.getColumnIndexOrThrow(User.Users.COLUMN_1)));
        items.add(cursor.getLong(cursor.getColumnIndexOrThrow(User.Users.COLUMN_2)));
        items.add(cursor.getLong(cursor.getColumnIndexOrThrow(User.Users.COLUMN_3)));
        items.add(cursor.getLong(cursor.getColumnIndexOrThrow(User.Users.COLUMN_4)));

        cursor.close();

        return items;
    }

    public int updateUser(Integer id, String name, String gender, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        String uid = id.toString();
        ContentValues values = new ContentValues();
        values.put(User.Users.COLUMN_1, name);
        values.put(User.Users.COLUMN_3, gender);
        values.put(User.Users.COLUMN_4, email);
        values.put(User.Users.COLUMN_5, password);

        // Which row to update, based on the title
        String selection = User.Users._ID + " LIKE ?";
        String[] selectionArgs = { uid };

        int count = db.update(
                User.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public void deleteUser(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String uid = id.toString();
        String selection = User.Users._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { uid };
        // Issue SQL statement.
        int deletedRows = db.delete(User.Users.TABLE_NAME, selection, selectionArgs);
    }
}