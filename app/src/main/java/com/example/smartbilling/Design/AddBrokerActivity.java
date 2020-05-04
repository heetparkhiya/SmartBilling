package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Bean.Bean_Response_Broker;
import com.example.smartbilling.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBrokerActivity extends AppCompatActivity {
    final Activity activity = this;
    EditText etBrokerName, etBrokerAddress, etBrokerTelephoneNo, etBrokerFaxNo, etBrokerMobileNumber, etBrokerEmail, etBrokerRate;
    ApiInterface apiInterface;
    String BrokerID, BrokerName, BrokerAddress, BrokerTelephoneNumber, BrokerFaxNo, BrokerMobileNumber, BrokerEmail, BrokerRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_broker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        BrokerID = getIntent().getStringExtra("BrokerID");
        BrokerName = getIntent().getStringExtra("BrokerName");
        BrokerAddress = getIntent().getStringExtra("BrokerAddress");
        BrokerTelephoneNumber = getIntent().getStringExtra("BrokerTelephoneNumber");
        BrokerFaxNo = getIntent().getStringExtra("BrokerFaxNo");
        BrokerMobileNumber = getIntent().getStringExtra("BrokerMobileNumber");
        BrokerEmail = getIntent().getStringExtra("BrokerEmail");
        BrokerRate = getIntent().getStringExtra("BrokerRate");

        if(BrokerID == null){
            getSupportActionBar().setTitle("Add Broker");
        }
        else
        {
            getSupportActionBar().setTitle("Edit Broker Details");
            etBrokerName.setText(BrokerName);
            etBrokerAddress.setText(BrokerAddress);
            etBrokerTelephoneNo.setText(BrokerTelephoneNumber);
            etBrokerFaxNo.setText(BrokerFaxNo);
            etBrokerMobileNumber.setText(BrokerMobileNumber);
            etBrokerEmail.setText(BrokerEmail);
            etBrokerRate.setText(BrokerRate);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.cancel:
                Intent brokerList = new Intent(activity, BrokerActivity.class);
                startActivity(brokerList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        boolean flag = false;
        if (etBrokerName.getText().length() <= 0) {
            flag = true;
            etBrokerName.setError("Enter broker name");
        }
        if (etBrokerAddress.getText().length() <= 0) {
            flag = true;
            etBrokerAddress.setError("Enter broker address");
        }
        if (etBrokerMobileNumber.getText().length() < 10) {
            flag = true;
            etBrokerMobileNumber.setError("Enter broker mobile number");
        }
        if (etBrokerFaxNo.getText().length() <= 0) {
            flag = true;
            etBrokerFaxNo.setError("Enter broker fax number");
        }
        if (!etBrokerEmail.getText().toString().trim().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            flag = true;
            etBrokerEmail.setError("Enter valid broker email address");
        }
        if (etBrokerTelephoneNo.getText().toString().length() <= 0) {
            flag = true;
            etBrokerTelephoneNo.setError("Enter valid broker telephone number");
        }
        if (etBrokerRate.getText().length() <= 0) {
            flag = true;
            etBrokerRate.setError("Enter valid broker rate");
        }
        if (!flag) {
            SaveData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    void SaveData() {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        String Name = etBrokerName.getText().toString();
        String Address = etBrokerName.getText().toString();
        String TelephoneNumber = etBrokerTelephoneNo.getText().toString();
        String FaxNumber = etBrokerFaxNo.getText().toString();
        String MobileNumber = etBrokerMobileNumber.getText().toString();
        String Email = etBrokerEmail.getText().toString();
        String Rate = etBrokerRate.getText().toString();
        String UserId = "1";
        String Remarks = "NULL";

        if(BrokerID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Broker> call = apiInterface.InsertBroker(UserId, Name, Address, TelephoneNumber, FaxNumber, MobileNumber, Email, Rate, Remarks);
            call.enqueue(new Callback<Bean_Response_Broker>() {
                @Override
                public void onResponse(Call<Bean_Response_Broker> call, Response<Bean_Response_Broker> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Broker details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    }
                    else{
                        Toast.makeText(activity, "Broker details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_Broker> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }

        else{
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Broker> call = apiInterface.UpdateBroker(BrokerID, Name, Address, TelephoneNumber, FaxNumber, MobileNumber, Email, Rate, Remarks);
            call.enqueue(new Callback<Bean_Response_Broker>() {
                @Override
                public void onResponse(Call<Bean_Response_Broker> call, Response<Bean_Response_Broker> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Broker details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, BrokerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(activity, "Broker details not updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_Broker> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }

    void Clear(){
        etBrokerName.setText("");
        etBrokerRate.setText("");
        etBrokerFaxNo.setText("");
        etBrokerEmail.setText("");
        etBrokerTelephoneNo.setText("");
        etBrokerMobileNumber.setText("");
        etBrokerAddress.setText("");
    }

    void init() {
        etBrokerName = (EditText) findViewById(R.id.etBrokerName);
        etBrokerAddress = (EditText) findViewById(R.id.etBrokerAddress);
        etBrokerTelephoneNo = (EditText) findViewById(R.id.etBrokerTelephoneNo);
        etBrokerFaxNo = (EditText) findViewById(R.id.etBrokerFaxNo);
        etBrokerMobileNumber = (EditText) findViewById(R.id.etBrokerMobileNumber);
        etBrokerEmail = (EditText) findViewById(R.id.etBrokerEmail);
        etBrokerRate = (EditText) findViewById(R.id.etBrokerRate);
    }
}