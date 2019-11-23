package com.abhi.fabkutbusiness.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by siddharthgautam on 21/08/18.
 */

public class BusinessSyncData implements RetrofitApi.ResponseListener{
    ArrayList<ResponseModelCustomerData> customer_data;
    int pos;
    Activity activity;
    Boolean isLogout;
    ResponseModelAppointments booking_data;

    public BusinessSyncData(Activity activity ,Boolean isLogout) {
        this.activity = activity;
        this.isLogout = isLogout;
    }

    public void syncCustomer(){
        customer_data =Utility.getResponseModelNotSyncCustomer(activity, Constants.keySalonNotSYncCustomerData).getData();

        for (int i = 0 ; i <customer_data.size(); i++){
            RetrofitApi.getInstance().addCustomerApiMethod(activity, this, customer_data.get(i));
            pos =i;
        }

        syncBooking();

    }


    public void syncBooking(){
        pos=0;
        booking_data  = Utility.getNotSynResponseModelAppointments(activity,Constants.keySalonNotSynAppointmentsData);
        if (booking_data !=null) {
            for (int j = 0; j < booking_data.getData().size(); j++) {
                //String currDate = Utility.getCurrentDate(Constants.displayDateFormat);
                pos = j;
                RetrofitApi.getInstance().finalSyncBookingApiMethod(activity, this,
                        booking_data.getData().get(j), 1, "" + booking_data.getData().get(j).getBookingDate());

            }
        }

        if (isLogout){

            logoutDialog();
        }



    }
    private void logoutDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setCancelable(true);

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you want to Logout ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utility.clearPreferenceData(activity);
                        activity.startActivity(new Intent(activity, LoginActivity.class));
                        activity.finishAffinity();
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });

        // create alert dialog
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseModelCustomerData){
            ResponseModelCustomer responseModelCustomer = Utility.getResponseModelNotSyncCustomer(activity, Constants.keySalonNotSYncCustomerData);
            responseModelCustomer.getData().remove(pos);
            Utility.addPreferencesNotSyncCustomerData(activity,Constants.keySalonNotSYncCustomerData,responseModelCustomer);
        }else if (obj instanceof ResponseModelAppointments){
            ResponseModelAppointments responseModelAppointments =Utility.getNotSynResponseModelAppointments(activity,Constants.keySalonNotSynAppointmentsData);
            responseModelAppointments.getData().remove(pos);
            Utility.addNotSynPreferencesAppointmentsData(activity,Constants.keySalonNotSynAppointmentsData,responseModelAppointments);
            //RetrofitApi.getInstance().bookingApiMethod(this, this, dataAppointment, assignBook, ""+ SimpleDateFormat.getDateInstance().format(new Date()));

        }

    }

    @Override
    public void _onNext1(Object obj) {

        if (obj instanceof ResponseModelCustomerData){
            Log.d("syncing customer >>>>>>", ""+pos);
        }else if (obj instanceof ResponseModelAppointments){
            Log.d("syncing Booking >>>>>>", ""+pos);
        }

    }
}
