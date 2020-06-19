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
import com.example.smartbilling.Design.AddOrderActivity;
import com.example.smartbilling.Design.DetailsOrderActivity;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Order extends RecyclerView.Adapter<Adapter_Order.ListEntryViewHolder> {

    private List<Bean_ListEntry> OredrList;
    private Activity activity;

    public Adapter_Order(List<Bean_ListEntry> OredrList, Activity activity) {
        this.OredrList = OredrList;
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
    public Adapter_Order.ListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_order, parent, false);
        return new Adapter_Order.ListEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Order.ListEntryViewHolder holder, final int position) {
        holder.tvListEntryID.setText(OredrList.get(position).getListID());
        holder.tvPartyName.setText(OredrList.get(position).getPartyName());
        holder.tvPartyMobileNo.setText(OredrList.get(position).getPartyMobileNo());
        holder.tvOrderProductName.setText(OredrList.get(position).getProductName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsOrderActivity.class);
                intent.putExtra("ListID", OredrList.get(position).getListID());
                intent.putExtra("ListType", OredrList.get(position).getListType());
                intent.putExtra("PartyName", OredrList.get(position).getPartyName());
                intent.putExtra("PartyMobileNo", OredrList.get(position).getPartyMobileNo());
                intent.putExtra("PartyAddress", OredrList.get(position).getPartyAddress());
                intent.putExtra("PartyCSTNo", OredrList.get(position).getPartyCSTNo());
                intent.putExtra("PartyTransportName", OredrList.get(position).getPartyTransportName());
                intent.putExtra("ProductName", OredrList.get(position).getProductName());
                intent.putExtra("ListNo", OredrList.get(position).getListNo());
                intent.putExtra("ListDate", OredrList.get(position).getListDate());
                intent.putExtra("NoofCases", OredrList.get(position).getNoofCases());
                intent.putExtra("TotalQuantity", OredrList.get(position).getTotalQuantity());
                activity.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(activity, AddOrderActivity.class);
                        intent.putExtra("ListID", OredrList.get(position).getListID());
                        intent.putExtra("PartyName", OredrList.get(position).getPartyName());
                        intent.putExtra("PartyMobileNo", OredrList.get(position).getPartyMobileNo());
                        intent.putExtra("PartyAddress", OredrList.get(position).getPartyAddress());
                        intent.putExtra("PartyCSTNo", OredrList.get(position).getPartyCSTNo());
                        intent.putExtra("PartyTransportName", OredrList.get(position).getPartyTransportName());
                        intent.putExtra("ProductName", OredrList.get(position).getProductName());
                        intent.putExtra("ListNo", OredrList.get(position).getListNo());
                        intent.putExtra("ListDate", OredrList.get(position).getListDate());
                        intent.putExtra("NoofCases", OredrList.get(position).getNoofCases());
                        intent.putExtra("TotalQuantity", OredrList.get(position).getTotalQuantity());
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
                String ContactNumber = OredrList.get(position).getPartyMobileNo();
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
                        String ListID = OredrList.get(position).getListID();
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
        return OredrList.size();
    }

    void Delete(String ListID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        SessionManager manager = new SessionManager(activity);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_ListEntry> call = apiInterface.DeleteListEntry(manager.getUserID(activity), ListID);
        call.enqueue(new Callback<Bean_Response_ListEntry>() {
            @Override
            public void onResponse(Call<Bean_Response_ListEntry> call, Response<Bean_Response_ListEntry> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Order delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }else{
                    Toast.makeText(activity, "Order not delete successfully", Toast.LENGTH_SHORT).show();
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