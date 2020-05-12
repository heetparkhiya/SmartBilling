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

public class AddPackingListActivity extends AppCompatActivity {

    final Activity activity = this;
    EditText etPackingListNo, etPackingListDate, etPackingListNoOfCases, etPackingListPartyAddress, etPackingListPartyCSTNo, etPackingListPartyTransport, etPackingListTotalQuantity;
    Spinner spPackingListPartyName;
    Calendar CurrentDate;
    int day, month, year;
    String PartyID = "-1", ListID, PartyAddress, PartyCSTNo, PartyTransportName, ListNo, ListDate, NoofCases, TotalQuantity;
    ApiInterface apiInterface;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_packing_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        FillSpinnerParty();

        ListID = getIntent().getStringExtra("ListID");
        PartyAddress = getIntent().getStringExtra("PartyAddress");
        PartyCSTNo = getIntent().getStringExtra("PartyCSTNo");
        PartyTransportName = getIntent().getStringExtra("PartyTransportName");
        ListNo = getIntent().getStringExtra("ListNo");
        ListDate = getIntent().getStringExtra("ListDate"); //Check It give or not
        NoofCases = getIntent().getStringExtra("NoofCases");
        TotalQuantity = getIntent().getStringExtra("TotalQuantity"); //Total Quantity Pending

        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etPackingListDate.setText(day + "/" + (month + 1) + "/" + year);
        etPackingListDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etPackingListDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        if (ListID == null) {
            getSupportActionBar().setTitle("Add Packing List");
        } else {
            getSupportActionBar().setTitle("Edit Packing List");
            etPackingListPartyAddress.setText(PartyAddress);
            etPackingListPartyCSTNo.setText(PartyCSTNo);
            etPackingListPartyTransport.setText(PartyTransportName);
            etPackingListNo.setText(ListNo);
            etPackingListNoOfCases.setText(NoofCases);
            etPackingListTotalQuantity.setText(TotalQuantity);
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
                    spPackingListPartyName.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    spPackingListPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            etPackingListPartyAddress.setText(PartyList.get(position).getAddress());
                            etPackingListPartyCSTNo.setText(PartyList.get(position).getCSTNo());
                            etPackingListPartyTransport.setText(PartyList.get(position).getTransportName());
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
                Intent packingList = new Intent(activity, PackingListActivity.class);
                startActivity(packingList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        if (etPackingListPartyAddress.getText().length() <= 0) {
            flag = true;
            etPackingListPartyAddress.setError("Enter party address");
        }

        if (etPackingListPartyCSTNo.getText().length() <= 0) {
            flag = true;
            etPackingListPartyCSTNo.setError("Enter CST no.");
        }

        if (etPackingListPartyTransport.getText().length() <= 0) {
            flag = true;
            etPackingListPartyTransport.setError("Enter transport name");
        }

        if (etPackingListNo.getText().length() <= 0) {
            flag = true;
            etPackingListNo.setError("Enter packing list no");
        }

        if (etPackingListNoOfCases.getText().length() <= 0) {
            flag = true;
            etPackingListNoOfCases.setError("Enter no of cases");
        }

        if(spPackingListPartyName.getSelectedItemPosition() ==0)
            Toast.makeText(getApplicationContext(), "Select a valid Party", Toast.LENGTH_SHORT).show();

        if (!flag)
            SaveData();
    }

    private void SaveData() {
        String PackingNo = etPackingListNo.getText().toString().trim();
        String PackingDate = etPackingListDate.getText().toString().trim();
        String PackingNoofCases = etPackingListNoOfCases.getText().toString().trim();
        String PackingTotalQuantity = etPackingListTotalQuantity.getText().toString().trim();
        String UserId = "1";
        String Remarks = "NULL";
        String ProductID = "4"; //This is Change not final

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        if(ListID == null){
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_ListEntry> call = apiInterface.InsertListEntry("Packing", UserId, PartyID, ProductID, PackingNo, PackingDate, PackingNoofCases, PackingTotalQuantity, Remarks);
            call.enqueue(new Callback<Bean_Response_ListEntry>() {
                @Override
                public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Packing details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    }
                    else{
                        Toast.makeText(activity, "Packing details not added", Toast.LENGTH_SHORT).show();
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
        else{
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_ListEntry> call = apiInterface.UpdateListEntry(ListID, PartyID, ProductID, PackingNo, PackingDate, PackingNoofCases, PackingTotalQuantity, Remarks);
            call.enqueue(new Callback<Bean_Response_ListEntry>() {
                @Override
                public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Order details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, PackingListActivity.class);
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
    void init(){
        etPackingListNo = (EditText) findViewById (R.id.etPackingListNo);
        etPackingListDate = (EditText) findViewById (R.id.etPackingListDate);
        etPackingListNoOfCases = (EditText) findViewById (R.id.etPackingListNoOfCases);
        etPackingListPartyAddress = (EditText) findViewById (R.id.etPackingListPartyAddress);
        etPackingListPartyCSTNo = (EditText) findViewById (R.id.etPackingListPartyCSTNo);
        etPackingListPartyTransport = (EditText) findViewById (R.id.etPackingListPartyTransport);
        etPackingListTotalQuantity = (EditText) findViewById (R.id.etPackingListTotalQuantity);
        spPackingListPartyName = (Spinner) findViewById (R.id.spPackingListPartyName);
    }

    void Clear(){
        etPackingListNo.setText("");
        etPackingListDate.setText("");
        etPackingListNoOfCases.setText("");
        etPackingListPartyAddress.setText("");
        etPackingListPartyCSTNo.setText("");
        etPackingListPartyTransport.setText("");
        etPackingListTotalQuantity.setText("");
    }
}
