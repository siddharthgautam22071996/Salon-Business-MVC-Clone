package com.abhi.fabkutbusiness.inventory.order.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SIDDHARTH on 8/25/2017.
 */

public class OrderCreation extends Fragment  {

    View v;
    Spinner poType,selectVendor,itemType,itemCategory,unit,unitValue,item;
    List<String> brand = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate( R.layout.fragment_order_creation, container, false);
        findByViewId();
        return v;
    }

    private void findByViewId() {
        poType =(Spinner)v.findViewById(R.id.spinner_poType);
        //poType.setOnItemSelectedListener(this);
        selectVendor =(Spinner)v.findViewById(R.id.spinner_selectVendor);
        //selectVendor.setOnItemSelectedListener(this);
        itemType =(Spinner)v.findViewById(R.id.spinner_itemType);
        //itemType.setOnItemSelectedListener(this);
        itemCategory =(Spinner)v.findViewById(R.id.spinner_itemCategory);
        //itemCategory.setOnItemSelectedListener(this);
        unit =(Spinner)v.findViewById(R.id.spinner_unit);
        //unit.setOnItemSelectedListener(this);
        item =(Spinner)v.findViewById(R.id.spinner_item);
        //item.setOnItemSelectedListener(this);
        unitValue =(Spinner)v.findViewById(R.id.spinner_unitValue);
        //unitValue.setOnItemSelectedListener(this);
        addnone();

    }

    private void addnone() {

        /*for (int i=0;i<responseItemBrand.getData().size();i++){
            brand.add(responseItemBrand.getData().get(i).getBrandName());
        }*/
        brand.add("Select");

        Utility.spinner(getActivity(),selectVendor,brand);
        Utility.spinner(getActivity(),itemType,brand);
        Utility.spinner(getActivity(),itemCategory,brand);
        Utility.spinner(getActivity(),unitValue,brand);
        Utility.spinner(getActivity(),item,brand);
    }

   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.spinner_poType:
                break;
            case R.id.spinner_selectVendor:
                break;
            case R.id.spinner_itemType:
                break;
            case R.id.spinner_itemCategory:
                break;
            case R.id.spinner_unitValue:
                break;
            case R.id.spinner_unit:
                break;
            case R.id.spinner_item:
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}


