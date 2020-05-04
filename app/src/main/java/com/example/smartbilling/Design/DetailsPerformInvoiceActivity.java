package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smartbilling.R;

public class DetailsPerformInvoiceActivity extends AppCompatActivity {

    TextView tvPartyName, tvPartyMobileNo, tvPartyAddress, tvPartyCSTNo, tvTransportName, tvProductName, tvListNo, tvListDate, tvCaseNo, tvTotalQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_perform_invoice);
        getSupportActionBar().setTitle("Invoice Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        final String ListID = getIntent().getStringExtra("ListID");
        final String ListType = getIntent().getStringExtra("ListType");
        final String PartyName = getIntent().getStringExtra("PartyName");
        final String PartyMobileNo = getIntent().getStringExtra("PartyMobileNo");
        final String PartyAddress = getIntent().getStringExtra("PartyAddress");
        final String PartyCSTNo = getIntent().getStringExtra("PartyCSTNo");
        final String PartyTransportName = getIntent().getStringExtra("PartyTransportName");
        final String ProductName = getIntent().getStringExtra("ProductName");
        final String ListNo = getIntent().getStringExtra("ListNo");
        final String ListDate = getIntent().getStringExtra("ListDate");
        final String NoofCases = getIntent().getStringExtra("NoofCases");
        final String TotalQuantity = getIntent().getStringExtra("TotalQuantity");

        if(PartyName != null)
            tvPartyName.setText(PartyName);

        if(PartyMobileNo != null)
            tvPartyMobileNo.setText(PartyMobileNo);

        if(PartyAddress != null)
            tvPartyAddress.setText(PartyAddress);

        if(PartyCSTNo != null)
            tvPartyCSTNo.setText(PartyCSTNo);

        if(PartyTransportName != null)
            tvTransportName.setText(PartyTransportName);

        if(ProductName != null)
            tvProductName.setText(ProductName);

        if(ListNo != null)
            tvListNo.setText(ListNo);

        if(ListDate != null)
            tvListDate.setText(ListDate);

        if(NoofCases != null)
            tvCaseNo.setText(NoofCases);

        if(TotalQuantity != null)
            tvTotalQuantity.setText(TotalQuantity);

        tvPartyMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PartyMobileNo, null));
                startActivity(intent);
            }
        });
    }

    void init(){
        tvPartyName = (TextView) findViewById (R.id.tvPartyName);
        tvPartyMobileNo = (TextView) findViewById (R.id.tvPartyMobileNo);
        tvPartyAddress = (TextView) findViewById (R.id.tvPartyAddress);
        tvPartyCSTNo = (TextView) findViewById (R.id.tvPartyCSTNo);
        tvTransportName = (TextView) findViewById (R.id.tvTransportName);
        tvProductName = (TextView) findViewById (R.id.tvProductName);
        tvListNo = (TextView) findViewById (R.id.tvListNo);
        tvListDate = (TextView) findViewById (R.id.tvListDate);
        tvCaseNo = (TextView) findViewById (R.id.tvCaseNo);
        tvTotalQuantity = (TextView) findViewById (R.id.tvTotalQuantity);
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