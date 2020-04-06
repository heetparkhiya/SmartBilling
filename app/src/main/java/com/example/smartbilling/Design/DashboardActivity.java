package com.example.smartbilling.Design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

public class DashboardActivity extends AppCompatActivity {

    CardView cvPerformInvoice, cvInvoiceList, cvBroker, cvProduct, cvParty, cvCompany, cvSize, cvOrder, cvPacking, cvTransport, cvCreditNote, cvCollection;
    TextView tvUsername, tvLogout;
    final Activity activity = this;
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setElevation(0);
        init();

        manager = new SessionManager(DashboardActivity.this);
        if (manager.getKeyEmail(DashboardActivity.this) != null && manager.getKeyPassword(DashboardActivity.this) != null) {
            tvUsername.setText(manager.getKeyEmail(DashboardActivity.this));
        } else {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        }

        cvPerformInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent performInvoice = new Intent(activity, PerformInvoiceActivity.class);
                startActivity(performInvoice);
            }
        });
        cvInvoiceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invoiceList = new Intent(activity, InvoiceActivity.class);
                startActivity(invoiceList);
            }
        });
        cvBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent broker = new Intent(activity, BrokerActivity.class);
                startActivity(broker);
            }
        });
        cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent product = new Intent(activity, ProductActivity.class);
                startActivity(product);
            }
        });
        cvParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent party = new Intent(activity, PartyActivity.class);
                startActivity(party);
            }
        });
        cvSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent size = new Intent(activity, SizeActivity.class);
                startActivity(size);
            }
        });
        cvCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent company = new Intent(activity, CompanyActivity.class);
                startActivity(company);
            }
        });
        cvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent order = new Intent(activity, OrderActivity.class);
                startActivity(order);
            }
        });
        cvPacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent packingList = new Intent(activity, PackingListActivity.class);
                startActivity(packingList);
            }
        });
        cvTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transport = new Intent(activity, TransportActivity.class);
                startActivity(transport);
            }
        });
        cvCreditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditNote = new Intent(activity, CreditNoteActivity.class);
                startActivity(creditNote);
            }
        });
        cvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent collection = new Intent(activity, CollectionActivity.class);
                startActivity(collection);
            }
        });
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(activity, LoginActivity.class);
                startActivity(logout);
                manager.Logout();
                finish();
            }
        });
    }

    void init() {
        cvPerformInvoice = (CardView) findViewById(R.id.cvPerformInvoice);
        cvInvoiceList = (CardView) findViewById(R.id.cvInvoiceList);
        cvBroker = (CardView) findViewById(R.id.cvBroker);
        cvProduct = (CardView) findViewById(R.id.cvProduct);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        cvParty = (CardView) findViewById(R.id.cvParty);
        cvCompany = (CardView) findViewById(R.id.cvCompany);
        cvSize = (CardView) findViewById(R.id.cvSize);
        cvOrder = (CardView) findViewById(R.id.cvOrder);
        cvPacking = (CardView) findViewById(R.id.cvPacking);
        cvTransport = (CardView) findViewById(R.id.cvTransport);
        cvCreditNote = (CardView) findViewById(R.id.cvCreditNote);
        cvCollection = (CardView) findViewById(R.id.cvCollection);
    }
}