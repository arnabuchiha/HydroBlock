package com.hydrocontract.hydroblock;
/*will be showing all the sellers available*/

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BuyerFragment extends Fragment {
    public BuyerFragment(){

    }
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
        sellerList.add(new seller("kishor","z405",500));
        sellerList.add(new seller("vinayak","m305",500));
        sellerList.add(new seller("aman","n305",500));
        sellerList.add(new seller("rajnish","z305",500));
        sellerList.add(new seller("kranti","t408",500));
        sellerList.add(new seller("akshat","r203",500));
        sellerList.add(new seller("abhishek","y204",500));
        sellerList.add(new seller("ananya","f401",500));
        sellerList.add(new seller("kishor","z305",500));
        adapter=new productAdapter(getActivity().getApplicationContext(),sellerList);
        recyclerView.setAdapter(adapter);
        return view;


    }

}

