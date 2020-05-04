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
import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.Bean.Bean_Response_General;
import com.example.smartbilling.Design.AddProductActivity;
import com.example.smartbilling.Design.DetailsProductActivity;
import com.example.smartbilling.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ProductViewHolder> {

    private List<Bean_Product> ProductList;
    private Activity activity;

    public Adapter_Product(List<Bean_Product> ProductList, Activity activity) {
        this.ProductList = ProductList;
        this.activity = activity;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {

        holder.tvProductName.setText(ProductList.get(position).getProductName());
        holder.tvDesignNumber.setText(ProductList.get(position).getProductDesignNumber());
        holder.tvProductStyle.setText(ProductList.get(position).getProductStyle());
        holder.tvProductName.setText(ProductList.get(position).getProductName());
        holder.tvProductMRP.setText(ProductList.get(position).getProductMRPPR());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNextActivity(position, DetailsProductActivity.class);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        callNextActivity(position, AddProductActivity.class);
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
                    public void onClick(DialogInterface dialog, int id) {
                        String ProductID = ProductList.get(position).getProductID();
                        Delete(ProductID, position);
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
        return ProductList.size();
    }

    void Delete(String ProductID, final int position) {
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bean_Response_General> call = apiInterface.DeleteProduct(ProductID);
        call.enqueue(new Callback<Bean_Response_General>() {
            @Override
            public void onResponse(Call<Bean_Response_General> call, Response<Bean_Response_General> response) {
                if (response.body().getResponse() == 1) {
                    notifyItemRemoved(position);
                    Toast.makeText(activity, "Product delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
                else{
                    Toast.makeText(activity, "Product not delete successfully", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Bean_Response_General> call, Throwable t) {
                Toast.makeText(activity, "Internet connection problem", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }

    private void callNextActivity(int position, Class<?> aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.putExtra("ProductID", ProductList.get(position).getProductID());
        intent.putExtra("ProductName", ProductList.get(position).getProductName());
        intent.putExtra("ProductDesignNumber", ProductList.get(position).getProductDesignNumber());
        intent.putExtra("ProductStyle", ProductList.get(position).getProductStyle());
        intent.putExtra("ProductMRP_PR", ProductList.get(position).getProductMRPPR());
        intent.putExtra("Size", ProductList.get(position).getSize());
        activity.startActivity(intent);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEdit;
        ImageView imgDelete;
        TextView tvProductName, tvDesignNumber, tvProductStyle, tvProductMRP;

        public ProductViewHolder(View v) {
            super(v);
            imgEdit = v.findViewById(R.id.imgEdit);
            imgDelete = v.findViewById(R.id.imgDelete);
            tvProductName = v.findViewById(R.id.tvProductName);
            tvDesignNumber = v.findViewById(R.id.tvDesignNumber);
            tvProductStyle = v.findViewById(R.id.tvProductStyle);
            tvProductMRP = v.findViewById(R.id.tvProductMRP);
        }
    }
}