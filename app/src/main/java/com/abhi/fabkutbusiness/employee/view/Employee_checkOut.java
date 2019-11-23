package com.abhi.fabkutbusiness.employee.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.employee.controller.EmployeeAttendenceAdapter;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpCheckIn;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by siddharthgautam on 21/04/18.
 */

public class Employee_checkOut extends Fragment implements RetrofitApi.ResponseListener, RecyclerView.OnClickListener {
    View v;
    RecyclerView lv;
    LinearLayoutManager llm;
    EmployeeAttendenceAdapter employeeAttendenceAdapter;
    String bId;
    SimpleDateFormat simpleTimeFormat;
    ArrayList<ResponseModelEmployeeData> empAttendenceData = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recycler_view, container, false);

        bId = Utility.getPreferences(getActivity(), Constants.keySalonBusinessId);
        findViewById();

//
//      RetrofitApi.getInstance().CrmBasicInfoShowApiMethod(getActivity(), this,ab,User_id);

        iniData();

        return v;
    }

    private void iniData() {
        empAttendenceData.clear();

        RetrofitApi.getInstance().GetEmployeCheckOutApiMethod(getActivity(), this, "" + bId
        );

    }

    private void findViewById() {

        lv = (RecyclerView) v.findViewById(R.id.rv_employee);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(llm);


    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        if (obj instanceof ResponseEmpCheckIn) {

            final ResponseEmpCheckIn responseEmpCheckIn = (ResponseEmpCheckIn) obj;
            if (responseEmpCheckIn.getSTATUS().equalsIgnoreCase("200")) {

                employeeAttendenceAdapter = new EmployeeAttendenceAdapter(getActivity(), responseEmpCheckIn.getData(), "Check Out");
                lv.setAdapter(employeeAttendenceAdapter);

            }
        }


    }


    @Override
    public void _onNext1(Object obj) {


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resunemjjjjjjjj :afafaf ");
    }
}
