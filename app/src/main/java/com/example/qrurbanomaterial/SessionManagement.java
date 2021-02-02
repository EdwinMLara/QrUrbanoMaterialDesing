package com.example.qrurbanomaterial;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "Session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String token){
        editor.putString(SESSION_KEY,token).commit();
    }

    public String getSessionUser(){
        return sharedPreferences.getString(SESSION_KEY,"none");
    }

    public void removeSession(){
        editor.putString(SESSION_KEY,"none").commit();
    }

}
