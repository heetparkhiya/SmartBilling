package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        final SessionManager manager = new SessionManager(SplashScreenActivity.this);
        if (manager.isLoggedIn()) {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        } else
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    manager.checkLogin();
                }
            },2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}