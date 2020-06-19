package com.example.smartbilling.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.Bean.Bean_Product;
import com.example.smartbilling.R;

import java.util.List;

public class Adapter_Invoice_Product extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Bean_Product> listItems;

    public Adapter_Invoice_Product(List<Bean_Product> listItems) {
        this.listItems = listItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return new VHHeader(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            Bean_Product product = listItems.get(position - 1);
            VHItem VHitem = (VHItem) holder;
            VHitem.txtDesign.setText(product.getProductDesignNumber());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return listItems.size() + 1;
    }

    class VHHeader extends RecyclerView.ViewHolder{
        public VHHeader(View itemView) {
            super(itemView);
        }
    }

    static class VHItem extends RecyclerView.ViewHolder {
        TextView txtDesign, txtSize, txtQuantity, txtAmount;

        public VHItem(View itemView) {
            super(itemView);
            this.txtDesign = (TextView) itemView.findViewById(R.id.txtDesign);
            this.txtSize = (TextView) itemView.findViewById(R.id.txtSize);
            this.txtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);
            this.txtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
        }
    }
}
