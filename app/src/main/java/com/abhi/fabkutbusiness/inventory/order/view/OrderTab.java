package com.abhi.fabkutbusiness.inventory.order.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.abhi.fabkutbusiness.R;

import java.util.ArrayList;
import java.util.List;

public class OrderTab extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_vendor);
        findViewById();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(mToolbar);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void findViewById() {

            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvTitle.setText("Order");
            back =(ImageView) findViewById(R.id.back);
            back.setOnClickListener(this);


    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new OrderCreation(), "CREATION");
        adapter.addFragment(new OrderRejection() , "REJECTION");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){

            case R.id.back:
                finish();
                break;
        }
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
}
