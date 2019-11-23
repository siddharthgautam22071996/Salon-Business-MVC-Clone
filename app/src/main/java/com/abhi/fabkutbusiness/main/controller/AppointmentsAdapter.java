package com.abhi.fabkutbusiness.main.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.UnifiedNativeAdViewHolder;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abhi on 21/05/17.
 */

public class AppointmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RetrofitApi.ResponseListener {

    private static final int MENU_ITEM_VIEW_TYPE = 0;
    // The unified native ad view type.
    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;
    private final List<Object> mRecyclerViewItems;
    Context context;
    int temp_pos;


    public AppointmentsAdapter(Context context, List<Object> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;

    }


    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder1, final int position) {


        int viewType = getItemViewType(position);
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                UnifiedNativeAd nativeAd = (UnifiedNativeAd) mRecyclerViewItems.get(position);
                populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder1).getAdView());
                break;
            case MENU_ITEM_VIEW_TYPE:
                // fall through
            default:
                MyViewHolder viewHolder = (MyViewHolder) holder1;

                final ResponseModelAppointmentsData appointmentsData =(ResponseModelAppointmentsData) mRecyclerViewItems.get(position);

                viewHolder.name.setText("" + appointmentsData.getCustomerEndUser_FirstName() + " " + appointmentsData.getCustomerEndUser_LastName());

                viewHolder.status.setText("Stylist :  " + appointmentsData.getEmployee().getEmp_name() + " ");
                viewHolder.tvDate.setText(" ( " + appointmentsData.getBookingDate() + "  ) ");


                if (appointmentsData.getServices() != null)
                    viewHolder.description.setText(Utility.getFormattedServiceList(appointmentsData.getServices()));

                if (appointmentsData.getSlots() != null)
                    viewHolder.time.setText("" + Utility.getFormattedSlotTime(appointmentsData.getSlots(), appointmentsData.getBookingDate()) + " ");

                viewHolder.num.setText("" + appointmentsData.getCustomerMobile() + " ");
                //Toast.makeText(context, ""+appointmentsData.getCustomerProfileImage(), Toast.LENGTH_SHORT).show();


//        Picasso.with(((NavigationMainActivity) context))
//                .load(appointmentsData.getCustomerProfileImage())
//                .placeholder(R.drawable.dummy_profile)
//                .into(viewHolder.ivCustomerPic);
//

                Picasso.get()
                        .load(appointmentsData.getCustomerProfileImage())
                        .placeholder(R.drawable.dummy_profile)
                        .into(viewHolder.ivCustomerPic);

                if (position == mRecyclerViewItems.size() - 1)
                    viewHolder.dummyFooter.setVisibility(View.INVISIBLE);
                else
                    viewHolder.dummyFooter.setVisibility(View.GONE);


                viewHolder.tvBookNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context, ""+appointmentsData.getBookingDate(), Toast.LENGTH_SHORT).show();
                        ((NavigationMainActivity) context).bookWaitingCustomer(position);

                    }
                });

                viewHolder.tvReschedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((NavigationMainActivity) context).rescheduleWaitingCustomer(position);

                    }
                });

                viewHolder.tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(((NavigationMainActivity) context), position, appointmentsData, appointmentsData.getCustomerEndUser_FirstName() + " " + appointmentsData.getCustomerEndUser_LastName());

                    }
                });

                viewHolder.num.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + appointmentsData.getCustomerMobile()));
                        context.startActivity(intent);
                    }
                });


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
                        .inflate(R.layout.item_appointments, viewGroup, false);

                return new MyViewHolder(v);
        }
    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder; // view lookup cache stored in tag
//
//        viewHolder = new ViewHolder();
//        if (convertView == null) {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.item_appointments, parent, false);
//
//
//            convertView.setTag(viewHolder);
//
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//    }

    private void showDialog(final Activity activity, final int pos, final ResponseModelAppointmentsData appointmentsData, String name) {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View deleteDialogView = factory.inflate(R.layout.booking_cancel_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(activity).create();
        deleteDialog.setView(deleteDialogView);

        TextView tvName = (TextView) deleteDialogView.findViewById(R.id.tv_cancel_name);
        tvName.setText(name);

        final EditText etReason = (EditText) deleteDialogView.findViewById(R.id.et_cancel_reason);


        TextView tvSubmit = (TextView) deleteDialogView.findViewById(R.id.tv_cancel_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strReason = etReason.getText().toString().trim();
                if (strReason.length() > 0) {

                    appointmentsData.setCancelReason(strReason);
                    synCancelApiCall(appointmentsData);
                    temp_pos = pos;
                    deleteDialog.dismiss();
                    mRecyclerViewItems.remove(pos);

                } else {
                    Utility.showToast(context, "Please enter the reason for cancellation.");
                }


            }
        });

        deleteDialog.show();

    }

    private void synCancelApiCall(final ResponseModelAppointmentsData appointmentsData) {

        RetrofitApi.getInstance().syncCancelBookingApiMethod(((NavigationMainActivity) context), this, appointmentsData, Utility.getPreferences(context, Constants.keySalonBusinessId));
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseModelAppointmentsData) {
            ResponseModelAppointmentsData appointmentsData = (ResponseModelAppointmentsData) obj;
            appointmentsData.setSync(true);
            Utility.cancelBooking(((NavigationMainActivity) context), appointmentsData, temp_pos);
            //appointmentsData.sy

            ((NavigationMainActivity) context).refreshAppointmentData();

        }

    }

    @Override
    public void _onNext1(Object obj) {

        if (obj instanceof ResponseModelAppointmentsData) {
            ResponseModelAppointmentsData appointmentsData = (ResponseModelAppointmentsData) obj;
            appointmentsData.setSync(false);
            Utility.cancelBooking(((NavigationMainActivity) context), appointmentsData, temp_pos);
            ((NavigationMainActivity) context).refreshAppointmentData();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, status, time, num, tvCancel, tvReschedule, tvBookNow, tvDate;
        LinearLayout dummyFooter;
        ImageView ivCustomerPic;

        public MyViewHolder(View convertView) {
            super(convertView);
            name = (TextView) convertView.findViewById(R.id.tv_name);
            description = (TextView) convertView.findViewById(R.id.tv_description);

            status = (TextView) convertView.findViewById(R.id.tv_emp);
            tvDate = (TextView) convertView.findViewById(R.id.tv_status);
            time = (TextView) convertView.findViewById(R.id.tv_time);
            num = (TextView) convertView.findViewById(R.id.tv_num);
            ivCustomerPic = (ImageView) convertView.findViewById(R.id.ivCustomerPic);
            tvBookNow = (TextView) convertView.findViewById(R.id.tv_book_now);
            tvCancel = (TextView) convertView.findViewById(R.id.tv_cancel_booking);
            tvReschedule = (TextView) convertView.findViewById(R.id.tv_reschedule_booking);
            dummyFooter = (LinearLayout) convertView.findViewById(R.id.dummy_footer);


        }


    }


}
