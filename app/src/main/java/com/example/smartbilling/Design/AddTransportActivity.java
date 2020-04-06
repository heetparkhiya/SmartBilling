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
import com.example.smartbilling.Bean.Bean_Response_Transport;
import com.example.smartbilling.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTransportActivity extends AppCompatActivity {

    final Activity activity = this;
    EditText etTransportName, etTransportAddress, etTransportMobileNo;
    String TransportID = "", TransportName = "", TransportMobileNumber = "", TransportAddress = "";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transport);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        TransportID = getIntent().getStringExtra("TransportID");
        TransportName = getIntent().getStringExtra("TransportName");
        TransportMobileNumber = getIntent().getStringExtra("TransportMobileNumber");
        TransportAddress = getIntent().getStringExtra("TransportAddress");

        if (TransportID == null)
            getSupportActionBar().setTitle("Add Transport");
        else {
            getSupportActionBar().setTitle("Edit Transport Details");
            etTransportName.setText(TransportName);
            etTransportMobileNo.setText(TransportMobileNumber);
            etTransportAddress.setText(TransportAddress);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.cancel:
                Intent transportList = new Intent(activity, TransportActivity.class);
                startActivity(transportList);
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
        if (etTransportName.getText().length() <= 0) {
            flag = true;
            etTransportName.setError("Enter transport name");
        }
        if (etTransportAddress.getText().length() <= 0) {
            flag = true;
            etTransportAddress.setError("Enter transport address");
        }
        if (etTransportMobileNo.getText().length() < 10) {
            flag = true;
            etTransportMobileNo.setError("Enter transport mobile number");
        }
        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String TransportName = etTransportName.getText().toString().trim();
        String TransportAddress = etTransportAddress.getText().toString().trim();
        String TransportMobileNo = etTransportMobileNo.getText().toString().trim();
        String UserId = "1";
        String Remarks = "NULL";

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        if (TransportID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Transport> call = apiInterface.InsertTransport(UserId, TransportName, TransportAddress, TransportMobileNo, Remarks);
            call.enqueue(new Callback<Bean_Response_Transport>() {
                @Override
                public void onResponse(Call<Bean_Response_Transport> call, Response<Bean_Response_Transport> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Transport details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    }
                    else{
                        Toast.makeText(activity, "Transport details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_Transport> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        } else {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Transport> call = apiInterface.UpdateTransport(TransportID, TransportName, TransportAddress, TransportMobileNo, Remarks);
            call.enqueue(new Callback<Bean_Response_Transport>() {
                @Override
                public void onResponse(Call<Bean_Response_Transport> call, Response<Bean_Response_Transport> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Transport details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, TransportActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(activity, "Transport details not updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Bean_Response_Transport> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }

    void Clear() {
        etTransportName.setText("");
        etTransportAddress.setText("");
        etTransportMobileNo.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    void init() {
        etTransportName = (EditText) findViewById(R.id.etTransportName);
        etTransportAddress = (EditText) findViewById(R.id.etTransportAddress);
        etTransportMobileNo = (EditText) findViewById(R.id.etTransportMobileNo);
    }
}