package com.abhi.fabkutbusiness.inventory.order.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.inventory.vendor.controller.PendingAdapter;
import com.abhi.fabkutbusiness.inventory.vendor.model.SalonData;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 8/25/2017.
 */

public class OrderRejection extends Fragment {

    View v;
    ArrayList<SalonData> dataModelList = new ArrayList<SalonData>();
    PendingAdapter itemsAdapter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate( R.layout.fragment_vendor_contract_details, container, false);

        RecyclerView lv=(RecyclerView) v.findViewById(R.id.lv);

        for (int i = 0; i < 50; i++) {
            //SalonData data = new SalonData();
            dataModelList.add(new SalonData("asvsv"+i, 123+i, 452+i, 1254+i));

        }

        // itemsAdapter = new PendingAdapter(getActivity(), dataModelList);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lv.setNestedScrollingEnabled(false);
        }


        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(llm);
        // specify an adapter (see also next example)
        PendingAdapter ca = new PendingAdapter(dataModelList);
        lv.setAdapter(ca);

        //lv.setAdapter(itemsAdapter);
/*
        lv.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, dataModelList));
        Helper.getListViewSize(lv);*/

        return v;
    }
}
