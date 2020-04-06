package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.smartbilling.Bean.Bean_Transport;
import com.example.smartbilling.R;
import java.util.List;

public class Adapter_Spinner_Transport extends BaseAdapter {

    List<Bean_Transport> arrayTransport;
    Activity activity;
    ViewHolder holder;

    public Adapter_Spinner_Transport(List<Bean_Transport> arrayTransport, Activity activity) {
        this.arrayTransport = arrayTransport;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayTransport.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayTransport.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        TextView tvTransportID;
        TextView tvTransportName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();

        if (view == null){
            view = inflater.inflate(R.layout.design_spinner_transport, null);
            holder = new ViewHolder();
            holder.tvTransportID= (TextView) view.findViewById(R.id.tvTransportID);
            holder.tvTransportName = (TextView) view.findViewById(R.id.tvTransportName);
            view.setTag(holder);
        }
        else
        {
            holder = (Adapter_Spinner_Transport.ViewHolder) view.getTag();
        }
        holder.tvTransportID.setText(arrayTransport.get(i).getTransportID() + "");
        holder.tvTransportName.setText(arrayTransport.get(i).getTransportName() + "");
        return view;
    }
}