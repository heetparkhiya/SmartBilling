package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_CreditNote;
import com.example.smartbilling.Bean.Bean_CreditNote;
import com.example.smartbilling.Bean.Bean_Response_CreditNote;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditNoteActivity extends AppCompatActivity {

    final Activity activity = this;
    SwipeRefreshLayout SwipeRefresh;
    RecyclerView rvCreditNoteList;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_note);
        getSupportActionBar().setTitle("Credit Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        getAllCreditNote();
        SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllCreditNote();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    void getAllCreditNote() {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCreditNoteList.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_CreditNote> call = apiInterface.getAllCreditNote();
        call.enqueue(new Callback<Bean_Response_CreditNote>() {
            @Override
            public void onResponse(Call<Bean_Response_CreditNote> call, Response<Bean_Response_CreditNote> response) {
                if (response.body().getResponse() == 1) {
                    List<Bean_CreditNote> CreditNoteList = response.body().getData();
                    rvCreditNoteList.setAdapter(new Adapter_CreditNote(CreditNoteList, activity));
                    progress.dismiss();
                }
                else
                    Toast.makeText(activity, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bean_Response_CreditNote> call, Throwable t) {
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
                Intent add_creditNote = new Intent(activity, AddCreditNoteActivity.class);
                startActivity(add_creditNote);
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
        rvCreditNoteList = (RecyclerView) findViewById (R.id.rvCreditNoteList);
    }
}
