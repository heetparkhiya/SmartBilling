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

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPerformInvoiceActivity extends AppCompatActivity {

    final Activity activity = this;
    EditText etPerInvNo, etPerInvDate, etPerInvNoOfCases, etPerInvPartyAddress, etPerInvPartyCSTNo, etPerInvPartyTransport, etPerInvQuantity;
    Spinner spPerInvPartyName;
    Calendar CurrentDate;
    int day, month, year;
    String PartyID = "-1", ListID, PartyAddress, PartyCSTNo, PartyTransportName, ListNo, ListDate, NoofCases, TotalQuantity;
    ApiInterface apiInterface;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_perform_invoice);
        getSupportActionBar().setTitle("Perform Invoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        FillSpinnerParty();

        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etPerInvDate.setText(day + "/" + (month + 1) + "/" + year);
        etPerInvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etPerInvDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        ListID = getIntent().getStringExtra("ListID");
        PartyAddress = getIntent().getStringExtra("PartyAddress");
        PartyCSTNo = getIntent().getStringExtra("PartyCSTNo");
        PartyTransportName = getIntent().getStringExtra("PartyTransportName");
        ListNo = getIntent().getStringExtra("ListNo");
        ListDate = getIntent().getStringExtra("ListDate"); //Check It give or not
        NoofCases = getIntent().getStringExtra("NoofCases");
        TotalQuantity = getIntent().getStringExtra("TotalQuantity"); //Total Quantity Pending

        if (ListID == null) {
            getSupportActionBar().setTitle("Add Invoice Details");
        } else {
            getSupportActionBar().setTitle("Edit Invoice Details");
            etPerInvPartyAddress.setText(PartyAddress);
            etPerInvPartyCSTNo.setText(PartyCSTNo);
            etPerInvPartyTransport.setText(PartyTransportName);
            etPerInvNo.setText(ListNo);
            etPerInvNoOfCases.setText(NoofCases);
            etPerInvQuantity.setText(TotalQuantity);
        }
    }

    void FillSpinnerParty() {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Party> call = apiInterface.getAllParty();
        call.enqueue(new Callback<Bean_Response_Party>() {
            @Override
            public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Party> PartyList = response.body().getData();
                    spPerInvPartyName.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    spPerInvPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            etPerInvPartyAddress.setText(PartyList.get(position).getAddress());
                            etPerInvPartyCSTNo.setText(PartyList.get(position).getCSTNo());
                            etPerInvPartyTransport.setText(PartyList.get(position).getTransportName());
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
                Intent performInvoiceList = new Intent(activity, PerformInvoiceActivity.class);
                startActivity(performInvoiceList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        if (etPerInvPartyAddress.getText().length() <= 0) {
            flag = true;
            etPerInvPartyAddress.setError("Enter party address");
        }

        if (etPerInvPartyCSTNo.getText().length() <= 0) {
            flag = true;
            etPerInvPartyCSTNo.setError("Enter CST no.");
        }

        if (etPerInvPartyTransport.getText().length() <= 0) {
            flag = true;
            etPerInvPartyTransport.setError("Enter transport name");
        }

        if (etPerInvNo.getText().length() <= 0) {
            flag = true;
            etPerInvNo.setError("Enter perform invoice no");
        }

        if (etPerInvNoOfCases.getText().length() <= 0) {
            flag = true;
            etPerInvNoOfCases.setError("Enter no of cases");
        }

        if (spPerInvPartyName.getSelectedItemPosition() == 0)
            Toast.makeText(getApplicationContext(), "Select a valid Party", Toast.LENGTH_SHORT).show();

        if (!flag)
            SaveData();
    }

    private void SaveData() {
        String PerInvNo = etPerInvNo.getText().toString().trim();
        String PerInvDate = etPerInvDate.getText().toString().trim();
        String PerInvNoofCases = etPerInvNoOfCases.getText().toString().trim();
        String PerInvTotalQuantity = etPerInvQuantity.getText().toString().trim();
        String UserId = "1";
        String Remarks = "NULL";
        String ProductID = "4"; //This is Change not final

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        if (ListID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_ListEntry> call = apiInterface.InsertListEntry("Invoice", UserId, PartyID, ProductID, PerInvNo, PerInvDate, PerInvNoofCases, PerInvTotalQuantity, Remarks);
            call.enqueue(new Callback<Bean_Response_ListEntry>() {
                @Override
                public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Invoice details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    } else {
                        Toast.makeText(activity, "Invoice details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Bean_Response_ListEntry> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        } else {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_ListEntry> call = apiInterface.UpdateListEntry(ListID, PartyID, ProductID, PerInvNo, PerInvDate, PerInvNoofCases, PerInvTotalQuantity, Remarks);
            call.enqueue(new Callback<Bean_Response_ListEntry>() {
                @Override
                public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Invoice details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, PerformInvoiceActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(activity, "Invoice details not updated", Toast.LENGTH_SHORT).show();
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
        etPerInvNo = (EditText) findViewById(R.id.etPerInvNo);
        etPerInvDate = (EditText) findViewById(R.id.etPerInvDate);
        etPerInvNoOfCases = (EditText) findViewById(R.id.etPerInvNoOfCases);
        etPerInvPartyAddress = (EditText) findViewById(R.id.etPerInvPartyAddress);
        etPerInvPartyCSTNo = (EditText) findViewById(R.id.etPerInvPartyCSTNo);
        etPerInvPartyTransport = (EditText) findViewById(R.id.etPerInvPartyTransport);
        etPerInvQuantity = (EditText) findViewById(R.id.etPerInvQuantity);
        spPerInvPartyName = (Spinner) findViewById(R.id.spPerInvPartyName);
    }

    void Clear(){
        etPerInvNo.setText("");
        etPerInvDate.setText("");
        etPerInvNoOfCases.setText("");
        etPerInvPartyAddress.setText("");
        etPerInvPartyCSTNo.setText("");
        etPerInvPartyTransport.setText("");
        etPerInvQuantity.setText("");
    }
}