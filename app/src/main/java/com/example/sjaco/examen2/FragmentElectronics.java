package com.example.sjaco.examen2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sjaco.examen2.beans.ItemProduct;
import com.example.sjaco.examen2.database.DataBaseHandler;
import com.example.sjaco.examen2.database.ItemProductControl;
import com.example.sjaco.examen2.tools.Constant;

import java.util.ArrayList;

public class FragmentElectronics extends Fragment {


    RecyclerView recyclerView;

    public FragmentElectronics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        ItemProductControl itemProductControl = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        ArrayList<ItemProduct> products = itemProductControl.getItemProductsByCategory(Constant.FRAGMENT_ELECTRONICS, dh);
        //products.add(new ItemProduct(6, "Refrigerador", "BestBuy", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_REFRIGERATOR));
        //products.add(new ItemProduct(7, "Micro", "Palacio de Hierro", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_MICRO));

        AdapterProduct adapterProduct = new AdapterProduct(Constant.FRAGMENT_ELECTRONICS, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
