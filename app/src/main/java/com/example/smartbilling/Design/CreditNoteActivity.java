package com.example.smartbilling.Design;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Adapter.Adapter_CreditNote;
import com.example.smartbilling.Bean.Bean_CreditNote;
import com.example.smartbilling.Bean.Bean_Response_CreditNote;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;
import com.example.smartbilling.databinding.ActivityCreditNoteBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditNoteActivity extends AppCompatActivity {

    final Activity activity = this;
    ActivityCreditNoteBinding binding;
    ApiInterface apiInterface;
    SessionManager manager;
    List<Bean_CreditNote> CreditNoteList = new ArrayList<>();
    Adapter_CreditNote adapterCreditNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Credit Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        manager = new SessionManager(activity);
        getAllCreditNote();
        binding.SwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllCreditNote();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.SwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    void getAllCreditNote() {
        String UserID = manager.getUserID(activity);
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        binding.rvCreditNoteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_CreditNote> call = apiInterface.getAllCreditNote(UserID);
        call.enqueue(new Callback<Bean_Response_CreditNote>() {
            @Override
            public void onResponse(Call<Bean_Response_CreditNote> call, Response<Bean_Response_CreditNote> response) {
                if (response.body().getResponse() == 1) {
                    CreditNoteList = response.body().getData();
                    adapterCreditNote.setCreditNoteList(CreditNoteList);
                    adapterCreditNote.notifyDataSetChanged();
                    progress.dismiss();
                } else
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

    void init() {
        adapterCreditNote = new Adapter_CreditNote(CreditNoteList, activity);
        binding.rvCreditNoteList.setAdapter(adapterCreditNote);
    }
}