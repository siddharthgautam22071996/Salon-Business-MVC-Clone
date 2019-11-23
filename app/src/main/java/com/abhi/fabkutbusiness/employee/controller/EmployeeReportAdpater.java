package com.abhi.fabkutbusiness.employee.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.model.Crmmodel;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmListData;
import com.abhi.fabkutbusiness.crm.view.CrmHistory;
import com.abhi.fabkutbusiness.crm.view.CrmHistory1;
import com.abhi.fabkutbusiness.crm.view.CrmTab;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpReport;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpReportData;
import com.abhi.fabkutbusiness.employee.view.EmployeeReport;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EmployeeReportAdpater extends
        RecyclerView.Adapter<EmployeeReportAdpater.MyViewHolder> implements View.OnClickListener, AdapterView.OnItemSelectedListener,RetrofitApi.ResponseListener{

    private ArrayList<ResponseEmpReportData> dataItem;
    private Context mContext;
    private LayoutInflater inflater;
    private String leaveType = "0",check,str_fromDate,str_ToDate;
    private LinearLayout layout_toDate,Layout_fromDate;
    private int day;
    private AlertDialog deleteDialog;
    private int month;
    private ArrayList<ResponseModelEmployeeData> employeeDatas = new ArrayList<>();
    int a;
    private int year;
    private Spinner employee;
   private String empName, businessId ,LeaveId;
   private List<String> Emp = new ArrayList<>();
   private List<String> Emp_id = new ArrayList<>();
    private TextView tv_reponse , report ,leave,halfDay,fullDay,longDays,fromDate,Todate,submit;

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        deleteDialog.dismiss();

    }

    @Override
    public void _onNext1(Object obj) {

    }


    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView name,from,tv_to,tv_total_days;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_empName);
            from = (TextView) view.findViewById(R.id.tv_from);
            tv_to = (TextView) view.findViewById(R.id.tv_to);
            tv_total_days= (TextView) view.findViewById(R.id.tv_total_days);
            final Context context = view.getContext();
            businessId = Utility.getPreferences(context,Constants.keySalonBusinessId);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    LeaveId = dataItem.get(getLayoutPosition()).getLEAVE_ID();
                    updateDiaolog(getLayoutPosition(),context  );
                    a= getLayoutPosition();
                    return false;
                }
            });


        }



    }


    public EmployeeReportAdpater(Context mContext, ArrayList<ResponseEmpReportData> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseEmpReportData c = dataItem.get(position);
        holder.name.setText(c.getName());
        holder.from.setText(c.getFromDate());
        holder.tv_total_days.setText(c.getLeaveTotal());
        holder.tv_to.setText(""+c.getToDate());


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee_report,parent, false);

        return new MyViewHolder(v);
    }




    private void leave_findByViewByID(View view , int pos ,Context context ) {
        employeeDatas = Utility.getResponseModelEmployee(context, Constants.keySalonEmployeeData).getData();
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
        Utility.spinner(context,employee,Emp);
        layout_toDate = (LinearLayout)view.findViewById(R.id.toDateLayout);
        Layout_fromDate = (LinearLayout)view.findViewById(R.id.fromDate_lauout);
        iniUpdataData(pos,context);

    }

    private void iniUpdataData(int pos,Context context) {

        if (dataItem.get(pos).getLeaveType().equalsIgnoreCase("1")){
            iniHalDay();
        }else if (dataItem.get(pos).getLeaveType().equalsIgnoreCase("0") &&
                dataItem.get(pos).getFromDate().equalsIgnoreCase(dataItem.get(pos).getToDate())){

            iniFullDay();
        }else if  (dataItem.get(pos).getLeaveType().equalsIgnoreCase("0") &&
                !dataItem.get(pos).getFromDate().equalsIgnoreCase(dataItem.get(pos).getToDate())){
            iniLondData(pos);

        }

    }

    private void iniLondData(int pos) {
        leaveType = "2";
        check = "1";
        fromDate.setText(dataItem.get(pos).getFromDate());
        Todate.setText(dataItem.get(pos).getToDate());
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
    }

    private void getDate(final TextView textView ,Context context) {
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

        DatePickerDialog dpDialog=new DatePickerDialog(context, listener, year, month, day);
        dpDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        empName = Emp_id.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void leveSubmit(Context context) {
        tv_reponse.setVisibility(View.GONE);

        if (leaveType.equalsIgnoreCase("0")){
            Utility.showToast(context,"Please Choose One Option");

        }else if (leaveType.equalsIgnoreCase("2") && check.equalsIgnoreCase("2")){
            RetrofitApi.getInstance().employeeLeaveUdateApi(context,this,
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

            if (isValidated(str_fromDate,str_ToDate)){
                RetrofitApi.getInstance().employeeLeaveUdateApi(context, this,
                        "" + businessId,
                        "" + empName,
                        "" + str_fromDate,
                        "" + str_ToDate,
                        "",
                        "" + leaveType,
                        "",
                        ""+LeaveId);
            }

        }else if (leaveType.equalsIgnoreCase("1")){
            RetrofitApi.getInstance().employeeLeaveUdateApi(context,this,
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
    public void onClick(View view) {
        Context context = view.getContext();
        switch (view.getId()){
            case R.id.tv_leave_submit:
                leveSubmit(context);
                break;

            case R.id.tv_halfDay:
                iniHalDay();

                break;

            case R.id.tv_fullDays:
                iniFullDay();
                break;

            case R.id.tv_longDays:
                iniLondData(a);

                break;

            case R.id.tv_from_date:

                getDate(fromDate,context);
                break;

            case R.id.tv_toDate:
                getDate(Todate,context);
                break;



        }
    }
    private boolean isValidated(String strFrom, String strto ) {

        if (strFrom.length() == 0) {
            tv_reponse.setVisibility(View.VISIBLE);
            tv_reponse.setText("Please fill From date ");
            return false;
        }

        if (strto.length() == 0) {
            tv_reponse.setVisibility(View.VISIBLE);
            tv_reponse.setText("Please fill To Date");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date from , to;
        try {
            from = sdf.parse(strFrom);
            to = sdf.parse(strto);

            if (from.after(to)){
                tv_reponse.setVisibility(View.VISIBLE);
                tv_reponse.setText("Invalid date ");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return true;
    }

    private void updateDiaolog(int i , Context context) {
        LayoutInflater factory = LayoutInflater.from(context);
        final View deleteDialogView = factory.inflate(R.layout.employee_leave, null);
        deleteDialog = new AlertDialog.Builder(context).create();
        leave_findByViewByID(deleteDialogView ,i ,context);
        TextView title = (TextView)deleteDialogView.findViewById(R.id.title) ;
        title.setText("Employee Leave Update");
        deleteDialog.setView(deleteDialogView);

        deleteDialog.show();
    }

}



