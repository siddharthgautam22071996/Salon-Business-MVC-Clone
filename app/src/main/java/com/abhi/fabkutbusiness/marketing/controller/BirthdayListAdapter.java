package com.abhi.fabkutbusiness.marketing.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.marketing.model.ResponseUpComingBirthday;
import com.abhi.fabkutbusiness.marketing.model.ResponseUpComingBirthdayData;
import com.abhi.fabkutbusiness.marketing.view.Marketing_Main;
import com.abhi.fabkutbusiness.marketing.view.RunPromotion;
import com.abhi.fabkutbusiness.marketing.view.SendPromotion;
import com.abhi.fabkutbusiness.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BirthdayListAdapter extends
        RecyclerView.Adapter<BirthdayListAdapter.MyViewHolder> {

    private ArrayList<ResponseUpComingBirthdayData> dataItem;
    private final Context mContext;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView user;
        TextView name ,dob;
        public Context mContext;



        public MyViewHolder(View view) {
            super(view);

            user= (CircleImageView) view.findViewById(R.id.photo);
            name = (TextView)view.findViewById(R.id.name);
            dob= (TextView)view.findViewById(R.id.date);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(context, RunPromotion.class).putExtra("flag","2")
                            .putExtra("name",dataItem.get(getLayoutPosition()).getEnduser_NAME() +" "+dataItem.get(getLayoutPosition()).getLname())
                            .putExtra("phone",dataItem.get(getLayoutPosition()).getContact_no())
                            .putExtra("enduser_code",dataItem.get(getLayoutPosition()).getEndusercode())
                            .putExtra("photo",""+dataItem.get(getLayoutPosition()).getProfie_pic())
                    );
                }
            });



        }
    }

    public BirthdayListAdapter(Context mContext, ArrayList<ResponseUpComingBirthdayData> dataItem) {
        this.dataItem = dataItem;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseUpComingBirthdayData  responseUpComingBirthdayData = dataItem.get(position);
        holder.name.setText(responseUpComingBirthdayData.getEnduser_NAME()+" "+responseUpComingBirthdayData.getLname() );
        holder.dob.setText(Utility.formatDateForDisplay(responseUpComingBirthdayData.getDate(),"yyyy-mm-dd",""+ Constants.displayDateFormat));
        if (!responseUpComingBirthdayData.getProfie_pic().equalsIgnoreCase("")) {
            Picasso.get()
                    .load(RetrofitClient.BASE_Image_URL_CUSTOMER + "" + responseUpComingBirthdayData.getProfie_pic())
                    .placeholder(R.drawable.dummy_profile)
                    .into(holder.user);

//              Picasso.with((Marketing_Main)mContext)
//                    .load(RetrofitClient.BASE_Image_URL_CUSTOMER + "" + responseUpComingBirthdayData.getProfie_pic())
//                    .placeholder(R.drawable.dummy_profile)
//                    .into(holder.user);
//
//
        }
        /*ResponseUserList c = dataItem.get(position);
        if (c.getId().equalsIgnoreCase("aria01")){

            Picasso.with(mContext)
                    .load(R.mipmap.bot2).fit()
                    .into(holder.user);

        }else {
            Picasso.with(mContext)
                    .load(c.getPic())
                    .placeholder(R.drawable.dummy_profile).fit()
                    .into(holder.user);
        }*/
         //holder.send.setText(""+c.getMsg() + " " );
         //holder.send.setText(""+c.getMsg() + " " );


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_birthday_list,parent, false);



        return new MyViewHolder(v);
    }


}