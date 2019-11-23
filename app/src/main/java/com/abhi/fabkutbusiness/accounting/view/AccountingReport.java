package com.abhi.fabkutbusiness.accounting.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.AccountingReportAdapter;
import com.abhi.fabkutbusiness.accounting.model.ResponseAccountReport;
import com.abhi.fabkutbusiness.booking.controller.CustomerDataAdapter;
import com.abhi.fabkutbusiness.crm.controller.CrmAdapter;
import com.abhi.fabkutbusiness.crm.controller.CrmSearchAdpater;
import com.abhi.fabkutbusiness.crm.model.Crmmodel;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class AccountingReport extends AppCompatActivity implements RetrofitApi.ResponseListener,View.OnClickListener {

    RecyclerView lv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView iconLeft;
    LayoutInflater inflater;
    TextView tvTitle;
    AccountingReportAdapter accountingReportAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoounting_report);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setActionBarView();
        findViewById();
        String businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);
        final AppBarLayout collapsingToolbarLayout = (AppBarLayout) findViewById(R.id.appbar);

//
        RetrofitApi.getInstance().AccountReportApi(this, this, businessId);

    }


    private void findViewById() {
        lv = (RecyclerView) findViewById(R.id.lv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(llm);



//        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
  //      tvTitle.setText("Customer");
       iconLeft = (ImageView)toolbar.findViewById(R.id.iv_back);
       tvTitle = (TextView)toolbar.findViewById(R.id.actionbar_view_title);
       iconLeft.setOnClickListener(this);


    }
    public void back(View view){
        finish();
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        ResponseAccountReport responseAccountReport  = (ResponseAccountReport) obj;
        Log.d("abc", "" + responseAccountReport);

        if (responseAccountReport.getSTATUS().equalsIgnoreCase("200")) {

            accountingReportAdapter = new AccountingReportAdapter(AccountingReport.this, responseAccountReport.getData());
            lv.setAdapter(accountingReportAdapter);
            lv.setNestedScrollingEnabled(false);

        } else {
            Utility.showToast(this,responseAccountReport.getMESSAGE());
        }
    }

    @Override
    public void _onNext1(Object obj) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
