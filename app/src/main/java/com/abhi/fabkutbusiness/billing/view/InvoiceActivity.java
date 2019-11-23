package com.abhi.fabkutbusiness.billing.view;

import android.Manifest;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.PdfGenrate;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.InvoiceServicesAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by abhi on 17/04/17.
 */

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    private ProgressDialog progressDialog;
    TextView tvTitle;

    ResponseModelBillingList _data;
    ImageView iconLeft, iconRight;
    RecyclerView listServices;
    TextView tvAmount,tvPaidAmount,tvTax,tvNetAmount,tvPbalance,tvPbalancePaid,tvBalance,tvSalonNAme,tvSalonContact,tvCustomer,tvCustomerContact,tvBookingDate;
    InvoiceServicesAdapter adapter;
    LinearLayout scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invoice);
        Utility.checkPermission(this);
        Utility.checkPermissions(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        setActionBarView();
        findViewById();
        initData();
    }


    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Details");
        scrollView = (LinearLayout) findViewById(R.id.ll_main) ;

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);
        iconRight.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_save));
        iconRight.setVisibility(View.INVISIBLE);
        iconRight.setOnClickListener(this);

        listServices = (RecyclerView) findViewById(R.id.rv_invoice_services);
        listServices.setNestedScrollingEnabled(false);

        tvAmount = (TextView)findViewById(R.id.tv_amount);
       // tvNetAmount = (TextView)findViewById(R.id.tv_net_amount);
       // tvTax = (TextView)findViewById(R.id.tv_tax);
        //tvPbalance = (TextView)findViewById(R.id.tvPaidAmountBalance);
        //tvPbalancePaid = (TextView)findViewById(R.id.tvPblancePaid);
       // tvBalance = (TextView)findViewById(R.id.balance);
        //tvPaidAmount = (TextView)findViewById(R.id.paidAmount);

        //tvSalonNAme = (TextView)findViewById(R.id.salonName);
        //tvSalonContact = (TextView)findViewById(R.id.mobile);
        tvCustomer = (TextView)findViewById(R.id.customer);
        tvCustomerContact = (TextView)findViewById(R.id.cutomer_mobile);
        tvBookingDate = (TextView)findViewById(R.id.tv_date);



    }

    private void initData() {

        ResponseModelAppointmentsData data = getIntent().getParcelableExtra("data");
        //_data  = Utility.getResponseModelBillingListData(this, Constants.keySalonBillingListData);

        adapter = new InvoiceServicesAdapter(data.getServices(), R.layout.item_services_invoice);
        listServices.setAdapter(adapter);
        tvAmount.setText(data.getTotalAmount());
        //tvSalonNAme.setText(Utility.getPreferences(this, Constants.keySalonBusinessName));
        //tvBookingDate.setText(Utility.formatDateForDisplay(data.getBookingDate(),"MM-dd-yyyy",""+Constants.displayDateFormat));
        tvCustomer.setText(data.getCustomerEndUser_FirstName()+" "+data.getCustomerEndUser_LastName());
        tvCustomerContact.setText(data.getCustomerMobile());

//        tvSalonContact.setText(Utility.getResponseModelLogin(this,Constants.keySalonProfileData).getData().get(0).getContact_no());
        tvBookingDate.setText("Date : "+data.getBookingDate());
       // Toast.makeText(this, ""+data.getBillPayment(), Toast.LENGTH_SHORT).show();
        //tvPaidAmount.setText();
        //tvBalance.setText(data.g);
        //tvTax.setText(_data.getDATA().get(data.getBookingDate()).get(0).getBillPayment().getTax());


    }
    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.actionbar_view_icon_right:
                saveInvoice();
                break;
        }
    }

    private void saveInvoice() {

        File file = PdfGenrate.saveBitMap(this, scrollView);
        progressDialog =  new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        if (file != null) {
            Log.i("TAG", "Drawing saved to the gallery!");
            try {
                PdfGenrate.imageToPDF(this);
                progressDialog.dismiss();
            } catch (FileNotFoundException e) {
                progressDialog.dismiss();
                e.printStackTrace();
            }
        } else {
            progressDialog.dismiss();
            Log.i("TAG", "Oops! Image could not be saved.");
        }
        //Utility.showToast(this, "Invoice saved");
    }


}
