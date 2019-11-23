package com.abhi.fabkutbusiness.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.AdapterView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.model.ResponseAccountReport;
import com.abhi.fabkutbusiness.accounting.model.ResponseGenerateVoucherNo;
import com.abhi.fabkutbusiness.accounting.model.ResponseGetVoucherDetails;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelect;
import com.abhi.fabkutbusiness.accounting.model.ResponsePoInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo2;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodayInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodaysStatement;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherUpdate;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillPaymentData;
import com.abhi.fabkutbusiness.billing.model.ResponsePomoCode;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistory;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfo;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseUnpaidAmount;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpAttendence;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpCheckIn;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpReport;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmp;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmpSummary;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemBrand;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemCategory;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieve;
import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseCity;
import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseLocation;
import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotion;
import com.abhi.fabkutbusiness.marketing.model.ResponseLatestOfferList;
import com.abhi.fabkutbusiness.marketing.model.ResponseUpComingBirthday;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.abhi.fabkutbusiness.Utils.Constants.errorMsgWrong;

/**
 * Created by abhishekagarwal on 3/21/17.
 */

public class RetrofitApi {

    private ProgressDialog mProgressDialog;
    private static RetrofitApi retrofitApi = null;
    private ResponseListener mlistener_response;


    public static RetrofitApi getInstance() {

        if (retrofitApi != null)
            return retrofitApi;
        else
            return new RetrofitApi();
    }


    public interface ResponseListener {

        void _onCompleted();

        void _onError(Throwable e);

        void _onNext(Object obj);

        void _onNext1(Object obj);


    }


    // --------------------- Retrofit Api Methods ----------------------------------------------------------


    public void loginApiMethod(final Activity activity, final ResponseListener _mlistener_response, String email, String password) {
        this.mlistener_response = _mlistener_response;

        System.out.println("ajkjaklflklkj :"+email+"    dfs   "+password);


        RetrofitClient.getClient().loginApiMethod(email, password).subscribeOn(Schedulers.newThread()).


                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelLogin>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelLogin responseModelLogin) {

                        mlistener_response._onNext(responseModelLogin);

                    }

                });
    }


    public void customerApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().customerApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelCustomer>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelCustomer responseModelCustomer) {

                        mlistener_response._onNext(responseModelCustomer);

                    }

                });

    }

    public void employeeApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().employeeApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelEmployee>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelEmployee responseModelEmployee) {

                        mlistener_response._onNext(responseModelEmployee);

                    }

                });

    }

    public void rateInfoApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().rateInfoApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelRateInfo>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelRateInfo responseModelRateInfo) {

                        mlistener_response._onNext(responseModelRateInfo);

                    }

                });

    }

    public void addCustomerApiMethod(final Activity activity, final ResponseListener _mlistener_response, final ResponseModelCustomerData responseModelCustomerData) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Uploading Data...");
        mProgressDialog.show();


        RetrofitClient.getClient().addCustomerApiMethod(
                responseModelCustomerData.getCustomerId(),
                responseModelCustomerData.getBusiness_id(),
                responseModelCustomerData.getEndUser_FirstName(),
                responseModelCustomerData.getGender(),
                responseModelCustomerData.getEmail(),
                responseModelCustomerData.getContact_no(),
                responseModelCustomerData.getAllergies(),
                responseModelCustomerData.getDob()
        ).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        mlistener_response._onError(e);
                        //Utility.showToast(activity, "Customer added successfully");
                        mlistener_response._onNext1(responseModelCustomerData);

                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        mProgressDialog.dismiss();
                        if (responseModel.getSTATUS().equals("200")) {
                           // Utility.showToast(activity, "Customer Sync successfully");
                            mlistener_response._onNext(responseModelCustomerData);
                        } else
                            Utility.showToast(activity, errorMsgWrong);

                    }

                });
    }



    public void bookingApiMethod(final Activity activity, final ResponseListener _mlistener_response, final ResponseModelAppointmentsData responseModelCustomerData, int assignBook,String date) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Uploading Data...");
        mProgressDialog.show();

        String slots[] = responseModelCustomerData.getSlots().get(0).split("/");
        String slot = "";
        if (slots.length == 2)
            slot = slots[1];

        RetrofitClient.getClient().bookingApiMethod(
                responseModelCustomerData.getBookingId(),
                responseModelCustomerData.getCustomerId(),
                responseModelCustomerData.getBusinessId(),
                slot,
                responseModelCustomerData.getServices().get(0).getEmployee_id(),
                getFormattedServicesIds(responseModelCustomerData.getServices()),
                responseModelCustomerData.getCustomerMobile(),
                "",
                "0",
                ""+date,
                responseModelCustomerData.getSeatNumber(),
                "" + assignBook
        ).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel1>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e ) {
                        mProgressDialog.dismiss();
                        Log.e("Error Test ",e.toString());
                        //Utility.showToast(activity, ""+e.toString());
                        Utility.showToast(activity, "Booking made successfully ");
                        mlistener_response._onNext1(responseModelCustomerData);
                        //mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModel1 responseModel) {
                        mProgressDialog.dismiss();
                        if (responseModel.getSTATUS().equals("200")) {
                            Utility.showToast(activity, "Booking Sync made successfully");
                            mlistener_response._onNext(responseModelCustomerData);
                        } else
                            Utility.showToast(activity, errorMsgWrong);


                    }

                });
    }

    private String getFormattedServicesIds(ArrayList<ResponseModelRateInfoData> services) {
        String serviceIds = "";
        int index = 0;
        for (ResponseModelRateInfoData modelRateInfoData : services) {
            serviceIds += modelRateInfoData.getId();
            if (index < (services.size() - 1))
                serviceIds += ",";
            index++;
        }
        return serviceIds;
    }


    // --------------------- Crm List Show api  ----------------------------------------------------------


    public void CrmListShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmListShowApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseCrmList>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseCrmList responseCrmList) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseCrmList);
            }
        });

    }

    // --------------------- Billing Services  api  ----------------------------------------------------------


    public void BillingServiceApiMethod(final Activity activity, final ResponseListener mlistener_response, String bookingId, final ResponseModelRateInfoData _data , String EmpId ,String buisness_id ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().BillingServiceApiMethod(bookingId,_data.getId(),"1","1",EmpId ,buisness_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel1>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
                Utility.showToast(activity,"Add Service Succesfully");
                mlistener_response._onNext1(_data);

            }

            @Override
            public void onNext(ResponseModel1 responseModel1) {
                mProgressDialog.dismiss();
                if (responseModel1.getSTATUS().equalsIgnoreCase("200")){
                    Utility.showToast(activity,"Add Service Succesfully");
                    mlistener_response._onNext(_data);
                }else {
                    Utility.showToast(activity,Constants.errorMsgWrong);
                }

            }
        });

    }



    // --------------------- Remove Billing Service  api  ----------------------------------------------------------


    public void RemoveBilingServiceApiMethod(final Activity activity, final ResponseListener mlistener_response, final ResponseModelRateInfoData _data , String Reason, String bookingId ,String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().RemoveBilingServiceApiMethod(Reason,bookingId,_data.getId(),business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
                Utility.showToast(activity,"Remove Sucessfully e");
                mlistener_response._onNext1(_data);

            }

            @Override
            public void onNext(ResponseModel responseModel1) {
                mProgressDialog.dismiss();
                if (responseModel1.getSTATUS().equalsIgnoreCase("200")) {
                    Utility.showToast(activity,"Remove Sucessfully");
                    mlistener_response._onNext(_data);
                }else{
                    Utility.showToast(activity,Constants.errorMsgWrong);
                }
            }


        });

    }

    // --------------------- Employee Leave Report api  ----------------------------------------------------------


    public void EmployeeLeaveReportApiMethod(final Activity activity, final ResponseListener mlistener_response, String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().EmployeeLeaveReportApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseEmpReport>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseEmpReport responseEmpReport) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseEmpReport);
            }
        });

    }

    // --------------------- SyncCancel Booking  api  ----------------------------------------------------------


    public void syncCancelBookingApiMethod(final Activity activity, final ResponseListener mlistener_response, final ResponseModelAppointmentsData appointmentsData,String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().syncCancelBookingApiMethod(appointmentsData.getBusinessId(),
                appointmentsData.getBookingId(),
                appointmentsData.getCancelReason(),
                business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
                Utility.showToast(activity,"Booking Cancel Succesfully");
                mlistener_response._onNext1(appointmentsData);
            }

            @Override
            public void onNext(ResponseModel responseModel) {
                if (responseModel.getSTATUS().equalsIgnoreCase("200")) {
                    mProgressDialog.dismiss();
                    Utility.showToast(activity,"Booking Cancel Succesfully");
                    mlistener_response._onNext(appointmentsData);
                }
            }
        });

    }


    // --------------------- Crm basic info Show api  ----------------------------------------------------------


    public void CrmBasicInfoShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, String EndUser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmBasicInfoShowApiMethod(business_id, EndUser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseBasicInfo>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseBasicInfo responseBasicInfo) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseBasicInfo);
            }
        });

    }
    // --------------------- Crm personal info Update api  ----------------------------------------------------------


    public void CrmPersonalInfoUpdateApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, String EndUser_id, String dob, String anidate, int m_um, int profile) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmPersonalInfoUpdateApiMethod(business_id, EndUser_id, dob, anidate, m_um, profile).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponsePersonalInfoUpdate>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponsePersonalInfoUpdate responsePersonalInfoUpdate) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responsePersonalInfoUpdate);
            }
        });

    }

// --------------------- Crm Social info Update api  ----------------------------------------------------------


    public void CrmSocialInfoUpdateApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, String EndUser_id, String homeAddress, String deliverAddress, String modeOfCommunication, String facebookId, String twitterId, String whatsappId, int social_p) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmSocialInfoUpdateApiMethod(business_id, EndUser_id, homeAddress, deliverAddress, modeOfCommunication, facebookId, twitterId, whatsappId, social_p).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseSocialInfoUpdate>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseSocialInfoUpdate responseSocialInfoUpdate) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responseSocialInfoUpdate);
            }
        });

    }


    // --------------------- Crm social info Show api  ----------------------------------------------------------


    public void CrmSocialInfoShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, String EndUser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmSocialInfoShowApiMethod(business_id, EndUser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseSocialInfo>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseSocialInfo responseSocialInfo) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseSocialInfo);
            }
        });

    }


    // --------------------- Crm personal info Show api  ----------------------------------------------------------


    public void CrmPersonalInfoShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, String EndUser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmPersonalInfoShowApiMethod(business_id, EndUser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponsePersonalInfo>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponsePersonalInfo responsePersonalInfo) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responsePersonalInfo);
            }
        });

    }

// --------------------- Crm basic info update api  ----------------------------------------------------------


    public void CrmBasicInfoUpdateApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, String EndUser_id, String fName, String Lname, String gender, String email, String contact_no, String phone1, String alergies, int profile_b) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmBasicInfoUpdateApiMethod(business_id, EndUser_id, fName, Lname, gender, email, contact_no, phone1, alergies, profile_b).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseBasicInfoUpdate>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseBasicInfoUpdate responseBasicInfoUpdate) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responseBasicInfoUpdate);
            }
        });

    }



    // ---------------------  Ledger Select Api  ----------------------------------------------------------


    public void LedgerSelectApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().LedgerSelectApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseLedgerSelect>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseLedgerSelect responseLedgerSelect) {

                        mlistener_response._onNext(responseLedgerSelect);

                    }

                });
    }

    // ---------------------  get Voucher  Update Api  ----------------------------------------------------------


    public void VoucherUpdateApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id, String voucherNo, String paidAmount, String assignTo, String empId, String remark) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().VoucherUpdateApiMethod(business_id,voucherNo,paidAmount,assignTo,empId,remark).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseVoucherUpdate>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseVoucherUpdate responseVoucherUpdate) {

                        mlistener_response._onNext(responseVoucherUpdate);

                    }

                });
    }


    // ---------------------  get Voucher  Details Api  ----------------------------------------------------------


    public void getVoucherDetailsApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;



        RetrofitClient.getClient().getVoucherDetailsApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseGetVoucherDetails>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseGetVoucherDetails responseGetVoucherDetails) {

                        mlistener_response._onNext(responseGetVoucherDetails);

                    }

                });
    }


    // ---------------------  Ledger Insert Api  ----------------------------------------------------------


    public void LedgerInsertApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id,String ledger_balance) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();


        RetrofitClient.getClient().LedgerInsertApiMethod(business_id,ledger_balance).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseLedgerInsert>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseLedgerInsert responseLedgerInsert) {

                        mlistener_response._onNext(responseLedgerInsert);

                    }

                });
    }

// ---------------------  Voucher Insert Api  ----------------------------------------------------------


    public void VoucherInsertApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id, String voucherNo, int paidAmount, String assignTo, String empId, String remark) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().VoucherInsertApiMethod(business_id,voucherNo,paidAmount,assignTo,empId,remark).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseVoucherInsert>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseVoucherInsert responseVoucherInsert) {

                        mlistener_response._onNext(responseVoucherInsert);

                    }

                });
    }

// ---------------------  PO Insert Api  ----------------------------------------------------------


    public void PoInsertApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id, String Paidamount,int PoNoId,String mode,String status,String AutoID,int PoBal,int balAmount) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().PoInsertApiMethod(business_id,Paidamount,PoNoId,mode,status,AutoID,PoBal,balAmount).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponsePoInsert>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponsePoInsert responsePoInsert) {

                        mlistener_response._onNext(responsePoInsert);

                    }

                });
    }



    // ---------------------  Get Todats Statement Api  ----------------------------------------------------------


    public void getTodaysStatementApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().GetTodaysStatementApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseTodaysStatement>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseTodaysStatement responseTodaysStatement) {

                        mlistener_response._onNext(responseTodaysStatement);

                    }

                });
    }


    // ---------------------  Insert Todats Statement Api  ----------------------------------------------------------


    public void TodaysStatementInsertApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id,int final_amount, int Todays_Revenue, int Advance_Pay,int Vouchers_Values,int Payment_AgainstPo,int Ledger_Bal,int closing_Bal,String eod) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().TodaysStatementInsertApiMethod(business_id,final_amount,Todays_Revenue,Advance_Pay,Vouchers_Values,Payment_AgainstPo,Ledger_Bal,closing_Bal,eod).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseTodayInsert>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseTodayInsert responseTodayInsert) {

                        mlistener_response._onNext(responseTodayInsert);

                    }

                });
    }


    // ---------------------  Sync Payment  Api  ----------------------------------------------------------


    public void syncPaymentApiMethod(final Activity activity, final ResponseListener _mlistener_response,String bID ,int isWaivedOff, String bookingID,final ResponseModelBillPaymentData paymentData, String payType, final String paid_unpaid) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().syncPaymentApiMethod(
                ""+bookingID,
                ""+payType,
                "0",
                ""+paymentData.getTax(),
                ""+paymentData.getPaid(),
                ""+isWaivedOff,
                ""+paymentData.getUnPaid(),
                "0",
                ""+bID,
                ""+paid_unpaid,
                ""+paymentData.getTotal()).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);
                        Utility.showToast(activity,"Payment  successfully");
                        mlistener_response._onNext1(paymentData);

                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        if (responseModel.getSTATUS().equalsIgnoreCase("200")){
                            Utility.showToast(activity,"Payment Sync successfully");
                            mlistener_response._onNext(paymentData);
                        }else {
                            Utility.showToast(activity,Constants.errorMsgWrong);
                        }


                    }

                });
    }




    // ---------------------  Select PO Api  ----------------------------------------------------------


    public void SelectPoApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().SelectPoApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseSelectPo>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }



                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseSelectPo responseSelectPo) {

                        mlistener_response._onNext(responseSelectPo);

                    }

                });
    }


    // ---------------------  Generate Voucher No Api Method  ----------------------------------------------------------


    public void GenerateVoucherNoApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().GenerateVoucherNoApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseGenerateVoucherNo>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseGenerateVoucherNo responseGenerateVoucherNo) {

                        mlistener_response._onNext(responseGenerateVoucherNo);

                    }

                });
    }


    // ---------------------  select Po2 Api Method  ----------------------------------------------------------


    public void selectPoApiMethod(final Activity activity, final ResponseListener _mlistener_response, int business_id , int PONO) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().selectPoApiMethod(business_id,PONO).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseSelectPo2>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseSelectPo2 responseSelectPo2) {

                        mlistener_response._onNext1(responseSelectPo2);

                    }

                });
    }


    //------------------------------- item master insert ---------------------------------------------------------------


   /* public void itemMasterInsertApiMethod(final Activity activity, final ResponseListener mlistener_response,String itemFor,String itemBrand, String itemType,String itemCategory,String itemCode,String itemName,String unit,String itemColor,String barcode,String itemCost,String itemRetailPrice,String minValue,String maxValue ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().itemMasterInsertApiMethod(itemFor,itemBrand,itemType,itemCategory,itemCode,itemName,unit,itemColor,barcode,itemCost,itemRetailPrice,minValue,maxValue).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseItemMasterInsert>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseItemMasterInsert itemMasterInsert) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(itemMasterInsert);
            }
        });

    }
*/

    //------------------------------- Order insert ---------------------------------------------------------------


 /*   public void OrderInsertApiMethod(final Activity activity, final ResponseListener mlistener_response,String poType ,String selectVendor, String itemType,String itemCategory,String itemCode,String item ,String unit,String unitValue,String itemCost,String itemMRP,String remark ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().OrderInsertApiMethod(poType,selectVendor,itemType,itemCategory,itemCode,item,unit,unitValue,itemCost,itemMRP,remark).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseOrderInsert>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseOrderInsert orderInsert) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(orderInsert);
            }
        });

    }
*/
    //-------------------------------  Vendor  insert ---------------------------------------------------------------


  /*  public void vendorInsertApiMethod(final Activity activity, final ResponseListener mlistener_response ,String vendorName,String code, String tin,String panNo,String contact ,String  phone,String deliveryMode,String agentName,String paymentMode,String address1,String address2,String state,String city,String location,String zipCode ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().vendorInsertApiMethod(vendorName,code,tin,panNo,contact,phone,deliveryMode,agentName,paymentMode,address1,address2,state,city,location,zipCode).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseVendorInsert>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseVendorInsert vendorInsert) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(vendorInsert);
            }
        });

    }
*/

    //-------------------------------  Add Contract insert ---------------------------------------------------------------


  /*  public void addContractInsertApiMethod(final Activity activity, final ResponseListener mlistener_response ,String selectVendor,String brand, String itemType,String itemCategory,String item ,String  decription,String unit,String unitValue,String itemCost,String itemMRP,String startDate,String endDate ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().addContractInsertApiMethod(selectVendor,brand,itemType,itemCategory,item,decription,unit,unitValue,itemCost,itemMRP,startDate,endDate).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseAddContractInsert>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseAddContractInsert addContractInsert) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(addContractInsert);
            }
        });

    }
*/
    //------------------------------- Brand Selecy ---------------------------------------------------------------


    public void brandtApiMethod(final Activity activity, final ResponseListener mlistener_response ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().brandtApiMethod().subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseItemBrand>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseItemBrand responseItemBrand) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseItemBrand);
            }
        });

    }

    //------------------------------- Item Category ---------------------------------------------------------------


    public void itemCategoryApiMethod(final Activity activity, final ResponseListener mlistener_response ) {
        this.mlistener_response = mlistener_response;

        RetrofitClient.getClient().itemCategoryApiMethod( ).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseItemCategory>() {
            @Override
            public void onCompleted() {
               // mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
             //   mProgressDialog.dismiss();x
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseItemCategory responseItemCategory) {
               // mProgressDialog.dismiss();
                mlistener_response._onNext(responseItemCategory);
            }
        });

    }

    //------------------------------- Item Type ---------------------------------------------------------------


 /* //  public void itemTypeApiMethod(final Activity activity, final ResponseListener mlistener_response ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().itemTypeApiMethod().subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseItemType>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseItemType responseItemType) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseItemType);
            }
        });

    }

    //------------------------------- Item Color ---------------------------------------------------------------
*/

  /*//  public void itemColorApiMethod(final Activity activity, final ResponseListener mlistener_response ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().itemColorApiMethod().subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseItemColor>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseItemColor responseItemColor) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseItemColor);
            }
        });

    }

  */  //------------------------------- Vendor List---------------------------------------------------------------


  /* // public void vendorListApiMethod(final Activity activity, final ResponseListener mlistener_response,String businessId ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().vendorListApiMethod(businessId).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseVendor>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseVendor responseVendor) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseVendor);
            }
        });

    }

    //-----------------------------------Unit Value----------------------------------------------------------

//    public void unitValueApiMethod(final Activity activity, final ResponseListener mlistener_response,String unit ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().unitValueApiMethod(unit).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseUnitValue>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseUnitValue responseUnitValue) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseUnitValue);
            }
        });

    }
*/
// --------------------- City api  ----------------------------------------------------------


    public void cityApiMethod(final Activity activity, final ResponseListener mlistener_response) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().cityApiMethod().subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseCity>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseCity responseCity) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseCity);
            }
        });

    }


    // --------------------- Employee Check In api  ----------------------------------------------------------


    public void employeCheckInApiMethod(final Context activity, final ResponseListener mlistener_response , final String business_id,
                                        final String EmpID, final String InTime , final String OutTime, final String AttDate, final String Remark) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().employeCheckInApiMethod(business_id,EmpID,InTime,OutTime,AttDate, Remark).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseEmpAttendence>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseEmpAttendence responseEmpAttendence) {
                mProgressDialog.dismiss();

                mlistener_response._onNext(responseEmpAttendence);
            }
        });

    }


    // ---------------------Get Employee Check In api  ----------------------------------------------------------


    public void GetEmployeCheckInApiMethod(final Context activity, final ResponseListener mlistener_response ,final String business_id ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().GetEmployeCheckInApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseEmpCheckIn>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseEmpCheckIn responseEmpCheckIn) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseEmpCheckIn);
            }
        });

    }



    // ---------------------Get Employee Check Out api  ----------------------------------------------------------


    public void GetEmployeCheckOutApiMethod(final Context activity, final ResponseListener mlistener_response ,final String business_id ) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().GetEmployeCheckOutApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseEmpCheckIn>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseEmpCheckIn responseEmpCheckIn) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseEmpCheckIn);
            }
        });

    }





    // --------------------- Employee Check Out and Update attendence api  ----------------------------------------------------------


    public void employeCheckOutApiMethod(final Context activity, final ResponseListener mlistener_response ,final String AttID, final String business_id,
                                        final String EmpID, final String InTime , final String OutTime, final String AttDate, final String Remark) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().employeCheckOutApiMethod(AttID,business_id,EmpID,InTime,OutTime,AttDate, Remark).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseEmpAttendence>(){
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                Utility.showToast(activity,"1");
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseEmpAttendence responseEmpAttendence) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responseEmpAttendence);
            }
        });

    }

    // --------------------- Employee Summary api  ----------------------------------------------------------


    public void employeSummaryApiMethod(final Context activity, final ResponseListener mlistener_response ,final String business_id,
                                        final String date) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().employeSummaryApiMethod(business_id,date).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModelEmp>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseModelEmp responseModelEmpSummary) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseModelEmpSummary);
            }
        });

    }

    // --------------------- Location api  ----------------------------------------------------------


    public void locationApiMethod(final Activity activity, final ResponseListener mlistener_response,int cityId) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().locationApiMethod(cityId).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseLocation>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseLocation responseLocation) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseLocation);
            }
        });

    }



    // --------------------- Crm history api  ----------------------------------------------------------


    public void crmHistoryApi(final Activity activity, final ResponseListener mlistener_response,String business_id ,String Enduser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().crmHistoryApi(business_id,Enduser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseCrmHistory>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseCrmHistory responseCrmHistory) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseCrmHistory);
            }
        });

    }


    // --------------------- Employee Leave api  ----------------------------------------------------------


    public void employeeLeaveApi(final Activity activity, final ResponseListener mlistener_response,String business_id ,String Emp_id,String from_date ,String to_date,String comment,String halfLeave,String Leavetotal) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().employeeLeaveApi(business_id,Emp_id,from_date,to_date,comment,halfLeave,Leavetotal).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel1>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseModel1 responseModel1) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseModel1);
            }
        });

    }
    // --------------------- Employee Leave  update api  ----------------------------------------------------------


    public void employeeLeaveUdateApi(final Context activity, final ResponseListener mlistener_response,String business_id ,String Emp_id,String from_date ,String to_date,String comment,String halfLeave,String Leavetotal ,String Leave_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().employeeLeaveUdateApi(business_id,Emp_id,from_date,to_date,comment,halfLeave,Leavetotal,Leave_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel1>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseModel1 responseModel1) {
                mProgressDialog.dismiss();
                if (responseModel1.getSTATUS().equalsIgnoreCase("200"))
                    Utility.showToast(activity,""+responseModel1.getMESSAGE());
                    mlistener_response._onNext(responseModel1);
                }

        });

    }

    // --------------------- Customer unpaid amount  api  ----------------------------------------------------------


    public void CustomerUnpaidApi(final Activity activity, final ResponseListener mlistener_response ,String CustomerID) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CustomerUnpaidApi(CustomerID).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseUnpaidAmount>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseUnpaidAmount responseUnpaidAmount) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseUnpaidAmount);
            }
        });

    }

    // --------------------- Account Report   api  ----------------------------------------------------------


    public void AccountReportApi(final Activity activity, final ResponseListener mlistener_response ,String bId) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
        RetrofitClient.getClient().AccountReportApi(bId).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseAccountReport>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseAccountReport responseAccountReport) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseAccountReport);
            }

        });

    }


    // --------------------- Inventory Item api  ----------------------------------------------------------


    public void itemApi(final Activity activity, final ResponseListener mlistener_response,String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().itemApi(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseOrderRecieve>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseOrderRecieve responseOrderRecieve) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseOrderRecieve);
            }
        });

    }



    // --------------------- Marketing latest Offer api  ----------------------------------------------------------


    public void latestOfferApi(final Activity activity, final ResponseListener mlistener_response,String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().latestOfferApi(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseLatestOfferList>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseLatestOfferList responseLatestOfferList) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseLatestOfferList);
            }
        });

    }


    // --------------------- final Syncapi  ----------------------------------------------------------


    public void finalSyncBookingApiMethod(final Activity activity, final ResponseListener _mlistener_response, final ResponseModelAppointmentsData responseModelCustomerData, int assignBook,String date) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Uploading Data...");
        mProgressDialog.show();

        String slots[] = responseModelCustomerData.getSlots().get(0).split("/");
        String slot = "";
        if (slots.length == 2)
            slot = slots[1];

        RetrofitClient.getClient().FinalSyncPaymentApiMethod(
                responseModelCustomerData.getBookingId(),
                responseModelCustomerData.getCustomerId(),
                responseModelCustomerData.getBusinessId(),
                slot,
                responseModelCustomerData.getServices().get(0).getEmployee_id(),
                getFormattedServicesIds(responseModelCustomerData.getServices()),
                responseModelCustomerData.getCustomerMobile(),
                "",
                "0",
                "" + date,
                responseModelCustomerData.getSeatNumber(),
                "" + assignBook,
                "",
                "0",
                "" + responseModelCustomerData.getBillPayment().getTax(),
                "" + responseModelCustomerData.getBillPayment().getPaid(),
                "",
                "" + responseModelCustomerData.getBillPayment().getUnPaid(),
                "0",
                "0",
                "",
                "" + responseModelCustomerData.getTotalAmount()
        ).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        Log.e("Error Test ", e.toString());
                        //Utility.showToast(activity, ""+e.toString());
                        //Utility.showToast(activity, "Booking made successfully ");
                        mlistener_response._onNext1(responseModelCustomerData);
                        //mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        mProgressDialog.dismiss();
                        if (responseModel.getSTATUS().equals("200")) {
                            //Utility.showToast(activity, "Booking Sync made successfully");
                            mlistener_response._onNext(responseModelCustomerData);
                        } else
                            Utility.showToast(activity, errorMsgWrong);


                    }

                });
    }


        //----------------------------- Marketing Upcoming Birthday ------------------------------------------

        public void upComingBirthdayOfferApi(final Activity activity, final ResponseListener mlistener_response,String business_id) {
            this.mlistener_response = mlistener_response;
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage("Please Wait");
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();

            RetrofitClient.getClient().upComingBirthdayOfferApi(business_id).subscribeOn(Schedulers.newThread()).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseUpComingBirthday>() {
                @Override
                public void onCompleted() {
                    mProgressDialog.dismiss();
                    mlistener_response._onCompleted();
                }

                @Override
                public void onError(Throwable e) {
                    mProgressDialog.dismiss();
                    mlistener_response._onError(e);
                }

                @Override
                public void onNext(ResponseUpComingBirthday responseUpComingBirthday) {
                    mProgressDialog.dismiss();
                    mlistener_response._onNext(responseUpComingBirthday);
                }
            });

        }



    //----------------------------- Marketing Upcoming Aniversaray ------------------------------------------

    public void upComingAniApi(final Activity activity, final ResponseListener mlistener_response,String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().upComingAniApi(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseUpComingBirthday>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseUpComingBirthday responseUpComingBirthday) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responseUpComingBirthday);
            }
        });

    }


    //----------------------------- Create Offer ------------------------------------------

    public void upCreateOfferApi(final Activity activity, final ResponseListener mlistener_response,
                                 String mode,
                                 String fabkut_offer_id,
                                 String fabkut_offer_type_id,
                                 String fabkut_offer_code,
                                 String fabkut_offer_name,
                                 String fabkut_offer_desc,
                                 String fabkut_offer_pic,
                                 String fabkut_offer_start,
                                 String fabkut_offer_end,
                                 String fabkut_offer_price,
                                 String active,
                                 String fabkut_offer_disc_perc,
                                 String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().upCreateOfferApi(mode,fabkut_offer_id,""+fabkut_offer_code,
                ""+fabkut_offer_name,""+fabkut_offer_desc,""+fabkut_offer_pic,
                ""+fabkut_offer_start,""+fabkut_offer_end,""+fabkut_offer_price,
                ""+fabkut_offer_disc_perc,""+active,""+business_id,""+fabkut_offer_type_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseCreatePromotion>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseCreatePromotion responseCreatePromotion) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseCreatePromotion);
            }
        });

    }


    //----------------------------- Run Offer ------------------------------------------

    public void runOfferApi(final Activity activity, final ResponseListener mlistener_response,
                                 String mode,
                                 String fabkut_offer_id,
                                 String active,
                                 String endusercode,
                                 String to_all,
                                 String business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().runOfferApi(mode,fabkut_offer_id,endusercode,
                to_all,""+active,""+business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel1>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseModel1 responseModel) {
                if (responseModel.getSTATUS().equals("200")) {
                    // Utility.showToast(activity, "Customer Sync successfully");
                    mlistener_response._onNext(responseModel);
                } else
                    Utility.showToast(activity, errorMsgWrong);

            }
        });





}


//----------------------------- Run Offer ------------------------------------------

    public void selectPromoCode(final Activity activity, final ResponseListener mlistener_response ,String businessId,String endUserCode) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().selectPromoCode(businessId, endUserCode).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponsePomoCode>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponsePomoCode responsePomoCode) {
                if (responsePomoCode.getSTATUS().equals("200")) {
                    // Utility.showToast(activity, "Customer Sync successfully");
                    mlistener_response._onNext(responsePomoCode);
                } else
                    Utility.showToast(activity, errorMsgWrong);

            }
        });


    }


        //----------------------------- booking Coupon  ------------------------------------------

    public void bookingCoupon(final Activity activity, final ResponseListener mlistener_response ,String booking,String businessId,String endUserCode) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().bookingCoupon(booking,businessId,endUserCode).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseModel>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseModel responseModel) {
                if (responseModel.getSTATUS().equals("200")) {
                    // Utility.showToast(activity, "Customer Sync successfully");
                    mlistener_response._onNext(responseModel);
                } else
                    Utility.showToast(activity, errorMsgWrong);

            }
        });

    }




}
