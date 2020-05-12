package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.API.ApiClient;
import com.example.smartbilling.API.ApiInterface;
import com.example.smartbilling.Bean.Bean_Broker;
import com.example.smartbilling.Bean.Bean_Response_Broker;
import com.example.smartbilling.Design.AddBrokerActivity;
import com.example.smartbilling.Design.DetailsBrokerActivity;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Broker extends RecyclerView.Adapter<Adapter_Broker.BrokerViewHolder> {

    private List<Bean_Broker> BrokerList;
    private Activity activity;

    public Adapter_Broker(List<Bean_Broker> BrokerList, Activity activity) {
        this.BrokerList = BrokerList;
        this.activity = activity;
    }

    public static class BrokerViewHolder extends RecyclerView.ViewHolder {

        TextView tvBrokerID;
        TextView tvBrokerName;
        TextView tvBrokerRate;
        TextView tvBrokerMobileNo;
        ImageView imgEdit;
        ImageView imgDelete;

        public BrokerViewHolder(View v) {
            super(v);
            tvBrokerID = (TextView) v.findViewById(R.id.tvBrokerID);
            tvBrokerName = (TextView) v.findViewById(R.id.tvBrokerName);
            tvBrokerRate = (TextView) v.findViewById(R.id.tvBrokerRate);
            tvBrokerMobileNo = (TextView) v.findViewById(R.id.tvBrokerMobileNo);
            imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Broker.BrokerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_broker, parent, false);
        return new Adapter_Broker.BrokerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Broker.BrokerViewHolder holder, final int position) {
        holder.tvBrokerID.setText(BrokerList.get(position).getBrokerID());
        holder.tvBrokerName.setText(BrokerList.get(position).getBrokerName());
        holder.tvBrokerRate.setText(BrokerList.get(position).getBrokerRate());
        holder.tvBrokerMobileNo.setText(BrokerList.get(position).getBrokerMobileNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsBrokerActivity.class);
                intent.putExtra("BrokerID",BrokerList.get(position).getBrokerID());
                intent.putExtra("BrokerName",BrokerList.get(position).getBrokerName());
                intent.putExtra("BrokerAddress",BrokerList.get(position).getBrokerAddress());
                intent.putExtra("BrokerMobileNumber",BrokerList.get(position).getBrokerMobileNumber());
                intent.putExtra("BrokerTelephoneNumber",BrokerList.get(position).getBrokerTelephoneNumber());
                intent.putExtra("BrokerEmail",BrokerList.get(position).getBrokerEmail());
                intent.putExtra("BrokerRate",BrokerList.get(position).getBrokerRate());
                intent.putExtra("BrokerFaxNo",BrokerList.get(position).getBrokerFaxNumber());
                activity.startActivity(intent);
            }
        });

        holder.tvBrokerMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MobileNumber = BrokerList.get(position).getBrokerMobileNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", MobileNumber, null));
                activity.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent intent = new Intent(activity, AddBrokerActivity.class);
                        intent.putExtra("BrokerID",BrokerList.get(position).getBrokerID());
                        intent.putExtra("BrokerName",BrokerList.get(position).getBrokerName());
                        intent.putExtra("BrokerAddress",BrokerList.get(position).getBrokerAddress());
                        intent.putExtra("BrokerMobileNumber",BrokerList.get(position).getBrokerMobileNumber());
                        intent.putExtra("BrokerTelephoneNumber",BrokerList.get(position).getBrokerTelephoneNumber());
                        intent.putExtra("BrokerEmail",BrokerList.get(position).getBrokerEmail());
                        intent.putExtra("BrokerRate",BrokerList.get(position).getBrokerRate());
                        intent.putExtra("BrokerFaxNo",BrokerList.get(position).getBrokerFaxNumber());
                        activity.startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("No", null);
                alertDialogBuilder.setMessage("Are you sure want to edit?");
                alertDialogBuilder.show();
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        String BrokerID = BrokerList.get(position).getBrokerID();
                        Delete(BrokerID);
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", null);
                alertDialogBuilder.setMessage("Are you sure want to delete?");
                alertDialogBuilder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return BrokerList.size();
    }

    void Delete(String BrokerID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Broker> call = apiInterface.DeleteBroker(BrokerID);
        call.enqueue(new Callback<Bean_Response_Broker>() {
            @Override
            public void onResponse(Call<Bean_Response_Broker> call, Response<Bean_Response_Broker> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Broker delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
                else {
                    Toast.makeText(activity, "Broker not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Broker> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}