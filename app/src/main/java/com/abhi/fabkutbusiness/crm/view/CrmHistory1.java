package com.abhi.fabkutbusiness.crm.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.controller.CrmHistoryAdapter;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistory;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistoryData;
import com.abhi.fabkutbusiness.crm.model.ResponseUnpaidAmount;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CrmHistory1 extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener{
    TextView title;
    TextView tvTitle, due_amount;
    TextView name;
    TextView mob,vists;
    TextView emai;
    TextView gender;
    ImageView edit;
    ImageView back;
    String user_id;
    String totalVist;
    Button bt_edit;
    Toolbar toolbar;
    RecyclerView rv_history;
    String user_name,email,phone;
    CrmHistoryAdapter crmHistoryAdapter;
    TextView  titile,revenue;
    Bundle ab;
    String photo;
    ArrayList<ResponseCrmHistoryData> crmHistoryData = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crm_history);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final AppBarLayout collapsingToolbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        title = (TextView)findViewById(R.id.actionbar_view_title);
        ab = getIntent().getExtras();
        user_id = ab.getString("item");
        photo = ab.getString("photo");
        user_name = ab.getString("name");
        totalVist = ab.getString("total_visit");

        //Toast.makeText(this,""+user_id, Toast.LENGTH_SHORT).show();

        findViewById();
    }

    private void findViewById() {
        rv_history = (RecyclerView) findViewById(R.id.lv);

        //rv_history.setHasFixedSize(true);
        rv_history.setNestedScrollingEnabled(false);
        name = (TextView)findViewById(R.id.tv_name);
        vists = (TextView)findViewById(R.id.tv_totalVist);
        emai = (TextView)findViewById(R.id.tv_email);
        mob = (TextView)findViewById(R.id.tv_mobile);
        revenue = (TextView)findViewById(R.id.tv_total_revenue);
        revenue.setText(ab.getString("revenue"));
        due_amount = (TextView)findViewById(R.id.tv_due);
        //bt_edit = (Button)findViewById(R.id.bt_editProfile);
//        bt_edit.setOnClickListener(this);
        edit = (ImageView) toolbar.findViewById(R.id.iv_edit);
        edit.setOnClickListener(this);
        tvTitle = (TextView) toolbar.findViewById(R.id.actionbar_view_title);

        back = (ImageView)toolbar.findViewById(R.id.iv_back);
        back.setOnClickListener(this);
        initData();
       // edit.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        Picasso.get()
                .load(photo)
                .placeholder(R.drawable.dummy_profile)
                .into(edit);
//   Picasso.with(this)
//                .load(photo)
//                .placeholder(R.drawable.dummy_profile)
//                .into(edit);

        name.setText(user_name);
        vists.setText("Total Visits : "+totalVist);
        String EndUserId = String.valueOf(user_id);
        String businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);
       RetrofitApi.getInstance().crmHistoryApi(this,this,businessId, EndUserId);
       RetrofitApi.getInstance().CustomerUnpaidApi(this,this,EndUserId);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_edit:

                if (Utility.isInternetConnected(this)) {
                    Intent i = new Intent(this, CrmTab.class);
                    i.putExtra("item", user_id);
                    i.putExtra("name", user_name);
                    i.putExtra("photo", photo);
                    startActivity(i);
                }
                break;

            case R.id.iv_back:
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

        if (obj instanceof ResponseCrmHistory) {
            ResponseCrmHistory responseCrmHistory = (ResponseCrmHistory) obj;
            if (responseCrmHistory.getSTATUS().equals("200")) {
                crmHistoryAdapter = new CrmHistoryAdapter(this, responseCrmHistory.getData());
                rv_history.setAdapter(crmHistoryAdapter);
                rv_history.setNestedScrollingEnabled(false);
            } else {
                Toast.makeText(this, "" + responseCrmHistory.getMESSAGE(), Toast.LENGTH_SHORT).show();
            }
        }else if (obj instanceof ResponseUnpaidAmount){
            ResponseUnpaidAmount responseUnpaidAmount = (ResponseUnpaidAmount)  obj;
            if (responseUnpaidAmount.getSTATUS().equalsIgnoreCase("200")){
                if (responseUnpaidAmount.getData().get(0).getBalanceAmount()!=null)
                    due_amount.setText(responseUnpaidAmount.getData().get(0).getBalanceAmount());
                else
                    due_amount.setText("0");
                if (responseUnpaidAmount.getData().get(0).getTotalVisit()!=null)
                    vists.setText("Total Visits : "+responseUnpaidAmount.getData().get(0).getTotalVisit());
                else
                    vists.setText("Total Visits : 0");

                if (responseUnpaidAmount.getData().get(0).getTotalReveniue()!=null)
                    revenue.setText(responseUnpaidAmount.getData().get(0).getTotalReveniue());
                else
                    revenue.setText("0");

            }else{
                Utility.showToast(this,responseUnpaidAmount.getMESSAGE());
            }
        }
    }

    @Override
    public void _onNext1(Object obj) {

    }
}
