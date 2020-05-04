package com.example.smartbilling.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.Bean.Bean_Size;
import com.example.smartbilling.R;

import java.util.List;

public class Adapter_ProductWise_Size extends RecyclerView.Adapter<Adapter_ProductWise_Size.ProductWiseSizeViewHolder> {

    private Context context;
    private List<Bean_Size> SizeList;
    @NonNull
    private OnItemCheckListener onItemCheckListener;
    private boolean isEditMode = false;

    public Adapter_ProductWise_Size(Context context, List<Bean_Size> SizeList, @NonNull OnItemCheckListener onItemCheckListener, boolean isEditMode) {
        this.context = context;
        this.SizeList = SizeList;
        this.onItemCheckListener = onItemCheckListener;
        this.isEditMode = isEditMode;
    }

    @NonNull
    @Override
    public ProductWiseSizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview_productwise_size, parent, false);
        return new ProductWiseSizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductWiseSizeViewHolder holder, final int position) {
        if (isEditMode) {
            holder.tvSize.setText(SizeList.get(position).getSize());
            holder.etProductMRP.setText(SizeList.get(position).getProductMRP());
            holder.etProductRate.setText(SizeList.get(position).getProductRate());
            holder.etProductMRP.setEnabled(true);
            holder.etProductRate.setEnabled(true);
            holder.chkSize.setChecked(true);
        } else {
            holder.tvSize.setText(SizeList.get(position).getSize());
        }
        holder.chkSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.chkSize.isChecked())
                    onItemCheckListener.onItemCheck(SizeList.get(position), holder);
                else {
                    onItemCheckListener.onItemUncheck(SizeList.get(position), holder);
                }
            }
        });

    }

    public List<Bean_Size> getList() {
        return SizeList;
    }

    @Override
    public int getItemCount() {
        return SizeList.size();
    }


    public interface OnItemCheckListener {
        void onItemCheck(Bean_Size item, ProductWiseSizeViewHolder chkSize);
        void onItemUncheck(Bean_Size item, ProductWiseSizeViewHolder chkSize);
    }

    public static class ProductWiseSizeViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgEdit;
        public ImageView imgDelete;
        public TextView tvSize;
        public CheckBox chkSize;
        public EditText etProductRate;
        public EditText etProductMRP;

        ProductWiseSizeViewHolder(View v) {
            super(v);
            imgEdit = v.findViewById(R.id.imgEdit);
            imgDelete = v.findViewById(R.id.imgDelete);
            tvSize = v.findViewById(R.id.tvSize);
            chkSize = v.findViewById(R.id.chkSize);
            etProductRate = v.findViewById(R.id.etProductRate);
            etProductMRP = v.findViewById(R.id.etProductMRP);
        }
    }
}