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
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.report.controller.SyncReportAdapter;
import com.abhi.fabkutbusiness.report.controller.UnpaidReportAdapter;

import java.util.ArrayList;

import static org.acra.ACRA.log;

/**
 * Created by siddharthgautam on 20/08/18.
 */

public class SyncData extends AppCompatActivity implements View.OnClickListener {

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
        tvTitle.setText("Sync Customer");
        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.listView);
        // ArrayList<ResponseModelAppointmentsData> responseModelAppointmentsData  = Utility.getResponseModelAppointments(this, Constants.keySalonCancelBookingData).getData();


        iniData();
        //ResponseModelBillingList _data = Utility.getResponseModelBillingListData(this, Constants.keySalonBillingListData);

    }


    private void iniData() {

        data = Utility.getResponseModelNotSyncCustomer(this, Constants.keySalonNotSYncCustomerData).getData();

        if (data.size() != 0){

            SyncReportAdapter reportAdapter = new SyncReportAdapter(this,data);
            listView.setAdapter(reportAdapter);
        }

      /*  SyncReportAdapter reportAdapter = new SyncReportAdapter(this,data);
        log.d("<<<<<<<>>>>>>>",data.get(0).getEndUser_FirstName() +" "+data.get(1).getEndUser_FirstName() );
        listView.setAdapter(reportAdapter);*/
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
