package com.hydrocontract.hydroblock;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class sell extends Fragment {
    int quantity;
    String s1,s2,s3,s4;
    public sell(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sell, container, false);
        Button sell=view.findViewById(R.id.sell_button);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hawk.init(getContext()).build();
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_sell);

                final int max = 130;
                final int min = 10;
                final int total = max - min;

                final FluidSlider slider = dialog.findViewById(R.id.slider);
                slider.setBeginTrackingListener(new Function0<Unit>() {
                    @Override
                    public Unit invoke() {
                        return Unit.INSTANCE;
                    }
                });

                slider.setEndTrackingListener(new Function0<Unit>() {
                    @Override
                    public Unit invoke() {
                        return Unit.INSTANCE;
                    }
                });

                // Java 8 lambda
                slider.setPositionListener(pos -> {
                    final String value = String.valueOf((int) (min + total * pos));
                    slider.setBubbleText(value);
                    quantity=Integer.parseInt(value);
                    return Unit.INSTANCE;
                });
                Button sell=dialog.findViewById(R.id.sell_button);
                sell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(sell_api.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                                .build();

                        sell_api api=retrofit.create(sell_api.class);
                        User user=new User();
                        user=Hawk.get("user");
                        if(Hawk.contains("user")){
                            Toast.makeText(getContext(),"No value",Toast.LENGTH_LONG).show();
                        }
                        System.out.println(user.getWallet_address());
                        Call<SuccessResponse> call=api.getResponse(String.valueOf(quantity),user.getWallet_address());
                        call.enqueue(new Callback<SuccessResponse>() {
                            @Override
                            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                                SuccessResponse successResponse=response.body();
                                if(successResponse.getSuccess())
                                    Toast.makeText(getContext(),"Item successfully added for sale!!",Toast.LENGTH_LONG).show();
                                //now we can do whatever we want with this list

                            }

                            @Override
                            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                System.out.println(quantity);
                slider.setPosition(0.3f);
                slider.setStartText(String.valueOf(min));
                slider.setEndText(String.valueOf(max));
                dialog.show();

            }
        });
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(buyersApi.Base_Url)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        buyersApi api1=retrofit1.create(buyersApi.class);
        Call<BuyersResponse> call=api1.getResponse();
        call.enqueue(new Callback<BuyersResponse>() {
            @Override
            public void onResponse(Call<BuyersResponse> call, Response<BuyersResponse> response) {

                CardView buyer_card=view.findViewById(R.id.card_buyers);
                buyer_card.setVisibility(View.VISIBLE);
                BuyersResponse BuyersResponse=response.body();
                s1=BuyersResponse.getResidentAddress();
                s2=BuyersResponse.getSupply();
                s3=BuyersResponse.getUsername();
                s4=BuyersResponse.getWalletAddress();
                TextView username=view.findViewById(R.id.username);
                TextView quantity=view.findViewById(R.id.quantity);
                TextView total_price=view.findViewById(R.id.price);
                TextView address=view.findViewById(R.id.address);
                username.setText(s3);
                quantity.setText(s2);
                total_price.setText("Rs."+String.valueOf(Integer.parseInt(s2)*5));
                address.setText(s1);

            }

            @Override
            public void onFailure(Call<BuyersResponse> call, Throwable t) {
                //Toast.makeText(getContext(),"No buyers",Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }
}
