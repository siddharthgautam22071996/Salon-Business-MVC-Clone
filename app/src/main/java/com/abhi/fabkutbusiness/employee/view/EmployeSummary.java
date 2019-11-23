/*
package com.abhi.fabkutbusiness.employee.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.employee.controller.EmployeeAttendenceAdapter;
import com.abhi.fabkutbusiness.employee.controller.EmployeeSummaryAdapter;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmp;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmpSummary;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EmployeSummary extends Fragment implements RetrofitApi.ResponseListener{
    View v;
    RecyclerView lv;
    LinearLayoutManager llm;
    EmployeeSummaryAdapter employeeSummaryAdapter;
    ArrayList<ResponseModelEmployeeData> employeeData =new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recycler_view, container, false);


        findViewById();
//
        //  RetrofitApi.getInstance().CrmBasicInfoShowApiMethod(getActivity(), this,ab,User_id);
        iniData();

        return v;
    }

    private void iniData() {

        RetrofitApi.getInstance().employeSummaryApiMethod(getActivity(),this,
                ""+Utility.getPreferences(getActivity(),Constants.keySalonBusinessId),
                ""+ SimpleDateFormat.getDateInstance().format(new Date()));



    }

    private void findViewById() {

        lv=(RecyclerView) v.findViewById(R.id.rv_employee);
        lv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseModelEmp){
            ResponseModelEmp responseModelEmpSummary = (ResponseModelEmp) obj;
            if (responseModelEmpSummary.getSTATUS().equalsIgnoreCase("200")) {
                employeeSummaryAdapter = new EmployeeSummaryAdapter(getActivity(), responseModelEmpSummary.getData());
                lv.setAdapter(employeeSummaryAdapter);
            }
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }
}
*/
