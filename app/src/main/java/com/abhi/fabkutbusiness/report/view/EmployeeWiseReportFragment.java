package com.abhi.fabkutbusiness.report.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.TodaysStatementAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillPaymentData;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.booking.controller.StylistAdapter;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.SelectServiceActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.report.controller.StatsEmpList;
import com.abhi.fabkutbusiness.report.model.modelBookingDataForReport;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by siddharthgautam on 23/08/18.
 */

public class EmployeeWiseReportFragment extends Fragment {
    View rootView;
    ResponseModelBillingList _data;
    StatsEmpList mAdapterStylist;
    RecyclerView rvStylist;
    String date;
    Float tempData= Float.valueOf(0);
    List<String> sale =new ArrayList<>();
    List<String> employee = new ArrayList<>();
    ArrayList<modelBookingDataForReport> modelBookingDataForReport = new ArrayList<>();

    List<PieEntry> pieEntryList =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.employee_stats, container, false);
        findViewById();
        date = ProgressReportActivity.getDate();

        setPieEntryList();


        return rootView;
    }

    private void findViewById() {
        rvStylist = (RecyclerView)rootView.findViewById(R.id.rvEmpList);
        rvStylist.setVisibility(View.VISIBLE);
        rvStylist.setNestedScrollingEnabled(false);
    }


    private void setPieEntryList() {
        pieEntryList.clear();
        modelBookingDataForReport.clear();
        //Toast.makeText(getActivity(), ""+date, Toast.LENGTH_SHORT).show();

        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(getActivity(), Constants.keySalonEmployeeData).getData();


        _data  = Utility.getResponseModelBillingListData(getActivity(), Constants.keySalonBillingListData);


        for (int i = 0 ; i<employeeDatas.size();i++){
            tempData= Float.valueOf(0);
            if ((_data.getDATA().get(date) != null)) {
                for (int j = 0; j < _data.getDATA().get(date).size(); j++) {
                    for (int k=0; k <_data.getDATA().get(date).get(j).getServices().size() ; k++) {
                        if (employeeDatas.get(i).getEmp_id().equalsIgnoreCase(_data.getDATA().get(date).get(j).getServices().get(k).getEmployee_id())) {
                            tempData = tempData + Integer.parseInt(_data.getDATA().get(date).get(j).getServices().get(k).getRate());
                        }

                    }
                }
            }else {
                tempData = Float.valueOf(0);

            }


            sale.add(String.valueOf(tempData));
            employee.add(employeeDatas.get(i).getEmp_name());
            pieEntryList.add(new PieEntry(tempData,""+employeeDatas.get(i).getEmp_name()));
            modelBookingDataForReport.add(new modelBookingDataForReport(""+employeeDatas.get(i).getEmp_name(),
                    ""+tempData,
                    ""+employeeDatas.get(i).getEmp_profile_image(),date));


        }
        setupPiChart();

        mAdapterStylist = new StatsEmpList(getActivity(),modelBookingDataForReport,R.layout.item_stats_emp);
        rvStylist.setAdapter(mAdapterStylist);

    }

    private void setupPiChart() {
        PieDataSet pieDataSet =new PieDataSet(pieEntryList,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        PieData pieData =new PieData(pieDataSet);


        final PieChart pieChart = (PieChart)rootView.findViewById(R.id.chart);
       /* Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

*/
        pieChart.setCenterText("Stylist\nSale");
        pieChart.setCenterTextColor(R.color.colorBlue4);
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);





        pieChart.animateY(1500);
        pieChart.invalidate();



        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h ) {
                Log.d("Emp", "onValueSelected: " + e.toString());
                Log.d("Emp", "onValueSelected: " + h.toString());

                Intent intent =new Intent(getActivity(), BookingHistoryActivity.class);
                intent.putExtra("emp", employee.get((int) h.getX()));
                intent.putExtra("date",date);
                startActivity(intent);

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

}
