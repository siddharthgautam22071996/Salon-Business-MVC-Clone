package com.abhi.fabkutbusiness.crm.controller;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.model.Crmmodel;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmListData;
import com.abhi.fabkutbusiness.crm.view.CrmHistory;
import com.abhi.fabkutbusiness.crm.view.CrmHistory1;
import com.abhi.fabkutbusiness.crm.view.CrmList;
import com.abhi.fabkutbusiness.crm.view.CrmTab;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CrmAdapter extends
        RecyclerView.Adapter<CrmAdapter.MyViewHolder>{

    private ArrayList<ResponseCrmListData> dataItem;
    private Context mContext;
    private LayoutInflater inflater;




    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView name,contact,profile,email;
        public ImageView profile_pic;
        public RelativeLayout editProfil;
        public ProgressBar profileBar;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_name);
            contact = (TextView) view.findViewById(R.id.crmlist_mobileNo_);
            profile = (TextView) view.findViewById(R.id.tv_profile);
            profileBar = (ProgressBar) view.findViewById(R.id.prog_profile);
            email= (TextView) view.findViewById(R.id.tv_email);
            profile_pic = (ImageView)view.findViewById(R.id.profile_image);
            editProfil = (RelativeLayout)view.findViewById(R.id.rl_editProfile);
            editProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    if (Utility.isInternetConnected(((CrmList)context))) {
                    Intent intent = new Intent(context, CrmTab.class);
                    intent.putExtra("item",dataItem.get(getLayoutPosition()).getEndusercode());
                    intent.putExtra("name",dataItem.get(getLayoutPosition()).getEnduser_name());
                    context.startActivity(intent);
                    }



                }
            });


            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    //Intent intent = new Intent(Intent.ACTION_CALL);
                   // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+dataItem.get(getLayoutPosition()).getContact_no()));

                    //intent.setData(Uri.parse("tel:"+dataItem.get(getLayoutPosition()).getContact_no()));
                    if (ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                    try {
                        Intent my_callIntent = new Intent(Intent.ACTION_CALL);
                        my_callIntent.setData(Uri.parse("tel:"+dataItem.get(getLayoutPosition()).getContact_no()));
                        //here the word 'tel' is important for making a call...
                        context.startActivity(my_callIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(mContext, "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    //context.startActivity(intent);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    if (Utility.isInternetConnected(((CrmList)context))) {
                        Intent intent = new Intent(context, CrmHistory1.class);
                        intent.putExtra("item", dataItem.get(getLayoutPosition()).getEndusercode());
                        intent.putExtra("photo", dataItem.get(getLayoutPosition()).getProfie_pic());
                        intent.putExtra("name", dataItem.get(getLayoutPosition()).getEnduser_name());
                        intent.putExtra("total_visit", dataItem.get(getLayoutPosition()).getTotalVisit());
                        intent.putExtra("revenue", dataItem.get(getLayoutPosition()).getTotal_Revinew());
                        context.startActivity(intent);
                    }

                }
            });

        }



    }


    public CrmAdapter(Context mContext, ArrayList<ResponseCrmListData> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseCrmListData c = dataItem.get(position);
        holder.name.setText(c.getEnduser_name());
        holder.contact.setText(c.getContact_no());
        holder.email.setText(c.getEmail());
        holder.profile.setText(""+c.getProfile_Comp_total()+" %");
        holder.profileBar.setProgress(c.getProfile_Comp_total());

        Picasso.get()
                .load(c.getProfie_pic())
                .placeholder(R.drawable.dummy_profile)
                .into(holder.profile_pic);

//
//        Picasso.with(mContext)
//                .load(c.getProfie_pic())
//                .placeholder(R.drawable.dummy_profile)
//                .into(holder.profile_pic);
//


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crm_list_item,parent, false);

        return new MyViewHolder(v);
    }
}



