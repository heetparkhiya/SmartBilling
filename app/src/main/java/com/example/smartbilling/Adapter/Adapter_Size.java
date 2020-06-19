package com.example.smartbilling.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.example.smartbilling.Bean.Bean_Response_Size;
import com.example.smartbilling.Bean.Bean_Size;
import com.example.smartbilling.R;
import com.example.smartbilling.SessionManager.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Size extends RecyclerView.Adapter<Adapter_Size.SizeViewHolder> {

    private List<Bean_Size> SizeList;
    private Activity activity;
    private  int isDelete;

    public Adapter_Size(List<Bean_Size> SizeList, Activity activity,int isDelete) {
        this.SizeList = SizeList;
        this.activity = activity;
        this.isDelete = isDelete;
    }

    public static class SizeViewHolder extends RecyclerView.ViewHolder {

        TextView tvSizeID;
        TextView tvSize;
        ImageView imgDelete;

        public SizeViewHolder(View v) {
            super(v);
            tvSizeID = (TextView) v.findViewById(R.id.tvSizeID);
            tvSize = (TextView) v.findViewById(R.id.tvSize);
            imgDelete = (ImageView) v.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public Adapter_Size.SizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_size, parent, false);
        return new Adapter_Size.SizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_Size.SizeViewHolder holder, final int position) {
        holder.tvSizeID.setText(SizeList.get(position).getSizeID());
        holder.tvSize.setText(SizeList.get(position).getSize());
        holder.imgDelete.setVisibility(isDelete);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        String SizeID = SizeList.get(position).getSizeID();
                        Delete(SizeID);
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
        return SizeList.size();
    }

    void Delete(String SizeID) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        SessionManager manager = new SessionManager(activity);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_Size> call = apiInterface.DeleteSize(manager.getUserID(activity), SizeID);
        call.enqueue(new Callback<Bean_Response_Size>() {
            @Override
            public void onResponse(Call<Bean_Response_Size> call, Response<Bean_Response_Size> response) {
                if (response.body().getResponse() == 1) {
                    Toast.makeText(activity, "Size delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }else{
                    Toast.makeText(activity, "Size not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_Size> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}