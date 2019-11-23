package com.abhi.fabkutbusiness.inventory.order.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.inventory.vendor.model.SalonData;

import java.util.ArrayList;

public class RecevieAdapter extends
        RecyclerView.Adapter<RecevieAdapter.MyViewHolder> {

    private ArrayList<SalonData> dataItem;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,contact,code,tin;

        public MyViewHolder(View view) {
            super(view);
            code = (TextView) view.findViewById(R.id.code);
            name = (TextView) view.findViewById( R.id.vendor_name);
            contact = (TextView) view.findViewById(R.id.contact);
            tin = (TextView) view.findViewById(R.id.tinNo);
        }
    }

    public RecevieAdapter(ArrayList<SalonData> dataItem) {
        this.dataItem = dataItem;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            SalonData c = dataItem.get(position);
            holder.name.setText(c.getName());
            holder.contact.setText(String.valueOf(c.getContact()));
            holder.code.setText(String.valueOf(c.getCode()));
            holder.tin.setText(String.valueOf(c.getTin()));

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_vendor,parent, false);
        return new MyViewHolder(v);
    }
}