package com.hydrocontract.hydroblock;
/*Adapter for Sellers*/

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
        mcx=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(mcx);
        View view=inflater.inflate(R.layout.list_sellers,null);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        seller seller=sellerList.get(position);
        holder.textViewUsername.setText(seller.getUsername());
        holder.textQuantity.setText(seller.getQuantity()+" Litres");
        int price=Integer.parseInt(seller.getQuantity())*5;
        holder.textViewPrice.setText("Rs."+String.valueOf(price));
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(mcx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_buy);
                Button confirm=dialog.findViewById(R.id.confirm_button);
                Button close=dialog.findViewById(R.id.close_button);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return sellerList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView textViewUsername,textQuantity,textViewPrice;
        Button buy;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewUsername=itemView.findViewById(R.id.username);
            textQuantity=itemView.findViewById(R.id.quantity);
            textViewPrice=itemView.findViewById(R.id.price);
            buy=itemView.findViewById(R.id.buy_button);
        }
    }
}
