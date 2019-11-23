package com.abhi.fabkutbusiness.billing.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.billing.model.ResponsePomoCodeData;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.EditBookingServiceActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;
import java.util.List;


public class SelectPromoCodeAdapter extends RecyclerView.Adapter<SelectPromoCodeAdapter.MyViewHolder> {

    private Activity context;
    private List<ResponsePomoCodeData> offerDataList;
    private List<ResponsePomoCodeData> searchedRateDataList;
    private ArrayList<ResponsePomoCodeData> selectedOfferDataList;
    private int mLayoutResourceId;
    private Boolean isSelectedLayout;
    int flag = 0;
    private int totalTime = 0, totalCost = 0;

    public ArrayList<ResponsePomoCodeData> getSelectedRateDataList() {
        return selectedOfferDataList;
    }

    public void filterData(ArrayList<ResponsePomoCodeData> searchedData) {
        offerDataList.clear();
        offerDataList.addAll(searchedData);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode, tvdiscription;
        ImageView ivTick;
        View convertView;


        MyViewHolder(View view) {
            super(view);
            convertView = view;

            ivTick = (ImageView) view.findViewById(R.id.iv_service_check);
            tvCode = (TextView) view.findViewById(R.id.tv_service_name);
            tvdiscription = (TextView) view.findViewById(R.id.tv_service_price);

        }
    }


    public SelectPromoCodeAdapter(List<ResponsePomoCodeData> offerDataList, ArrayList<ResponsePomoCodeData> selectedData, int flag, int mLayoutResourceId, Boolean isSelectedLayout, Activity context) {
        this.offerDataList = offerDataList;
        this.mLayoutResourceId = mLayoutResourceId;
        this.isSelectedLayout = isSelectedLayout;
        this.context = context;
        selectedOfferDataList = selectedData;
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
        final ResponsePomoCodeData rateData = offerDataList.get(position);

        if (position == 0) {
            totalCost = 0;
            totalTime = 0;
        }

        if (!isSelectedLayout) {
            holder.ivTick.setVisibility(View.GONE);
            for (ResponsePomoCodeData data : selectedOfferDataList) {
                if (data.getFabkut_offer_code().equals(rateData.getFabkut_offer_code())) {
                    holder.ivTick.setVisibility(View.VISIBLE);
                }
            }
        }

        holder.tvCode.setText(rateData.getFabkut_offer_code());
        holder.tvdiscription.setTextColor(context.getResources().getColor(R.color.colorGrey));
        holder.tvdiscription.setText("" + rateData.getFabkut_offer_desc() + "");


        if (flag == 121){

        }else {
            holder.convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isSelectedLayout) {
                        if (holder.ivTick.getVisibility() == View.GONE) {
                            selectedOfferDataList.add(rateData);
                            holder.ivTick.setVisibility(View.VISIBLE);
                            //Toast.makeText(context, ""+searchedRateDataList, Toast.LENGTH_SHORT).show();
                        } else {
                            for (ResponsePomoCodeData data : selectedOfferDataList) {
                                if (data.getFabkut_offer_code().equals(rateData.getFabkut_offer_code())) {
                                    selectedOfferDataList.remove(data);
                                    break;
                                }
                            }
                            holder.ivTick.setVisibility(View.GONE);
                        }
                        // notifyItemChanged(position);
                    }
                }
            });
        }



        /*if (isSelectedLayout) {


            if (position == (rateDataList.size() - 1)) {
                if (context instanceof BookNowActivity)
                    ((BookNowActivity) context).setServiceTotal(totalCost, totalTime);
                else if (context instanceof EditBookingServiceActivity)
                    ((EditBookingServiceActivity) context).setServiceTotal(totalCost, totalTime);
            }
        }*/

    }


    @Override
    public int getItemCount() {
        return offerDataList.size();
    }


}