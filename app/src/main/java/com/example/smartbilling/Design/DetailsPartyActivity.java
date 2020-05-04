package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smartbilling.R;

public class DetailsPartyActivity extends AppCompatActivity {

    TextView tvPartyMobileNo, tvPartyTelephoneNo, tvPartyEmail, tvPartyAddress, tvPartyLocation, tvPartyReference, tvPartyCST, tvPartyTIN, tvPartyBankThrough, tvPartyCreditDays, tvPartyDISC, tvPartyFaxNo, tvPartyTransportName, tvPartyTransportMobileNo, tvPartyTransportAddress, tvPartyBrokerName, tvPartyBrokerRate, tvPartyBrokerMobile, tvPartyBrokerTelephone, tvPartyBrokerEmail, tvPartyBrokerAddress, tvPartyBrokerFax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_party);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        final String PartyID = getIntent().getStringExtra("PartyID");
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
        final String PartyBrokerID = getIntent().getStringExtra("PartyBrokerID");
        final String PartyBrokerName = getIntent().getStringExtra("PartyBrokerName");
        final String PartyBrokerRate = getIntent().getStringExtra("PartyBrokerRate");
        final String PartyBrokerMobileNo = getIntent().getStringExtra("PartyBrokerMobileNo");
        final String PartyBrokerTelephoneNo = getIntent().getStringExtra("PartyBrokerTelephoneNo");
        final String PartyBrokerEmail = getIntent().getStringExtra("PartyBrokerEmail");
        final String PartyBrokerAddress = getIntent().getStringExtra("PartyBrokerAddress");
        final String PartyBrokerFaxNo = getIntent().getStringExtra("PartyBrokerFaxNo");
        final String PartyTransportID = getIntent().getStringExtra("PartyTransportID");
        final String PartyTransportName = getIntent().getStringExtra("PartyTransportName");
        final String PartyTransportAddress = getIntent().getStringExtra("PartyTransportAddress");
        final String PartyTransportMobileNo = getIntent().getStringExtra("PartyTransportMobileNo");

        getSupportActionBar().setTitle(PartyName);

        if(PartyMobileNumber != null)
            tvPartyMobileNo.setText(PartyMobileNumber);

        if(PartyTelephoneNumber != null)
            tvPartyTelephoneNo.setText(PartyTelephoneNumber);

        if(PartyEmail != null)
            tvPartyEmail.setText(PartyEmail);

        if(PartyAddress != null)
            tvPartyAddress.setText(PartyAddress + ", "+ PartyState + ", "+ PartyCity + ", " + PartyPin);

        if(PartyLocation != null)
            tvPartyLocation.setText(PartyLocation);

        if(PartyRefName != null)
            tvPartyReference.setText(PartyRefName);

        if(PartyCSTNumber != null)
            tvPartyCST.setText(PartyCSTNumber);

        if(PartyTINNumber != null)
            tvPartyTIN.setText(PartyTINNumber);

        if(PartyBankThrough != null)
            tvPartyBankThrough.setText(PartyBankThrough);

        if(PartyCreditDays != null)
            tvPartyCreditDays.setText(PartyCreditDays);

        if(PartyDISC != null)
            tvPartyDISC.setText(PartyDISC);

        if(PartyFaxNumber != null)
            tvPartyFaxNo.setText(PartyFaxNumber);

        if(PartyBrokerName != null)
            tvPartyBrokerName.setText(PartyBrokerName);

        if(PartyBrokerRate != null)
            tvPartyBrokerRate.setText(PartyBrokerRate);

        if(PartyBrokerMobileNo != null)
            tvPartyBrokerMobile.setText(PartyBrokerMobileNo);

        if(PartyBrokerTelephoneNo != null)
            tvPartyBrokerTelephone.setText(PartyBrokerTelephoneNo);

        if(PartyBrokerEmail != null)
            tvPartyBrokerEmail.setText(PartyBrokerEmail);

        if(PartyBrokerAddress != null)
            tvPartyBrokerAddress.setText(PartyBrokerAddress);

        if(PartyBrokerFaxNo != null)
            tvPartyBrokerFax.setText(PartyBrokerFaxNo);

        if(PartyTransportName != null)
            tvPartyTransportName.setText(PartyTransportName);

        if(PartyTransportMobileNo != null)
            tvPartyTransportMobileNo.setText(PartyTransportMobileNo);

        if(PartyTransportAddress != null)
            tvPartyTransportAddress.setText(PartyTransportAddress);

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

        tvPartyBrokerMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PartyBrokerMobileNo, null));
                startActivity(intent);
            }
        });

        tvPartyBrokerTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PartyBrokerTelephoneNo, null));
                startActivity(intent);
            }
        });

        tvPartyTransportMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PartyTransportMobileNo, null));
                startActivity(intent);
            }
        });
    }

    void init(){
        tvPartyMobileNo = (TextView) findViewById (R.id.tvPartyMobileNo);
        tvPartyTelephoneNo = (TextView) findViewById (R.id.tvPartyTelephoneNo);
        tvPartyEmail = (TextView) findViewById (R.id.tvPartyEmail);
        tvPartyAddress = (TextView) findViewById (R.id.tvPartyAddress);
        tvPartyLocation = (TextView) findViewById (R.id.tvPartyLocation);
        tvPartyReference = (TextView) findViewById (R.id.tvPartyReference);
        tvPartyCST = (TextView) findViewById (R.id.tvPartyCST);
        tvPartyTIN = (TextView) findViewById (R.id.tvPartyTIN);
        tvPartyBankThrough = (TextView) findViewById (R.id.tvPartyBankThrough);
        tvPartyCreditDays = (TextView) findViewById (R.id.tvPartyCreditDays);
        tvPartyDISC = (TextView) findViewById (R.id.tvPartyDISC);
        tvPartyFaxNo = (TextView) findViewById (R.id.tvPartyFaxNo);
        tvPartyTransportName = (TextView) findViewById (R.id.tvPartyTransportName);
        tvPartyTransportMobileNo = (TextView) findViewById (R.id.tvPartyTransportMobileNo);
        tvPartyTransportAddress = (TextView) findViewById (R.id.tvPartyTransportAddress);
        tvPartyBrokerName = (TextView) findViewById (R.id.tvPartyBrokerName);
        tvPartyBrokerRate = (TextView) findViewById (R.id.tvPartyBrokerRate);
        tvPartyBrokerMobile = (TextView) findViewById (R.id.tvPartyBrokerMobile);
        tvPartyBrokerTelephone = (TextView) findViewById (R.id.tvPartyBrokerTelephone);
        tvPartyBrokerEmail = (TextView) findViewById (R.id.tvPartyBrokerEmail);
        tvPartyBrokerAddress = (TextView) findViewById (R.id.tvPartyBrokerAddress);
        tvPartyBrokerFax = (TextView) findViewById (R.id.tvPartyBrokerFax);
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