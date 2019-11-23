package com.abhi.fabkutbusiness.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelSeats;
import com.abhi.fabkutbusiness.main.model.ResponseModelSeatsData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 04/09/18.
 */

public class BusinessLocalData implements RetrofitApi.ResponseListener {

    private ProgressDialog mProgressDialog;
    private String businessId, businessName;
    Activity activity;


    public BusinessLocalData(Activity activity) {
        this.activity = activity;
    }

    public void getBusinessData(Activity activity) {
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Syncing Data");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        //Toast.makeText(activity, "hey", Toast.LENGTH_SHORT).show();
        RetrofitApi.getInstance().loginApiMethod(activity, this, Utility.getResponseModelLogin(activity,Constants.keySalonProfileData).getData().get(0).getBusiness_email_id(),
                Utility.getResponseModelLogin(activity,Constants.keySalonProfileData).getData().get(0).getSalon_password());
    }

    @Override
    public void _onCompleted() {
        
    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseModelLogin) {
            ResponseModelLogin modelLogin = (ResponseModelLogin) obj;
            if (modelLogin.getSTATUS().equals("200")) {

                Utility.addPreferencesUserData(activity, Constants.keySalonProfileData, modelLogin);

                businessId = modelLogin.getData().get(0).getBusiness_id();
                Utility.addPreferences(activity, Constants.keySalonBusinessId, businessId);

                businessName = modelLogin.getData().get(0).getBusiness_name();
                Utility.addPreferences(activity, Constants.keySalonBusinessName, businessName);



                String openTime = modelLogin.getData().get(0).getOpening_hours();
                Utility.addPreferences(activity, Constants.keySalonOpenTime, openTime);

                String closeTime = modelLogin.getData().get(0).getClosing_hours();
                Utility.addPreferences(activity, Constants.keySalonCloseTime, closeTime);

                String seatsNum = modelLogin.getData().get(0).getSeats();
                Utility.addPreferences(activity, Constants.keySalonSeatsNum, seatsNum);

                Double tax_percentage = modelLogin.getData().get(0).getTax_percentage();
                Utility.addPreferences(activity, Constants.keySalonTaxPercentage, "" + tax_percentage);


                int seats = Integer.parseInt(seatsNum);
                ArrayList<String> seatStatusList = new ArrayList<>();
                for (int i = 0; i < seats; i++) {
                    seatStatusList.add("0");
                }
                Utility.addPreferencesSeatStatusData(activity, Constants.keySalonSeatsStatusList, seatStatusList);

                ResponseModelAppointments responseModelAppointments = new ResponseModelAppointments();
                responseModelAppointments.setData(new ArrayList<ResponseModelAppointmentsData>());

                ArrayList<ResponseModelSeatsData> seatsData = new ArrayList<>();

                for (int i = 0; i < Integer.parseInt(modelLogin.getData().get(0).getSeats()); i++) {
                    seatsData.add(new ResponseModelSeatsData());
                }

                ResponseModelSeats responseModelSeats = new ResponseModelSeats();
                responseModelSeats.setData(seatsData);

               /* Utility.addPreferencesAppointmentsData(activity, Constants.keySalonAppointmentsData, responseModelAppointments);
                Utility.addNotSynPreferencesAppointmentsData(activity, Constants.keySalonNotSynAppointmentsData, responseModelAppointments);
                Utility.addPreferencesBookingData(activity, Constants.keySalonBookingData, responseModelAppointments);
                Utility.addPreferencesCancelBookingData(activity, Constants.keySalonCancelBookingData, responseModelAppointments);
                Utility.addPreferencesSeatsData(activity, Constants.keySalonSeatsData, responseModelSeats);

                ResponseModelBillingList responseModelBillingList = new ResponseModelBillingList();
                Utility.addPreferencesBillingListData(activity, Constants.keySalonBillingListData, responseModelBillingList);
*/
                RetrofitApi.getInstance().employeeApiMethod(activity, this, businessId);

            } else {
                callRollback(modelLogin.getMESSAGE());
            }
        }  else if (obj instanceof ResponseModelEmployee) {
            ResponseModelEmployee modelEmployee = (ResponseModelEmployee) obj;
            if (modelEmployee.getSTATUS().equals("200")) {
                Utility.addPreferencesEmployeeData(activity, Constants.keySalonEmployeeData, modelEmployee);
                RetrofitApi.getInstance().rateInfoApiMethod(activity, this, businessId);


            } else {
                callRollback(modelEmployee.getMESSAGE());
            }
        } else if (obj instanceof ResponseModelRateInfo) {
            ResponseModelRateInfo modelRateInfo = (ResponseModelRateInfo) obj;
            if (modelRateInfo.getSTATUS().equals("200")) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Utility.addPreferencesRateInfoData(activity, Constants.keySalonRateInfoData, modelRateInfo);
                Utility.addPreferences(activity, Constants.keyLoginCheck, true);
                activity.startActivity(new Intent(activity, NavigationMainActivity.class));
                activity.finish();
            } else {
                callRollback(modelRateInfo.getMESSAGE());
            }
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }


    private void callRollback(String msg) {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        Utility.showToast(activity, msg);
        Utility.clearPreferenceData(activity);
    }

}
