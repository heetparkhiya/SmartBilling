package com.example.smartbilling.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.smartbilling.Design.LoginActivity;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "SmartBilling";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "username";

    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getKeyEmail(Context context)
    {
        return pref.getString(KEY_USERNAME,null);
    }
    public String getKeyPassword(Context context)
    {
        return pref.getString(KEY_PASSWORD,null);
    }

    public void createLoginSession(String password, String username) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void Logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}