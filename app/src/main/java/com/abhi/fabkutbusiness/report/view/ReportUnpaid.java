package com.abhi.fabkutbusiness.report.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.report.controller.UnpaidReportAdapter;
import com.abhi.fabkutbusiness.report.model.ModelUnpaidData;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 25/07/18.
 */

public class ReportUnpaid extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    TextView tvTitle;
    private ArrayList<ResponseModelCustomerData> data;
    ImageView iconLeft;
    View actionBarView;
    private ArrayList<ResponseModelCustomerData> unpaidData = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upaid_customer);
        setActionBarView();
        findViewById();
    }


    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Unpaid Customer");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        listView = (ListView)findViewById(R.id.listView);
       // ArrayList<ResponseModelAppointmentsData> responseModelAppointmentsData  = Utility.getResponseModelAppointments(this, Constants.keySalonCancelBookingData).getData();

        data = Utility.getResponseModelCustomer(this, Constants.keySalonCustomerData).getData();
        iniData();
        //ResponseModelBillingList _data = Utility.getResponseModelBillingListData(this, Constants.keySalonBillingListData);

    }
    private void iniData() {


        for (int j = 0; j <data.size(); j++) {
            if (data.get(j).getPrevious_balance()!=0) {
                unpaidData.add(data.get(j));

            }

        }

        UnpaidReportAdapter reportAdapter = new UnpaidReportAdapter(this,unpaidData);
        listView.setAdapter(reportAdapter);
    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionbar_view_icon_left:
                finish();
                break;
        }
    }

}
