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
import com.example.smartbilling.Bean.Bean_Party;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.Design.AddPartyActivity;
import com.example.smartbilling.Design.DetailsPartyActivity;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Party extends RecyclerView.Adapter<Adapter_Party.PartyViewHolder> {

    private List<Bean_Party> PartyList;
    private Activity activity;

    public Adapter_Party(List<Bean_Party> PartyList, Activity activity) {
        this.PartyList = PartyList;
        this.activity = activity;
    }

    public static class PartyViewHolder extends RecyclerView.ViewHolder {

        TextView tvPartyID;
        TextView tvPartyName;
        TextView tvPartyMobileNo;
        TextView tvPartyEmail;
        ImageView imgEdit;
        ImageView imgDelete;

        public PartyViewHolder(View v) {
            super(v);
            tvPartyID = (TextView) v.findViewById(R.id.tvPartyID);
            tvPartyName = (TextView) v.findViewById(R.id.tvPartyName);
            tvPartyMobileNo = (TextView) v.findViewById(R.id.tvPartyMobileNo);
            tvPartyEmail = (TextView) v.findViewById(R.id.tvPartyEmail);
            imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Party.PartyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_party, parent, false);
        return new Adapter_Party.PartyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Party.PartyViewHolder holder, final int position) {
        holder.tvPartyID.setText(PartyList.get(position).getPartyID());
        holder.tvPartyName.setText(PartyList.get(position).getPartyName());
        holder.tvPartyMobileNo.setText(PartyList.get(position).getMobileNumber());
        holder.tvPartyEmail.setText(PartyList.get(position).getEmailID());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsPartyActivity.class);
                intent.putExtra("PartyID", PartyList.get(position).getPartyID());
                intent.putExtra("PartyName", PartyList.get(position).getPartyName());
                intent.putExtra("PartyMobileNumber", PartyList.get(position).getMobileNumber());
                intent.putExtra("PartyTelephoneNumber", PartyList.get(position).getTelephoneNumber());
                intent.putExtra("PartyEmail", PartyList.get(position).getEmailID());
                intent.putExtra("PartyAddress", PartyList.get(position).getAddress());
                intent.putExtra("PartyCity", PartyList.get(position).getCity());
                intent.putExtra("PartyState", PartyList.get(position).getState());
                intent.putExtra("PartyPin", PartyList.get(position).getPIN());
                intent.putExtra("PartyLocation", PartyList.get(position).getPartyLocation());
                intent.putExtra("PartyRefName", PartyList.get(position).getReferenceName());
                intent.putExtra("PartyCSTNumber", PartyList.get(position).getCSTNo());
                intent.putExtra("PartyTINNumber", PartyList.get(position).getTINNo());
                intent.putExtra("PartyBankThrough", PartyList.get(position).getBankThrough());
                intent.putExtra("PartyCreditDays", PartyList.get(position).getCreditDays());
                intent.putExtra("PartyDISC", PartyList.get(position).getDISC());
                intent.putExtra("PartyFaxNumber", PartyList.get(position).getFaxNumber());
                intent.putExtra("PartyBrokerID", PartyList.get(position).getBrokerID());
                intent.putExtra("PartyBrokerName", PartyList.get(position).getBrokerName());
                intent.putExtra("PartyBrokerRate", PartyList.get(position).getBrokerRate());
                intent.putExtra("PartyBrokerMobileNo", PartyList.get(position).getBrokerMobileNumber());
                intent.putExtra("PartyBrokerTelephoneNo", PartyList.get(position).getBrokerTelephoneNumber());
                intent.putExtra("PartyBrokerEmail", PartyList.get(position).getBrokerEmail());
                intent.putExtra("PartyBrokerAddress", PartyList.get(position).getBrokerAddress());
                intent.putExtra("PartyBrokerFaxNo", PartyList.get(position).getBrokerFaxNumber());
                intent.putExtra("PartyTransportID", PartyList.get(position).getTransportID());
                intent.putExtra("PartyTransportName", PartyList.get(position).getTransportName());
                intent.putExtra("PartyTransportAddress", PartyList.get(position).getTransportAddress());
                intent.putExtra("PartyTransportMobileNo", PartyList.get(position).getTransportMobileNumber());
                intent.putExtra("PartyBrokerRage", PartyList.get(position).getBrokerRage());
                activity.startActivity(intent);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(activity, AddPartyActivity.class);
                        intent.putExtra("PartyID", PartyList.get(position).getPartyID());
                        intent.putExtra("PartyName", PartyList.get(position).getPartyName());
                        intent.putExtra("PartyMobileNumber", PartyList.get(position).getMobileNumber());
                        intent.putExtra("PartyTelephoneNumber", PartyList.get(position).getTelephoneNumber());
                        intent.putExtra("PartyEmail", PartyList.get(position).getEmailID());
                        intent.putExtra("PartyAddress", PartyList.get(position).getAddress());
                        intent.putExtra("PartyCity", PartyList.get(position).getCity());
                        intent.putExtra("PartyState", PartyList.get(position).getState());
                        intent.putExtra("PartyPin", PartyList.get(position).getPIN());
                        intent.putExtra("PartyLocation", PartyList.get(position).getPartyLocation());
                        intent.putExtra("PartyRefName", PartyList.get(position).getReferenceName());
                        intent.putExtra("PartyCSTNumber", PartyList.get(position).getCSTNo());
                        intent.putExtra("PartyTINNumber", PartyList.get(position).getTINNo());
                        intent.putExtra("PartyBankThrough", PartyList.get(position).getBankThrough());
                        intent.putExtra("PartyCreditDays", PartyList.get(position).getCreditDays());
                        intent.putExtra("PartyDISC", PartyList.get(position).getDISC());
                        intent.putExtra("PartyFaxNumber", PartyList.get(position).getFaxNumber());
                        intent.putExtra("PartyBrokerID", PartyList.get(position).getBrokerID());
                        intent.putExtra("PartyTransportID", PartyList.get(position).getTransportID());
                        intent.putExtra("PartyBrokerRage", PartyList.get(position).getBrokerRage());
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
                    public void onClick(DialogInterface dialog, int id) {
                        String PartyID = PartyList.get(position).getPartyID();
                        Delete(PartyID);
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", null);
                alertDialogBuilder.setMessage("Are you sure want to delete?");
                alertDialogBuilder.show();
            }
        });

        holder.tvPartyMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MobileNumber = PartyList.get(position).getMobileNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", MobileNumber, null));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PartyList.size();
    }

    void Delete(String PartyID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Party> call = apiInterface.DeleteParty(PartyID);
        call.enqueue(new Callback<Bean_Response_Party>() {
            @Override
            public void onResponse(Call<Bean_Response_Party> call, Response<Bean_Response_Party> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Party delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }else{
                    Toast.makeText(activity, "Party not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Party> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}