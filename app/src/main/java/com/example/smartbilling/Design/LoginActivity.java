package com.example.smartbilling.Design;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Bean.Bean_Response_User;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    SessionManager session;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setElevation(0);
        init();
        session = new SessionManager(LoginActivity.this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Loading");
                dialog.setMessage("Wait while loading...");

                final String username = etPassword.getText().toString();
                final String password = etPassword.getText().toString();

                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    dialog.show();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<Bean_Response_User> call = apiInterface.LoginUser(username, password);
                    call.enqueue(new Callback<Bean_Response_User>() {
                        @Override
                        public void onResponse(Call<Bean_Response_User> call, Response<Bean_Response_User> response) {
                            dialog.dismiss();
                            if (response.body().getResponse() == 1) {
                                session.createLoginSession(username, password);
                                startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Bean_Response_User> call, Throwable t) {
                            dialog.dismiss();
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id){
                                }
                            });
                            alertDialog.setMessage("Username or Password must be incorrect.");
                            alertDialog.show();
                        }
                    });
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                        }
                    });
                    alertDialog.setMessage("Enter Username or Password");
                    alertDialog.show();
                }
            }
        });
    }

    void init() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
}