package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.BillingServicesAdapter;
import com.abhi.fabkutbusiness.billing.controller.BillingServicesAdapter2;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.retrofit.ResponseModel;
import com.abhi.fabkutbusiness.retrofit.ResponseModel1;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

/**
 * Created by abhi on 17/04/17.
 */

public class BillDetailActivity extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    RecyclerView listServices;
    ResponseModelAppointmentsData billingData;
    TextView tvAddServices, tvPay;
    TextView tvName, tvNumber, tvSubTotal, tvTax, tvPreviousBalance, tvOrderTotal;
    BillingServicesAdapter2 adapter;
    int subTotal = 0,check = 0;
    AlertDialog deleteDialog;
    RetrofitApi.ResponseListener responseListener;
    Double taxPercentage = 18.0;
    int PreviousBalance;
    int orderTotal;
    String temp_serviceName,temp_reason,business_id;
    int billingItemPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bill_detail);
        business_id = Utility.getPreferences(this,Constants.keySalonBusinessId);
        setActionBarView();
        findViewById();
        initData();


    }


    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Billing");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvNumber = (TextView) findViewById(R.id.tv_number);

        listServices = (RecyclerView) findViewById(R.id.list_services_billing);

        tvSubTotal = (TextView) findViewById(R.id.tv_subtotal);
        tvTax = (TextView) findViewById(R.id.tv_tax);
        tvPreviousBalance = (TextView) findViewById(R.id.tv_previous_balance);
        tvOrderTotal = (TextView) findViewById(R.id.tv_order_total);

        tvAddServices = (TextView) findViewById(R.id.tv_add_services);
        tvAddServices.setOnClickListener(this);
        tvPay = (TextView) findViewById(R.id.tv_pay);
        tvPay.setOnClickListener(this);

    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void initData() {

        billingData = getIntent().getParcelableExtra("data");
        billingItemPos = getIntent().getIntExtra("pos", -1);
        taxPercentage = Utility.getPreferences(this, Constants.keySalonTaxPercentage, 0.0);
        PreviousBalance = Utility.getPreviousBalance(this, billingData.getCustomerId());

        adapter = new BillingServicesAdapter2(BillDetailActivity.this, billingData.getServices());
        listServices.setAdapter(adapter);

        tvName.setText(billingData.getCustomerEndUser_FirstName() + " " + billingData.getCustomerEndUser_LastName());
        tvNumber.setText(billingData.getCustomerMobile());
        tvPreviousBalance.setText("Rs. " + PreviousBalance + " ");
        setPaymentData();

    }

    private void setPaymentData() {

        subTotal = Utility.getBillingSubTotalAmount(billingData.getServices());
        billingData.setTotalAmount("" + subTotal);
        tvSubTotal.setText("Rs. " + subTotal);

        int taxAmount = Utility.getTaxAmount(subTotal, taxPercentage);
        tvTax.setText("Rs. " + taxAmount + "");

        orderTotal = subTotal + taxAmount;
        tvOrderTotal.setText("Rs. " + orderTotal + "");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.tv_add_services:
                startActivityForResult(new Intent(BillDetailActivity.this, AddBillingServiceActivity.class), 101);
                break;

            case R.id.tv_pay:

                if (billingData.getServices().size() > 0) {
                    startActivityForResult(new Intent(BillDetailActivity.this, PaymentActivity.class)
                            .putExtra("data", billingData)
                            .putExtra("total", orderTotal)
                            .putExtra("pos", billingItemPos), 102);
                    //Utility.showToast(this,""+orderTotal);

                } else {
                    Utility.showToast(BillDetailActivity.this, "One service should be added for billing.");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                ResponseModelRateInfoData _data = data.getParcelableExtra("data");
                check =2;
                RetrofitApi.getInstance().BillingServiceApiMethod(this,this,
                        ""+billingData.getBookingId(),
                        _data,
                        ""+_data.getEmployee_id(),business_id);

                //addService(_data);
            }
        } else if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    public void showRemoveServiceDialog(final String serviceName) {

      /*  final AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setTitle("Alert");
        dialog.setMessage("Remove 1 service : " + serviceName);
        dialog.setCancelable(true);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (ResponseModelRateInfoData _data : billingData.getServices()) {
                    if (_data.getSub_service_name().equals(serviceName)) {
                        billingData.getServices().remove(_data);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
                setPaymentData();
                dialog.dismiss();
            }

        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        dialog.show();*/

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.booking_cancel_dialog, null);
         deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        TextView tvName = (TextView) deleteDialogView.findViewById(R.id.tv_cancel_name);
        tvName.setText("Remove 1 service : " + serviceName + "");

        final EditText etReason = (EditText) deleteDialogView.findViewById(R.id.et_cancel_reason);
        etReason.setHint("Type here reason for removing service");


        TextView tvSubmit = (TextView) deleteDialogView.findViewById(R.id.tv_cancel_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strReason = etReason.getText().toString().trim();
                if (strReason.length() > 0) {
                    temp_serviceName = serviceName;
                    temp_reason = strReason;

                    for (ResponseModelRateInfoData _data : billingData.getServices()) {
                        if (_data.getSub_service_name().equals(serviceName)) {

                            removeServerCall(_data);
                            //billingData.getServices().remove(_data);

                            break;
                        }
                    }
                    /*adapter.notifyDataSetChanged();
                    setPaymentData();*/
                    deleteDialog.dismiss();


                } else {
                    Utility.showToast(BillDetailActivity.this, "Please enter the reason.");
                }

            }
        });

        deleteDialog.show();

    }

    private void removeServerCall(ResponseModelRateInfoData _data) {
        check =1;
        RetrofitApi.getInstance().RemoveBilingServiceApiMethod(BillDetailActivity.this,
                this,_data,""+temp_reason,""+billingData.getBookingId(),business_id);
    }

    private void addService(ResponseModelRateInfoData data,String sync) {
        if (billingData.getServices().size() == 0) {
            billingData.getServices().clear();
        }
        billingData.getServices().add(data);
        billingData.getServiceTrackRecord().add("Added 1 service : " + data.getSub_service_name() +" isSync = "+sync);
        adapter.notifyDataSetChanged();
        setPaymentData();

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseModelRateInfoData && check == 2) {

                //Utility.showToast(this, "Add Servicess Successfully");
                ResponseModelRateInfoData responseModelRateInfo = (ResponseModelRateInfoData) obj;
                addService(responseModelRateInfo, "true");

            //billingData.getServiceTrackRecord().add("Added 1 service : " + data.getSub_service_name() +" isSync = true");

        }else if (obj instanceof ResponseModelRateInfoData && check ==1){
                ResponseModelRateInfoData responseModelRateInfo = (ResponseModelRateInfoData) obj;
            for (ResponseModelRateInfoData _data : billingData.getServices()) {
                if (_data.getSub_service_name().equals(temp_serviceName)) {

                    billingData.getServices().remove(_data);
                    billingData.getServiceTrackRecord().add("Removed 1 service : " + temp_serviceName + " because " + temp_reason + " isSync = true");


                    break;
                }
            }
            adapter.notifyDataSetChanged();
            setPaymentData();

                //Utility.showToast(this,"Remove Servicess Successfully");

            }


    }

    @Override
    public void _onNext1(Object obj) {
        if (obj instanceof ResponseModelRateInfoData && check == 2) {

            //Utility.showToast(this, "Add Servicess Successfully");
            ResponseModelRateInfoData responseModelRateInfo = (ResponseModelRateInfoData) obj;
            addService(responseModelRateInfo, "false");

            //billingData.getServiceTrackRecord().add("Added 1 service : " + data.getSub_service_name() +" isSync = true");

        }else if (obj instanceof ResponseModelRateInfoData && check ==1){
            ResponseModelRateInfoData responseModelRateInfo = (ResponseModelRateInfoData) obj;
            for (ResponseModelRateInfoData _data : billingData.getServices()) {
                if (_data.getSub_service_name().equals(temp_serviceName)) {

                    billingData.getServices().remove(_data);
                    billingData.getServiceTrackRecord().add("Removed 1 service : " + temp_serviceName + " because " + temp_reason + " isSync = trufalsee");


                    break;
                }
            }
            adapter.notifyDataSetChanged();
            setPaymentData();
        }


    }
}
