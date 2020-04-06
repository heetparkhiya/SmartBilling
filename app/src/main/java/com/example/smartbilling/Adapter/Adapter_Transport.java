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
import com.example.smartbilling.Bean.Bean_Response_Transport;
import com.example.smartbilling.Bean.Bean_Transport;
import com.example.smartbilling.Design.AddTransportActivity;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Transport extends RecyclerView.Adapter<Adapter_Transport.TransportViewHolder> {

    private List<Bean_Transport> TransportList;
    private Activity activity;

    public Adapter_Transport(List<Bean_Transport> TransportList, Activity activity) {
        this.TransportList = TransportList;
        this.activity = activity;
    }

    public static class TransportViewHolder extends RecyclerView.ViewHolder {

        TextView tvTransportID;
        TextView tvTransportName;
        TextView tvTransportMobileNo;
        TextView tvTransportAddress;
        ImageView imgEdit;
        ImageView imgDelete;

        public TransportViewHolder(View v) {
            super(v);
            tvTransportID = (TextView) v.findViewById (R.id.tvTransportID);
            tvTransportName = (TextView) v.findViewById (R.id.tvTransportName);
            tvTransportMobileNo = (TextView) v.findViewById (R.id.tvTransportMobileNo);
            tvTransportAddress = (TextView) v.findViewById (R.id.tvTransportAddress);
            imgEdit = (ImageView) v.findViewById (R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById (R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Transport.TransportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_transport,parent,false);
        return new TransportViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final TransportViewHolder holder, final int position) {
        holder.tvTransportID.setText(TransportList.get(position).getTransportID());
        holder.tvTransportName.setText(TransportList.get(position).getTransportName());
        holder.tvTransportMobileNo.setText(TransportList.get(position).getTransportMobileNumber());
        holder.tvTransportAddress.setText(TransportList.get(position).getTransportAddress());

        holder.tvTransportMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MobileNumber = TransportList.get(position).getTransportMobileNumber();
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
                        Intent intent = new Intent(activity,AddTransportActivity.class);
                        intent.putExtra("TransportID",TransportList.get(position).getTransportID());
                        intent.putExtra("TransportName",TransportList.get(position).getTransportName());
                        intent.putExtra("TransportMobileNumber",TransportList.get(position).getTransportMobileNumber());
                        intent.putExtra("TransportAddress",TransportList.get(position).getTransportAddress());
                        activity.startActivity(intent);
                        dialog.dismiss();
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
                        String TransportID = TransportList.get(position).getTransportID();
                        Delete(TransportID);
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
        return TransportList.size();
    }

    void Delete(String TransportID){
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Transport> call = apiInterface.DeleteTransport(TransportID);
        call.enqueue(new Callback<Bean_Response_Transport>() {
            @Override
            public void onResponse(Call<Bean_Response_Transport> call, Response<Bean_Response_Transport> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Transport delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }else{
                    Toast.makeText(activity, "Transport not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Transport> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}