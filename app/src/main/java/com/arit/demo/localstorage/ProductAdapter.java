package com.arit.demo.localstorage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arit.demo.localstorage.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;
    private List<Product> filteredProducts;
    ProductAdapter(List<Product> products){
        this.products = products;
        this.filteredProducts = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.productCode.setText(this.filteredProducts.get(i).getProductCode());
        viewHolder.productName.setText(this.filteredProducts.get(i).getProductName());
    }

    @Override
    public int getItemCount() {
        return this.filteredProducts.size();
    }

    public void doFilter(String searchText){
        if(!searchText.isEmpty()) {
            filteredProducts = new ArrayList<Product>();
            for(Product product: this.products) {
                if (product.getProductName().toLowerCase().contains(searchText)) {
                    filteredProducts.add(product);
                }
            }
        }else{
            this.filteredProducts = this.products;
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvProductName) TextView productName;
        @BindView(R.id.tvProductCode) TextView productCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
