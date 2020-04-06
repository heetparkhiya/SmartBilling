package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smartbilling.R;

public class DetailsCompanyActivity extends AppCompatActivity {

    TextView tvCompanyContactNo, tvCompanyAddress, tvCompanyEmail, tvCompanyVatTinNo, tvCompanyCstTinNo, tvCompanyFaxNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_company);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        final String CompanyID = getIntent().getStringExtra("CompanyID");
        final String CompanyName = getIntent().getStringExtra("CompanyName");
        final String CompanyContactNumber = getIntent().getStringExtra("CompanyContactNumber");
        final String CompanyAddress = getIntent().getStringExtra("CompanyAddress");
        final String CompanyEmail = getIntent().getStringExtra("CompanyEmail");
        final String CompanyVAT_TIN_No = getIntent().getStringExtra("CompanyVAT_TIN_No");
        final String CompanyCST_TIN_No = getIntent().getStringExtra("CompanyCST_TIN_No");
        final String CompanyFaxNo = getIntent().getStringExtra("CompanyFaxNo");

        getSupportActionBar().setTitle(CompanyName);

        if(CompanyContactNumber != null)
            tvCompanyContactNo.setText(CompanyContactNumber);

        if(CompanyAddress != null)
            tvCompanyAddress.setText(CompanyAddress);

        if(CompanyEmail != null)
            tvCompanyEmail.setText(CompanyEmail);

        if(CompanyVAT_TIN_No != null)
            tvCompanyVatTinNo.setText(CompanyVAT_TIN_No);


        if(CompanyCST_TIN_No != null)
            tvCompanyCstTinNo.setText(CompanyCST_TIN_No);

        if(CompanyFaxNo != null)
            tvCompanyFaxNo.setText(CompanyFaxNo);

        tvCompanyContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", CompanyContactNumber, null));
                startActivity(intent);
            }
        });
    }

    void init(){
        tvCompanyContactNo = (TextView) findViewById (R.id.tvCompanyContactNo);
        tvCompanyAddress = (TextView) findViewById (R.id.tvCompanyAddress);
        tvCompanyEmail = (TextView) findViewById (R.id.tvCompanyEmail);
        tvCompanyVatTinNo = (TextView) findViewById (R.id.tvCompanyVatTinNo);
        tvCompanyCstTinNo = (TextView) findViewById (R.id.tvCompanyCstTinNo);
        tvCompanyFaxNo = (TextView) findViewById (R.id.tvCompanyFaxNo);
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
