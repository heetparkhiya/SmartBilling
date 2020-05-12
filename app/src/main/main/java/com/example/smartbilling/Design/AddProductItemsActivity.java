package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Product;
import com.example.smartbilling.Adapter.Adapter_Spinner_Product;
import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.Bean.Bean_ProductItems;
import com.example.smartbilling.Bean.Bean_Response_Product;
import com.example.smartbilling.DBHelper.DB_ProductList;
import com.example.smartbilling.R;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductItemsActivity extends AppCompatActivity {

    Spinner spProduct;
    EditText etMRP, etQuantity, etProductItemsDesignNo;
    Button btnAdd, btnCancel;
    final Activity activity = this;
    ApiInterface apiInterface;
    String ProductID, ProductName, DesignNo;
    Boolean flag = true;
    Bean_ProductItems bean_productItems;
    DB_ProductList db_productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_items);
        init();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddInvoiceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Product> call = apiInterface.getAllProduct();
        call.enqueue(new Callback<Bean_Response_Product>() {
            @Override
            public void onResponse(Call<Bean_Response_Product> call, Response<Bean_Response_Product> response) {
                if (response.body().getResponse() == 1) {
                    final List<Bean_Product> List = response.body().getData();
                    spProduct.setAdapter(new Adapter_Spinner_Product(List, activity));
                    spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ProductID = List.get(position).getProductID();
                            ProductName = List.get(position).getProductName();
                            etProductItemsDesignNo.setText(List.get(position).getProductDesignNumber());
                            etMRP.setText(List.get(position).getProductMRPPR());
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
            public void onFailure(Call<Bean_Response_Product> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        db_productList = new DB_ProductList(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etProductItemsDesignNo.getText().length() < 0) {
                    etProductItemsDesignNo.setError("Enter Design No.");
                    flag = false;
                } else {
                    bean_productItems.setDesignNo(etProductItemsDesignNo.getText().toString());
                }

                bean_productItems.setSize("Demo");

                if (etMRP.getText().length() < 0) {
                    etMRP.setError("Enter MRP");
                    flag = false;
                } else {
                    bean_productItems.setRate(etMRP.getText().toString());
                }

                if (etQuantity.getText().length() < 0) {
                    etQuantity.setError("Enter Quantity");
                    flag = false;
                } else {
                    bean_productItems.setQty(etQuantity.getText().toString());
                }

                Double Total = Double.parseDouble(etQuantity.getText().toString().trim()) * Double.parseDouble(etMRP.getText().toString().trim());
                bean_productItems.setAmount(String.valueOf(Total));

                if (flag == true) {
                    db_productList.InsertProduct(bean_productItems);
                    Toast.makeText(activity, "Product Added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    void init() {
        bean_productItems = new Bean_ProductItems();
        spProduct = (Spinner) findViewById(R.id.spProduct);
        etProductItemsDesignNo = (EditText) findViewById(R.id.etProductItemsDesignNo);
        etMRP = (EditText) findViewById(R.id.etMRP);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }
}
