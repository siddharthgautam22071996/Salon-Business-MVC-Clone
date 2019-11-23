package com.abhi.fabkutbusiness.booking.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.UnifiedNativeAdViewHolder;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataAdapter extends ArrayAdapter<ResponseModelCustomerData> {


    private final Context mContext;
    private final List<ResponseModelCustomerData> responseModelCustomerDatas;
    private final List<ResponseModelCustomerData> responseModelCustomerDatas_All;
    private final List<ResponseModelCustomerData> responseModelCustomerDatas_Suggestion;
    private final int mLayoutResourceId;


    public CustomerDataAdapter(Context context, int resource, List<ResponseModelCustomerData> responseModelCustomerDatas) {
        super(context, resource, responseModelCustomerDatas);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.responseModelCustomerDatas = new ArrayList<>(responseModelCustomerDatas);
        this.responseModelCustomerDatas_All = new ArrayList<>(responseModelCustomerDatas);
        this.responseModelCustomerDatas_Suggestion = new ArrayList<>();
    }





//    private void loadNativeAds(LinearLayout linearLayout) {
//
//        AdLoader.Builder builder = new AdLoader.Builder(getContext(), "ca-app-pub-3940256099942544/2247696110");
//        adLoader = builder.forUnifiedNativeAd(
//                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                    @Override
//                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                        // A native ad loaded successfully, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
////                        mNativeAds.add(unifiedNativeAd);
//                        if (!adLoader.isLoading()) {
//
//
////                            insertAdsInMenuItems();
//                        }
//                    }
//                }).withAdListener(
//                new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(int errorCode) {
//                        // A native ad failed to load, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
//                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
//                                + " load another.");
//                        if (!adLoader.isLoading()) {
////                            insertAdsInMenuItems();
//                        }
//                    }
//                }).build();
//
//        // Load the Native ads.
//        adLoader.loadAd(new AdRequest.Builder().build());
//
//    }


    public int getCount() {
        return responseModelCustomerDatas.size();
    }

    public ResponseModelCustomerData getItem(int position) {
        return responseModelCustomerDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }



            final ResponseModelCustomerData responseModelCustomerData = getItem(position);


            TextView name = (TextView) convertView.findViewById(R.id.textView);
            name.setText("" + responseModelCustomerData.getEndUser_FirstName() + " (" + responseModelCustomerData.getContact_no() + ")");


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity activity = (NavigationMainActivity) mContext;

                    String seatNum = Utility.getEmptySeatNum(activity);

                    activity.startActivity(new Intent(activity, BookNowActivity.class)
                            .putExtra("data", responseModelCustomerData)
                            .putExtra("isEdit", false));
                    //    ((BookNowActivity) mContext).setData(responseModelCustomerData);

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                //return ((ResponseModelCustomerData) resultValue).getEndUser_FirstName();

                return "";
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    responseModelCustomerDatas_Suggestion.clear();
                    for (ResponseModelCustomerData responseModelCustomerData : responseModelCustomerDatas_All) {
                        if (responseModelCustomerData.getEndUser_FirstName().toLowerCase().startsWith(constraint.toString().toLowerCase())
                                || responseModelCustomerData.getContact_no().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            responseModelCustomerDatas_Suggestion.add(responseModelCustomerData);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = responseModelCustomerDatas_Suggestion;
                    filterResults.count = responseModelCustomerDatas_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                responseModelCustomerDatas.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using responseModelCustomerDatas.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof ResponseModelCustomerData) {
                            responseModelCustomerDatas.add((ResponseModelCustomerData) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in

                    responseModelCustomerDatas.addAll(responseModelCustomerDatas_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}