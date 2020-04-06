package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.R;

import java.util.List;

public class Adapter_Spinner_Product extends BaseAdapter {

    List<Bean_Product> arrayProduct;
    Activity activity;
    ViewHolder holder;

    public Adapter_Spinner_Product(List<Bean_Product> arrayProduct, Activity activity) {
        this.arrayProduct = arrayProduct;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        TextView tvProductID;
        TextView tvProductName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.design_spinner_product, null);
            holder = new ViewHolder();
            holder.tvProductID = (TextView) view.findViewById(R.id.tvProductID);
            holder.tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            view.setTag(holder);
        } else {
            holder = (Adapter_Spinner_Product.ViewHolder) view.getTag();
        }
        holder.tvProductID.setText(arrayProduct.get(i).getProductID() + "");
        holder.tvProductName.setText(arrayProduct.get(i).getProductStyle() + "");
        return view;
    }
}