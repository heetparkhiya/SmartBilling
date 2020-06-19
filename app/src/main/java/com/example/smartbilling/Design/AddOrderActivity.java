package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Spinner_Party;
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.Bean.Bean_Response_ListEntry;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOrderActivity extends AppCompatActivity {

    final Activity activity = this;
    EditText etOrderNo, etOrderDate, etOrderNoOfCases, etOrderPartyAddress, etOrderPartyCSTNo, etOrderPartyTransport, etTotalQuantity;
    Spinner spOrderPartyName;
    Calendar CurrentDate;
    int day, month, year;
    String PartyID = "-1", ListID, PartyAddress, PartyCSTNo, PartyTransportName, ListNo, ListDate, NoofCases, TotalQuantity;
    ApiInterface apiInterface;
    boolean flag = false;
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getSupportActionBar().setTitle("Add Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        manager = new SessionManager(activity);
        FillSpinnerParty();
        ListID = getIntent().getStringExtra("ListID");
        PartyAddress = getIntent().getStringExtra("PartyAddress");
        PartyCSTNo = getIntent().getStringExtra("PartyCSTNo");
        PartyTransportName = getIntent().getStringExtra("PartyTransportName");
        ListNo = getIntent().getStringExtra("ListNo");
        ListDate = getIntent().getStringExtra("ListDate"); //Check It give or not
        NoofCases = getIntent().getStringExtra("NoofCases");
        TotalQuantity = getIntent().getStringExtra("TotalQuantity"); //Total Quantity Pending

        if (ListID == null) {
            getSupportActionBar().setTitle("Add Order");
        } else {
            getSupportActionBar().setTitle("Edit Order Details");
            etOrderPartyAddress.setText(PartyAddress);
            etOrderPartyCSTNo.setText(PartyCSTNo);
            etOrderPartyTransport.setText(PartyTransportName);
            etOrderNo.setText(ListNo);
            etOrderNoOfCases.setText(NoofCases);
            etTotalQuantity.setText(TotalQuantity);
        }

        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etOrderDate.setText(day + "/" + (month + 1) + "/" + year);
        etOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etOrderDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    void FillSpinnerParty() {
        String UserID = manager.getUserID(activity);
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Party> call = apiInterface.getAllParty(UserID);
        call.enqueue(new Callback<Bean_Response_Party>() {
            @Override
            public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Party> PartyList = response.body().getData();
                    spOrderPartyName.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    spOrderPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                etOrderPartyAddress.setText(PartyList.get(position).getAddress());
                                etOrderPartyCSTNo.setText(PartyList.get(position).getCSTNo());
                                etOrderPartyTransport.setText(PartyList.get(position).getTransportName());
                                PartyID = PartyList.get(position).getPartyID();
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
            public void onFailure(Call<Bean_Response_Party> call, Throwable t) {
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
                Intent orderList = new Intent(activity, OrderActivity.class);
                startActivity(orderList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        if (etOrderPartyAddress.getText().length() <= 0) {
            flag = true;
            etOrderPartyAddress.setError("Enter party address");
        }

        if (etOrderPartyCSTNo.getText().length() <= 0) {
            flag = true;
            etOrderPartyCSTNo.setError("Enter CST no.");
        }

        if (etOrderPartyTransport.getText().length() <= 0) {
            flag = true;
            etOrderPartyTransport.setError("Enter transport name");
        }

        if (etOrderNo.getText().length() <= 0) {
            flag = true;
            etOrderNo.setError("Enter order no");
        }

        if (etOrderNoOfCases.getText().length() <= 0) {
            flag = true;
            etOrderNoOfCases.setError("Enter no of cases");
        }

        if(spOrderPartyName.getSelectedItemPosition() ==0)
            Toast.makeText(getApplicationContext(), "Select a valid Party", Toast.LENGTH_SHORT).show();

        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String OrderNo = etOrderNo.getText().toString().trim();
        String OrderDate = etOrderDate.getText().toString().trim();
        String OrderNoofCases = etOrderNoOfCases.getText().toString().trim();
        String OrderTotalQuantity = etTotalQuantity.getText().toString().trim();
        String UserID = manager.getUserID(activity);
        String Remarks = "NULL";
        String ProductID = "4"; //This is Change not final

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        if(ListID == null){
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_ListEntry> call = apiInterface.InsertListEntry("Order", UserID, PartyID, ProductID, OrderNo, OrderDate, OrderNoofCases, OrderTotalQuantity,Remarks);
            call.enqueue(new Callback<Bean_Response_ListEntry>() {
                @Override
                public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Order details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    }
                    else{
                        Toast.makeText(activity, "Order details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_ListEntry> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        else
        {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_ListEntry> call = apiInterface.UpdateListEntry(UserID, ListID, PartyID, ProductID, OrderNo, OrderDate, OrderNoofCases, OrderTotalQuantity, Remarks);
            call.enqueue(new Callback<Bean_Response_ListEntry>() {
                @Override
                public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Order details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, OrderActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(activity, "Order details not updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_ListEntry> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    void init() {
        etOrderNo = (EditText) findViewById(R.id.etOrderNo);
        etOrderDate = (EditText) findViewById(R.id.etOrderDate);
        etOrderNoOfCases = (EditText) findViewById(R.id.etOrderNoOfCases);
        etOrderPartyAddress = (EditText) findViewById(R.id.etOrderPartyAddress);
        etOrderPartyCSTNo = (EditText) findViewById(R.id.etOrderPartyCSTNo);
        etOrderPartyTransport = (EditText) findViewById(R.id.etOrderPartyTransport);
        etTotalQuantity = (EditText) findViewById(R.id.etTotalQuantity);
        spOrderPartyName = (Spinner) findViewById(R.id.spOrderPartyName);
    }

    void Clear(){
        etOrderNo.setText("");
        etOrderDate.setText("");
        etOrderNoOfCases.setText("");
        etOrderPartyAddress.setText("");
        etOrderPartyCSTNo.setText("");
        etOrderPartyTransport.setText("");
        etTotalQuantity.setText("");
    }
}