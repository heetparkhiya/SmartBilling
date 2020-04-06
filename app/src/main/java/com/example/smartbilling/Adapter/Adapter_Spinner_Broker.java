package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartbilling.Bean.Bean_Broker;
import com.example.smartbilling.R;

import java.util.List;

public class Adapter_Spinner_Broker extends BaseAdapter {

    List<Bean_Broker> arrayBroker;
    Activity activity;
    ViewHolder holder;

    public Adapter_Spinner_Broker(List<Bean_Broker> arrayBroker, Activity activity) {
        this.arrayBroker = arrayBroker;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayBroker.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayBroker.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        TextView tvBrokerID;
        TextView tvBrokerName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();

        if (view == null){
            view = inflater.inflate(R.layout.design_spinner_broker, null);
            holder = new ViewHolder();
            holder.tvBrokerID= (TextView) view.findViewById(R.id.tvBrokerID);
            holder.tvBrokerName = (TextView) view.findViewById(R.id.tvBrokerName);
            view.setTag(holder);
        }
        else
        {
            holder = (Adapter_Spinner_Broker.ViewHolder) view.getTag();
        }
        holder.tvBrokerID.setText(arrayBroker.get(i).getBrokerID() + "");
        holder.tvBrokerName.setText(arrayBroker.get(i).getBrokerName() + "");
        return view;
    }
}