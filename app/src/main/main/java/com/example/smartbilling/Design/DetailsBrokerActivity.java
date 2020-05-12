package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smartbilling.R;

public class DetailsBrokerActivity extends AppCompatActivity {

    TextView tvBrokerMobileNo, tvBrokerAddress, tvBrokerTelephoneNo, tvBrokerFaxNo, tvBrokerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_broker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        final String BrokerID = getIntent().getStringExtra("BrokerID");
        final String BrokerName = getIntent().getStringExtra("BrokerName");
        final String BrokerAddress = getIntent().getStringExtra("BrokerAddress");
        final String BrokerTelephoneNumber = getIntent().getStringExtra("BrokerTelephoneNumber");
        final String BrokerFaxNo = getIntent().getStringExtra("BrokerFaxNo");
        final String BrokerMobileNumber = getIntent().getStringExtra("BrokerMobileNumber");
        final String BrokerEmail = getIntent().getStringExtra("BrokerEmail");
        final String BrokerRate = getIntent().getStringExtra("BrokerRate");

        getSupportActionBar().setTitle(BrokerName);
        getSupportActionBar().setSubtitle("Rate: "+BrokerRate);

        if(BrokerAddress != null)
            tvBrokerAddress.setText(BrokerAddress);

        if(BrokerTelephoneNumber != null)
            tvBrokerTelephoneNo.setText(BrokerTelephoneNumber);

        if(BrokerFaxNo != null)
            tvBrokerFaxNo.setText(BrokerFaxNo);

        if(BrokerMobileNumber != null)
            tvBrokerMobileNo.setText(BrokerMobileNumber);

        if(BrokerEmail != null)
            tvBrokerEmail.setText(BrokerEmail);

        tvBrokerMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", BrokerMobileNumber, null));
                startActivity(intent);
            }
        });

        tvBrokerTelephoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", BrokerTelephoneNumber, null));
                startActivity(intent);
            }
        });
    }

    void init(){
        tvBrokerMobileNo = (TextView) findViewById (R.id.tvBrokerMobileNo);
        tvBrokerMobileNo = (TextView) findViewById (R.id.tvBrokerMobileNo);
        tvBrokerAddress = (TextView) findViewById (R.id.tvBrokerAddress);
        tvBrokerTelephoneNo = (TextView) findViewById (R.id.tvBrokerTelephoneNo);
        tvBrokerFaxNo = (TextView) findViewById (R.id.tvBrokerFaxNo);
        tvBrokerEmail = (TextView) findViewById (R.id.tvBrokerEmail);
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
