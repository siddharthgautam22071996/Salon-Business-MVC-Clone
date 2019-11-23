package com.abhi.fabkutbusiness.marketing.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.CustomerDataAdapter;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.marketing.controller.CustomerAdapter;
import com.abhi.fabkutbusiness.marketing.controller.OffersListAdapter;
import com.abhi.fabkutbusiness.retrofit.ResponseModel1;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 18/12/18.
 */

public class SendPromotion extends AppCompatActivity implements OnClickListener,RetrofitApi.ResponseListener,TextWatcher{
    CardView cvEveryone,cvIndividual;
    TextView tvEveryone,tvIndividual,proceed;
    AutoCompleteTextView search;
    View actionBarView;
    RecyclerView rvCustomer;
    int toAll = 0;;
    String customerId= "";
    CustomerAdapter mAdapter;
    int flag=0;
    ArrayList<ResponseModelCustomerData> searchedData = new ArrayList<>();
    ArrayList<ResponseModelCustomerData> customerData =new ArrayList<>();


    TextView tvTitle;
    ImageView iconLeft, iconRight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_promotion_activity);
        setActionBarView();
        findViewById();



    }

    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Send Promotion");
        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);
        cvIndividual = (CardView)findViewById(R.id.cvIndi);
        cvIndividual.setOnClickListener(this);
        cvEveryone = (CardView)findViewById(R.id.cvEveryone);
        cvEveryone.setOnClickListener(this);
        tvEveryone =(TextView)findViewById(R.id.everyone);
        tvIndividual =(TextView)findViewById(R.id.individual);
        proceed =(TextView)findViewById(R.id.proceed);
        proceed.setOnClickListener(this);
        rvCustomer = (RecyclerView)findViewById(R.id.rvCustomer);
        search  = (AutoCompleteTextView)findViewById(R.id.act_search);
        search.addTextChangedListener(this);
        cvIndividual.setBackgroundColor(getResources().getColor(R.color.colorBlue5));
        initUi();

    }
    private void setActionBarView() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();

    }


    private void initUi() {
        customerData = Utility.getResponseModelCustomer(this,Constants.keySalonCustomerData).getData();

        searchedData.addAll(customerData);

        mAdapter = new CustomerAdapter(searchedData,0, R.layout.item_service_list, false, this);
        rvCustomer.setAdapter(mAdapter);
        rvCustomer.setNestedScrollingEnabled(false);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvIndi:
                flag= 0;
                cvIndividual.setBackgroundColor(getResources().getColor(R.color.colorBlue5));
                tvIndividual.setTextColor(getResources().getColor(R.color.colorWhite));
                cvEveryone.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                tvEveryone.setTextColor(getResources().getColor(R.color.colorBlue5));
                rvCustomer.setVisibility(View.VISIBLE);
                search.setVisibility(View.VISIBLE);
                toAll = 0;

                break;
            case R.id.cvEveryone:
                flag =1;
                cvEveryone.setBackgroundColor(getResources().getColor(R.color.colorBlue5));
                tvEveryone.setTextColor(getResources().getColor(R.color.colorWhite));
                cvIndividual.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                tvIndividual.setTextColor(getResources().getColor(R.color.colorBlue5));
                rvCustomer.setVisibility(View.GONE);
                search.setVisibility(View.GONE);
                toAll= 1;
                break;

            case R.id.actionbar_view_icon_left:
                finish();
                break;
            case R.id.proceed:
                if (flag == 1){
                    runAllPromotion();
                }else if(flag ==0){
                    runIndividualPromotion();
                }

                break;
        }

    }

    private void runIndividualPromotion() {


       for (int i=0;i<mAdapter.getSelectedCustomerDataList().size() ; i++){
           //Utility.showToast(this,""+mAdapter.getSelectedCustomerDataList().get(i).getEndUser_FirstName());
           RetrofitApi.getInstance().runOfferApi(this,this,
                   "insert",
                   ""+getIntent().getExtras().getString("id"),
                   "1",
                   ""+mAdapter.getSelectedCustomerDataList().get(i).getENDUSERCODE(),
                   ""+toAll,
                   ""+Utility.getPreferences(this, Constants.keySalonBusinessId));
       }
    }

    private void runAllPromotion() {
        RetrofitApi.getInstance().runOfferApi(this,this,
                "insert",
                ""+getIntent().getExtras().getString("id"),
                "1",
                "",
                ""+toAll,
                ""+Utility.getPreferences(this, Constants.keySalonBusinessId));

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        ResponseModel1 responseModel1 = (ResponseModel1) obj;
        if (responseModel1.getSTATUS().equalsIgnoreCase("200")){
            Utility.showToast(this,responseModel1.getMESSAGE());
            startActivity(new Intent(this,NavigationMainActivity.class));
            finish();
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        searchedData.clear();
        if (s.toString().length() > 0) {
            for (ResponseModelCustomerData data : customerData) {
                if (data.getEndUser_FirstName().toLowerCase().contains(s.toString().toLowerCase()) || data.getContact_no().toLowerCase().contains(s.toString().toLowerCase())) {
                    searchedData.add(data);
                }
            }
        } else {
            searchedData.addAll(customerData);
        }
        mAdapter.notifyDataSetChanged();
    }
}
