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
import com.example.smartbilling.Bean.Bean_Response_Collection;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.R;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCollectionActivity extends AppCompatActivity {

    final Activity activity = this;
    Spinner spCollectionPartyName, spCollectionMOP;
    EditText etCollectionBillNo, etCollectionBillDate, etCollectionDays, etCollectionBankName, etCollectionBankBranch, etCollectionNo, etCollectionDate, etCollectionChequeNo, etCollectionChequeDate, etCollectionBillAmount, etCollectionCreditNote, etCollectionDiscountPR, etCollectionDiscount, etCollectionReceivedAmount, etCollectionAmount, etCollectionClearanceDate;
    Calendar CurrentDate;
    int day, month, year;
    String PartyID = "-1";
    ApiInterface apiInterface;
    boolean flag = false;
    String CollectionID, SP_PartyID, PartyName, BillNo, BillDate, Days, BankName, BankBranch, CollectionNo, CollectionDate, ModeOfPayment, ChequeNo, ChequeDate, BillAmount, CreditNote, DiscountPR, Discount, ReceivedAmount, CollectionAmount, ClearanceDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        FillSpinnerParty();
        CollectionID = getIntent().getStringExtra("CollectionID");
        SP_PartyID = getIntent().getStringExtra("PartyID");
        PartyName = getIntent().getStringExtra("PartyName");
        BillNo = getIntent().getStringExtra("BillNo");
        BillDate = getIntent().getStringExtra("BillDate");
        Days = getIntent().getStringExtra("Days");
        BankName = getIntent().getStringExtra("BankName");
        BankBranch = getIntent().getStringExtra("BankBranch");
        CollectionNo = getIntent().getStringExtra("CollectionNo");
        CollectionDate = getIntent().getStringExtra("CollectionDate");
        ModeOfPayment = getIntent().getStringExtra("ModeOfPayment");
        ChequeNo = getIntent().getStringExtra("ChequeNo");
        ChequeDate = getIntent().getStringExtra("ChequeDate");
        BillAmount = getIntent().getStringExtra("BillAmount");
        CreditNote = getIntent().getStringExtra("CreditNote");
        DiscountPR = getIntent().getStringExtra("DiscountPR");
        Discount = getIntent().getStringExtra("Discount");
        ReceivedAmount = getIntent().getStringExtra("ReceivedAmount");
        CollectionAmount = getIntent().getStringExtra("CollectionAmount");
        ClearanceDate = getIntent().getStringExtra("ClearanceDate");

        if(CollectionID == null)
            getSupportActionBar().setTitle("Add Collection");
        else {
            getSupportActionBar().setTitle("Edit Collection Details");
            spCollectionPartyName.setSelection(Integer.parseInt(SP_PartyID));
            etCollectionBillNo.setText(BillNo);
            etCollectionBillDate.setText(BillDate);
            etCollectionDays.setText(Days);
            etCollectionBankName.setText(BankName);
            etCollectionBankBranch.setText(BankBranch);
            etCollectionNo.setText(CollectionNo);

            // Here mode of payment and collection date are pending

            etCollectionChequeNo.setText(ChequeNo);

            // Here mode of Cheque Date is pending

            etCollectionBillAmount.setText(BillAmount);
            etCollectionCreditNote.setText(CreditNote);
            etCollectionDiscountPR.setText(DiscountPR);
            etCollectionDiscount.setText(Discount);
            etCollectionReceivedAmount.setText(ReceivedAmount);
            etCollectionAmount.setText(CollectionAmount);
            etCollectionClearanceDate.setText(ClearanceDate);
        }


        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etCollectionBillDate.setText(day + "/" + (month + 1) + "/" + year);
        etCollectionBillDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etCollectionBillDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etCollectionDate.setText(day + "/" + (month + 1) + "/" + year);
        etCollectionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etCollectionDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etCollectionClearanceDate.setText(day + "/" + (month + 1) + "/" + year);
        etCollectionClearanceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etCollectionClearanceDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        etCollectionChequeDate.setText(day + "/" + (month + 1) + "/" + year);
        etCollectionChequeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etCollectionChequeDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
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
                    spCollectionPartyName.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    spCollectionPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            etCollectionDays.setText(PartyList.get(position).getCreditDays());
                            etCollectionBankName.setText(PartyList.get(position).getBankThrough());
                            //etCollectionBankBranch.setText(PartyList.get(position).getBankThrough());
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
                Intent collectionList = new Intent(activity, CollectionActivity.class);
                startActivity(collectionList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        if (etCollectionBillNo.getText().length() <= 0) {
            flag = true;
            etCollectionBillNo.setError("Enter bill no.");
        }

        if (etCollectionDays.getText().length() <= 0) {
            flag = true;
            etCollectionDays.setError("Enter days");
        }

        if (etCollectionBankName.getText().length() <= 0) {
            flag = true;
            etCollectionBankName.setError("Enter bank name");
        }

        if (etCollectionBankBranch.getText().length() <= 0) {
            flag = true;
            etCollectionBankBranch.setError("Enter bank branch");
        }

        if (etCollectionNo.getText().length() <= 0) {
            flag = true;
            etCollectionNo.setError("Enter collection no.");
        }

        if (etCollectionChequeNo.getText().length() <= 0) {
            flag = true;
            etCollectionChequeNo.setError("Enter cheque no.");
        }

        if (etCollectionBillAmount.getText().length() <= 0) {
            flag = true;
            etCollectionBillAmount.setError("Enter bill amount");
        }

        if (etCollectionCreditNote.getText().length() <= 0) {
            flag = true;
            etCollectionCreditNote.setError("Enter credit note");
        }

        if (etCollectionDiscountPR.getText().length() <= 0) {
            flag = true;
            etCollectionDiscountPR.setError("Enter discount in %");
        }

        if (etCollectionDiscount.getText().length() <= 0) {
            flag = true;
            etCollectionDiscount.setError("Enter discount");
        }

        if (etCollectionReceivedAmount.getText().length() <= 0) {
            flag = true;
            etCollectionReceivedAmount.setError("Enter received amount");
        }

        if (etCollectionAmount.getText().length() <= 0) {
            flag = true;
            etCollectionAmount.setError("Enter collection amount");
        }

        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String BillNo = etCollectionBillNo.getText().toString().trim();
        String BillDate = etCollectionBillDate.getText().toString().trim();
        String Days = etCollectionDays.getText().toString().trim();
        String BankName = etCollectionBankName.getText().toString().trim();
        String BankBranch = etCollectionBankBranch.getText().toString().trim();
        String CollectionNo = etCollectionNo.getText().toString().trim();
        String CollectionDate = etCollectionDate.getText().toString().trim();
        String PaymentMode = spCollectionMOP.getSelectedItem().toString();
        String ChequeNo = etCollectionChequeNo.getText().toString().trim();
        String ChequeDate = etCollectionChequeDate.getText().toString().trim();
        String BillAmount = etCollectionBillAmount.getText().toString().trim();
        String DiscountPR = etCollectionDiscountPR.getText().toString().trim();
        String Discount = etCollectionDiscount.getText().toString().trim();
        String ReceivedAmount = etCollectionReceivedAmount.getText().toString().trim();
        String CollectionAmount = etCollectionAmount.getText().toString().trim();
        String ClearanceDate = etCollectionClearanceDate.getText().toString().trim();

        String CreditNote = "1"; //Check and Verify and How to use it from the database PENDING
        String UserID = "1";
        String BrokerID = "1"; //Check it need or not PENDING
        String Remarks = "NULL";

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        if(CollectionID == null){
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Collection> call = apiInterface.InsertCollection(UserID, PartyID, BrokerID, CreditNote, BillNo, BillDate, Days, BankName, BankBranch, CollectionNo, CollectionDate, PaymentMode, ChequeNo, ChequeDate, CollectionDate, BillAmount, DiscountPR, Discount, ReceivedAmount, CollectionAmount, ClearanceDate, Remarks);
            call.enqueue(new Callback<Bean_Response_Collection>() {
                @Override
                public void onResponse(Call<Bean_Response_Collection> call, Response<Bean_Response_Collection> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Collection details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    }
                    else{
                        Toast.makeText(activity, "Collection details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_Collection> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        else{
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Collection> call = apiInterface.UpdateCollection(CollectionID, PartyID, BrokerID, CreditNote, BillNo, BillDate, Days, BankName, BankBranch, CollectionNo, CollectionDate, PaymentMode, ChequeNo, ChequeDate, CollectionDate, BillAmount, DiscountPR, Discount, ReceivedAmount, CollectionAmount, ClearanceDate, Remarks);
            call.enqueue(new Callback<Bean_Response_Collection>() {
                @Override
                public void onResponse(Call<Bean_Response_Collection> call, Response<Bean_Response_Collection> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Collection details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, CollectionActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(activity, "Collection details not updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_Collection> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }

    void init() {
        spCollectionPartyName = (Spinner) findViewById(R.id.spCollectionPartyName);
        spCollectionMOP = (Spinner) findViewById(R.id.spCollectionMOP);
        etCollectionBillNo = (EditText) findViewById(R.id.etCollectionBillNo);
        etCollectionBillDate = (EditText) findViewById(R.id.etCollectionBillDate);
        etCollectionDays = (EditText) findViewById(R.id.etCollectionDays);
        etCollectionBankName = (EditText) findViewById(R.id.etCollectionBankName);
        etCollectionBankBranch = (EditText) findViewById(R.id.etCollectionBankBranch);
        etCollectionNo = (EditText) findViewById(R.id.etCollectionNo);
        etCollectionDate = (EditText) findViewById(R.id.etCollectionDate);
        etCollectionChequeNo = (EditText) findViewById(R.id.etCollectionChequeNo);
        etCollectionChequeDate = (EditText) findViewById(R.id.etCollectionChequeDate);
        etCollectionBillAmount = (EditText) findViewById(R.id.etCollectionBillAmount);
        etCollectionCreditNote = (EditText) findViewById(R.id.etCollectionCreditNote);
        etCollectionDiscountPR = (EditText) findViewById(R.id.etCollectionDiscountPR);
        etCollectionDiscount = (EditText) findViewById(R.id.etCollectionDiscount);
        etCollectionReceivedAmount = (EditText) findViewById(R.id.etCollectionReceivedAmount);
        etCollectionAmount = (EditText) findViewById(R.id.etCollectionAmount);
        etCollectionClearanceDate = (EditText) findViewById(R.id.etCollectionClearanceDate);
    }

    void Clear(){
        etCollectionBillNo.setText("");
        etCollectionBillDate.setText("");
        etCollectionDays.setText("");
        etCollectionBankName.setText("");
        etCollectionBankBranch.setText("");
        etCollectionNo.setText("");
        etCollectionDate.setText("");
        etCollectionChequeNo.setText("");
        etCollectionChequeDate.setText("");
        etCollectionBillAmount.setText("");
        etCollectionCreditNote.setText("");
        etCollectionDiscountPR.setText("");
        etCollectionDiscount.setText("");
        etCollectionReceivedAmount.setText("");
        etCollectionAmount.setText("");
        etCollectionClearanceDate.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}