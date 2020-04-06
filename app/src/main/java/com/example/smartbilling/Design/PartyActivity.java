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
import com.example.smartbilling.Adapter.Adapter_Party;
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyActivity extends AppCompatActivity {

    final Activity activity = this;
    SwipeRefreshLayout SwipeRefresh;
    RecyclerView rvPartyList;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        getSupportActionBar().setTitle("Party");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        getAllParty();

        SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllParty();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    void getAllParty(){
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPartyList.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Party> call = apiInterface.getAllParty();
        call.enqueue(new Callback<Bean_Response_Party>() {
            @Override
            public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_Party> PartyList = response.body().getData();
                    rvPartyList.setAdapter(new Adapter_Party(PartyList, activity));
                    progress.dismiss();
                } else {
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Party> call, Throwable t) {
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
                Intent add_party = new Intent(activity, AddPartyActivity.class);
                startActivity(add_party);
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
        rvPartyList = (RecyclerView) findViewById (R.id.rvPartyList);
    }
}
