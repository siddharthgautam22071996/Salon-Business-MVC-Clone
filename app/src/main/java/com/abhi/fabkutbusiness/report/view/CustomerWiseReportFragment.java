package com.abhi.fabkutbusiness.report.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.TodaysStatementAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.report.controller.StatsEmpList;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthgautam on 23/08/18.
 */

public class CustomerWiseReportFragment extends Fragment {
    View rootView;
    ResponseModelBillingList _data;
    StatsEmpList mAdapterStylist;
    RecyclerView rvStylist;
    ArrayList<ResponseModelAppointmentsData> responseModelAppointmentsData = new ArrayList<>();

    List<PieEntry> pieEntryList =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.employee_stats, container, false);
        _data  = Utility.getResponseModelBillingListData(getActivity(), Constants.keySalonBillingListData);

        findViewById();
        iniData(ProgressReportActivity.getDate());
        //Toast.makeText(getActivity(), ""+ProgressReportActivity.getDate(), Toast.LENGTH_SHORT).show();

        return rootView;
    }




    private void iniData(String reporDate) {
        responseModelAppointmentsData.clear();
        if ((_data.getDATA().get(reporDate) != null)) {
            responseModelAppointmentsData.addAll(_data.getDATA().get(reporDate));
            TodaysStatementAdapter todaysStatementAdapter = new TodaysStatementAdapter(getContext(), responseModelAppointmentsData,R.layout.item_billing,1);
            rvStylist.setAdapter(todaysStatementAdapter);
            rvStylist.setNestedScrollingEnabled(false);
        }else{
            TodaysStatementAdapter todaysStatementAdapter = new TodaysStatementAdapter(getContext(), responseModelAppointmentsData,R.layout.item_billing,1);
            rvStylist.setAdapter(todaysStatementAdapter);
            Toast.makeText(getContext(), "No Booking On this Date", Toast.LENGTH_SHORT).show();
        }

    }

    private void findViewById() {
        rvStylist = (RecyclerView)rootView.findViewById(R.id.rvEmpList);
        rvStylist.setVisibility(View.VISIBLE);

        PieChart pieChart = (PieChart)rootView.findViewById(R.id.chart);
        pieChart.setVisibility(View.GONE);
    }




}
