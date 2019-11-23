package com.abhi.fabkutbusiness.report.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.report.model.modelBookingDataForReport;
import com.abhi.fabkutbusiness.report.view.BookingHistoryActivity;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by abhi on 21/05/17.
 */




public class StatsEmpList extends RecyclerView.Adapter<StatsEmpList.MyViewHolder> {

    private List<modelBookingDataForReport> rateDataList;
    private int mLayoutResourceId;
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  name, contact;
        ImageView profile;


        MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tv_empName);
            contact = (TextView) view.findViewById(R.id.tv_empNo);
            profile = (ImageView) view.findViewById(R.id.iv_profile_image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(mContext, BookingHistoryActivity.class);
                    intent.putExtra("emp", rateDataList.get(getLayoutPosition()).getEmployee());
                    intent.putExtra("date",rateDataList.get(getLayoutPosition()).getDate());
                    mContext.startActivity(intent);
                }
            });




        }
    }


    public StatsEmpList(Context context, List<modelBookingDataForReport> rateDataList, int mLayoutResourceId) {
        this.mContext = context;
        this.rateDataList = rateDataList;
        this.mLayoutResourceId = mLayoutResourceId;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);


        return new  MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final modelBookingDataForReport responseModelEmployeeData = rateDataList.get(position);
        holder.contact.setText(""+responseModelEmployeeData.getTotal_revenue());
        holder.name.setText(responseModelEmployeeData.getEmployee());
        Picasso.get()
                .load(responseModelEmployeeData.getPhoto())
                .placeholder(R.drawable.dummy_profile)
                .into(holder.profile);
//     Picasso.with(mContext)
//                .load(responseModelEmployeeData.getPhoto())
//                .placeholder(R.drawable.dummy_profile)
//                .into(holder.profile);


    }






    @Override
    public int getItemCount() {
        return rateDataList.size();
    }


}

