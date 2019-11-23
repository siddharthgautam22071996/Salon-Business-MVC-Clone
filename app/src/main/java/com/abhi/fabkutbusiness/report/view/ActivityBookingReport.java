package com.abhi.fabkutbusiness.report.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthgautam on 07/12/18.
 */

public class ActivityBookingReport extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ImageView iconLeft;
    private Toolbar mToolbar;
    TextView tv_date;
    static String currDate;
    Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_report);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        findviewById();
        intTab();
    }


    private void intTab() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        currDate = Utility.getCurrentDate(Constants.displayDateFormat);
        tv_date.setText(currDate);


    }

    private void findviewById() {

        tv_date= (TextView)findViewById(R.id.tv_date);
        tv_date.setOnClickListener(this);
        iconLeft = (ImageView)findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setOnClickListener(this);

    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAllBooking(), "Success");
        adapter.addFragment(new FragmentAllBooking(), "Cancel");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();
        currDate = Utility.formatDateForDisplay(date,"MM-dd-yyyy",""+ Constants.displayDateFormat);
        //serviceRefresh();



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



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_date:
                Utility.datePickerDialogBackDate(this, this);
                break;

            case R.id.actionbar_view_icon_left:
                finish();
                break;
        }
    }
}
