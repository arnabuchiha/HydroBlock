package com.hydrocontract.hydroblock;
/*Adapter for Sellers*/

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*seller*/

import com.orhanobut.hawk.Hawk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
//        int price=Integer.parseInt(seller.getQuantity())*5;
        holder.textViewPrice.setText("Rs."+seller.getQuantity());
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(mcx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_buy);
                Button confirm=dialog.findViewById(R.id.confirm_button);
                EditText resident_address=dialog.findViewById(R.id.resident_address);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Hawk.init(mcx).build();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(buyApi.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                                .build();
                        buyApi api=retrofit.create(buyApi.class);
                        User user =Hawk.get("user");
                        Call<SuccessResponse> call=api.getResponse(seller.getAddress(),Integer.parseInt(seller.getQuantity()),user.getWallet_address(),resident_address.getText().toString());
                        call.enqueue(new Callback<SuccessResponse>() {
                            @Override
                            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                                SuccessResponse successResponse=response.body();
                                if(successResponse.getSuccess())
                                    Toast.makeText(mcx,"Ordered!!",Toast.LENGTH_LONG).show();

                                holder.escro_card.setVisibility(View.VISIBLE);
                                holder.no_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(mcx,"You will get back your Ethereum soon!!",Toast.LENGTH_LONG);
                                        holder.escro_card.setVisibility(View.GONE);
                                    }
                                });
                                holder.yes_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        holder.escro_card.setVisibility(View.GONE);
                                    }
                                });
                                dialog.dismiss();
                                //now we can do whatever we want with this list

                            }

                            @Override
                            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                                Toast.makeText(mcx, t.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });
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
        Button buy,yes_button,no_button;
        CardView escro_card;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewUsername=itemView.findViewById(R.id.username);
            textQuantity=itemView.findViewById(R.id.quantity);
            textViewPrice=itemView.findViewById(R.id.price);
            buy=itemView.findViewById(R.id.buy_button);
            escro_card=itemView.findViewById(R.id.escro_card);
            yes_button=itemView.findViewById(R.id.yes_button);
            no_button=itemView.findViewById(R.id.no_button);
        }
    }
}
