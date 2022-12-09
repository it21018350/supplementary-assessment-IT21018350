package com.example.supplementaryassessmentit21018350.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.SimpleCursorAdapter;

import com.example.supplementaryassessmentit21018350.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandlerNotes extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    Context context;

    public DBHandlerNotes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Note.Notes.TABLE_NAME + " (" +
                    Note.Notes._ID + " INTEGER PRIMARY KEY," +
                    Note.Notes.COLUMN_1 + " TEXT," +
                    Note.Notes.COLUMN_2 + " TEXT," +
                    Note.Notes.COLUMN_3 + " TEXT," +
                    Note.Notes.COLUMN_4 + "TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Note.Notes.TABLE_NAME;



    public long addNote(String title, String note){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateTime = formatter.format(date);

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Note.Notes.COLUMN_1, title);
        values.put(Note.Notes.COLUMN_2, note);
        values.put(Note.Notes.COLUMN_3, dateTime);
        values.put(Note.Notes.COLUMN_4, "");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Note.Notes.TABLE_NAME, null, values);
        return newRowId;
    }

    public ArrayList fetchNotebyID(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String idString = Integer.toString(id);

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Note.Notes.COLUMN_1,
                Note.Notes.COLUMN_2,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Note.Notes._ID + " = ?";
        String[] selectionArgs = { idString };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Note.Notes._ID + " DESC";

        Cursor cursor = db.query(
                Note.Notes.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<String> items = new ArrayList<String>();

        while (cursor.moveToNext()){
            items.add(cursor.getString(cursor.getColumnIndexOrThrow(Note.Notes.COLUMN_1)));
            items.add(cursor.getString(cursor.getColumnIndexOrThrow(Note.Notes.COLUMN_2)));
        }

        cursor.close();

        return items;
    }

    public SimpleCursorAdapter fetchAllNoteById() {
        SQLiteDatabase db = getReadableDatabase();

//        try (Cursor result = db.rawQuery("SELECT * FROM "+ Note.Notes.TABLE_NAME, null)){
//            if (result.getCount() != 0){
//                while (result.moveToNext()){
//                    int id = result.getInt(1);
//                    String title = result.getString(2);
//                    String note = result.getString(3);
//                    String cDate = result.getString(4);
//                    String mDate = result.getString(5);
//                    NoteModle noteModle = new NoteModle(id, title, note, cDate, mDate);
//                    NoteModle.noteArrayList.add();
//                }
//            }
//        }

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Note.Notes.COLUMN_1,
                Note.Notes.COLUMN_2,
                Note.Notes.COLUMN_3,
                Note.Notes.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
//        String selection = User.Users._ID + " = ?";
//        String[] selectionArgs = { uId };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Note.Notes.COLUMN_3 + " DESC";

        Cursor cursor = db.query(
                Note.Notes.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,                   // The columns for the WHERE clause
                null,                   // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        String[] fromFieldNames = new String[]{
                BaseColumns._ID,
                Note.Notes.COLUMN_1,
                Note.Notes.COLUMN_2,
                Note.Notes.COLUMN_3,
                Note.Notes.COLUMN_4
        };

        int[] toViewIDs = new int[]{R.id.item_id, R.id.item_title, R.id.item_date};

        SimpleCursorAdapter noteAdaptor = new SimpleCursorAdapter(
                context,
                R.layout.single_item,
                cursor,
                fromFieldNames,
                toViewIDs
        );

        return noteAdaptor;

    }

    public int updateNote(Integer id, String title, String note) {
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateTime = formatter.format(date);

        // New value for one column
        String uid = id.toString();
        ContentValues values = new ContentValues();
        values.put(Note.Notes.COLUMN_1, title);
        values.put(Note.Notes.COLUMN_2, note);
        values.put(Note.Notes.COLUMN_4, dateTime);

        // Which row to update, based on the title
        String selection = Note.Notes._ID + " LIKE ?";
        String[] selectionArgs = { uid };

        int count = db.update(
                Note.Notes.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public int deleteNote(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String uid = id.toString();
        String selection = Note.Notes._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { uid };
        // Issue SQL statement.
        int deletedRows = db.delete(Note.Notes.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }

}
