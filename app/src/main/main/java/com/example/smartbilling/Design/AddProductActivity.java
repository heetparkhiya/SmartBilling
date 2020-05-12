package com.example.smartbilling.Design;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_ProductWise_Size;
import com.example.smartbilling.Bean.Bean_Response_General;
import com.example.smartbilling.Bean.Bean_Response_Product_Multi;
import com.example.smartbilling.Bean.Bean_Response_Size;
import com.example.smartbilling.Bean.Bean_Size;
import com.example.smartbilling.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {

    final Context context = this;
    RecyclerView rvProductWiseSize;
    Activity activity = AddProductActivity.this;
    ApiInterface apiInterface;
    Adapter_ProductWise_Size adapter;
    TextInputEditText etProductDesignNo, etProductName, etProductStyle, etProductMRP_PR;
    String ProductID = null, ProductName = null, ProductDesignNumber = null, ProductStyle = null, ProductMRP_PR = null;
    private List<Bean_Size> SizeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        init();
        ProductID = getIntent().getStringExtra("ProductID");
        ProductName = getIntent().getStringExtra("ProductName");
        ProductDesignNumber = getIntent().getStringExtra("ProductDesignNumber");
        ProductStyle = getIntent().getStringExtra("ProductStyle");
        ProductMRP_PR = getIntent().getStringExtra("ProductMRP_PR");
        if (ProductID != null) {
            SizeList = (ArrayList<Bean_Size>) getIntent().getSerializableExtra("Size");
            getSupportActionBar().setTitle("Edit Product");
            etProductName.setText(ProductName);
            etProductStyle.setText(ProductStyle);
            etProductDesignNo.setText(ProductDesignNumber);
            etProductMRP_PR.setText(ProductMRP_PR);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvProductWiseSize.setLayoutManager(layoutManager);
            adapter = new Adapter_ProductWise_Size(activity, SizeList, new Adapter_ProductWise_Size.OnItemCheckListener() {
                @Override
                public void onItemCheck(Bean_Size item, Adapter_ProductWise_Size.ProductWiseSizeViewHolder holder) {
                    if (holder.chkSize.isChecked()) {
                        holder.etProductMRP.setEnabled(true);
                        holder.etProductRate.setEnabled(true);
                        AddProductActivity.this.SizeList.add(item);
                    } else {
                        holder.etProductRate.getText().clear();
                        holder.etProductMRP.getText().clear();
                        holder.etProductMRP.setEnabled(false);
                        holder.etProductRate.setEnabled(false);
                        AddProductActivity.this.SizeList.remove(item);
                    }
                }

                @Override
                public void onItemUncheck(Bean_Size item, Adapter_ProductWise_Size.ProductWiseSizeViewHolder holder) {
                    if (holder.chkSize.isChecked()) {
                        holder.etProductMRP.setEnabled(true);
                        holder.etProductRate.setEnabled(true);
                        AddProductActivity.this.SizeList.add(item);
                    } else {
                        holder.etProductRate.getText().clear();
                        holder.etProductMRP.getText().clear();
                        holder.etProductMRP.setEnabled(false);
                        holder.etProductRate.setEnabled(false);
                        AddProductActivity.this.SizeList.remove(item);
                    }

                }
            }, true);
            rvProductWiseSize.setAdapter(adapter);
        } else {
            getSupportActionBar().setTitle("Add Product");
            getAllSize();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        rvProductWiseSize = findViewById(R.id.rvProductWiseSize);
        etProductDesignNo = findViewById(R.id.etProductDesignNo);
        etProductName = findViewById(R.id.etProductName);
        etProductStyle = findViewById(R.id.etProductStyle);
        etProductMRP_PR = findViewById(R.id.etProductMRP_PR);
    }

    void getAllSize() {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProductWiseSize.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Size> call = apiInterface.getAllSize();
        call.enqueue(new Callback<Bean_Response_Size>() {
            @Override
            public void onResponse(Call<Bean_Response_Size> call, Response<Bean_Response_Size> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_Size> SizeList = response.body().getData();
                    adapter = new Adapter_ProductWise_Size(activity, SizeList, new Adapter_ProductWise_Size.OnItemCheckListener() {
                        @Override
                        public void onItemCheck(Bean_Size item, Adapter_ProductWise_Size.ProductWiseSizeViewHolder holder) {
                            if (holder.chkSize.isChecked()) {
                                holder.etProductMRP.setEnabled(true);
                                holder.etProductRate.setEnabled(true);
                                AddProductActivity.this.SizeList.add(item);
                            } else {
                                holder.etProductRate.getText().clear();
                                holder.etProductMRP.getText().clear();
                                holder.etProductMRP.setEnabled(false);
                                holder.etProductRate.setEnabled(false);
                                AddProductActivity.this.SizeList.remove(item);
                            }
                        }

                        @Override
                        public void onItemUncheck(Bean_Size item, Adapter_ProductWise_Size.ProductWiseSizeViewHolder holder) {
                            if (holder.chkSize.isChecked()) {
                                holder.etProductMRP.setEnabled(true);
                                holder.etProductRate.setEnabled(true);
                                AddProductActivity.this.SizeList.add(item);
                            } else {
                                holder.etProductRate.getText().clear();
                                holder.etProductMRP.getText().clear();
                                holder.etProductMRP.setEnabled(false);
                                holder.etProductRate.setEnabled(false);
                                AddProductActivity.this.SizeList.remove(item);
                            }
                        }
                    }, false);
                    rvProductWiseSize.setAdapter(adapter);
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Size> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }

    void Clear() {
    }

    private void SaveData() {
        String ProductDesignNumber = etProductDesignNo.getText().toString().trim();
        String ProductName = etProductName.getText().toString().trim();
        String ProductStyle = etProductStyle.getText().toString().trim();
        String ProductMRP_PR = etProductMRP_PR.getText().toString().trim();
        int UserID = 1;
        String Remarks = "No Remarks";
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        if (ProductID == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_Product_Multi> call = apiInterface.InsertProduct(UserID, ProductDesignNumber, ProductName, ProductMRP_PR, ProductStyle, Remarks);
            call.enqueue(new Callback<Bean_Response_Product_Multi>() {
                @Override
                public void onResponse(Call<Bean_Response_Product_Multi> call, Response<Bean_Response_Product_Multi> response) {
                    progress.dismiss();
                    System.out.println(response.body());
                    if (response.body().getResponse() == 1) {
                        List<Integer> data = response.body().getData();
                        int ProductID = data.get(0);
                        List<Bean_Size> sizeList = new ArrayList<>(SizeList);
                        for (int i = 0; i < SizeList.size(); i++) {
                            View view = rvProductWiseSize.getChildAt(i);
                            CheckBox chkSize = view.findViewById(R.id.chkSize);
                            if (chkSize.isChecked()) {
                                EditText etProductRate = view.findViewById(R.id.etProductRate);
                                EditText etProductMRP = view.findViewById(R.id.etProductMRP);
                                String ProductRate = etProductRate.getText().toString();
                                String ProductMRP = etProductMRP.getText().toString();
                                sizeList.get(i).setProductMRP(ProductMRP);
                                sizeList.get(i).setProductRate(ProductRate);
                                sizeList.get(i).setRemarks("No Remarks");
                                sizeList.get(i).setProductID(ProductID);
                                sizeList.get(i).setSizeID(sizeList.get(i).getSizeID());
                            }
                        }
                        String sizeListString = new Gson().toJson(sizeList, new TypeToken<List<Bean_Size>>() {
                        }.getType());
                        Log.e("sizeList", sizeListString);
                        Call<Bean_Response_General> callInsertSize = apiInterface.InsertProductWiseSize(sizeListString);
                        callInsertSize.enqueue(new Callback<Bean_Response_General>() {
                            @Override
                            public void onResponse(Call<Bean_Response_General> call, Response<Bean_Response_General> response) {
                                Toast.makeText(activity, "Product Inserted Successfully", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Bean_Response_General> call, Throwable t) {
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Bean_Response_Product_Multi> call, Throwable t) {
                    progress.dismiss();
                }
            });

            Clear();

        }
        else {
            List<Bean_Size> sizeList = new ArrayList<>(SizeList);
            for (int i = 0; i < SizeList.size(); i++) {
                View view = rvProductWiseSize.getChildAt(i);
                CheckBox chkSize = view.findViewById(R.id.chkSize);
                if (chkSize.isChecked()) {
                    EditText etProductRate = view.findViewById(R.id.etProductRate);
                    EditText etProductMRP = view.findViewById(R.id.etProductMRP);
                    String ProductRate = etProductRate.getText().toString();
                    String ProductMRP = etProductMRP.getText().toString();
                    sizeList.get(i).setProductMRP(ProductMRP);
                    sizeList.get(i).setProductRate(ProductRate);
                    sizeList.get(i).setRemarks("No Remarks");
                    sizeList.get(i).setProductID(Integer.parseInt(ProductID));
                    sizeList.get(i).setSizeID(sizeList.get(i).getSizeID());
                }
            }
            String sizeListString = new Gson().toJson(sizeList, new TypeToken<List<Bean_Size>>() {
            }.getType());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Bean_Response_General> call = apiInterface.UpdateProduct(ProductID, ProductDesignNumber, ProductName, ProductMRP_PR, ProductStyle, Remarks, sizeListString);
            call.enqueue(new Callback<Bean_Response_General>() {
                @Override
                public void onResponse(Call<Bean_Response_General> call, Response<Bean_Response_General> response) {
                    Log.e("repsonse", response.body().getData().get(0));
                    Toast.makeText(activity, "Product Details Updated", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    Intent intent = new Intent(activity, ProductActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Bean_Response_General> call, Throwable t) {
                    progress.dismiss();
                    Log.e("Error0", String.valueOf(t.getCause()));
                    Toast.makeText(activity, "Product Details Not Updated", Toast.LENGTH_SHORT).show();
                }
            });
            Clear();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.cancel:
                Intent productList = new Intent(context, ProductActivity.class);
                startActivity(productList);
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
        if (etProductDesignNo.getText().toString().trim().length() <= 0) {
            flag = true;
            etProductDesignNo.setError("Enter the Product Design Number");
        }
        if (etProductName.getText().toString().trim().length() <= 0) {
            flag = true;
            etProductName.setError("Enter the Product Name");
        }
        if (etProductStyle.getText().toString().trim().length() <= 0) {
            flag = true;
            etProductStyle.setError("Enter the Product Style");
        }
        if (etProductMRP_PR.getText().toString().trim().length() <= 0) {
            flag = true;
            etProductMRP_PR.setError("Enter the Product MRP Percentage");
        }
        if (SizeList.size() == 0) {
            flag = true;
        }
        /*for (int i = 0; i < SizeList.size(); i++) {
            View view = rvProductWiseSize.getChildAt(i);
            if (((CheckBox) view.findViewById(R.id.chkSize)).isChecked()) {
                EditText etProductRate = view.findViewById(R.id.etProductRate);
                EditText etProductMRP = view.findViewById(R.id.etProductMRP);
                if (Integer.parseInt(SizeList.get(i).getProductMRP()) < 0) {
                    flag = true;
                    etProductRate.setError("Enter the Product MRP");
                }
                if (Integer.parseInt(SizeList.get(i).getProductRate()) < 0) {
                    flag = true;
                    etProductMRP.setError("Enter the Product Rate");
                }
            }
        }*/
        if (!flag) {
            SaveData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}