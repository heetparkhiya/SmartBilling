package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Packing;
import com.example.smartbilling.Bean.Bean_ListEntry;
import com.example.smartbilling.Bean.Bean_Response_ListEntry;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackingListActivity extends AppCompatActivity {

    final Activity activity = this;
    SwipeRefreshLayout SwipeRefresh;
    RecyclerView rvPackingList;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_list);
        getSupportActionBar().setTitle("Packing List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        getAllList();
        SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllList();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    void getAllList() {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPackingList.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_ListEntry> call = apiInterface.getAllListEntry("Packing");
        call.enqueue(new Callback<Bean_Response_ListEntry>() {
            @Override
            public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_ListEntry> ListEntry = response.body().getData();
                    rvPackingList.setAdapter(new Adapter_Packing(ListEntry, activity));
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_ListEntry> call, Throwable t) {
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
                Intent add_packingList = new Intent(activity, AddPackingListActivity.class);
                startActivity(add_packingList);
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

    void init(){
        SwipeRefresh = (SwipeRefreshLayout) findViewById (R.id.SwipeRefresh);
        rvPackingList = (RecyclerView) findViewById (R.id.rvPackingList);
    }
}
