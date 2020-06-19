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
import com.example.smartbilling.Adapter.Adapter_Broker;
import com.example.smartbilling.Bean.Bean_Broker;
import com.example.smartbilling.Bean.Bean_Response_Broker;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrokerActivity extends AppCompatActivity {

    final Activity activity = this;
    SwipeRefreshLayout SwipeRefresh;
    RecyclerView rvBrokerList;
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker);
        getSupportActionBar().setTitle("Broker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        manager = new SessionManager(activity);
        getAllBroker();
        SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllBroker();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    void getAllBroker() {
        String UserID = manager.getUserID(activity);
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        rvBrokerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Broker> call = apiInterface.getAllBroker(UserID);
        call.enqueue(new Callback<Bean_Response_Broker>() {
            @Override
            public void onResponse(Call<Bean_Response_Broker> call, Response<Bean_Response_Broker> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_Broker> BrokerList = response.body().getData();
                    rvBrokerList.setAdapter(new Adapter_Broker(BrokerList, activity));
                    progress.dismiss();
                }
                else
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bean_Response_Broker> call, Throwable t) {
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
                Intent add_broker = new Intent(activity, AddBrokerActivity.class);
                startActivity(add_broker);
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
        rvBrokerList = (RecyclerView) findViewById (R.id.rvBrokerList);
    }
}