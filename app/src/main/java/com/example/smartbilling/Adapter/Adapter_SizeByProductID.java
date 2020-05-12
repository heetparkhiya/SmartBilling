package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.Bean.Bean_Product_Item_Size;
import com.example.smartbilling.Bean.Bean_Size;
import com.example.smartbilling.Design.AddProductItemsActivity;
import com.example.smartbilling.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_SizeByProductID extends RecyclerView.Adapter<Adapter_SizeByProductID.ProductItemSizeViewHolder> {

    private List<Bean_Product_Item_Size> list;
    private Activity activity;

    public Adapter_SizeByProductID(List<Bean_Product_Item_Size> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    public static class ProductItemSizeViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductItemSizeID;
        TextView tvSizeName;
        TextView tvSizeMRP;
        TextView tvSizeRate;
        EditText etSizeQty;
        CheckBox chSizeCheckbox;

        public ProductItemSizeViewHolder(View v) {
            super(v);
            tvProductItemSizeID = (TextView) v.findViewById(R.id.tvProductItemSizeID);
            tvSizeName = (TextView) v.findViewById(R.id.tvSizeName);
            tvSizeMRP = (TextView) v.findViewById(R.id.tvSizeMRP);
            tvSizeRate = (TextView) v.findViewById(R.id.tvSizeRate);
            etSizeQty = (EditText) v.findViewById(R.id.etSizeQty);
            chSizeCheckbox = (CheckBox) v.findViewById(R.id.chSizeCheckbox);
        }
    }

    @Override
    public Adapter_SizeByProductID.ProductItemSizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_product_item_size, parent, false);
        return new Adapter_SizeByProductID.ProductItemSizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_SizeByProductID.ProductItemSizeViewHolder holder, final int position) {
        holder.tvProductItemSizeID.setText(list.get(position).getSizeID());
        holder.tvSizeName.setText(list.get(position).getSize());
        holder.tvSizeMRP.setText(list.get(position).getMRP());
        holder.tvSizeRate.setText(list.get(position).getRate());
        List<Bean_Product_Item_Size> itemSizes = new ArrayList<>();
        holder.chSizeCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.chSizeCheckbox.isChecked()){
                    ((AddProductItemsActivity)activity).insertProductWiseSize(new Bean_Product_Item_Size(list.get(position).getSize(),list.get(position).getRate(),list.get(position).getMRP()));
                }
                else {
                    ((AddProductItemsActivity)activity).deleteProductWiseSize(new Bean_Product_Item_Size(list.get(position).getSize(),list.get(position).getRate(),list.get(position).getMRP()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}