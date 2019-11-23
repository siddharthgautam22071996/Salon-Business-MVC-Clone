package com.abhi.fabkutbusiness.marketing.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotion;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotionData;
import com.abhi.fabkutbusiness.marketing.view.AddOffer;
import com.abhi.fabkutbusiness.marketing.view.RunPromotion;
import com.abhi.fabkutbusiness.marketing.view.SendPromotion;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OffersListAdapter extends
        RecyclerView.Adapter<OffersListAdapter.MyViewHolder> implements RetrofitApi.ResponseListener{

    private final String viewStaus;
    private ArrayList<ResponseCreatePromotionData> dataItem;
    private final Context mContext;

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        ResponseCreatePromotion responseCreatePromotion = (ResponseCreatePromotion) obj;
        if (responseCreatePromotion.getSTATUS().equalsIgnoreCase("200")){
            Utility.showToast(((RunPromotion)mContext),responseCreatePromotion.getMESSAGE());
            ((RunPromotion)mContext).startActivity(new Intent((RunPromotion)mContext,RunPromotion.class));
            ((RunPromotion) mContext).finish();

        }
    }

    @Override
    public void _onNext1(Object obj) {

    }

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView user;
        public Context mContext;
        public ImageView send,edit,sendDirect;
        public TextView title,mini,startDate,endDate,code,price,type;



        public MyViewHolder(View view) {
            super(view);

            //user= (CircleImageView) view.findViewById(R.id.iv_user);
            title = (TextView)view.findViewById(R.id.title);
            code = (TextView)view.findViewById(R.id.tv_code);
            price = (TextView)view.findViewById(R.id.tv_price);
            type = (TextView)view.findViewById(R.id.type);
            mini = (TextView)view.findViewById(R.id.mini);
            startDate = (TextView)view.findViewById(R.id.startDate);
            endDate = (TextView)view.findViewById(R.id.endDate);
            send = (ImageView)view.findViewById(R.id.send);
            edit = (ImageView)view.findViewById(R.id.editMenu);
            sendDirect = (ImageView)view.findViewById(R.id.sendDirect);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final Context context = view.getContext();
                    PopupMenu popupMenu = new PopupMenu(view.getContext(),edit);
                    popupMenu.getMenuInflater().inflate(R.menu.pop_menu_edit, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_edit:
                                    Intent intent = new Intent(view.getContext(),AddOffer.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) dataItem);
                                    bundle.putInt("pos",getLayoutPosition());
                                    bundle.putBoolean("isEdit",true);
                                    intent.putExtras(bundle);
                                    context.startActivity(intent);
                                    break;
                                case R.id.menu_delete:
                                    delete(context,getLayoutPosition());

                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context= view.getContext();
                    context.startActivity(new Intent(context, SendPromotion.class).putExtra("id",dataItem.get(getLayoutPosition()).getFabkut_offer_id()));
                }
            });

            sendDirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog(view.getContext(),getLayoutPosition());
                }
            });




        }
    }

    public  void showAlertDialog(final Context context , final int pos) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();

        dialog.setTitle("Alert");
        dialog.setMessage("Do You Want to send "+dataItem.get(pos).getFabkut_offer_name() +"");
        dialog.setCancelable(true);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sendOffer(pos,context);
                dialog.dismiss();
            }

        });

        dialog.show();
    }

    private void sendOffer(int pos, Context context) {
        RetrofitApi.getInstance().runOfferApi((Activity) context,this,
                "insert",
                ""+dataItem.get(pos).getFabkut_offer_id(),
                "1",
                ""+((RunPromotion)context).getCustomerCode(),
                "0",
                ""+Utility.getPreferences(context, Constants.keySalonBusinessId));

    }

    private void delete(Context context ,int pos) {
        RetrofitApi.getInstance().upCreateOfferApi(((Activity)context),this,
                "delete",
                ""+dataItem.get(pos).getFabkut_offer_id(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

    }

    public OffersListAdapter(Context mContext, ArrayList<ResponseCreatePromotionData> dataItem,String viewStaus) {
        this.dataItem = dataItem;
        this.mContext = mContext;
        this.viewStaus = viewStaus;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ResponseCreatePromotionData c = dataItem.get(position);

        switch (viewStaus){
            case "1":
                holder.send.setVisibility(View.VISIBLE);
                holder.edit.setVisibility(View.GONE);
                holder.sendDirect.setVisibility(View.GONE);
                break;
            case "2":
                holder.send.setVisibility(View.GONE);
                holder.sendDirect.setVisibility(View.VISIBLE);
                holder.edit.setVisibility(View.GONE);
                break;
            case "0":
                holder.send.setVisibility(View.GONE);
                holder.edit.setVisibility(View.VISIBLE);
                holder.sendDirect.setVisibility(View.GONE);
                break;

        }

        holder.code.setText(c.getFabkut_offer_code());
        holder.title.setText(c.getFabkut_offer_name());
        holder.mini.setText("Applicable On Minimum Value : "+c.getFabkut_offer_price());
        holder.startDate.setText("Start Date : "+ Utility.formatDateForDisplay(c.getFabkut_offer_start(),"YYYY-mm-dd hh:mm",""+ Constants.displayDateFormatWithTime));
        holder.endDate.setText("End Date :"+Utility.formatDateForDisplay(c.getFabkut_offer_end(),"YYYY-mm-dd hh:mm",""+ Constants.displayDateFormatWithTime));

        if (c.getFabkut_offer_type_id().equalsIgnoreCase("1")){
            holder.type.setText("Coupan");
            holder.price.setText(c.getFabkut_offer_disc_perc() +" INR");
        } else if (c.getFabkut_offer_type_id().equalsIgnoreCase("2")) {
            holder.type.setText("Offer");
            holder.price.setText(c.getFabkut_offer_disc_perc() +" %");
        }

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offers,parent, false);



        return new MyViewHolder(v);
    }


}