package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.smartbilling.Bean.Bean_Collection;
import com.example.smartbilling.Bean.Bean_Response_Collection;
import com.example.smartbilling.Design.AddCollectionActivity;
import com.example.smartbilling.Design.DetailsCollectionActivity;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Collection extends RecyclerView.Adapter<Adapter_Collection.CollectionViewHolder> {

    private List<Bean_Collection> CollectionList;
    private Activity activity;

    public Adapter_Collection(List<Bean_Collection> CollectionList, Activity activity) {
        this.CollectionList = CollectionList;
        this.activity = activity;
    }

    public static class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView tvCollectionID;
        TextView tvPartyName;
        TextView tvCollectionNo;
        TextView tvCollectionDate;
        TextView tvCollectionAmount;
        ImageView imgEdit;
        ImageView imgDelete;

        public CollectionViewHolder(View v) {
            super(v);
            tvCollectionID = (TextView) v.findViewById (R.id.tvCollectionID);
            tvPartyName = (TextView) v.findViewById (R.id.tvPartyName);
            tvCollectionNo = (TextView) v.findViewById (R.id.tvCollectionNo);
            tvCollectionDate = (TextView) v.findViewById (R.id.tvCollectionDate);
            tvCollectionAmount = (TextView) v.findViewById (R.id.tvCollectionAmount);
            imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Collection.CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_collection, parent, false);
        return new Adapter_Collection.CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Collection.CollectionViewHolder holder, final int position) {
        holder.tvCollectionID.setText(CollectionList.get(position).getCollectionID());
        holder.tvPartyName.setText(CollectionList.get(position).getPartyName());
        holder.tvCollectionNo.setText(CollectionList.get(position).getCollectionNo());
        holder.tvCollectionDate.setText(CollectionList.get(position).getCollectionDate());
        holder.tvCollectionAmount.setText(CollectionList.get(position).getCollectionAmount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsCollectionActivity.class);
                intent.putExtra("CollectionID", CollectionList.get(position).getCollectionID());
                intent.putExtra("PartyName", CollectionList.get(position).getPartyName());
                intent.putExtra("BillNo", CollectionList.get(position).getBillNo());
                intent.putExtra("BillDate", CollectionList.get(position).getBillDate());
                intent.putExtra("Days", CollectionList.get(position).getCreditDays());
                intent.putExtra("BankName", CollectionList.get(position).getBankThrough());
                intent.putExtra("BankBranch", CollectionList.get(position).getBankBranch());
                intent.putExtra("CollectionNo", CollectionList.get(position).getCollectionNo());
                intent.putExtra("CollectionDate", CollectionList.get(position).getCollectionDate());
                intent.putExtra("ModeOfPayment", CollectionList.get(position).getModeofPayment());
                intent.putExtra("ChequeNo", CollectionList.get(position).getChequeNo());
                intent.putExtra("ChequeDate", CollectionList.get(position).getChequeDate());
                intent.putExtra("BillAmount", CollectionList.get(position).getBillAmount());
                intent.putExtra("CreditNote", CollectionList.get(position).getCreditNoteGrandTotal());
                intent.putExtra("DiscountPR", CollectionList.get(position).getDiscountPR());
                intent.putExtra("Discount", CollectionList.get(position).getDiscount());
                intent.putExtra("ReceivedAmount", CollectionList.get(position).getReceivedAmount());
                intent.putExtra("CollectionAmount", CollectionList.get(position).getCollectionAmount());
                intent.putExtra("ClearanceDate", CollectionList.get(position).getClearanceDate());
                activity.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(activity, AddCollectionActivity.class);
                        intent.putExtra("CollectionID", CollectionList.get(position).getCollectionID());
                        intent.putExtra("PartyID", CollectionList.get(position).getPartyID());
                        intent.putExtra("PartyName", CollectionList.get(position).getPartyName());
                        intent.putExtra("BillNo", CollectionList.get(position).getBillNo());
                        intent.putExtra("BillDate", CollectionList.get(position).getBillDate());
                        intent.putExtra("Days", CollectionList.get(position).getCreditDays());
                        intent.putExtra("BankName", CollectionList.get(position).getBankThrough());
                        intent.putExtra("BankBranch", CollectionList.get(position).getBankBranch());
                        intent.putExtra("CollectionNo", CollectionList.get(position).getCollectionNo());
                        intent.putExtra("CollectionDate", CollectionList.get(position).getCollectionDate());
                        intent.putExtra("ModeOfPayment", CollectionList.get(position).getModeofPayment());
                        intent.putExtra("ChequeNo", CollectionList.get(position).getChequeNo());
                        intent.putExtra("ChequeDate", CollectionList.get(position).getChequeDate());
                        intent.putExtra("BillAmount", CollectionList.get(position).getBillAmount());
                        intent.putExtra("CreditNote", CollectionList.get(position).getCreditNoteGrandTotal());
                        intent.putExtra("DiscountPR", CollectionList.get(position).getDiscountPR());
                        intent.putExtra("Discount", CollectionList.get(position).getDiscount());
                        intent.putExtra("ReceivedAmount", CollectionList.get(position).getReceivedAmount());
                        intent.putExtra("CollectionAmount", CollectionList.get(position).getCollectionAmount());
                        intent.putExtra("ClearanceDate", CollectionList.get(position).getClearanceDate());
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
                        String CollectionID = CollectionList.get(position).getCollectionID();
                        Delete(CollectionID);
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
        return CollectionList.size();
    }

    void Delete(String CollectionID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        SessionManager manager = new SessionManager(activity);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Collection> call = apiInterface.DeleteCollection(manager.getUserID(activity), CollectionID);
        call.enqueue(new Callback<Bean_Response_Collection>() {
            @Override
            public void onResponse(Call<Bean_Response_Collection> call, Response<Bean_Response_Collection> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Collection delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }else{
                    Toast.makeText(activity, "Collection not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Collection> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}