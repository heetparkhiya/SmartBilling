package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Spinner_Broker;
import com.example.smartbilling.Adapter.Adapter_Spinner_Transport;
import com.example.smartbilling.Bean.Bean_Broker;
import com.example.smartbilling.Bean.Bean_Response_Broker;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.Bean.Bean_Response_Transport;
import com.example.smartbilling.Bean.Bean_Transport;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPartyActivity extends AppCompatActivity {

    final Activity activity = this;
    EditText etPartyName, etPartyRefName, etPartyAddress, etPartyBankThrough, etPartyPin, etPartyCity, etPartyState, etPartyTelephoneNo, etPartyFaxNo, etPartyMobileNumber, etPartyEmail, etPartyLocation, etPartyCreditDays, etPartyTinNo, etPartyCSTNo, etPartyDISC, etPartyBrokerRage;
    Spinner spPartyTransport, spPartyBroker;
    ApiInterface apiInterface;
    SessionManager manager;
    String TransportID, BrokerID, PartyID, PartyName, PartyMobileNumber, PartyTelephoneNumber, PartyEmail, PartyAddress, PartyCity, PartyState, PartyPin, PartyLocation, PartyRefName, PartyCSTNumber, PartyTINNumber, PartyBankThrough, PartyCreditDays, PartyDISC, PartyFaxNumber, PartyBrokerID, PartyTransportID, PartyBrokerRage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        manager = new SessionManager(activity);
        FillSpinner();
        PartyID = getIntent().getStringExtra("PartyID");
        PartyName = getIntent().getStringExtra("PartyName");
        PartyMobileNumber = getIntent().getStringExtra("PartyMobileNumber");
        PartyTelephoneNumber = getIntent().getStringExtra("PartyTelephoneNumber");
        PartyEmail = getIntent().getStringExtra("PartyEmail");
        PartyAddress = getIntent().getStringExtra("PartyAddress");
        PartyCity = getIntent().getStringExtra("PartyCity");
        PartyState = getIntent().getStringExtra("PartyState");
        PartyPin = getIntent().getStringExtra("PartyPin");
        PartyLocation = getIntent().getStringExtra("PartyLocation");
        PartyRefName = getIntent().getStringExtra("PartyRefName");
        PartyCSTNumber = getIntent().getStringExtra("PartyCSTNumber");
        PartyTINNumber = getIntent().getStringExtra("PartyTINNumber");
        PartyBankThrough = getIntent().getStringExtra("PartyBankThrough");
        PartyCreditDays = getIntent().getStringExtra("PartyCreditDays");
        PartyDISC = getIntent().getStringExtra("PartyDISC");
        PartyFaxNumber = getIntent().getStringExtra("PartyFaxNumber");
        PartyBrokerID = getIntent().getStringExtra("PartyBrokerID");
        PartyTransportID = getIntent().getStringExtra("PartyTransportID");
        PartyBrokerRage = getIntent().getStringExtra("PartyBrokerRage");

        if (PartyID == null) {
            getSupportActionBar().setTitle("Add Party");
        } else {
            getSupportActionBar().setTitle("Update Party");
            etPartyName.setText(PartyName);
            etPartyRefName.setText(PartyRefName);
            etPartyAddress.setText(PartyAddress);
            etPartyBankThrough.setText(PartyBankThrough);
            etPartyPin.setText(PartyPin);
            etPartyCity.setText(PartyCity);
            etPartyState.setText(PartyState);
            etPartyTelephoneNo.setText(PartyTelephoneNumber);
            etPartyMobileNumber.setText(PartyMobileNumber);
            etPartyState.setText(PartyState);
            etPartyFaxNo.setText(PartyFaxNumber);
            etPartyEmail.setText(PartyEmail);
            etPartyLocation.setText(PartyLocation);
            etPartyTinNo.setText(PartyTINNumber);
            etPartyCSTNo.setText(PartyCSTNumber);
            etPartyCreditDays.setText(PartyCreditDays);
            etPartyDISC.setText(PartyDISC);
            spPartyTransport.setSelection(Integer.parseInt(PartyTransportID));
            spPartyBroker.setSelection(Integer.parseInt(PartyBrokerID));
            etPartyBrokerRage.setText(PartyBrokerRage);
        }
    }

    void FillSpinner(){
        String UserID = manager.getUserID(activity);
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Transport> callTransport = apiInterface.getAllTransport(UserID);
        callTransport.enqueue(new Callback<Bean_Response_Transport>() {
            @Override
            public void onResponse(Call<Bean_Response_Transport> call, Response<Bean_Response_Transport> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Transport> TransportList = response.body().getData();
                    spPartyTransport.setAdapter(new Adapter_Spinner_Transport(TransportList, activity));
                    spPartyTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            TransportID = TransportList.get(position).getTransportID();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Transport> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        Call<Bean_Response_Broker> callBroker = apiInterface.getAllBroker(UserID);
        callBroker.enqueue(new Callback<Bean_Response_Broker>() {
            @Override
            public void onResponse(Call<Bean_Response_Broker> call, Response<Bean_Response_Broker> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Broker> BrokerList = response.body().getData();
                    spPartyBroker.setAdapter(new Adapter_Spinner_Broker(BrokerList, activity));
                    spPartyBroker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            BrokerID = BrokerList.get(position).getBrokerID();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.cancel:
                Intent partyList = new Intent(activity, PartyActivity.class);
                startActivity(partyList);
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
        if (etPartyName.getText().length() <= 0) {
            flag = true;
            etPartyName.setError("Enter party name");
        }
        if (etPartyRefName.getText().length() <= 0) {
            flag = true;
            etPartyRefName.setError("Enter party reference name");
        }
        if (etPartyAddress.getText().length() <= 0) {
            flag = true;
            etPartyAddress.setError("Enter party address");
        }
        if (etPartyBankThrough.getText().length() <= 0) {
            flag = true;
            etPartyBankThrough.setError("Enter party bank through");
        }
        if (etPartyPin.getText().length() <= 0) {
            flag = true;
            etPartyPin.setError("Enter pin number");
        }
        if (etPartyCity.getText().length() <= 0) {
            flag = true;
            etPartyCity.setError("Enter party city");
        }
        if (etPartyState.getText().length() <= 0) {
            flag = true;
            etPartyState.setError("Enter party state");
        }
        if (etPartyTelephoneNo.getText().length() <= 0) {
            flag = true;
            etPartyTelephoneNo.setError("Enter party telephone number");
        }
        if (etPartyFaxNo.getText().length() <= 0) {
            flag = true;
            etPartyFaxNo.setError("Enter valid party fax number");
        }
        if (etPartyMobileNumber.getText().length() < 10) {
            flag = true;
            etPartyMobileNumber.setError("Enter party mobile number");
        }
        if (!etPartyEmail.getText().toString().trim().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            flag = true;
            etPartyEmail.setError("Enter valid party email address");
        }
        if (etPartyLocation.getText().length() <= 0) {
            flag = true;
            etPartyLocation.setError("Enter party location");
        }
        if (etPartyCreditDays.getText().length() <= 0) {
            flag = true;
            etPartyCreditDays.setError("Enter party credit days");
        }
        if (etPartyTinNo.getText().length() <= 0) {
            flag = true;
            etPartyTinNo.setError("Enter party TIN no.");
        }
        if (etPartyCSTNo.getText().length() <= 0) {
            flag = true;
            etPartyCSTNo.setError("Enter party CST no.");
        }
        if (etPartyDISC.getText().length() <= 0) {
            flag = true;
            etPartyDISC.setError("Enter party Disc");
        }
        if (etPartyBrokerRage.getText().length() <= 0) {
            flag = true;
            etPartyBrokerRage.setError("Enter party broker rage");
        }

        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String PartyName = etPartyName.getText().toString().trim();
        String PartyRefName = etPartyRefName.getText().toString().trim();
        String PartyAddress = etPartyAddress.getText().toString().trim();
        String PartyBankThrough = etPartyBankThrough.getText().toString().trim();
        String PartyPin = etPartyPin.getText().toString().trim();
        String PartyCity = etPartyCity.getText().toString().trim();
        String PartyState = etPartyState.getText().toString().trim();
        String PartyTelephone = etPartyTelephoneNo.getText().toString().trim();
        String PartyMobile = etPartyMobileNumber.getText().toString().trim();
        String PartyFaxNo = etPartyFaxNo.getText().toString().trim();
        String PartyEmail = etPartyEmail.getText().toString().trim();
        String PartyLocation = etPartyLocation.getText().toString().trim();
        String PartyTINNo = etPartyTinNo.getText().toString().trim();
        String PartyCSTNo = etPartyCSTNo.getText().toString().trim();
        String PartyCreditDays = etPartyCreditDays.getText().toString().trim();
        String PartyDISC = etPartyDISC.getText().toString().trim();
        String PartyTransport = TransportID;
        String PartyBroker = BrokerID;

        String PartyBrokerRage = etPartyBrokerRage.getText().toString().trim();
        String UserID = manager.getUserID(activity);
        String Remarks = "NULL";

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        if (PartyID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Party> call = apiInterface.InsertParty(UserID, PartyTransport, PartyBroker, PartyName, PartyRefName, PartyAddress, PartyBankThrough, PartyPin, PartyCity, PartyState, PartyTelephone, PartyMobile, PartyFaxNo, PartyEmail, PartyLocation, PartyTINNo, PartyCSTNo, PartyCreditDays, PartyDISC, PartyBrokerRage, Remarks);
            call.enqueue(new Callback<Bean_Response_Party>() {
                @Override
                public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Party details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    } else {
                        Toast.makeText(activity, "Party details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Bean_Response_Party> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        } else {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Party> call = apiInterface.UpdateParty(UserID, PartyID, PartyTransport, PartyBroker, PartyName, PartyRefName, PartyAddress, PartyBankThrough, PartyPin, PartyCity, PartyState, PartyTelephone, PartyFaxNo, PartyMobile, PartyEmail, PartyLocation, PartyCreditDays, PartyTINNo, PartyCSTNo, PartyDISC, PartyBrokerRage, Remarks);
            call.enqueue(new Callback<Bean_Response_Party>() {
                @Override
                public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Party details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, PartyActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(activity, "Party details not updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Bean_Response_Party> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }


    void init() {
        etPartyName = (EditText) findViewById(R.id.etPartyName);
        etPartyRefName = (EditText) findViewById(R.id.etPartyRefName);
        etPartyAddress = (EditText) findViewById(R.id.etPartyAddress);
        etPartyBankThrough = (EditText) findViewById(R.id.etPartyBankThrough);
        etPartyPin = (EditText) findViewById(R.id.etPartyPin);
        etPartyCity = (EditText) findViewById(R.id.etPartyCity);
        etPartyState = (EditText) findViewById(R.id.etPartyState);
        etPartyTelephoneNo = (EditText) findViewById(R.id.etPartyTelephoneNo);
        etPartyFaxNo = (EditText) findViewById(R.id.etPartyFaxNo);
        etPartyMobileNumber = (EditText) findViewById(R.id.etPartyMobileNumber);
        etPartyEmail = (EditText) findViewById(R.id.etPartyEmail);
        etPartyLocation = (EditText) findViewById(R.id.etPartyLocation);
        etPartyCreditDays = (EditText) findViewById(R.id.etPartyCreditDays);
        etPartyTinNo = (EditText) findViewById(R.id.etPartyTinNo);
        etPartyCSTNo = (EditText) findViewById(R.id.etPartyCSTNo);
        etPartyDISC = (EditText) findViewById(R.id.etPartyDISC);
        etPartyBrokerRage = (EditText) findViewById(R.id.etPartyBrokerRage);
        spPartyTransport = (Spinner) findViewById(R.id.spPartyTransport);
        spPartyBroker = (Spinner) findViewById(R.id.spPartyBroker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    void Clear() {
        etPartyName.setText("");
        etPartyRefName.setText("");
        etPartyAddress.setText("");
        etPartyBankThrough.setText("");
        etPartyPin.setText("");
        etPartyCity.setText("");
        etPartyState.setText("");
        etPartyTelephoneNo.setText("");
        etPartyFaxNo.setText("");
        etPartyMobileNumber.setText("");
        etPartyEmail.setText("");
        etPartyLocation.setText("");
        etPartyCreditDays.setText("");
        etPartyTinNo.setText("");
        etPartyCSTNo.setText("");
        etPartyDISC.setText("");
        etPartyBrokerRage.setText("");
    }
}