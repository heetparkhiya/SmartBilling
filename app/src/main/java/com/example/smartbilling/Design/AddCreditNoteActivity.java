package com.example.smartbilling.Design;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Spinner_Party;
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.Bean.Bean_Response_CreditNote;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;
import com.example.smartbilling.databinding.ActivityAddCreditNoteBinding;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCreditNoteActivity extends AppCompatActivity {

    final Activity activity = this;
    ActivityAddCreditNoteBinding binding;
    ApiInterface apiInterface;
    boolean flag = false;
    Calendar CurrentDate;
    int day, month, year;
    SessionManager manager;
    String PartyID = "-1", CreditNoteID, CreditNoteNo, CreditNoteDate, CreditNoteNoOfCases, CreditNoteInvoiceNo, CreditNoteInvoiceDate, CreditNoteTotalQuantity, CreditNoteTotalAmount, CreditNoteDiscountPR, CreditNoteDiscount, CreditNoteTotal, CreditNoteTaxPR, CreditNoteTax, CreditNoteOtherCharges, CreditNoteGrandTotal, CreditNoteCForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCreditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        else {
            getSupportActionBar().setTitle("Edit Credit Note");
            binding.etCNNo.setText(CreditNoteNo);
            binding.etCNNoOfCases.setText(CreditNoteNoOfCases);
            binding.etCNInvoiceNo.setText(CreditNoteInvoiceNo);
            binding.etCNTotal.setText(CreditNoteTotal);
            binding.etCNQuantity.setText(CreditNoteTotalQuantity);
            binding.etCNAmount.setText(CreditNoteTotalAmount);
            binding.etCNDiscountPR.setText(CreditNoteDiscountPR);
            binding.etCNDiscount.setText(CreditNoteDiscount);
            binding.etCNTotal.setText(CreditNoteTotal);
            binding.etCNTAXPR.setText(CreditNoteTaxPR);
            binding.etCNTAX.setText(CreditNoteTax);
            binding.etCNOtherCharge.setText(CreditNoteOtherCharges);
            binding.etCNGrandTotal.setText(CreditNoteGrandTotal);

            if (CreditNoteCForm == "Yes")
                binding.cbCForm.setChecked(true);
            else
                binding.cbCForm.setChecked(false);
        }

        CurrentDate = Calendar.getInstance();
        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        binding.etCNDate.setText(day + "/" + (month + 1) + "/" + year);
        binding.etCNDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        binding.etCNDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        day = CurrentDate.get(Calendar.DAY_OF_MONTH);
        month = CurrentDate.get(Calendar.MONTH);
        year = CurrentDate.get(Calendar.YEAR);
        binding.etCNInvoiceDate.setText(day + "/" + (month + 1) + "/" + year);
        binding.etCNInvoiceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                        monthofyear = monthofyear + 1;
                        binding.etCNInvoiceDate.setText(dayOfMonth + "/" + monthofyear + "/" + year);
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
                    binding.spCNPartyName.setAdapter(new Adapter_Spinner_Party(PartyList, activity));
                    binding.spCNPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            binding.etCNPartyAddress.setText(PartyList.get(position).getAddress());
                            binding.etCNPartyCSTNo.setText(PartyList.get(position).getCSTNo());
                            binding.etCNPartyTransport.setText(PartyList.get(position).getTransportName());
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
        if (binding.etCNPartyAddress.getText().length() <= 0) {
            flag = true;
            binding.etCNPartyAddress.setError("Enter address");
        }

        if (binding.etCNPartyCSTNo.getText().length() <= 0) {
            flag = true;
            binding.etCNPartyCSTNo.setError("Enter CST no.");
        }

        if (binding.etCNPartyTransport.getText().length() <= 0) {
            flag = true;
            binding.etCNPartyTransport.setError("Enter transport");
        }

        if (binding.etCNNo.getText().length() <= 0) {
            flag = true;
            binding.etCNNo.setError("Enter credit note no.");
        }

        if (binding.etCNNoOfCases.getText().length() <= 0) {
            flag = true;
            binding.etCNNoOfCases.setError("Enter no. of cases");
        }

        if (binding.etCNInvoiceNo.getText().length() <= 0) {
            flag = true;
            binding.etCNInvoiceNo.setError("Enter invoice no.");
        }

        if (binding.etCNQuantity.getText().length() <= 0) {
            flag = true;
            binding.etCNQuantity.setError("Enter total quantity");
        }

        if (binding.etCNAmount.getText().length() <= 0) {
            flag = true;
            binding.etCNAmount.setError("Enter total amount");
        }

        if (binding.etCNDiscountPR.getText().length() <= 0) {
            flag = true;
            binding.etCNDiscountPR.setError("Enter discount in %");
        }

        if (binding.etCNDiscount.getText().length() <= 0) {
            flag = true;
            binding.etCNDiscount.setError("Enter discount");
        }

        if (binding.etCNTotal.getText().length() <= 0) {
            flag = true;
            binding.etCNTotal.setError("Enter total");
        }

        if (binding.etCNTAXPR.getText().length() <= 0) {
            flag = true;
            binding.etCNTAXPR.setError("Enter tax in %");
        }

        if (binding.etCNTAX.getText().length() <= 0) {
            flag = true;
            binding.etCNTAX.setError("Enter tax");
        }

        if (binding.etCNOtherCharge.getText().length() <= 0) {
            flag = true;
            binding.etCNOtherCharge.setError("Enter other charges");
        }

        if (binding.etCNGrandTotal.getText().length() <= 0) {
            flag = true;
            binding.etCNGrandTotal.setError("Enter grand total");
        }

        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String CreditNoteNo = binding.etCNNo.getText().toString().trim();
        String CreditDate = binding.etCNDate.getText().toString().trim();
        String NoofCases = binding.etCNNoOfCases.getText().toString().trim();
        String InvoiceNo = binding.etCNInvoiceNo.getText().toString().trim();
        String InvoiceDate = binding.etCNInvoiceDate.getText().toString().trim();
        String TotalQuantity = binding.etCNQuantity.getText().toString().trim();
        String TotalAmount = binding.etCNAmount.getText().toString().trim();
        String DiscountPR = binding.etCNDiscountPR.getText().toString().trim();
        String Discount = binding.etCNDiscount.getText().toString().trim();
        String Total = binding.etCNTotal.getText().toString().trim();
        String TaxPR = binding.etCNTAXPR.getText().toString().trim();
        String Tax = binding.etCNTAX.getText().toString().trim();
        String OtherCharges = binding.etCNOtherCharge.getText().toString().trim();
        String GrandTotal = binding.etCNGrandTotal.getText().toString().trim();
        String CForm = "";
        /*if(CreditNoteCForm.equals("Yes"))
            binding.cbCForm.setChecked(true);
        else
            binding.cbCForm.setChecked(false);*/

        String UserID = manager.getUserID(activity);
        String Remarks = "NULL";

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (CreditNoteID == null) {
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
        } else {
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


    void Clear() {
        binding.etCNPartyAddress.setText("");
        binding.etCNPartyCSTNo.setText("");
        binding.etCNPartyTransport.setText("");
        binding.etCNNo.setText("");
        binding.etCNNoOfCases.setText("");
        binding.etCNDate.setText("");
        binding.etCNInvoiceNo.setText("");
        binding.etCNInvoiceDate.setText("");
        binding.etCNQuantity.setText("");
        binding.etCNAmount.setText("");
        binding.etCNDiscountPR.setText("");
        binding.etCNDiscount.setText("");
        binding.etCNTotal.setText("");
        binding.etCNTAXPR.setText("");
        binding.etCNTAX.setText("");
        binding.etCNOtherCharge.setText("");
        binding.etCNGrandTotal.setText("");
    }
}