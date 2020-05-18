package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_SizeByProductID;
import com.example.smartbilling.Adapter.Adapter_Spinner_Product;
import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.Bean.Bean_ProductItems;
import com.example.smartbilling.Bean.Bean_Product_Item_Size;
import com.example.smartbilling.Bean.Bean_Response_Product;
import com.example.smartbilling.Bean.Bean_Response_Product_Item_Size;
import com.example.smartbilling.DBHelper.DB_ProductList;
import com.example.smartbilling.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductItemsActivity extends AppCompatActivity {

    Spinner spProduct;
    List<Bean_Product_Item_Size> SizeList = new ArrayList<>();
    EditText etProductItemsDesignNo;
    RecyclerView rvSizeList;
    List<Bean_Product> List = new ArrayList<>();
    final Activity activity = this;
    ApiInterface apiInterface;
    String ProductID, ProductName;
    Boolean flag = true;
    Bean_ProductItems bean_productItems;
    DB_ProductList db_productList;

    List<String> Sizes = new LinkedList<>();
    List<String> MRPS = new LinkedList<>();
    List<String> Rates = new LinkedList<>();
    List<String> Qty = new LinkedList<>();
    String Size = null, MRP = null, Rate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_items);
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    List = response.body().getData();
                    spProduct.setAdapter(new Adapter_Spinner_Product(List, activity));
                    spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ProductID = List.get(position).getProductID();
                            ProductName = List.get(position).getProductName();
                            etProductItemsDesignNo.setText(List.get(position).getProductDesignNumber());
                            FillSizeList(ProductID);
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
    }

    void FillSizeList(String ProductID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        rvSizeList.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Product_Item_Size> call = apiInterface.SelectSizeByProductID(ProductID);
        call.enqueue(new Callback<Bean_Response_Product_Item_Size>() {
            @Override
            public void onResponse(Call<Bean_Response_Product_Item_Size> call, Response<Bean_Response_Product_Item_Size> response) {
                if (response.body().getResponse() == 1) {
                    SizeList = response.body().getData();
                    rvSizeList.setAdapter(new Adapter_SizeByProductID(SizeList, activity));
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Product_Item_Size> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }

    public void insertProductWiseSize(Bean_Product_Item_Size position) {
        Sizes.add(position.getSize());
        MRPS.add(position.getMRP());
        Rates.add(position.getRate());
        Qty.add(position.getSizeWiseQty());

        if(Size == null)
            Size = position.getSize();
        else
            Size = Size + "," + position.getSize();

        if(Rate == null)
            Rate = position.getRate();
        else
            Rate = Rate + "," + position.getRate();

        if(MRP == null)
            MRP = position.getMRP();
        else
            MRP = MRP + "," + position.getMRP();
    }


    public void deleteProductWiseSize(Bean_Product_Item_Size bean_product_item_size){
        /*List<String> items = new LinkedList<String>(Arrays.asList(Size.split(",")));
        List<String> itemsRate = new LinkedList<String>(Arrays.asList(Rate.split(",")));
        List<String> itemsMRP = new LinkedList<String>(Arrays.asList(MRP.split(",")));*/
        for(int i= 0;i<Sizes.size();i++)
        {
            if(Sizes.get(i).equals(bean_product_item_size.getSize()) && Rates.get(i).equals(bean_product_item_size.getRate()) && MRPS.get(i).equals(bean_product_item_size.getMRP()) && Qty.get(i).equals(bean_product_item_size.getSizeWiseQty()))
            {
                Sizes.remove(i);
                Rates.remove(i);
                MRPS.remove(i);
                Qty.remove(i);
            }
        }
        Collections.sort(Sizes);
        Collections.sort(Rates);
        Collections.sort(MRPS);
        Size = Sizes.toString();
        Log.e("Sizzzz",Sizes.toString());
        Rate = Rates.toString();
        MRP = MRPS.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.cancel:
                Intent intent = new Intent(activity, AddInvoiceActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.save:
                if (etProductItemsDesignNo.getText().length() < 0) {
                    etProductItemsDesignNo.setError("Enter Design No.");
                    flag = false;
                } else {
                    bean_productItems.setDesignNo(etProductItemsDesignNo.getText().toString());
                }
                if (flag == true)
                {
                    Log.e("Qty",Qty.toString());
                    db_productList.InsertProduct(new Bean_ProductItems(etProductItemsDesignNo.getText().toString(), Size,null,Qty.toString(),null, Rate, MRP));
                    Toast.makeText(activity, "Product Added", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    void init() {
        bean_productItems = new Bean_ProductItems();
        spProduct = (Spinner) findViewById(R.id.spProduct);
        etProductItemsDesignNo = (EditText) findViewById(R.id.etProductItemsDesignNo);
        rvSizeList = (RecyclerView) findViewById(R.id.rvSizeList);
    }
}