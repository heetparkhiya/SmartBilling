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
import com.example.smartbilling.Bean.Bean_CreditNote;
import com.example.smartbilling.Bean.Bean_Response_CreditNote;
import com.example.smartbilling.Design.AddCreditNoteActivity;
import com.example.smartbilling.Design.DetailsCreditNoteActivity;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_CreditNote extends RecyclerView.Adapter<Adapter_CreditNote.CreditNoteViewHolder> {

    private List<Bean_CreditNote> CreditNoteList;
    private Activity activity;

    public Adapter_CreditNote(List<Bean_CreditNote> CreditNoteList, Activity activity) {
        this.CreditNoteList = CreditNoteList;
        this.activity = activity;
    }

    public static class CreditNoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvCreditNoteID;
        TextView tvPartyName;
        TextView tvCreditNoteNo;
        TextView tvCreditDate;
        TextView tvGrandTotal;
        ImageView imgEdit;
        ImageView imgDelete;

        public CreditNoteViewHolder(View v) {
            super(v);
            tvCreditNoteID = (TextView) v.findViewById(R.id.tvCreditNoteID);
            tvPartyName = (TextView) v.findViewById(R.id.tvPartyName);
            tvCreditNoteNo = (TextView) v.findViewById(R.id.tvCreditNoteNo);
            tvCreditDate = (TextView) v.findViewById(R.id.tvCreditDate);
            tvGrandTotal = (TextView) v.findViewById(R.id.tvGrandTotal);
            imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_CreditNote.CreditNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_credit_note, parent, false);
        return new Adapter_CreditNote.CreditNoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_CreditNote.CreditNoteViewHolder holder, final int position) {
        holder.tvCreditNoteID.setText(CreditNoteList.get(position).getCreditNoteID());
        holder.tvPartyName.setText(CreditNoteList.get(position).getPartyName());
        holder.tvCreditNoteNo.setText(CreditNoteList.get(position).getCreditNoteNo());
        holder.tvCreditDate.setText(CreditNoteList.get(position).getCreditDate());
        holder.tvGrandTotal.setText(CreditNoteList.get(position).getGrandTotal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsCreditNoteActivity.class);
                intent.putExtra("CreditNoteID", CreditNoteList.get(position).getCreditNoteID());
                intent.putExtra("CreditNoteNo", CreditNoteList.get(position).getCreditNoteNo());
                intent.putExtra("CreditNoteDate", CreditNoteList.get(position).getCreditDate());
                intent.putExtra("CreditNoteNoOfCases", CreditNoteList.get(position).getNoofCases());
                intent.putExtra("CreditNoteInvoiceNo", CreditNoteList.get(position).getInvoiceNo());
                intent.putExtra("CreditNoteInvoiceDate", CreditNoteList.get(position).getInvoiceDate());
                intent.putExtra("CreditNoteTotalQuantity", CreditNoteList.get(position).getTotalQuantity());
                intent.putExtra("CreditNoteTotalAmount", CreditNoteList.get(position).getTotalAmount());
                intent.putExtra("CreditNoteDiscountPR", CreditNoteList.get(position).getDiscountPR());
                intent.putExtra("CreditNoteDiscount", CreditNoteList.get(position).getDiscount());
                intent.putExtra("CreditNoteTotal", CreditNoteList.get(position).getTotal());
                intent.putExtra("CreditNoteTaxPR", CreditNoteList.get(position).getTaxPR());
                intent.putExtra("CreditNoteTax", CreditNoteList.get(position).getTax());
                intent.putExtra("CreditNoteOtherCharges", CreditNoteList.get(position).getOtherCharges());
                intent.putExtra("CreditNoteGrandTotal", CreditNoteList.get(position).getGrandTotal());
                intent.putExtra("CreditNoteCForm", CreditNoteList.get(position).getCForm());
                intent.putExtra("PartyName", CreditNoteList.get(position).getPartyName());
                intent.putExtra("PartyMobileNumber", CreditNoteList.get(position).getPartyMobileNumber());
                intent.putExtra("PartyTelephoneNumber", CreditNoteList.get(position).getPartyTelephoneNumber());
                intent.putExtra("PartyEmail", CreditNoteList.get(position).getPartyEmailID());
                intent.putExtra("PartyAddress", CreditNoteList.get(position).getPartyAddress());
                intent.putExtra("PartyCity", CreditNoteList.get(position).getPartyCity());
                intent.putExtra("PartyState", CreditNoteList.get(position).getPartyState());
                intent.putExtra("PartyPin", CreditNoteList.get(position).getPartyPIN());
                intent.putExtra("PartyLocation", CreditNoteList.get(position).getPartyLocation());
                intent.putExtra("PartyRefName", CreditNoteList.get(position).getPartyReferenceName());
                intent.putExtra("PartyCSTNumber", CreditNoteList.get(position).getPartyCSTNo());
                intent.putExtra("PartyTINNumber", CreditNoteList.get(position).getPartyTINNo());
                intent.putExtra("PartyBankThrough", CreditNoteList.get(position).getPartyBankThrough());
                intent.putExtra("PartyCreditDays", CreditNoteList.get(position).getPartyCreditDays());
                intent.putExtra("PartyDISC", CreditNoteList.get(position).getPartyDISC());
                intent.putExtra("PartyFaxNumber", CreditNoteList.get(position).getPartyFaxNumber());
                activity.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(activity, AddCreditNoteActivity.class);
                        intent.putExtra("CreditNoteID", CreditNoteList.get(position).getCreditNoteID());
                        intent.putExtra("CreditNoteNo", CreditNoteList.get(position).getCreditNoteNo());
                        intent.putExtra("CreditNoteDate", CreditNoteList.get(position).getCreditDate());
                        intent.putExtra("CreditNoteNoOfCases", CreditNoteList.get(position).getNoofCases());
                        intent.putExtra("CreditNoteInvoiceNo", CreditNoteList.get(position).getInvoiceNo());
                        intent.putExtra("CreditNoteInvoiceDate", CreditNoteList.get(position).getInvoiceDate());
                        intent.putExtra("CreditNoteTotalQuantity", CreditNoteList.get(position).getTotalQuantity());
                        intent.putExtra("CreditNoteTotalAmount", CreditNoteList.get(position).getTotalAmount());
                        intent.putExtra("CreditNoteDiscount", CreditNoteList.get(position).getDiscount());
                        intent.putExtra("CreditNoteTotal", CreditNoteList.get(position).getTotal());
                        intent.putExtra("CreditNoteTax", CreditNoteList.get(position).getTax());
                        intent.putExtra("CreditNoteOtherCharges", CreditNoteList.get(position).getOtherCharges());
                        intent.putExtra("CreditNoteGrandTotal", CreditNoteList.get(position).getGrandTotal());
                        intent.putExtra("CreditNoteCForm", CreditNoteList.get(position).getCForm());
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
                        String CreditNote = CreditNoteList.get(position).getCreditNoteID();
                        Delete(CreditNote);
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
        return CreditNoteList.size();
    }

    void Delete(String CreditNoteID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_CreditNote> call = apiInterface.DeleteCreditNote(CreditNoteID);
        call.enqueue(new Callback<Bean_Response_CreditNote>() {
            @Override
            public void onResponse(Call<Bean_Response_CreditNote> call, Response<Bean_Response_CreditNote> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Credit note delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
                else {
                    Toast.makeText(activity, "Credit note not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_CreditNote> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}