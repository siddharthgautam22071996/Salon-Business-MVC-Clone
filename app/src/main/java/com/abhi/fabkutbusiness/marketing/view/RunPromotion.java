package com.abhi.fabkutbusiness.marketing.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.view.Basic_Info;
import com.abhi.fabkutbusiness.crm.view.CrmTab;
import com.abhi.fabkutbusiness.crm.view.Personal_Info;
import com.abhi.fabkutbusiness.crm.view.Social_Info;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.marketing.controller.OffersListAdapter;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotion;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.abhi.fabkutbusiness.retrofit.RetrofitClient;
import com.github.mikephil.charting.charts.PieChart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthgautam on 17/12/18.
 */

public class RunPromotion extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener {
    TextView tvTitle;
    View actionBarView;
    RecyclerView rvPromotion;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ImageView iconLeft,profilePhoto;
    String viewStatus;
    RelativeLayout customerLayout;
    TextView name,contact;
    String customerCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_attendence);
//        setActionBarView();
        viewStatus = getIntent().getExtras().getString("flag");
        findViewById();




    }
    public String getViewStatus(){
        this.viewStatus =viewStatus;
        return viewStatus;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new OfferFragment(), "OFFER");
        adapter.addFragment(new CouponFragment(), "COUPON");
        viewPager.setAdapter(adapter);
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


    public String getCustomerCode(){
        String customerCode = "";
        this.customerCode= customerCode;
        return customerCode;

    }

    private void findViewById() {
        LinearLayout botton_layout = (LinearLayout)findViewById(R.id.botton_layout);
        botton_layout.setVisibility(View.GONE);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue5));
        TextView tvTitle = (TextView)mToolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Run Promotion");
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(mToolbar);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        iconLeft = (ImageView)mToolbar.findViewById(R.id.back);
        profilePhoto = (ImageView)findViewById(R.id.iv_profile);
        name = (TextView)findViewById(R.id.name);
        contact = (TextView)findViewById(R.id.contact);


       if (viewStatus.equalsIgnoreCase("2")){
           customerLayout = (RelativeLayout)findViewById(R.id.customerLayout);
           customerLayout.setVisibility(View.VISIBLE);
           name.setText(getIntent().getExtras().getString("name"));
           contact.setText(getIntent().getExtras().getString("phone"));
           customerCode =getIntent().getExtras().getString("enduser_code");

           if (!getIntent().getExtras().getString("photo").equalsIgnoreCase("")) {
               Picasso.get()
                       .load(RetrofitClient.BASE_Image_URL_CUSTOMER + "" + getIntent().getExtras().getString("photo"))
                       .placeholder(R.drawable.dummy_profile)
                       .into(profilePhoto);
//                  Picasso.with(RunPromotion.this)
//                       .load(RetrofitClient.BASE_Image_URL_CUSTOMER + "" + getIntent().getExtras().getString("photo"))
//                       .placeholder(R.drawable.dummy_profile)
//                       .into(profilePhoto);
//
           }

       }
        iconLeft.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back:
                finish();
                break;
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
        ResponseCreatePromotion responseCreatePromotion = (ResponseCreatePromotion) obj;


    }

    @Override
    public void _onNext1(Object obj) {

    }
}
