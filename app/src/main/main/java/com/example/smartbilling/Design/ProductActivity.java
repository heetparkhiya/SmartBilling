package com.example.smartbilling.Design;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Product;
import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.Bean.Bean_Response_Product;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    final Context context = this;
    final Activity activity = this;
    SwipeRefreshLayout SwipeRefresh;
    RecyclerView rvProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        getAllProduct();
        SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProduct();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });


    }

    private void getAllProduct() {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProductList.setLayoutManager(layoutManager);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Product> call = apiInterface.getAllProduct();
        call.enqueue(new Callback<Bean_Response_Product>() {
            @Override
            public void onResponse(Call<Bean_Response_Product> call, Response<Bean_Response_Product> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_Product> ProductList = response.body().getData();
                    rvProductList.setAdapter(new Adapter_Product(ProductList, activity));
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Product> call, Throwable t) {
                Log.e("Error",t.getMessage());
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.add:
                Intent add_product = new Intent(context, AddProductActivity.class);
                startActivity(add_product);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    void init() {
        SwipeRefresh = findViewById(R.id.SwipeRefresh);
        rvProductList = findViewById(R.id.rvProductList);
    }
}
