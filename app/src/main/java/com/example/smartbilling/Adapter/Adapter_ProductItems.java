package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartbilling.Bean.Bean_ProductItems;
import com.example.smartbilling.R;

import java.util.ArrayList;

public class Adapter_ProductItems extends BaseAdapter {

    ArrayList<Bean_ProductItems> arrayItems;
    Activity context;
    Adapter_ProductItems.ViewHolder holder;

    public Adapter_ProductItems(Activity context, ArrayList<Bean_ProductItems> arrayItems) {
        this.context = context;
        this.arrayItems = arrayItems;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        TextView txtDesign;
        TextView txtSize;
        TextView txtRate;
        TextView txtQuantity;
        TextView txtAmount;
        TextView txtID;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = context.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.txtID = (TextView) view.findViewById(R.id.txtID);
            holder.txtDesign = (TextView) view.findViewById(R.id.txtDesign);
            holder.txtSize = (TextView) view.findViewById(R.id.txtSize);
            holder.txtRate = (TextView) view.findViewById(R.id.txtRate);
            holder.txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
            holder.txtAmount = (TextView) view.findViewById(R.id.txtAmount);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtID.setText(arrayItems.get(i).getItemID() + "");
        holder.txtDesign.setText(arrayItems.get(i).getDesignNo() + "");
        if(arrayItems.get(i).getSize() != null) {
            String Size = arrayItems.get(i).getSize().trim().replaceAll("\\[", "").replaceAll("\\]", "");
            Size = Size.charAt(0) == ',' ? Size.substring(1, Size.length()) : Size;
            holder.txtSize.setText(Size);
        }

        /*if (Size.charAt(0) == ',') {
            Size = Size.substring(1, Size.length() - 1);
        }*/
        holder.txtRate.setText(arrayItems.get(i).getRateSize() + "");
        Log.e("QTY",arrayItems.get(i).getQty());
        holder.txtQuantity.setText(arrayItems.get(i).getQty() + "");
        holder.txtAmount.setText(arrayItems.get(i).getAmount() + "");
        return view;
    }
}