package com.abhi.fabkutbusiness.employee.view;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.employee.controller.EmployeeReportAdpater;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpReport;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpReportData;
import com.abhi.fabkutbusiness.inventory.itemMaster.controller.MyStockAdapter;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseMyStockData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.ResponseModel;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public class EmployeeReport extends AppCompatActivity implements View.OnClickListener ,RetrofitApi.ResponseListener {
    View actionBarView;
    RecyclerView listView;
    ImageView back;
    AlertDialog deleteDialog;
    TextView new_item,tvTitle;
    List<String> Emp = new ArrayList<>();
    List<String> Emp_id = new ArrayList<>();
    Spinner employee;
    String empName, businessId ,LeaveId;
    LinearLayout layout_toDate,Layout_fromDate;
    private int day;
    private int month;
    private int year;
    ArrayList<ResponseModelEmployeeData> employeeDatas = new ArrayList<>();
    EmployeeReportAdpater employeeReportAdpater;
    String leaveType = "0",check,str_fromDate,str_ToDate;
    TextView tv_reponse , report ,leave,halfDay,fullDay,longDays,fromDate,Todate,submit;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_report);
        //setActionBarView();
        findViewById();
        iniData();
    }

    private void iniData() {

        businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);
        RetrofitApi.getInstance().EmployeeLeaveReportApiMethod(this,this,
                Utility.getPreferences(this, Constants.keySalonBusinessId));
    }

    private void findViewById() {
        listView = (RecyclerView) findViewById(R.id.listView);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);
        
    }

   /* private void updateDiaolog(ResponseEmpReport responseEmpReportData,int i) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.employee_leave, null);
        deleteDialog = new AlertDialog.Builder(this).create();
        leave_findByViewByID(deleteDialogView ,i,responseEmpReportData);
        TextView title = (TextView)deleteDialogView.findViewById(R.id.title) ;
        title.setText("Employee Leave Update");
        deleteDialog.setView(deleteDialogView);



        deleteDialog.show();
    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.tv_leave_submit:
                leveSubmit();
                break;

            /*case R.id.tv_halfDay:
                iniHalDay();

                break;

            case R.id.tv_fullDays:
                iniFullDay();
                break;

            case R.id.tv_longDays:
                iniLondData();

                break;

            case R.id.tv_from_date:
                getDate(fromDate);
                break;

            case R.id.tv_toDate:
                getDate(Todate);
                break;
*/


        }
    }

  /*  private void iniLondData() {
        leaveType = "2";
        check = "1";
        layout_toDate.setVisibility(View.VISIBLE);
        Layout_fromDate.setVisibility(View.VISIBLE);
        longDays.setBackgroundResource(R.drawable.layout_17);
        longDays.setTextColor(Color.WHITE);
        fullDay.setBackgroundResource(R.drawable.layout_bg16);
        fullDay.setTextColor(Color.GRAY);
        halfDay.setBackgroundResource(R.drawable.layout_bg16);
        halfDay.setTextColor(Color.GRAY);
    }

    private void iniFullDay() {
        leaveType = "2";
        check = "2";
        Layout_fromDate.setVisibility(View.GONE);
        layout_toDate.setVisibility(View.GONE);
        fullDay.setBackgroundResource(R.drawable.layout_17);
        fullDay.setTextColor(Color.WHITE);
        halfDay.setBackgroundResource(R.drawable.layout_bg16);
        halfDay.setTextColor(Color.GRAY);
        longDays.setBackgroundResource(R.drawable.layout_bg16);
        longDays.setTextColor(Color.GRAY);
    }

    private void iniHalDay() {
        leaveType = "1";
        layout_toDate.setVisibility(View.GONE);
        Layout_fromDate.setVisibility(View.GONE);
        halfDay.setBackgroundResource(R.drawable.layout_17);
        halfDay.setTextColor(Color.WHITE);
        fullDay.setBackgroundResource(R.drawable.layout_bg16);
        fullDay.setTextColor(Color.GRAY);
        longDays.setBackgroundResource(R.drawable.layout_bg16);
        longDays.setTextColor(Color.GRAY);
    }*/

    private void getDate(final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                textView.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }};

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();
    }

    private void leveSubmit() {
        tv_reponse.setVisibility(View.GONE);

        if (leaveType.equalsIgnoreCase("0")){
            Utility.showToast(this,"Please Choose One Option");

        }else if (leaveType.equalsIgnoreCase("2") && check.equalsIgnoreCase("2")){
            RetrofitApi.getInstance().employeeLeaveUdateApi(this,this,
                    ""+businessId,
                    ""+empName,
                    ""+ SimpleDateFormat.getDateInstance().format(new Date()),
                    ""+SimpleDateFormat.getDateInstance().format(new Date()),
                    "",
                    ""+leaveType,
                    "1",
                    ""+LeaveId);

        }else if (leaveType.equalsIgnoreCase("2") && check.equalsIgnoreCase("1")){
            str_fromDate = fromDate.getText().toString();
            str_ToDate =  Todate.getText().toString();
            if (str_fromDate.length() == 0 && str_ToDate.length() ==0) {
                RetrofitApi.getInstance().employeeLeaveUdateApi(this, this,
                        "" + businessId,
                        "" + empName,
                        "" + str_fromDate,
                        "" + str_ToDate,
                        "",
                        "" + leaveType,
                        "",
                        ""+LeaveId);
            }else{
                tv_reponse.setVisibility(View.VISIBLE);
                tv_reponse.setText("Please fill From date or To Date");
            }

        }else if (leaveType.equalsIgnoreCase("1")){
            RetrofitApi.getInstance().employeeLeaveUdateApi(this,this,
                    ""+businessId,
                    ""+empName,
                    ""+ SimpleDateFormat.getDateInstance().format(new Date()),
                    ""+SimpleDateFormat.getDateInstance().format(new Date()),
                    "",
                    ""+leaveType,
                    "1",
                    ""+LeaveId);



        }

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof  ResponseEmpReport){
            final ResponseEmpReport responseEmpReport =(ResponseEmpReport) obj;
            if (responseEmpReport.getSTATUS().equalsIgnoreCase("200")){
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                listView.setLayoutManager(llm);
                employeeReportAdpater = new EmployeeReportAdpater(getApplicationContext(),responseEmpReport.getData());
                listView.setAdapter(employeeReportAdpater);
                /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                        updateDiaolog(responseEmpReport,i);
                        return false;
                    }
                });*/
            }else{
                Utility.showToast(this,""+responseEmpReport.getMESSAGE());
            }
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }

 /*   private void leave_findByViewByID(View view , int pos ,ResponseEmpReport responseEmpReportData) {
        employeeDatas = Utility.getResponseModelEmployee(this,Constants.keySalonEmployeeData).getData();
        Emp.clear();
        Emp_id.clear();
        halfDay = (TextView)view.findViewById(R.id.tv_halfDay);
        halfDay.setOnClickListener(this);
        fullDay = (TextView)view.findViewById(R.id.tv_fullDays);
        fullDay.setOnClickListener(this);
        longDays = (TextView)view.findViewById(R.id.tv_longDays);
        longDays.setOnClickListener(this);
        fromDate = (TextView)view.findViewById(R.id.tv_from_date);
        fromDate.setOnClickListener(this);
        Todate = (TextView)view.findViewById(R.id.tv_toDate);
        Todate.setOnClickListener(this);
        submit = (TextView)view.findViewById(R.id.tv_leave_submit);
        submit.setOnClickListener(this);
        tv_reponse = (TextView)view.findViewById(R.id.tv_leave_reponse);
        employee = (Spinner)view.findViewById(R.id.spinner_employeeName);
        employee.setOnItemSelectedListener(this);
        for (int j = 0; j < employeeDatas.size(); j++) {
            Emp.add(employeeDatas.get(j).getEmp_name());
            Emp_id.add(employeeDatas.get(j).getEmp_id());
        }
        Utility.spinner(this,employee,Emp);
        layout_toDate = (LinearLayout)view.findViewById(R.id.toDateLayout);
        Layout_fromDate = (LinearLayout)view.findViewById(R.id.fromDate_lauout);
        iniUpdataData(pos,responseEmpReportData);

    }

    private void iniUpdataData(int pos,ResponseEmpReport responseEmpReportData) {

        if (responseEmpReportData.getData().get(pos).getLeaveType().equalsIgnoreCase("1")){
            iniHalDay();
        }else if (responseEmpReportData.getData().get(pos).getLeaveType().equalsIgnoreCase("0") &&
                responseEmpReportData.getData().get(pos).getFromDate().equalsIgnoreCase(responseEmpReportData.getData().get(pos).getToDate())){

            iniFullDay();
        }else if  (responseEmpReportData.getData().get(pos).getLeaveType().equalsIgnoreCase("0") &&
                !responseEmpReportData.getData().get(pos).getFromDate().equalsIgnoreCase(responseEmpReportData.getData().get(pos).getToDate())){
            iniLondData();

        }

    }

*/

   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        empName = Emp_id.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}
