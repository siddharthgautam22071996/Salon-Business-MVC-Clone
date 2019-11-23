package com.abhi.fabkutbusiness.crm.controller;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistoryData;
import com.abhi.fabkutbusiness.retrofit.RetrofitClient;
import com.stfalcon.multiimageview.MultiImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class CrmHistoryAdapter extends
        RecyclerView.Adapter<CrmHistoryAdapter.MyViewHolder>{

    private ArrayList<ResponseCrmHistoryData> dataItem;
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String> services;




    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView serviceId,staffName,date,billAmount;
        public MultiImageView profile_img;
        public RecyclerView rvServices;



        public MyViewHolder(View view) {
            super(view);
            serviceId = (TextView)view. findViewById(R.id.tv_servicId);
            staffName = (TextView) view.findViewById(R.id.tv_StaffName);
            date = (TextView) view.findViewById(R.id.tv_date);
            profile_img = (MultiImageView) view.findViewById(R.id.profile_image);
            rvServices = (RecyclerView) view.findViewById(R.id.rv_services);
            billAmount = (TextView) view.findViewById(R.id.tv_bill_amount);

        }



    }


    public CrmHistoryAdapter(Context mContext,ArrayList<ResponseCrmHistoryData> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        ResponseCrmHistoryData responseCrmHistoryData = dataItem.get(position);

//        holder.servces.setText(""+responseCrmHistoryData.getService_name()+ " " );
        holder.staffName.setText(""+responseCrmHistoryData.getEmp_name() + " " );
        holder.serviceId.setText("Booking Id : "+responseCrmHistoryData.getBooking_id()+" ");
        holder.date.setText(""+ Utility.formatDateForDisplay(responseCrmHistoryData.getBookingdate(),"yyyy-MM-dd",""+ Constants.displayDateFormat)+ " " );
        if (responseCrmHistoryData.getAmount()!=null)
            holder.billAmount.setText(""+responseCrmHistoryData.getAmount() + " " );
        else
            holder.billAmount.setText("0");
        /*Picasso.with(mContext)
                .load(RetrofitClient.BASE_Image_URL_EMP+""+responseCrmHistoryData.getEmp_profile_image())
                .placeholder(R.drawable.dummy_profile)
                .into(holder.profile_img);*/

        System.out.println("jahfajkljj :"+responseCrmHistoryData.getService_name());
        if (responseCrmHistoryData.getService_name()!=null) {
            if (responseCrmHistoryData.getService_name().toString().contains(",")) {
                services = new ArrayList<String>(Arrays.asList(responseCrmHistoryData.getService_name().split(",")));

            } else {
                services = new ArrayList<>();
                services.add(responseCrmHistoryData.getService_name());
            }

            CrmHistoryServicesAdapter crmHistoryServicesAdapter = new CrmHistoryServicesAdapter(mContext, services);
            holder.rvServices.setAdapter(crmHistoryServicesAdapter);

        }


        holder.profile_img.setShape(MultiImageView.Shape.CIRCLE);
        if (responseCrmHistoryData.getEmp_profile_image()!=null) {
            if (responseCrmHistoryData.getEmp_profile_image().length() > 0) {
                ArrayList<String> photos = new ArrayList<String>(Arrays.asList(responseCrmHistoryData.getEmp_profile_image().split(",")));

                try {
                    for (int i = 0; i < photos.size(); i++) {
                        final URL url = new URL("" + RetrofitClient.BASE_Image_URL_EMP + "" + photos.get(i));
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    holder.profile_img.addImage(BitmapFactory.decodeStream(url.openStream()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();

                        //image.add(Utility.getImageBitmapFromURL(mContext, RetrofitClient.BASE_Image_URL_EMP + "" + photos.get(i)));
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
            } else {
                holder.profile_img.addImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dummy_profile));
            }
        }else {
            holder.profile_img.addImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dummy_profile));
        }


    }



    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crm_history,parent, false);

        return new MyViewHolder(v);
    }
}


