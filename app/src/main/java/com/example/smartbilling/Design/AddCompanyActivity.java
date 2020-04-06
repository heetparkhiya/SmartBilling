package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Bean.Bean_Response_Company;
import com.example.smartbilling.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCompanyActivity extends AppCompatActivity {

    final Activity activity = this;
    EditText etCompanyName, etCompanyAddress, etCompanyNumber, etCompanyFaxNo, etCompanyEmail, etCompanyVatTinNo, etCompanyCstTinNo;
    String CompanyID = "", CompanyName = "", CompanyContactNo = "", CompanyAddress = "", CompanyEmail = "", CompanyVAT_TIN_NO = "", CompanyCST_TIN_NO = "", CompanyFaxNO = "";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        CompanyID = getIntent().getStringExtra("CompanyID");
        CompanyName = getIntent().getStringExtra("CompanyName");
        CompanyContactNo = getIntent().getStringExtra("CompanyContactNo");
        CompanyAddress = getIntent().getStringExtra("CompanyAddress");
        CompanyEmail = getIntent().getStringExtra("CompanyEmail");
        CompanyVAT_TIN_NO = getIntent().getStringExtra("CompanyVAT_TIN_NO");
        CompanyCST_TIN_NO = getIntent().getStringExtra("CompanyCST_TIN_NO");
        CompanyFaxNO = getIntent().getStringExtra("CompanyFaxNO");

        if (CompanyID == null) {
            getSupportActionBar().setTitle("Add Company");
        } else {
            getSupportActionBar().setTitle("Edit Company Details");
            etCompanyName.setText(CompanyName);
            etCompanyNumber.setText(CompanyContactNo);
            etCompanyAddress.setText(CompanyAddress);
            etCompanyEmail.setText(CompanyEmail);
            etCompanyVatTinNo.setText(CompanyVAT_TIN_NO);
            etCompanyCstTinNo.setText(CompanyCST_TIN_NO);
            etCompanyFaxNo.setText(CompanyFaxNO);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.cancel:
                Intent companyList = new Intent(activity, CompanyActivity.class);
                startActivity(companyList);
                finish();
                break;

            case R.id.save:
                Validate();
                break;
        }
        return true;
    }

    private void Validate() {
        boolean flag = false;
        if (etCompanyName.getText().length() <= 0) {
            flag = true;
            etCompanyName.setError("Enter company name");
        }
        if (etCompanyAddress.getText().length() <= 0) {
            flag = true;
            etCompanyAddress.setError("Enter company address");
        }
        if (etCompanyNumber.getText().length() < 10) {
            flag = true;
            etCompanyNumber.setError("Enter company mobile number");
        }
        if (etCompanyFaxNo.getText().length() <= 0) {
            flag = true;
            etCompanyFaxNo.setError("Enter valid company fax number");
        }
        if (!etCompanyEmail.getText().toString().trim().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            flag = true;
            etCompanyEmail.setError("Enter valid company email address");
        }
        if (etCompanyVatTinNo.getText().toString().length() <= 0) {
            flag = true;
            etCompanyVatTinNo.setError("Enter valid company VAT TIN number");
        }
        if (etCompanyCstTinNo.getText().toString().length() <= 0) {
            flag = true;
            etCompanyCstTinNo.setError("Enter valid company CST TIN Number");
        }
        if (!flag) {
            SaveData();
        }
    }

    private void SaveData() {
        String CompanyName = etCompanyName.getText().toString().trim();
        String CompanyAddress = etCompanyAddress.getText().toString().trim();
        String CompanyContactNumber = etCompanyNumber.getText().toString().trim();
        String CompanyEmail = etCompanyEmail.getText().toString().trim();
        String CompanyFaxNo = etCompanyFaxNo.getText().toString().trim();
        String CompanyVAT_TIN_NO = etCompanyVatTinNo.getText().toString().trim();
        String CompanyCST_TIN_NO = etCompanyCstTinNo.getText().toString().trim();
        String UserId = "1";
        String Remarks = "NULL";


        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        if (CompanyID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Company> call = apiInterface.InsertCompany(UserId, CompanyName, CompanyAddress, CompanyContactNumber, CompanyFaxNo, CompanyEmail, CompanyVAT_TIN_NO, CompanyCST_TIN_NO, Remarks);
            call.enqueue(new Callback<Bean_Response_Company>() {
                @Override
                public void onResponse(Call<Bean_Response_Company> call, Response<Bean_Response_Company> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Company details added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Clear();
                    }
                    else{
                        Toast.makeText(activity, "Company details not added", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<Bean_Response_Company> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });

        } else {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Company> call = apiInterface.UpdateCompany(CompanyID, CompanyName, CompanyAddress, CompanyContactNumber, CompanyFaxNo, CompanyEmail, CompanyVAT_TIN_NO, CompanyCST_TIN_NO, Remarks);
            call.enqueue(new Callback<Bean_Response_Company>() {
                @Override
                public void onResponse(Call<Bean_Response_Company> call, Response<Bean_Response_Company> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(activity, "Company details updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Intent intent = new Intent(activity, CompanyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(activity, "Company details not updated", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Bean_Response_Company> call, Throwable t) {
                    Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }

    void Clear() {
        etCompanyName.setText("");
        etCompanyNumber.setText("");
        etCompanyAddress.setText("");
        etCompanyEmail.setText("");
        etCompanyVatTinNo.setText("");
        etCompanyCstTinNo.setText("");
        etCompanyFaxNo.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    void init() {
        etCompanyName = (EditText) findViewById(R.id.etCompanyName);
        etCompanyAddress = (EditText) findViewById(R.id.etCompanyAddress);
        etCompanyNumber = (EditText) findViewById(R.id.etCompanyNumber);
        etCompanyFaxNo = (EditText) findViewById(R.id.etCompanyFaxNo);
        etCompanyEmail = (EditText) findViewById(R.id.etCompanyEmail);
        etCompanyVatTinNo = (EditText) findViewById(R.id.etCompanyVatTinNo);
        etCompanyCstTinNo = (EditText) findViewById(R.id.etCompanyCstTinNo);
    }
}
