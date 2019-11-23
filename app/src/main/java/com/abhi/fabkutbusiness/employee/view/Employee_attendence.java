package com.abhi.fabkutbusiness.employee.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.employee.controller.EmployeeAttendenceAdapter;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpCheckIn;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.ResponseModel1;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by siddharthgautam on 13/04/18.
 */

public class Employee_attendence extends AppCompatActivity implements RetrofitApi.ResponseListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    RecyclerView lv;
    EditText Search;
    View actionBarView;
    ImageView iconLeft;
    Spinner employee;
    String businessId, empName;
    LayoutInflater inflater;
    LinearLayout layout_toDate, Layout_fromDate;
    String leaveType = "0", check, str_fromDate, str_ToDate;
    TextView tv_reponse, report, leave, halfDay, fullDay, longDays, fromDate, Todate, submit;
    EmployeeAttendenceAdapter employeeAttendenceAdapter;
    AutoCompleteTextView actSearch;
    ArrayList<ResponseModelEmployeeData> employeeDatas = new ArrayList<>();
    Toolbar toolbar;
    AlertDialog deleteDialog;
    List<String> Emp = new ArrayList<>();
    List<String> Emp_id = new ArrayList<>();
    //ArrayList<CrmList_model> crmList_models = new ArrayList<CrmList_model>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_attendence);
        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //setActionBarView();
        findViewById();

        businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);
        final AppBarLayout collapsingToolbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        employeeDatas = Utility.getResponseModelEmployee(this, Constants.keySalonEmployeeData).getData();
        initData();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                System.out.println("adsjhafs :"+i);
                if(i==0){
                    RetrofitApi.getInstance().GetEmployeCheckInApiMethod(Employee_attendence.this, Employee_attendence.this,
                            Utility.getPreferences(Employee_attendence.this, Constants.keySalonBusinessId));
                }else if(i==1){
                    RetrofitApi.getInstance().GetEmployeCheckOutApiMethod(Employee_attendence.this, Employee_attendence.this,
                            Utility.getPreferences(Employee_attendence.this, Constants.keySalonBusinessId));

                }





            }

            @Override
            public void onPageScrollStateChanged(int i) {


            }
        });




    }

    private void initData() {

    }






    private void findViewById() {
        lv = (RecyclerView) findViewById(R.id.lv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        Search = (EditText) findViewById(R.id.et_search);
        setSupportActionBar(mToolbar);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        report = (TextView) findViewById(R.id.tv_emplReport);
        report.setOnClickListener(this);
        leave = (TextView) findViewById(R.id.tv_empLeave);
        leave.setOnClickListener(this);
//        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        //      tvTitle.setText("Customer");
        iconLeft = (ImageView) mToolbar.findViewById(R.id.back);

        iconLeft.setOnClickListener(this);

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        //adapter.addFragment(new Personal_Info(), "PERSONAL");
        adapter.addFragment(new Employee_checkIn(), "Check In");
        adapter.addFragment(new Employee_checkOut(), "Check Out");
        //adapter.addFragment(new EmployeSummary(), "Summery");
        viewPager.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        empName = Emp_id.get(i);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void back(View view) {
        finish();

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        if (obj instanceof ResponseEmpCheckIn){
            final ResponseEmpCheckIn responseEmpCheckIn = (ResponseEmpCheckIn) obj;
            if (responseEmpCheckIn.getSTATUS().equalsIgnoreCase("200")){
                employeeAttendenceAdapter.notifyDataSetChanged();

            }
        }else {
            final ResponseModel1 responseModel1 = (ResponseModel1) obj;
            Log.d("abc", "" + responseModel1);

            if (responseModel1.getSTATUS().equalsIgnoreCase("200")) {

                Utility.showToast(this, responseModel1.getMESSAGE());
                deleteDialog.dismiss();


            } else {
                tv_reponse.setVisibility(View.VISIBLE);
                tv_reponse.setText("* " + responseModel1.getMESSAGE());
            }
        }



    }

    @Override
    public void _onNext1(Object obj) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.tv_emplReport:
                if (Utility.isInternetConnected(this)) {
                    startActivity(new Intent(Employee_attendence.this, EmployeeReport.class));
                }
                break;

            case R.id.tv_empLeave:
                leaveDialog();
                break;
            case R.id.tv_leave_submit:
                if (Utility.isInternetConnected(this)) {
                    leveSubmit();
                }

                break;

            case R.id.tv_halfDay:
                leaveType = "1";
                layout_toDate.setVisibility(View.GONE);
                Layout_fromDate.setVisibility(View.GONE);
                halfDay.setBackgroundResource(R.drawable.layout_17);
                halfDay.setTextColor(Color.WHITE);
                fullDay.setBackgroundResource(R.drawable.layout_bg16);
                fullDay.setTextColor(Color.GRAY);
                longDays.setBackgroundResource(R.drawable.layout_bg16);
                longDays.setTextColor(Color.GRAY);
                break;

            case R.id.tv_fullDays:
                leaveType = "0";
                check = "2";
                Layout_fromDate.setVisibility(View.GONE);
                layout_toDate.setVisibility(View.GONE);
                fullDay.setBackgroundResource(R.drawable.layout_17);
                fullDay.setTextColor(Color.WHITE);
                halfDay.setBackgroundResource(R.drawable.layout_bg16);
                halfDay.setTextColor(Color.GRAY);
                longDays.setBackgroundResource(R.drawable.layout_bg16);
                longDays.setTextColor(Color.GRAY);
                break;

            case R.id.tv_longDays:
                leaveType = "0";
                check = "1";
                layout_toDate.setVisibility(View.VISIBLE);
                Layout_fromDate.setVisibility(View.VISIBLE);
                longDays.setBackgroundResource(R.drawable.layout_17);
                longDays.setTextColor(Color.WHITE);
                fullDay.setBackgroundResource(R.drawable.layout_bg16);
                fullDay.setTextColor(Color.GRAY);
                halfDay.setBackgroundResource(R.drawable.layout_bg16);
                halfDay.setTextColor(Color.GRAY);
                break;

            case R.id.tv_from_date:
                getDate(fromDate);
                break;

            case R.id.tv_toDate:
                getDate(Todate);
                break;

        }
    }

    private void getDate(final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                textView.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();
    }

    private void leveSubmit() {
        tv_reponse.setVisibility(View.GONE);

        if (leaveType.equalsIgnoreCase("2")) {
            Utility.showToast(this, "Please Choose One Option");

        } else if (leaveType.equalsIgnoreCase("0") && check.equalsIgnoreCase("2")) {
            RetrofitApi.getInstance().employeeLeaveApi(this, this,
                    "" + businessId,
                    "" + empName,
                    "" + SimpleDateFormat.getDateInstance().format(new Date()),
                    "" + SimpleDateFormat.getDateInstance().format(new Date()),
                    "",
                    "" + leaveType,
                    "1");

        } else if (leaveType.equalsIgnoreCase("0") && check.equalsIgnoreCase("1")) {
            str_fromDate = fromDate.getText().toString().trim();
            str_ToDate = Todate.getText().toString().trim();

            if (isValidated(str_fromDate, str_ToDate))
                RetrofitApi.getInstance().employeeLeaveApi(this, this,
                        "" + businessId,
                        "" + empName,
                        "" + str_fromDate,
                        "" + str_ToDate,
                        "",
                        "" + leaveType,
                        "");

        } else if (leaveType.equalsIgnoreCase("1")) {
            RetrofitApi.getInstance().employeeLeaveApi(this, this,
                    "" + businessId,
                    "" + empName,
                    "" + SimpleDateFormat.getDateInstance().format(new Date()),
                    "" + SimpleDateFormat.getDateInstance().format(new Date()),
                    "",
                    "" + leaveType,
                    "1");


        }

    }

    private boolean isValidated(String strFrom, String strto) {

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
        Date from, to;
        try {
            from = sdf.parse(strFrom);
            to = sdf.parse(strto);

            if (from.after(to)) {
                tv_reponse.setVisibility(View.VISIBLE);
                tv_reponse.setText("Invalid date ");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return true;
    }

    private void leaveDialog() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.employee_leave, null);
        deleteDialog = new AlertDialog.Builder(this).create();
        leave_findByViewByID(deleteDialogView);
        deleteDialog.setView(deleteDialogView);


        deleteDialog.show();
    }

    private void leave_findByViewByID(View view) {
        Emp.clear();
        Emp_id.clear();
        halfDay = (TextView) view.findViewById(R.id.tv_halfDay);
        halfDay.setOnClickListener(this);
        fullDay = (TextView) view.findViewById(R.id.tv_fullDays);
        fullDay.setOnClickListener(this);
        longDays = (TextView) view.findViewById(R.id.tv_longDays);
        longDays.setOnClickListener(this);
        fromDate = (TextView) view.findViewById(R.id.tv_from_date);
        fromDate.setOnClickListener(this);
        Todate = (TextView) view.findViewById(R.id.tv_toDate);
        Todate.setOnClickListener(this);
        submit = (TextView) view.findViewById(R.id.tv_leave_submit);
        submit.setOnClickListener(this);
        tv_reponse = (TextView) view.findViewById(R.id.tv_leave_reponse);
        employee = (Spinner) view.findViewById(R.id.spinner_employeeName);
        employee.setOnItemSelectedListener(this);
        for (int i = 0; i < employeeDatas.size(); i++) {
            Emp.add(employeeDatas.get(i).getEmp_name());
            Emp_id.add(employeeDatas.get(i).getEmp_id());
        }
        Utility.spinner(this, employee, Emp);
        layout_toDate = (LinearLayout) view.findViewById(R.id.toDateLayout);
        Layout_fromDate = (LinearLayout) view.findViewById(R.id.fromDate_lauout);


    }

    @Override
    public void onBackPressed() {

        finish();

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {

            System.out.println("hjkhkh"+position);

            return mFragments.get(position);

        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {



            return mFragmentTitles.get(position);
        }
    }

}
