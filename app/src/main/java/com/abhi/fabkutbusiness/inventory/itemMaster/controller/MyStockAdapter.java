package com.abhi.fabkutbusiness.inventory.itemMaster.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.UnifiedNativeAdViewHolder;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseMyStockData;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.List;

/**
 * Created by abhi on 21/05/17.
 */

public class MyStockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MENU_ITEM_VIEW_TYPE = 0;
    // The unified native ad view type.
    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;
    private final List<Object> mRecyclerViewItems;
    Context context;

    public MyStockAdapter(Context context, List<Object> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;


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
                View v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_my_stock, viewGroup, false);

                return new MyViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                UnifiedNativeAd nativeAd = (UnifiedNativeAd) mRecyclerViewItems.get(position);
                populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder1).getAdView());
                break;
            case MENU_ITEM_VIEW_TYPE:
                // fall through
            default:
                MyViewHolder  holder= (MyViewHolder) holder1;

                final ResponseMyStockData responseMyStockData = (ResponseMyStockData)mRecyclerViewItems.get(position);

                holder.expiry.setText("" + responseMyStockData.getExpiryDate() + " ");
                holder.name.setText("" + responseMyStockData.getProduct_name() + " ");
                holder.qty.setText("" + responseMyStockData.getQty() + " ");
                holder.unit.setText("" + responseMyStockData.getUnit() + " ");
        }
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


    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }


    /**
     * Determines the view type for the given position.
     */
    @Override
    public int getItemViewType(int position) {

        Object recyclerViewItem = mRecyclerViewItems.get(position);
        if (recyclerViewItem instanceof UnifiedNativeAd) {
            return UNIFIED_NATIVE_AD_VIEW_TYPE;
        }
        return MENU_ITEM_VIEW_TYPE;
    }



//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder; // view lookup cache stored in tag
//
//        final ResponseMyStockData responseMyStockData = getItem(position);
//
//        viewHolder = new ViewHolder();
//        if (convertView == null) {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.item_my_stock, parent, false);
//            viewHolder.expiry = (TextView) convertView.findViewById(R.id.expiry);
//            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
//            viewHolder.qty = (TextView) convertView.findViewById(R.id.qty);
//            viewHolder.unit = (TextView) convertView.findViewById(R.id.unit);
//
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//
//        viewHolder.
//        //viewHolder.advance.setText("Rs. "+advancePayModel.getAdvance()+" ");
//
//
//        return convertView;
//    }
//
//    private static class ViewHolder {
//        TextView name, qty, unit, expiry;
//        LinearLayout llTotal;
//
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, qty, unit, expiry;
        public ImageView owner_image;

        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            expiry = view.findViewById(R.id.expiry);
            qty = view.findViewById(R.id.qty);
            unit = view.findViewById(R.id.unit);

        }


    }

}
