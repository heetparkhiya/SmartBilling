package com.example.smartbilling.Design;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_Size;
import com.example.smartbilling.Bean.Bean_Response_Size;
import com.example.smartbilling.Bean.Bean_Size;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SizeActivity extends AppCompatActivity {

    final Activity activity = this;
    SwipeRefreshLayout SwipeRefresh;
    RecyclerView rvSizeList;
    ApiInterface apiInterface;
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        getSupportActionBar().setTitle("Size");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        manager = new SessionManager(activity);
        getAllSize();
        SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllSize();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    void getAllSize() {
        String UserID = manager.getUserID(activity);
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        rvSizeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Size> call = apiInterface.getAllSize(UserID);
        call.enqueue(new Callback<Bean_Response_Size>() {
            @Override
            public void onResponse(Call<Bean_Response_Size> call, Response<Bean_Response_Size> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_Size> SizeList = response.body().getData();
                    rvSizeList.setAdapter(new Adapter_Size(SizeList, activity,0));
                    progress.dismiss();
                } else
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bean_Response_Size> call, Throwable t) {
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
                LayoutInflater layoutInflater = LayoutInflater.from(activity);
                View promptsView = layoutInflater.inflate(R.layout.prompt_size, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setView(promptsView);
                final EditText etSize = (EditText) promptsView.findViewById(R.id.etSize);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String Size = etSize.getText().toString().trim();
                                        SaveData(Size);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
        return true;
    }

    void SaveData(String Size) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        String UserId = "1";
        String Remarks = "NULL";
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Size> call = apiInterface.InsertSize(UserId, Size, Remarks);
        call.enqueue(new Callback<Bean_Response_Size>() {
            @Override
            public void onResponse(Call<Bean_Response_Size> call, Response<Bean_Response_Size> response) {
                if(response.body().getResponse() == 1){
                    Toast.makeText(activity, "Size added", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
                else{
                    Toast.makeText(activity, "Size not added", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Size> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    void init() {
        SwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.SwipeRefresh);
        rvSizeList = (RecyclerView) findViewById(R.id.rvSizeList);
    }
}