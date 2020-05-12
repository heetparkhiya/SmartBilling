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
import com.example.smartbilling.Bean.Bean_ListEntry;
import com.example.smartbilling.Bean.Bean_Response_ListEntry;
import com.example.smartbilling.Design.AddPerformInvoiceActivity;
import com.example.smartbilling.Design.DetailsPerformInvoiceActivity;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Perform_Invoice extends RecyclerView.Adapter<Adapter_Perform_Invoice.ListEntryViewHolder> {

    private List<Bean_ListEntry> PerformInvoiceList;
    private Activity activity;

    public Adapter_Perform_Invoice(List<Bean_ListEntry> PerformInvoiceList, Activity activity) {
        this.PerformInvoiceList = PerformInvoiceList;
        this.activity = activity;
    }

    public static class ListEntryViewHolder extends RecyclerView.ViewHolder {

        TextView tvListEntryID;
        TextView tvPartyName;
        TextView tvPartyMobileNo;
        TextView tvOrderProductName;
        ImageView imgEdit;
        ImageView imgDelete;

        public ListEntryViewHolder(View v) {
            super(v);
            tvListEntryID = (TextView) v.findViewById(R.id.tvListEntryID);
            tvPartyName = (TextView) v.findViewById(R.id.tvPartyName);
            tvPartyMobileNo = (TextView) v.findViewById(R.id.tvPartyMobileNo);
            tvOrderProductName = (TextView) v.findViewById(R.id.tvOrderProductName);
            imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Perform_Invoice.ListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_perform_invoice, parent, false);
        return new Adapter_Perform_Invoice.ListEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Perform_Invoice.ListEntryViewHolder holder, final int position) {
        holder.tvListEntryID.setText(PerformInvoiceList.get(position).getListID());
        holder.tvPartyName.setText(PerformInvoiceList.get(position).getPartyName());
        holder.tvPartyMobileNo.setText(PerformInvoiceList.get(position).getPartyMobileNo());
        holder.tvOrderProductName.setText(PerformInvoiceList.get(position).getProductName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsPerformInvoiceActivity.class);
                intent.putExtra("ListID", PerformInvoiceList.get(position).getListID());
                intent.putExtra("ListType", PerformInvoiceList.get(position).getListType());
                intent.putExtra("PartyName", PerformInvoiceList.get(position).getPartyName());
                intent.putExtra("PartyMobileNo", PerformInvoiceList.get(position).getPartyMobileNo());
                intent.putExtra("PartyAddress", PerformInvoiceList.get(position).getPartyAddress());
                intent.putExtra("PartyCSTNo", PerformInvoiceList.get(position).getPartyCSTNo());
                intent.putExtra("PartyTransportName", PerformInvoiceList.get(position).getPartyTransportName());
                intent.putExtra("ProductName", PerformInvoiceList.get(position).getProductName());
                intent.putExtra("ListNo", PerformInvoiceList.get(position).getListNo());
                intent.putExtra("ListDate", PerformInvoiceList.get(position).getListDate());
                intent.putExtra("NoofCases", PerformInvoiceList.get(position).getNoofCases());
                intent.putExtra("TotalQuantity", PerformInvoiceList.get(position).getTotalQuantity());
                activity.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(activity, AddPerformInvoiceActivity.class);
                        intent.putExtra("ListID", PerformInvoiceList.get(position).getListID());
                        intent.putExtra("PartyName", PerformInvoiceList.get(position).getPartyName());
                        intent.putExtra("PartyMobileNo", PerformInvoiceList.get(position).getPartyMobileNo());
                        intent.putExtra("PartyAddress", PerformInvoiceList.get(position).getPartyAddress());
                        intent.putExtra("PartyCSTNo", PerformInvoiceList.get(position).getPartyCSTNo());
                        intent.putExtra("PartyTransportName", PerformInvoiceList.get(position).getPartyTransportName());
                        intent.putExtra("ProductName", PerformInvoiceList.get(position).getProductName());
                        intent.putExtra("ListNo", PerformInvoiceList.get(position).getListNo());
                        intent.putExtra("ListDate", PerformInvoiceList.get(position).getListDate());
                        intent.putExtra("NoofCases", PerformInvoiceList.get(position).getNoofCases());
                        intent.putExtra("TotalQuantity", PerformInvoiceList.get(position).getTotalQuantity());
                        activity.startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("No", null);
                alertDialogBuilder.setMessage("Are you sure want to edit?");
                alertDialogBuilder.show();
            }
        });

        holder.tvPartyMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ContactNumber = PerformInvoiceList.get(position).getPartyMobileNo();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", ContactNumber, null));
                activity.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String ListID = PerformInvoiceList.get(position).getListID();
                        Delete(ListID);
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
        return PerformInvoiceList.size();
    }

    void Delete(String ListID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_ListEntry> call = apiInterface.DeleteListEntry(ListID);
        call.enqueue(new Callback<Bean_Response_ListEntry>() {
            @Override
            public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Invoice entry delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }else{
                    Toast.makeText(activity, "Invoice entry not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_ListEntry> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}

