package com.abhi.fabkutbusiness.booking.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.UnifiedNativeAdViewHolder;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.EditBookingServiceActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;
import java.util.List;


public class ServicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MENU_ITEM_VIEW_TYPE = 0;
    // The unified native ad view type.
    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;
    private final List<Object> mRecyclerViewItems;
    int flag = 0;
    private Activity context;
    private List<ResponseModelRateInfoData> rateDataList;
    private List<ResponseModelRateInfoData> searchedRateDataList;
    private ArrayList<ResponseModelRateInfoData> selectedRateDataList;
    private int mLayoutResourceId;
    private Boolean isSelectedLayout;
    private int totalTime = 0, totalCost = 0;


    public ServicesAdapter(List<Object> recyclerViewItems, ArrayList<ResponseModelRateInfoData> selectedData, int flag, int mLayoutResourceId, Boolean isSelectedLayout, Activity context) {
        this.rateDataList = rateDataList;
        this.mLayoutResourceId = mLayoutResourceId;
        this.isSelectedLayout = isSelectedLayout;
        this.context = context;
        selectedRateDataList = selectedData;
        totalCost = 0;
        totalTime = 0;
        this.flag = flag;
        this.mRecyclerViewItems = recyclerViewItems;

    }

    public ArrayList<ResponseModelRateInfoData> getSelectedRateDataList() {
        return selectedRateDataList;
    }

    public void filterData(ArrayList<ResponseModelRateInfoData> searchedData) {
        rateDataList.clear();
        rateDataList.addAll(searchedData);
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int position) {

        Object recyclerViewItem = mRecyclerViewItems.get(position);
        if (recyclerViewItem instanceof UnifiedNativeAd) {
            return UNIFIED_NATIVE_AD_VIEW_TYPE;
        }
        return MENU_ITEM_VIEW_TYPE;
    }






    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                View unifiedNativeLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.native_ads_small,
                        viewGroup, false);
                return new UnifiedNativeAdViewHolder(unifiedNativeLayoutView);
            case MENU_ITEM_VIEW_TYPE:
                // Fall through.
            default:
                View itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(mLayoutResourceId, viewGroup, false);

                return new MyViewHolder(itemView);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, final int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                UnifiedNativeAd nativeAd = (UnifiedNativeAd) mRecyclerViewItems.get(position);
                populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder1).getAdView());
                break;
            case MENU_ITEM_VIEW_TYPE:
                // fall through
            default:
                final MyViewHolder holder = (MyViewHolder) holder1;
//                final ResponseStayingBookingData upcomingGuestData = (ResponseStayingBookingData) mRecyclerViewItems.get(position);


                final ResponseModelRateInfoData rateData = (ResponseModelRateInfoData)mRecyclerViewItems.get(position);

                if (position == 0) {
                    totalCost = 0;
                    totalTime = 0;
                }

                if (!isSelectedLayout) {
                    holder.ivTick.setVisibility(View.GONE);
                    for (ResponseModelRateInfoData data : selectedRateDataList) {
                        if (data.getSub_service_name().equals(rateData.getSub_service_name())) {
                            holder.ivTick.setVisibility(View.VISIBLE);
                        }
                    }
                }

                holder.tvName.setText(rateData.getSub_service_name());
                holder.tvPrice.setText("" + rateData.getRate() + "rs");

                if (flag == 121) {

                } else {
                    holder.convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!isSelectedLayout) {
                                if (holder.ivTick.getVisibility() == View.GONE) {
                                    selectedRateDataList.add(rateData);
                                    holder.ivTick.setVisibility(View.VISIBLE);
                                    //Toast.makeText(context, ""+searchedRateDataList, Toast.LENGTH_SHORT).show();
                                } else {
                                    for (ResponseModelRateInfoData data : selectedRateDataList) {
                                        if (data.getSub_service_name().equals(rateData.getSub_service_name())) {
                                            selectedRateDataList.remove(data);
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


                if (isSelectedLayout) {

                    totalCost += Integer.parseInt(rateData.getRate());
                    totalTime += Integer.parseInt(rateData.getEta());

                    if (position == (mRecyclerViewItems.size() - 1)) {
                        if (context instanceof BookNowActivity)
                            ((BookNowActivity) context).setServiceTotal(totalCost, totalTime);
                        else if (context instanceof EditBookingServiceActivity)
                            ((EditBookingServiceActivity) context).setServiceTotal(totalCost, totalTime);
                    }
                }
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                      UnifiedNativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        NativeAd.Image icon = nativeAd.getIcon();

        if (icon == null) {
            adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
//            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
//            adView.getPriceView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
//            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
//            adView.getStoreView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
//            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
//            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAd);
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


}