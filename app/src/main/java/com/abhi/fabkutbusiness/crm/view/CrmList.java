package com.abhi.fabkutbusiness.crm.view;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.CustomerDataAdapter;
import com.abhi.fabkutbusiness.crm.controller.CrmAdapter;
import com.abhi.fabkutbusiness.crm.controller.CrmSearchAdpater;
import com.abhi.fabkutbusiness.crm.model.Crmmodel;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmListData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.HashMap;

import static com.abhi.fabkutbusiness.R.drawable.rectangle4;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class CrmList extends AppCompatActivity implements RetrofitApi.ResponseListener,View.OnClickListener {

    RecyclerView lv;
    EditText Search;
    //ArrayList<CrmList_model> crmList_models = new ArrayList<CrmList_model>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    View actionBarView;
    ImageView iconLeft;
    LayoutInflater inflater;
    TextView tvTitle;
    CrmAdapter ca;
    CrmSearchAdpater crmSearchAdpater;
    CustomerDataAdapter customerDataAdapter;
    AutoCompleteTextView actSearch;
    ArrayList<Crmmodel> crmModels = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crm_list);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setActionBarView();
        findViewById();
        int businessId = Integer.parseInt(Utility.getPreferences(this, Constants.keySalonBusinessId));
        final AppBarLayout collapsingToolbarLayout = (AppBarLayout) findViewById(R.id.appbar);

//
        RetrofitApi.getInstance().CrmListShowApiMethod(this, this, businessId);

    }


    private void findViewById() {
        lv = (RecyclerView) findViewById(R.id.lv);
        lv.setBackgroundColor(Color.parseColor("#dedede"));
        Search = (EditText) findViewById(R.id.et_search);
//        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
  //      tvTitle.setText("Customer");
       iconLeft = (ImageView)toolbar.findViewById(R.id.iv_back);
       tvTitle = (TextView)toolbar.findViewById(R.id.actionbar_view_title);
       iconLeft.setOnClickListener(this);
       actSearch = (AutoCompleteTextView) findViewById(R.id.act_search);

        ArrayList<ResponseModelCustomerData>  crmModel = Utility.getResponseModelCustomer(this,Constants.keySalonCustomerData).getData();
        crmSearchAdpater = new CrmSearchAdpater(this, R.layout.simple_text_view, crmModel);
        actSearch.setThreshold(1);
        actSearch.setAdapter(crmSearchAdpater);
        actSearch.setText("");
        actSearch.clearFocus();

    }
    public void back(View view){
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

        final ResponseCrmList responseCrmList = (ResponseCrmList) obj;
        Log.d("abc", "" + responseCrmList);

        if (responseCrmList.getSTATUS().equalsIgnoreCase("200")) {

           /* LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            lv.setLayoutManager(llm);*/
            lv.setNestedScrollingEnabled(false);
            ca = new CrmAdapter(getApplicationContext(), responseCrmList.getData());
            lv.setAdapter(ca);


           // CrmAdapter ca = new CrmAdapter(getApplicationContext(), crmList_models);

        } else {

            Toast.makeText(this, responseCrmList.getMESSAGE(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void _onNext1(Object obj) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
