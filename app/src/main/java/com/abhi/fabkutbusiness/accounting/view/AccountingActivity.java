package com.abhi.fabkutbusiness.accounting.view;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.AccountingPagerAdapter;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelect;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelectData;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodaysStatement;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by abhi on 16/06/17.
 */

public class AccountingActivity extends AppCompatActivity implements View.OnClickListener , RetrofitApi.ResponseListener {

    View actionBarView;
    TextView tvTitle , pettyCash;
    ImageView iconLeft;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AccountingPagerAdapter adapter;
    String businessId;
    ArrayList<ResponseLedgerSelectData> PettyCashData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accounting);
        businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);


        setActionBarView();
        findViewById();
        initData();
        RetrofitApi.getInstance().LedgerSelectApiMethod(AccountingActivity.this, this,businessId);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.actionbar_view_icon_left:
                finish();
                break;
        }
    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Accounting");
        pettyCash = (TextView)findViewById(R.id.tv_petty_cash);

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);

    }

    private void initData() {
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Petty Cash"));
        tabLayout.addTab(tabLayout.newTab().setText("VOUCHER"));
        tabLayout.addTab(tabLayout.newTab().setText("ADVANCE PAY"));
        tabLayout.addTab(tabLayout.newTab().setText("PO"));
        tabLayout.addTab(tabLayout.newTab().setText("TODAY'S STATEMENT"));
        tabLayout.addTab(tabLayout.newTab().setText("REPORT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        adapter = new AccountingPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        //viewPager.setCurrentItem(3);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseLedgerSelect) {
            ResponseLedgerSelect modelLedgerSelect = (ResponseLedgerSelect) obj;
            if (modelLedgerSelect.getSTATUS().equals("200")) {

                Utility.addPreferencesPettyCashData(AccountingActivity.this, Constants.keySalonPettyCashData, modelLedgerSelect);
                RetrofitApi.getInstance().getTodaysStatementApiMethod(AccountingActivity.this,this,businessId);
                pettyCash.setText(modelLedgerSelect.getData().get(0).getLedger_balance());


            } else {
                // Toast.makeText(getActivity(),modelLedgerSelect.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }

      }



