package com.admin.Retrofit;


import android.app.Activity;
import android.app.ProgressDialog;


import com.admin.Model.AllSaloonModel;
import com.admin.Model.AllServicesModel;
import com.admin.Model.Form1Model;
import com.admin.Model.Form2Model;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitApi {

    private static RetrofitApi retrofitApi = null;
    private ResponseListener mlistener_response;
    private ProgressDialog mProgressDialog;


    public static RetrofitApi getInstance() {

        if (retrofitApi != null)
            return retrofitApi;
        else
            return new RetrofitApi();
    }

    public void addForm1(final Activity activity, final ResponseListener mlistener_response, String business_Name, String Contact_Person,String contact_No,String business_email_id,String Salon_password) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        RetrofitClient.getClient().addForm1(
                ""+business_Name,
                ""+Contact_Person,
                ""+contact_No,
                ""+business_email_id,
                ""+Salon_password).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<Form1Model>() {
            @Override
            public void onCompleted() {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(Form1Model responseVendor) {
                mlistener_response._onNext(responseVendor);
            }
        });

    }

    public void addForm2(final Activity activity, final ResponseListener mlistener_response, String business_id, String No_of_Chairs,String no_of_emp,String opening_hours,String closing_hours, String latitude, String longitute) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        RetrofitClient.getClient().addForm2(
                ""+business_id,
                ""+No_of_Chairs,
                ""+no_of_emp,
                ""+opening_hours,
                ""+closing_hours,
                ""+latitude,
                ""+longitute).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<Form2Model>() {
            @Override
            public void onCompleted() {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(Form2Model responseVendor) {
                mlistener_response._onNext(responseVendor);
            }
        });

    }

    public void getSaloonList(final Activity activity, final ResponseListener mlistener_response) {
        this.mlistener_response = mlistener_response;

        RetrofitClient.getClient().getSaloonList().subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<AllSaloonModel>() {
            @Override
            public void onCompleted() {
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(AllSaloonModel allSaloonModel) {
                mlistener_response._onNext(allSaloonModel);

            }
        });
    }

    public void getServicesList(final Activity activity, final ResponseListener mlistener_response) {
        this.mlistener_response = mlistener_response;

        RetrofitClient.getClient().getServicesList().subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<AllServicesModel>() {
            @Override
            public void onCompleted() {
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(AllServicesModel allServicesModel) {
                mlistener_response._onNext(allServicesModel);

            }
        });
    }

    public interface ResponseListener {

        void _onCompleted();

        void _onError(Throwable e);

        void _onNext(Object obj);


    }

}
