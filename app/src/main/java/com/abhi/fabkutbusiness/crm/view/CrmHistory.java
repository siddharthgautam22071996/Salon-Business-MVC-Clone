package com.abhi.fabkutbusiness.crm.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.controller.CrmHistoryAdapter;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistoryData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.Format;
import java.util.ArrayList;

/**
 * Created by siddharth on 1/10/2018.
 */

public class CrmHistory extends AppCompatActivity implements RetrofitApi.ResponseListener ,View.OnClickListener {

    View actionBarView;
    //ListView lv_history;
    TextView tvTitle;
    TextView name;
    TextView mob;
    TextView emai;
    TextView gender;
    ImageView edit;
    int user_id;
    String user_name,email,phone;
    CrmHistoryAdapter crmHistoryAdapter;
    ArrayList<ResponseCrmHistoryData> crmHistoryData = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crm_history);
        Bundle ab = getIntent().getExtras();
        user_id = ab.getInt("item");
        user_name = ab.getString("name");
        email = ab.getString("email");
        phone = ab.getString("contact");
        setActionBarView();
        findViewById();
        int businessId = Integer.parseInt(Utility.getPreferences(this, Constants.keySalonBusinessId));
    }

    private void findViewById() {
        //lv_history = (ListView)findViewById(R.id.lv_crmHistory);
        name = (TextView)findViewById(R.id.tv_name);
        emai = (TextView)findViewById(R.id.tv_email);
        mob = (TextView)findViewById(R.id.tv_mobile);
        edit = (ImageView)findViewById(R.id.iv_edit);
        edit.setOnClickListener(this);
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);

        initData();
    }

    private void initData() {
        tvTitle.setText("Customer History");
        emai.setText(email);
        name.setText(user_name);
        mob.setText(phone);
        crmHistoryData.clear();


    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View customView = getLayoutInflater().inflate(R.layout.actionbar_view_custom, null);
        getSupportActionBar().setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);*/
        actionBarView = getSupportActionBar().getCustomView();
    }


    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

    }

    @Override
    public void _onNext1(Object obj) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_edit:
                Intent i = new Intent(getApplicationContext(),CrmTab.class);
                i.putExtra("item",user_id);
                startActivity(i);

                break;
        }
    }
}
