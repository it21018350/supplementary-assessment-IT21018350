package com.example.supplementaryassessmentit21018350.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(UserM user){
        long id = user.getId();

        editor.putLong(SESSION_KEY, id).commit();
    }

    public long getSession(){
        return sharedPreferences.getLong(SESSION_KEY, -1);
    }

    public void removeSession(){
        editor.putLong(SESSION_KEY, -1).commit();
    }
}
