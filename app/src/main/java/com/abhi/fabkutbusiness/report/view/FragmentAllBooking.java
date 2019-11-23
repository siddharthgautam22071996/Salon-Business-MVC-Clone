package com.abhi.fabkutbusiness.report.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.TodaysStatementAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 07/12/18.
 */

public class FragmentAllBooking extends Fragment implements View.OnClickListener {
    View rootView;
    RecyclerView rvALLBooking;
    TextView tvOnline,tvAll,tvOffline;
    ArrayList<ResponseModelAppointmentsData> responseModelAppointmentsData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_booking_report, container, false);
        findViewById();
        setData();
        return rootView;
    }

    private void findViewById() {
        rvALLBooking = (RecyclerView)rootView.findViewById(R.id.rvBooingReport);
        tvAll = (TextView)rootView.findViewById(R.id.tvAll);
        tvAll.setOnClickListener(this);
        tvOffline = (TextView)rootView.findViewById(R.id.tvOffline);
        tvOffline.setOnClickListener(this);
        tvOnline = (TextView)rootView.findViewById(R.id.tvOnline);
        tvOnline.setOnClickListener(this);
    }

    private void setData() {
        responseModelAppointmentsData = Utility.getResponseModelAppointments(getActivity(), Constants.keySalonNotSynAppointmentsData).getData();
        TodaysStatementAdapter todaysStatementAdapter = new TodaysStatementAdapter(getContext(), responseModelAppointmentsData,R.layout.item_billing,1);
        rvALLBooking.setAdapter(todaysStatementAdapter);
        rvALLBooking.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAll:
                tvAll.setBackgroundResource(R.drawable.layout_17);
                tvAll.setTextColor(Color.WHITE);
                tvOffline.setBackgroundResource(R.drawable.layout_bg16);
                tvOffline.setTextColor(Color.GRAY);
                tvOnline.setBackgroundResource(R.drawable.layout_bg16);
                tvOnline.setTextColor(Color.GRAY);
                break;

            case R.id.tvOffline:
                tvOffline.setBackgroundResource(R.drawable.layout_17);
                tvOffline.setTextColor(Color.WHITE);
                tvOnline.setBackgroundResource(R.drawable.layout_bg16);
                tvOnline.setTextColor(Color.GRAY);
                tvAll.setBackgroundResource(R.drawable.layout_bg16);
                tvAll.setTextColor(Color.GRAY);
                break;

            case R.id.tvOnline:
                tvOnline.setBackgroundResource(R.drawable.layout_17);
                tvOnline.setTextColor(Color.WHITE);
                tvAll.setBackgroundResource(R.drawable.layout_bg16);
                tvAll.setTextColor(Color.GRAY);
                tvOffline.setBackgroundResource(R.drawable.layout_bg16);
                tvOffline.setTextColor(Color.GRAY);
                break;
        }

    }
}
