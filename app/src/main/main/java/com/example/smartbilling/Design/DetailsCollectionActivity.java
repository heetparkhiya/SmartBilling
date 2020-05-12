package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.smartbilling.R;

public class DetailsCollectionActivity extends AppCompatActivity {

    TextView tvPartyName, tvBillNo, tvBillDate, tvDays, tvPartyBank, tvPartyBankBranch, tvCollectionNo, tvCollectionDate, tvPaymentMode, tvChequeNo, tvChequeDate, tvBillAmount, tvCreditNote, tvDiscountPR, tvDiscount, tvReceivedAmount, tvCollectionAmount, tvClearanceDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_collection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Collection Details");
        init();
        final String CollectionID = getIntent().getStringExtra("CollectionID");
        final String PartyName = getIntent().getStringExtra("PartyName");
        final String BillNo = getIntent().getStringExtra("BillNo");
        final String BillDate = getIntent().getStringExtra("BillDate");
        final String Days = getIntent().getStringExtra("Days");
        final String BankName = getIntent().getStringExtra("BankName");
        final String BankBranch = getIntent().getStringExtra("BankBranch");
        final String CollectionNo = getIntent().getStringExtra("CollectionNo");
        final String CollectionDate = getIntent().getStringExtra("CollectionDate");
        final String ModeOfPayment = getIntent().getStringExtra("ModeOfPayment");
        final String ChequeNo = getIntent().getStringExtra("ChequeNo");
        final String ChequeDate = getIntent().getStringExtra("ChequeDate");
        final String BillAmount = getIntent().getStringExtra("BillAmount");
        final String CreditNote = getIntent().getStringExtra("CreditNote");
        final String DiscountPR = getIntent().getStringExtra("DiscountPR");
        final String Discount = getIntent().getStringExtra("Discount");
        final String ReceivedAmount = getIntent().getStringExtra("ReceivedAmount");
        final String CollectionAmount = getIntent().getStringExtra("CollectionAmount");
        final String ClearanceDate = getIntent().getStringExtra("ClearanceDate");

        if(PartyName != null)
            tvPartyName.setText(PartyName);

        if(BillNo != null)
            tvBillNo.setText(BillNo);

        if(BillDate != null)
            tvBillDate.setText(BillDate);

        if(Days != null)
            tvDays.setText(Days);

        if(BankName != null)
            tvPartyBank.setText(BankName);

        if(BankBranch != null)
            tvPartyBankBranch.setText(BankBranch);

        if(CollectionNo != null)
            tvCollectionNo.setText(CollectionNo);

        if(CollectionDate != null)
            tvCollectionDate.setText(CollectionDate);

        if(ModeOfPayment != null)
            tvPaymentMode.setText(ModeOfPayment);

        if(ChequeNo != null)
            tvChequeNo.setText(ChequeNo);

        if(ChequeDate != null)
            tvChequeDate.setText(ChequeDate);

        if(BillAmount != null)
            tvBillAmount.setText(BillAmount);

        if(CreditNote != null)
            tvCreditNote.setText(CreditNote);

        if(DiscountPR != null)
            tvDiscountPR.setText(DiscountPR);

        if(Discount != null)
            tvDiscount.setText(Discount);

        if(ReceivedAmount != null)
            tvReceivedAmount.setText(ReceivedAmount);

        if(CollectionAmount != null)
            tvCollectionAmount.setText(CollectionAmount);

        if(ClearanceDate != null)
            tvClearanceDate.setText(ClearanceDate);
    }

    void init(){
        tvPartyName = (TextView) findViewById (R.id.tvPartyName);
        tvBillNo = (TextView) findViewById (R.id.tvBillNo);
        tvBillDate = (TextView) findViewById (R.id.tvBillDate);
        tvDays = (TextView) findViewById (R.id.tvDays);
        tvPartyBank = (TextView) findViewById (R.id.tvPartyBank);
        tvPartyBankBranch = (TextView) findViewById (R.id.tvPartyBankBranch);
        tvCollectionNo = (TextView) findViewById (R.id.tvCollectionNo);
        tvCollectionDate = (TextView) findViewById (R.id.tvCollectionDate);
        tvPaymentMode = (TextView) findViewById (R.id.tvPaymentMode);
        tvChequeNo = (TextView) findViewById (R.id.tvChequeNo);
        tvChequeDate = (TextView) findViewById (R.id.tvChequeDate);
        tvBillAmount = (TextView) findViewById (R.id.tvBillAmount);
        tvCreditNote = (TextView) findViewById (R.id.tvCreditNote);
        tvDiscountPR = (TextView) findViewById (R.id.tvDiscountPR);
        tvDiscount = (TextView) findViewById (R.id.tvDiscount);
        tvReceivedAmount = (TextView) findViewById (R.id.tvReceivedAmount);
        tvCollectionAmount = (TextView) findViewById (R.id.tvCollectionAmount);
        tvClearanceDate = (TextView) findViewById (R.id.tvClearanceDate);
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
