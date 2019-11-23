package com.abhi.fabkutbusiness.crm.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistoryData;
import com.abhi.fabkutbusiness.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CrmHistoryServicesAdapter extends
        RecyclerView.Adapter<CrmHistoryServicesAdapter.MyViewHolder>{

    private List<String> dataItem;
    private Context mContext;
    private LayoutInflater inflater;




    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView serviceId,staffName,servces,date,billAmount;
        public ImageView profile_img;



        public MyViewHolder(View view) {
            super(view);
            serviceId = (TextView)view. findViewById(R.id.service);

        }



    }


    public CrmHistoryServicesAdapter(Context mContext, ArrayList<String> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String service = dataItem.get(position);
        holder.serviceId.setText(""+service+ " " );


    }



    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_services,parent, false);

        return new MyViewHolder(v);
    }
}


