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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Spinner_Party;
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.Bean.Bean_Response_CreditNote;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCreditNoteActivity extends AppCompatActivity {

    final Activity activity = this;
    Spinner spCNPartyName;
    EditText etCNPartyAddress, etCNPartyCSTNo, etCNPartyTransport, etCNNo, etCNNoOfCases, etCNDate, etCNInvoiceNo, etCNInvoiceDate, etCNQuantity, etCNAmount, etCNDiscountPR, etCNDiscount, etCNTotal, etCNTAXPR, etCNTAX, etCNOtherCharge, etCNGrandTotal;
    CheckBox cbCForm;
    ApiInterface apiInterface;
    boolean flag = false;
    Calendar CurrentDate;
    int day, month, year;
    SessionManager manager;
    String PartyID = "-1", CreditNoteID, CreditNoteNo, CreditNoteDate, CreditNoteNoOfCases, CreditNoteInvoiceNo, CreditNoteInvoiceDate, CreditNoteTotalQuantity, CreditNoteTotalAmount, CreditNoteDiscountPR, CreditNoteDiscount, CreditNoteTotal, CreditNoteTaxPR, CreditNoteTax, CreditNoteOtherCharges, CreditNoteGrandTotal, CreditNoteCForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        manager = new SessionManager(activity);
        FillSpinnerParty();

        CreditNoteID = getIntent().getStringExtra("CreditNoteID");
        CreditNoteNo = getIntent().getStringExtra("CreditNoteNo");
        CreditNoteDate = getIntent().getStringExtra("CreditNoteDate");
        CreditNoteNoOfCases = getIntent().getStringExtra("CreditNoteNoOfCases");
        CreditNoteInvoiceNo = getIntent().getStringExtra("CreditNoteInvoiceNo");
        CreditNoteInvoiceDate = getIntent().getStringExtra("CreditNoteInvoiceDate");
        CreditNoteTotalQuantity = getIntent().getStringExtra("CreditNoteTotalQuantity");
        CreditNoteTotalAmount = getIntent().getStringExtra("CreditNoteTotalAmount");
        CreditNoteDiscountPR = getIntent().getStringExtra("CreditNoteDiscountPR");
        CreditNoteDiscount = getIntent().getStringExtra("CreditNoteDiscount");
        CreditNoteTotal = getIntent().getStringExtra("CreditNoteTotal");
        CreditNoteTaxPR = getIntent().getStringExtra("CreditNoteTaxPR");
        CreditNoteTax = getIntent().getStringExtra("CreditNoteTax");
        CreditNoteOtherCharges = getIntent().getStringExtra("CreditNoteOtherCharges");
        CreditNoteGrandTotal = getIntent().getStringExtra("CreditNoteGrandTotal");
        CreditNoteCForm = getIntent().getStringExtra("CreditNoteCForm");

        if (CreditNoteID == null)
            getSupportActionBar().setTitle("Add Credit Note");
        else{
            getSupportActionBar().setTitle("Edit Credit Note");
            etCNNo.setText(CreditNoteNo);
            etCNNoOfCases.setText(CreditNoteNoOfCases);
            etCNInvoiceNo.setText(CreditNoteInvoiceNo);
            etCNTotal.setText(CreditNoteTotal);
            etCNQuantity.setText(CreditNoteTotalQuantity);
            etCNAmount.setText(CreditNoteTotalAmount);
            etCNDiscountPR.setText(CreditNoteDiscountPR);
            etCNDiscount.setText(CreditNoteDiscount);
            etCNTotal.setText(CreditNoteTotal);
            etCNTAXPR.setText(CreditNoteTaxPR);
            etCNTAX.setText(CreditNoteTax);
            etCNOtherCharge.setText(CreditNoteOtherCharges);
            etCNGrandTotal.setText(CreditNoteGrandTotal);

            if(CreditNoteCForm == "Yes")
                cbCForm.setChecked(true);
            else
                cbCForm.setChecked(false);
        }

        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etCNDate.setText(day + "/" + (month + 1) + "/" + year);
        etCNDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etCNDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        etCNInvoiceDate.setText(day + "/" + (month + 1) + "/" + year);
        etCNInvoiceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        etCNInvoiceDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
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
                    spCNPartyName.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    spCNPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            etCNPartyAddress.setText(PartyList.get(position).getAddress());
                            etCNPartyCSTNo.setText(PartyList.get(position).getCSTNo());
                            etCNPartyTransport.setText(PartyList.get(position).getTransportName());
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
                Intent creditNoteList = new Intent(activity, CreditNoteActivity.class);
                startActivity(creditNoteList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        if (etCNPartyAddress.getText().length() <= 0) {
            flag = true;
            etCNPartyAddress.setError("Enter address");
        }

        if (etCNPartyCSTNo.getText().length() <= 0) {
            flag = true;
            etCNPartyCSTNo.setError("Enter CST no.");
        }

        if (etCNPartyTransport.getText().length() <= 0) {
            flag = true;
            etCNPartyTransport.setError("Enter transport");
        }

        if (etCNNo.getText().length() <= 0) {
            flag = true;
            etCNNo.setError("Enter credit note no.");
        }

        if (etCNNoOfCases.getText().length() <= 0) {
            flag = true;
            etCNNoOfCases.setError("Enter no. of cases");
        }

        if (etCNInvoiceNo.getText().length() <= 0) {
            flag = true;
            etCNInvoiceNo.setError("Enter invoice no.");
        }

        if (etCNQuantity.getText().length() <= 0) {
            flag = true;
            etCNQuantity.setError("Enter total quantity");
        }

        if (etCNAmount.getText().length() <= 0) {
            flag = true;
            etCNAmount.setError("Enter total amount");
        }

        if (etCNDiscountPR.getText().length() <= 0) {
            flag = true;
            etCNDiscountPR.setError("Enter discount in %");
        }

        if (etCNDiscount.getText().length() <= 0) {
            flag = true;
            etCNDiscount.setError("Enter discount");
        }

        if (etCNTotal.getText().length() <= 0) {
            flag = true;
            etCNTotal.setError("Enter total");
        }

        if (etCNTAXPR.getText().length() <= 0) {
            flag = true;
            etCNTAXPR.setError("Enter tax in %");
        }

        if (etCNTAX.getText().length() <= 0) {
            flag = true;
            etCNTAX.setError("Enter tax");
        }

        if (etCNOtherCharge.getText().length() <= 0) {
            flag = true;
            etCNOtherCharge.setError("Enter other charges");
        }

        if (etCNGrandTotal.getText().length() <= 0) {
            flag = true;
            etCNGrandTotal.setError("Enter grand total");
        }

        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String CreditNoteNo = etCNNo.getText().toString().trim();
        String CreditDate = etCNDate.getText().toString().trim();
        String NoofCases = etCNNoOfCases.getText().toString().trim();
        String InvoiceNo = etCNInvoiceNo.getText().toString().trim();
        String InvoiceDate = etCNInvoiceDate.getText().toString().trim();
        String TotalQuantity = etCNQuantity.getText().toString().trim();
        String TotalAmount = etCNAmount.getText().toString().trim();
        String DiscountPR = etCNDiscountPR.getText().toString().trim();
        String Discount = etCNDiscount.getText().toString().trim();
        String Total = etCNTotal.getText().toString().trim();
        String TaxPR = etCNTAXPR.getText().toString().trim();
        String Tax = etCNTAX.getText().toString().trim();
        String OtherCharges = etCNOtherCharge.getText().toString().trim();
        String GrandTotal = etCNGrandTotal.getText().toString().trim();
        String CForm = "";
        if(CreditNoteCForm.equals("Yes"))
            cbCForm.setChecked(true);
        else
            cbCForm.setChecked(false);

        String UserID = manager.getUserID(activity);
        String Remarks = "NULL";

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        if(CreditNoteID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_CreditNote> call = apiInterface.InsertCreditNote(UserID, PartyID, CreditNoteNo, CreditDate, NoofCases, InvoiceNo, InvoiceDate, TotalQuantity, TotalAmount, DiscountPR, Discount, Total, TaxPR, Tax, OtherCharges, GrandTotal, CForm, Remarks);
            call.enqueue(new Callback<Bean_Response_CreditNote>() {
                @Override
                public void onResponse(Call<Bean_Response_CreditNote> call, Response<Bean_Response_CreditNote> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Credit note details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    } else {
                        Toast.makeText(activity, "Credit note details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_CreditNote> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        else{
           apiInterface = ApiClient.getClient().create(ApiInterface.class);
           Call<Bean_Response_CreditNote> call = apiInterface.UpdateCreditNote(UserID, CreditNoteID, PartyID, CreditNoteNo, CreditDate, NoofCases, InvoiceNo, InvoiceDate, TotalQuantity, TotalAmount, DiscountPR, Discount, Total, TaxPR, Tax, OtherCharges, GrandTotal, CForm, Remarks);
           call.enqueue(new Callback<Bean_Response_CreditNote>() {
               @Override
               public void onResponse(Call<Bean_Response_CreditNote> call, Response<Bean_Response_CreditNote> response) {
                   if (response.body().getResponse() == 1) {
                       Toast.makeText(activity, "Credit note details updated", Toast.LENGTH_SHORT).show();
                       progress.dismiss();
                       Intent intent = new Intent(activity, CreditNoteActivity.class);
                       startActivity(intent);
                       finish();
                   } else {
                       Toast.makeText(activity, "Credit note details not updated", Toast.LENGTH_SHORT).show();
                       progress.dismiss();
                   }
               }

               @Override
               public void onFailure(Call<Bean_Response_CreditNote> call, Throwable t) {
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
        spCNPartyName = (Spinner) findViewById(R.id.spCNPartyName);
        etCNPartyAddress = (EditText) findViewById(R.id.etCNPartyAddress);
        etCNPartyCSTNo = (EditText) findViewById(R.id.etCNPartyCSTNo);
        etCNPartyTransport = (EditText) findViewById(R.id.etCNPartyTransport);
        etCNNo = (EditText) findViewById(R.id.etCNNo);
        etCNNoOfCases = (EditText) findViewById(R.id.etCNNoOfCases);
        etCNDate = (EditText) findViewById(R.id.etCNDate);
        etCNInvoiceNo = (EditText) findViewById(R.id.etCNInvoiceNo);
        etCNInvoiceDate = (EditText) findViewById(R.id.etCNInvoiceDate);
        etCNQuantity = (EditText) findViewById(R.id.etCNQuantity);
        etCNAmount = (EditText) findViewById(R.id.etCNAmount);
        etCNDiscountPR = (EditText) findViewById(R.id.etCNDiscountPR);
        etCNDiscount = (EditText) findViewById(R.id.etCNDiscount);
        etCNTotal = (EditText) findViewById(R.id.etCNTotal);
        etCNTAXPR = (EditText) findViewById(R.id.etCNTAXPR);
        etCNTAX = (EditText) findViewById(R.id.etCNTAX);
        etCNOtherCharge = (EditText) findViewById(R.id.etCNOtherCharge);
        etCNGrandTotal = (EditText) findViewById(R.id.etCNGrandTotal);
        cbCForm = (CheckBox) findViewById(R.id.cbCForm);
    }

    void Clear(){
        etCNPartyAddress.setText("");
        etCNPartyCSTNo.setText("");
        etCNPartyTransport.setText("");
        etCNNo.setText("");
        etCNNoOfCases.setText("");
        etCNDate.setText("");
        etCNInvoiceNo.setText("");
        etCNInvoiceDate.setText("");
        etCNQuantity.setText("");
        etCNAmount.setText("");
        etCNDiscountPR.setText("");
        etCNDiscount.setText("");
        etCNTotal.setText("");
        etCNTAXPR.setText("");
        etCNTAX.setText("");
        etCNOtherCharge.setText("");
        etCNGrandTotal.setText("");
    }
}