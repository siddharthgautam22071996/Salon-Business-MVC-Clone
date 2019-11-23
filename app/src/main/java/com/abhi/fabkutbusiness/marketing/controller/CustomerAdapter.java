package com.abhi.fabkutbusiness.marketing.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.EditBookingServiceActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;
import java.util.List;


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Activity context;
    private List<ResponseModelCustomerData> customerData;
    private List<ResponseModelCustomerData> searchedCustomerDataList;
    private ArrayList<ResponseModelCustomerData> selectedCustomerDataList= new ArrayList<>();
    private int mLayoutResourceId;
    private Boolean isSelectedLayout;
    int flag = 0;
    private int totalTime = 0, totalCost = 0;

    public ArrayList<ResponseModelCustomerData> getSelectedCustomerDataList() {
        return selectedCustomerDataList;
    }

    public void filterData(ArrayList<ResponseModelCustomerData> searchedData) {
        customerData.clear();
        customerData.addAll(searchedData);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView ivTick;
        View convertView;


        MyViewHolder(View view) {
            super(view);
            convertView = view;

            ivTick = (ImageView) view.findViewById(R.id.iv_service_check);
            tvName = (TextView) view.findViewById(R.id.tv_service_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_service_price);

        }
    }


    public CustomerAdapter(List<ResponseModelCustomerData> customerData, int flag, int mLayoutResourceId, Boolean isSelectedLayout, Activity context) {
        this.customerData = customerData;
        this.mLayoutResourceId = mLayoutResourceId;
        this.isSelectedLayout = isSelectedLayout;
        this.context = context;
        totalCost = 0;
        totalTime = 0;
        this.flag = flag;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ResponseModelCustomerData data = customerData.get(position);
        holder.tvName.setText(data.getEndUser_FirstName());
        holder.tvPrice.setText("" + data.getContact_no() + "");


            holder.convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        if (holder.ivTick.getVisibility() == View.GONE) {
                            selectedCustomerDataList.add(data);
                            holder.ivTick.setVisibility(View.VISIBLE);
                            //Toast.makeText(context, ""+searchedRateDataList, Toast.LENGTH_SHORT).show();
                        } else {
                            for (ResponseModelCustomerData data : selectedCustomerDataList) {
                                if (data.getEndUser_FirstName().equals(data.getEndUser_FirstName())) {
                                    selectedCustomerDataList.remove(data);
                                    break;
                                }
                            }
                            holder.ivTick.setVisibility(View.GONE);
                        }
                        // notifyItemChanged(position);

                }
            });





    }


    @Override
    public int getItemCount() {
        return customerData.size();
    }


}