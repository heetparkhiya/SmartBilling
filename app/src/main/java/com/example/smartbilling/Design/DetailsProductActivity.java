package com.example.smartbilling.Design;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.Adapter.Adapter_Size;
import com.example.smartbilling.Bean.Bean_Size;
import com.example.smartbilling.R;

import java.util.ArrayList;

public class DetailsProductActivity extends AppCompatActivity {
    TextView tvProductName, tvProductDesignNumber, tvProductStyle, tvProductMRP_PR, tvSizeMRP, tvSizeRate;
    ArrayList<Bean_Size> SizeList;
    RecyclerView rvSizeList;
    Activity activity = DetailsProductActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        init();
        String ProductID = getIntent().getStringExtra("ProductID");
        String ProductName = getIntent().getStringExtra("ProductName");
        String ProductDesignNumber = getIntent().getStringExtra("ProductDesignNumber");
        String ProductStyle = getIntent().getStringExtra("ProductStyle");
        String ProductMRP_PR = getIntent().getStringExtra("ProductMRP_PR");
        SizeList = (ArrayList<Bean_Size>) getIntent().getSerializableExtra("Size");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSizeList.setLayoutManager(layoutManager);
        rvSizeList.setAdapter(new Adapter_Size(SizeList, activity));

        getSupportActionBar().setTitle(ProductName);

        if (ProductName != null)
            tvProductName.setText(Html.fromHtml("Product Name : " + "\t" + ProductName));

        if (ProductDesignNumber != null)
            tvProductDesignNumber.setText(ProductDesignNumber);

        if (ProductStyle != null)
            tvProductStyle.setText(ProductStyle);

        if (ProductMRP_PR != null)
            tvProductMRP_PR.setText(ProductMRP_PR);
    }

    private void init() {
        tvProductName = findViewById(R.id.tvProductName);
        tvProductDesignNumber = findViewById(R.id.tvProductDesignNumber);
        tvProductMRP_PR = findViewById(R.id.tvProductMRP_PR);
        tvProductStyle = findViewById(R.id.tvProductStyle);
        rvSizeList = findViewById(R.id.rvSizeList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return true;
    }
}
