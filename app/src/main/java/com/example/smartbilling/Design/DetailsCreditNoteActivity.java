package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smartbilling.R;

public class DetailsCreditNoteActivity extends AppCompatActivity {

    TextView tvCreditNoteNo, tvCreditDate, tvCreditNoteNoOfCases, tvCreditNoteInvoiceNo, tvCreditNoteInvoiceDate, tvCreditNoteTotalQuantity, tvCreditNoteAmount, tvCreditNoteDiscountPR, tvCreditNoteDiscount, tvCreditNoteTotal, tvCreditNoteTaxPR, tvCreditNoteTax, tvCreditNoteOtherCharges, tvCreditNoteGrandTotal, tvCreditNoteCForm, tvPartyName, tvPartyMobileNo, tvPartyTelephoneNo, tvPartyEmail, tvPartyAddress, tvPartyLocation, tvPartyReference, tvPartyCST, tvPartyTIN, tvPartyBankThrough, tvPartyCreditDays, tvPartyDISC, tvPartyFaxNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_credit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        final String CreditNoteID = getIntent().getStringExtra("CreditNoteID");
        final String CreditNoteNo = getIntent().getStringExtra("CreditNoteNo");
        final String CreditNoteDate = getIntent().getStringExtra("CreditNoteDate");
        final String CreditNoteNoOfCases = getIntent().getStringExtra("CreditNoteNoOfCases");
        final String CreditNoteInvoiceNo = getIntent().getStringExtra("CreditNoteInvoiceNo");
        final String CreditNoteInvoiceDate = getIntent().getStringExtra("CreditNoteInvoiceDate");
        final String CreditNoteTotalQuantity = getIntent().getStringExtra("CreditNoteTotalQuantity");
        final String CreditNoteTotalAmount = getIntent().getStringExtra("CreditNoteTotalAmount");
        final String CreditNoteDiscountPR = getIntent().getStringExtra("CreditNoteDiscountPR");
        final String CreditNoteDiscount = getIntent().getStringExtra("CreditNoteDiscount");
        final String CreditNoteTotal = getIntent().getStringExtra("CreditNoteTotal");
        final String CreditNoteTaxPR = getIntent().getStringExtra("CreditNoteTaxPR");
        final String CreditNoteTax = getIntent().getStringExtra("CreditNoteTax");
        final String CreditNoteOtherCharges = getIntent().getStringExtra("CreditNoteOtherCharges");
        final String CreditNoteGrandTotal = getIntent().getStringExtra("CreditNoteGrandTotal");
        final String CreditNoteCForm = getIntent().getStringExtra("CreditNoteCForm");
        final String PartyName = getIntent().getStringExtra("PartyName");
        final String PartyMobileNumber = getIntent().getStringExtra("PartyMobileNumber");
        final String PartyTelephoneNumber = getIntent().getStringExtra("PartyTelephoneNumber");
        final String PartyEmail = getIntent().getStringExtra("PartyEmail");
        final String PartyAddress = getIntent().getStringExtra("PartyAddress");
        final String PartyCity = getIntent().getStringExtra("PartyCity");
        final String PartyState = getIntent().getStringExtra("PartyState");
        final String PartyPin = getIntent().getStringExtra("PartyPin");
        final String PartyLocation = getIntent().getStringExtra("PartyLocation");
        final String PartyRefName = getIntent().getStringExtra("PartyRefName");
        final String PartyCSTNumber = getIntent().getStringExtra("PartyCSTNumber");
        final String PartyTINNumber = getIntent().getStringExtra("PartyTINNumber");
        final String PartyBankThrough = getIntent().getStringExtra("PartyBankThrough");
        final String PartyCreditDays = getIntent().getStringExtra("PartyCreditDays");
        final String PartyDISC = getIntent().getStringExtra("PartyDISC");
        final String PartyFaxNumber = getIntent().getStringExtra("PartyFaxNumber");

        getSupportActionBar().setTitle("Credit Note No.: " + CreditNoteNo);

        if (CreditNoteNo != null)
            tvCreditNoteNo.setText(CreditNoteNo);

        if (CreditNoteDate != null)
            tvCreditDate.setText(CreditNoteDate);

        if (CreditNoteNoOfCases != null)
            tvCreditNoteNoOfCases.setText(CreditNoteNoOfCases);

        if (CreditNoteInvoiceNo != null)
            tvCreditNoteInvoiceNo.setText(CreditNoteInvoiceNo);

        if (CreditNoteInvoiceDate != null)
            tvCreditNoteInvoiceDate.setText(CreditNoteInvoiceDate);

        if (CreditNoteTotalQuantity != null)
            tvCreditNoteTotalQuantity.setText(CreditNoteTotalQuantity);

        if (CreditNoteTotalAmount != null)
            tvCreditNoteAmount.setText(CreditNoteTotalAmount);

        if (CreditNoteDiscountPR != null)
            tvCreditNoteDiscountPR.setText(CreditNoteDiscountPR);

        if (CreditNoteDiscount != null)
            tvCreditNoteDiscount.setText(CreditNoteDiscount);

        if (CreditNoteTotal != null)
            tvCreditNoteTotal.setText(CreditNoteTotal);

        if (CreditNoteTaxPR != null)
            tvCreditNoteTaxPR.setText(CreditNoteTaxPR);

        if (CreditNoteTax != null)
            tvCreditNoteTax.setText(CreditNoteTax);

        if (CreditNoteOtherCharges != null)
            tvCreditNoteOtherCharges.setText(CreditNoteOtherCharges);

        if (CreditNoteGrandTotal != null)
            tvCreditNoteGrandTotal.setText(CreditNoteGrandTotal);

        if (CreditNoteCForm != null)
            tvCreditNoteCForm.setText(CreditNoteCForm);

        if (PartyName != null)
            tvPartyName.setText(PartyName);

        if (PartyMobileNumber != null)
            tvPartyMobileNo.setText(PartyMobileNumber);

        if (PartyTelephoneNumber != null)
            tvPartyTelephoneNo.setText(PartyTelephoneNumber);

        if (PartyEmail != null)
            tvPartyEmail.setText(PartyEmail);

        if (PartyAddress != null)
            tvPartyAddress.setText(PartyAddress + ", " + PartyState + ", " + PartyCity + ", " + PartyPin);

        if (PartyLocation != null)
            tvPartyLocation.setText(PartyLocation);

        if (PartyRefName != null)
            tvPartyReference.setText(PartyRefName);

        if (PartyCSTNumber != null)
            tvPartyCST.setText(PartyCSTNumber);

        if (PartyTINNumber != null)
            tvPartyTIN.setText(PartyTINNumber);

        if (PartyBankThrough != null)
            tvPartyBankThrough.setText(PartyBankThrough);

        if (PartyCreditDays != null)
            tvPartyCreditDays.setText(PartyCreditDays);

        if (PartyDISC != null)
            tvPartyDISC.setText(PartyDISC);

        if (PartyFaxNumber != null)
            tvPartyFaxNo.setText(PartyFaxNumber);

        tvPartyMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PartyMobileNumber, null));
                startActivity(intent);
            }
        });

        tvPartyTelephoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PartyTelephoneNumber, null));
                startActivity(intent);
            }
        });
    }

    void init() {
        tvCreditNoteNo = (TextView) findViewById(R.id.tvCreditNoteNo);
        tvCreditDate = (TextView) findViewById(R.id.tvCreditDate);
        tvCreditNoteNoOfCases = (TextView) findViewById(R.id.tvCreditNoteNoOfCases);
        tvCreditNoteInvoiceNo = (TextView) findViewById(R.id.tvCreditNoteInvoiceNo);
        tvCreditNoteInvoiceDate = (TextView) findViewById(R.id.tvCreditNoteInvoiceDate);
        tvCreditNoteTotalQuantity = (TextView) findViewById(R.id.tvCreditNoteTotalQuantity);
        tvCreditNoteAmount = (TextView) findViewById(R.id.tvCreditNoteAmount);
        tvCreditNoteDiscountPR = (TextView) findViewById(R.id.tvCreditNoteDiscountPR);
        tvCreditNoteDiscount = (TextView) findViewById(R.id.tvCreditNoteDiscount);
        tvCreditNoteTotal = (TextView) findViewById(R.id.tvCreditNoteTotal);
        tvCreditNoteTaxPR = (TextView) findViewById(R.id.tvCreditNoteTaxPR);
        tvCreditNoteTax = (TextView) findViewById(R.id.tvCreditNoteTax);
        tvCreditNoteOtherCharges = (TextView) findViewById(R.id.tvCreditNoteOtherCharges);
        tvCreditNoteGrandTotal = (TextView) findViewById(R.id.tvCreditNoteGrandTotal);
        tvCreditNoteCForm = (TextView) findViewById(R.id.tvCreditNoteCForm);
        tvPartyName = (TextView) findViewById(R.id.tvPartyName);
        tvPartyMobileNo = (TextView) findViewById(R.id.tvPartyMobileNo);
        tvPartyTelephoneNo = (TextView) findViewById(R.id.tvPartyTelephoneNo);
        tvPartyEmail = (TextView) findViewById(R.id.tvPartyEmail);
        tvPartyAddress = (TextView) findViewById(R.id.tvPartyAddress);
        tvPartyLocation = (TextView) findViewById(R.id.tvPartyLocation);
        tvPartyReference = (TextView) findViewById(R.id.tvPartyReference);
        tvPartyCST = (TextView) findViewById(R.id.tvPartyCST);
        tvPartyTIN = (TextView) findViewById(R.id.tvPartyTIN);
        tvPartyBankThrough = (TextView) findViewById(R.id.tvPartyBankThrough);
        tvPartyCreditDays = (TextView) findViewById(R.id.tvPartyCreditDays);
        tvPartyDISC = (TextView) findViewById(R.id.tvPartyDISC);
        tvPartyFaxNo = (TextView) findViewById(R.id.tvPartyFaxNo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}