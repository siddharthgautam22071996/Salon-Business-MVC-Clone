package com.abhi.fabkutbusiness.report.view;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.TodaysStatementAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.report.controller.StatsEmpList;
import com.abhi.fabkutbusiness.report.model.modelBookingDataForReport;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 25/07/18.
 */

public class BookingHistoryActivity extends AppCompatActivity implements View.OnClickListener{

    ResponseModelBillingList _data;
    StatsEmpList mAdapterStylist;
    RecyclerView rvStylist;
    Bundle bundle;
    TextView tvTitle;
    ImageView iconLeft;
    View actionBarView;
    String date;
    static String employee;
    ArrayList<ResponseModelAppointmentsData> responseModelAppointmentsData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_stats);
        bundle = getIntent().getExtras();
        employee = bundle.getString("emp");
        date = bundle.getString("date");

        setActionBarView();
        findViewById();
        _data  = Utility.getResponseModelBillingListData(this, Constants.keySalonBillingListData);


        //iniData(Utility.getCurrentDate(Constants.displayDateFormat));
        iniData(date);
    }

    public static String getEmpName(){
        String emp="";
        emp = employee;
        return emp;
    }

    private void iniData(String reporDate) {
        responseModelAppointmentsData.clear();
        if ((_data.getDATA().get(reporDate) != null)) {
            for (int i = 0; i<_data.getDATA().get(reporDate).size(); i++) {
                for (int k = 0; k < _data.getDATA().get(reporDate).get(i).getServices().size(); k++) {
                    if (employee.equalsIgnoreCase(_data.getDATA().get(reporDate).get(i).getServices().get(k).getEmployee_name())) {
                        //responseModelAppointmentsData.addAll(i,_data.getDATA().get(reporDate));
                       if(responseModelAppointmentsData.contains(_data.getDATA().get(reporDate).get(i))) {

                        }else {
                           responseModelAppointmentsData.add(_data.getDATA().get(reporDate).get(i));
                       }


                    }
                }
            }

            if (responseModelAppointmentsData.size() == 0){
                TodaysStatementAdapter todaysStatementAdapter = new TodaysStatementAdapter(this, responseModelAppointmentsData,R.layout.item_billing ,2);
                rvStylist.setAdapter(todaysStatementAdapter);
                Toast.makeText(this, "No Booking On this Date", Toast.LENGTH_SHORT).show();
            }
            TodaysStatementAdapter todaysStatementAdapter = new TodaysStatementAdapter(this, responseModelAppointmentsData,R.layout.item_billing ,2);
            rvStylist.setAdapter(todaysStatementAdapter);
        }


    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }
    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText(employee);

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        rvStylist = (RecyclerView)findViewById(R.id.rvEmpList);
        rvStylist.setVisibility(View.VISIBLE);

        PieChart pieChart = (PieChart)findViewById(R.id.chart);
        pieChart.setVisibility(View.GONE);
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
