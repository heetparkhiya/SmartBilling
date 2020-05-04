package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smartbilling.BuildConfig;
import com.example.smartbilling.R;

import java.io.File;
import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {
    final Activity activity = this;
    ListView lvBillList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        getSupportActionBar().setTitle("Invoice List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        lvBillList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object fileName = lvBillList.getItemAtPosition(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                File file = new File(Environment.getExternalStorageDirectory() + "/SmartBilling/" + String.valueOf(fileName));
                Uri data = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", file);
                intent.setDataAndType(data, "application/pdf");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        ArrayList<String> arrayList = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().toString() + "/SmartBilling";
        File directory = new File(path);
        File[] files = directory.listFiles();
        if(files!= null && files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                arrayList.add(files[i].getName());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            lvBillList.setAdapter(arrayAdapter);
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.add:
                Intent add_invoiceList = new Intent(activity, AddInvoiceActivity.class);
                startActivity(add_invoiceList);
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
        lvBillList = (ListView) findViewById(R.id.lvBillList);
    }
}