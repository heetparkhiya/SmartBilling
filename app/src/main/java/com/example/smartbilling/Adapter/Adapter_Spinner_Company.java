package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartbilling.Bean.Bean_Company;
import com.example.smartbilling.R;

import java.util.List;

public class Adapter_Spinner_Company extends BaseAdapter {

    List<Bean_Company> arrayCompany;
    Activity activity;
    ViewHolder holder;

    public Adapter_Spinner_Company(List<Bean_Company> arrayCompany, Activity activity) {
        this.arrayCompany = arrayCompany;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayCompany.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCompany.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        TextView tvCompanyID;
        TextView tvCompanyName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.design_spinner_company, null);
            holder = new ViewHolder();
            holder.tvCompanyID = (TextView) view.findViewById(R.id.tvCompanyID);
            holder.tvCompanyName = (TextView) view.findViewById(R.id.tvCompanyName);
            view.setTag(holder);
        } else {
            holder = (Adapter_Spinner_Company.ViewHolder) view.getTag();
        }
        holder.tvCompanyID.setText(arrayCompany.get(i).getCompanyID() + "");
        holder.tvCompanyName.setText(arrayCompany.get(i).getCompanyName() + "");
        return view;
    }
}
