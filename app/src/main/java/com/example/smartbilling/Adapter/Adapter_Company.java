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
import com.example.smartbilling.Bean.Bean_Company;
import com.example.smartbilling.Bean.Bean_Response_Company;
import com.example.smartbilling.Design.AddCompanyActivity;
import com.example.smartbilling.Design.DetailsCompanyActivity;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Company extends RecyclerView.Adapter<Adapter_Company.CompanyViewHolder> {

    private List<Bean_Company> CompanyList;
    private Activity activity;

    public Adapter_Company(List<Bean_Company> CompanyList, Activity activity) {
        this.CompanyList = CompanyList;
        this.activity = activity;
    }

    public static class CompanyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCompanyID;
        TextView tvCompanyName;
        TextView tvCompanyMobileNo;
        ImageView imgEdit;
        ImageView imgDelete;

        public CompanyViewHolder(View v) {
            super(v);
            tvCompanyID = (TextView) v.findViewById(R.id.tvCompanyID);
            tvCompanyName = (TextView) v.findViewById(R.id.tvCompanyName);
            tvCompanyMobileNo = (TextView) v.findViewById(R.id.tvCompanyMobileNo);
            imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Company.CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_company, parent, false);
        return new Adapter_Company.CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Company.CompanyViewHolder holder, final int position) {
        holder.tvCompanyID.setText(CompanyList.get(position).getCompanyID());
        holder.tvCompanyName.setText(CompanyList.get(position).getCompanyName());
        holder.tvCompanyMobileNo.setText(CompanyList.get(position).getCompanyContactNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsCompanyActivity.class);
                intent.putExtra("CompanyID",CompanyList.get(position).getCompanyID());
                intent.putExtra("CompanyName",CompanyList.get(position).getCompanyName());
                intent.putExtra("CompanyAddress",CompanyList.get(position).getCompanyAddress());
                intent.putExtra("CompanyContactNumber",CompanyList.get(position).getCompanyContactNumber());
                intent.putExtra("CompanyEmail",CompanyList.get(position).getCompanyEmail());
                intent.putExtra("CompanyVAT_TIN_No",CompanyList.get(position).getCompanyVATTINNo());
                intent.putExtra("CompanyCST_TIN_No",CompanyList.get(position).getCompanyCSTTINNo());
                intent.putExtra("CompanyFaxNo",CompanyList.get(position).getCompanyFaxNumber());
                activity.startActivity(intent);
            }
        });

        holder.tvCompanyMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ContactNumber = CompanyList.get(position).getCompanyContactNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", ContactNumber, null));
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
                        Intent intent = new Intent(activity, AddCompanyActivity.class);
                        intent.putExtra("CompanyID",CompanyList.get(position).getCompanyID());
                        intent.putExtra("CompanyName",CompanyList.get(position).getCompanyName());
                        intent.putExtra("CompanyContactNo",CompanyList.get(position).getCompanyContactNumber());
                        intent.putExtra("CompanyAddress",CompanyList.get(position).getCompanyAddress());
                        intent.putExtra("CompanyEmail",CompanyList.get(position).getCompanyEmail());
                        intent.putExtra("CompanyVAT_TIN_NO",CompanyList.get(position).getCompanyVATTINNo());
                        intent.putExtra("CompanyCST_TIN_NO",CompanyList.get(position).getCompanyCSTTINNo());
                        intent.putExtra("CompanyFaxNO",CompanyList.get(position).getCompanyFaxNumber());
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
                        String CompanyID = CompanyList.get(position).getCompanyID();
                        Delete(CompanyID);
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
        return CompanyList.size();
    }

    void Delete(String CompanyID){
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        SessionManager manager = new SessionManager(activity);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Company> call = apiInterface.DeleteCompany(manager.getUserID(activity), CompanyID);
        call.enqueue(new Callback<Bean_Response_Company>() {
            @Override
            public void onResponse(Call<Bean_Response_Company> call, Response<Bean_Response_Company> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Company delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
                else {
                    Toast.makeText(activity, "Company not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Company> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
