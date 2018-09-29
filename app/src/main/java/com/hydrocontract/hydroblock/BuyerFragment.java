package com.hydrocontract.hydroblock;
/*will be showing all the sellers available*/

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyerFragment extends Fragment {
    public BuyerFragment(){

    }
    Boolean success;
    String count,s1,s2,s3;
    int count_int;
    RecyclerView recyclerView;
    productAdapter adapter;
    List<seller> sellerList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_seller,container,false);
        sellerList=new ArrayList<>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        /* To fetch count from server--------------------*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(countApi.Base_url)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        countApi api = retrofit.create(countApi.class);
        Call<countPojo> call = api.getCount();
        call.enqueue(new Callback<countPojo>() {
            @Override
            public void onResponse(Call<countPojo> call, Response<countPojo> response) {
                countPojo countPojo=response.body();
                if(countPojo.getSuccess())
                {
                    success=countPojo.getSuccess();
                    count=countPojo.getCount();
                    System.out.println(count);
                    count_int=Integer.parseInt(count);
                    Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl(sellerApi.Base_Url)
                            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                            .build();
                    sellerApi api1 = retrofit1.create(sellerApi.class);
                    for(int i=count_int-1;i>=0;i--) {

                        Call<sellerPojo> call1 = api1.getSellers(i);
                        call1.enqueue(new Callback<sellerPojo>() {
                            @Override
                            public void onResponse(Call<sellerPojo> call, Response<sellerPojo> response) {
                                sellerPojo sellerPojo = response.body();
                                s1=sellerPojo.getUsername();
                                s2=sellerPojo.getSupply();
                                s3=sellerPojo.getWalletAddress();
                                System.out.println(s3);
                                sellerList.add(new seller(s1, s2, s3));
                                adapter=new productAdapter(getActivity().getApplicationContext(),sellerList);
                                recyclerView.setAdapter(adapter);

                            }

                            @Override
                            public void onFailure(Call<sellerPojo> call, Throwable t) {



                            }
                        });

                    }


                }
            }

            @Override
            public void onFailure(Call<countPojo> call, Throwable t) {

            }
        });

           // sellerList.add(new seller("kishore", count, count));

        //the no of sellers
        /* ----------------------------------count fetched*/
        /*to fetch sellers according to id*/


        /*to add at last*/

        return view;


    }

}

