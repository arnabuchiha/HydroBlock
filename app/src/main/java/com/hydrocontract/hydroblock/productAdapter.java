package com.hydrocontract.hydroblock;
/*Adapter for Sellers*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/*seller*/
import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductViewHolder> {
    private Context mcx;
    private List<seller> sellerList;

    public productAdapter(Context mcx, List<seller> sellerList) {
        this.mcx = mcx;
        this.sellerList = sellerList;
    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcx);
        View view=inflater.inflate(R.layout.list_sellers,null);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        seller seller=sellerList.get(position);
        holder.textViewSeller.setText(seller.getSeller());
        holder.textViewAddress.setText(seller.getAddress());
        holder.textViewSupply.setText(String.valueOf(seller.getSupply()));

    }

    @Override
    public int getItemCount() {
        return sellerList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView textViewSeller,textViewAddress,textViewSupply;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewSeller=itemView.findViewById(R.id.seller);
            textViewAddress=itemView.findViewById(R.id.address);
            textViewSupply=itemView.findViewById(R.id.supply);
        }
    }
}
