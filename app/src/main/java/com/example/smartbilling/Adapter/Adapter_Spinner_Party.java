package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.R;

import java.util.List;

public class Adapter_Spinner_Party extends BaseAdapter {

    List<Bean_Party> arrayParty;
    Activity activity;
    ViewHolder holder;

    public Adapter_Spinner_Party(List<Bean_Party> arrayParty, Activity activity) {
        this.arrayParty = arrayParty;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayParty.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayParty.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        TextView tvPartyID;
        TextView tvPartyName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.design_spinner_broker, null);
            holder = new ViewHolder();
            holder.tvPartyID = (TextView) view.findViewById(R.id.tvBrokerID);
            holder.tvPartyName = (TextView) view.findViewById(R.id.tvBrokerName);
            view.setTag(holder);
        } else {
            holder = (Adapter_Spinner_Party.ViewHolder) view.getTag();
        }
        holder.tvPartyID.setText(arrayParty.get(i).getPartyID() + "");
        holder.tvPartyName.setText(arrayParty.get(i).getPartyName() + "");
        return view;
    }
}