package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.SelectPromoCodeAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillPaymentData;
import com.abhi.fabkutbusiness.billing.model.ResponsePomoCode;
import com.abhi.fabkutbusiness.billing.model.ResponsePomoCodeData;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 20/01/19.
 */

public class SelectPromoCode extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener{

    View actionBarView;
    TextView tvTitle, tvRight;
    ImageView iconLeft, iconRight;
    RecyclerView rvServices;
    EditText etSearch;
    RelativeLayout relativeLayout;
    SelectPromoCodeAdapter selectPromoCodeAdapter;
    ArrayList<ResponsePomoCodeData> searchedData;
    ArrayList<ResponsePomoCodeData> selectedData;
    ArrayList<ResponsePomoCodeData> rateData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_services);
        setActionBarView();
        findViewById();
        RetrofitApi.getInstance().selectPromoCode(this,this, Utility.getPreferences(this, Constants.keySalonBusinessId),getIntent().getStringExtra("endUserCode"));
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Select Offer");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.close));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);

        tvRight = (TextView) actionBarView.findViewById(R.id.actionbar_view_tv_right2);
        tvRight.setTextColor(getResources().getColor(R.color.colorGreen));
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("DONE");
        tvRight.setOnClickListener(this);

        EditText et = (EditText) findViewById(R.id.et_search);
        etSearch = (EditText) findViewById(R.id.et_promocode);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        relativeLayout.setVisibility(View.VISIBLE);
        etSearch.setVisibility(View.VISIBLE);
        et.setVisibility(View.GONE);
        rvServices = (RecyclerView) findViewById(R.id.rv_services);


    }

    private void setActionBarView() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.actionbar_view_tv_right2:
/*
                ArrayList<ResponseModelRateInfoData> dataList = mAdapter.getSelectedRateDataList();
                Intent intent = new Intent();
                intent.putExtra("dataList", dataList);
                setResult(RESULT_OK, intent);
                finish();
*/
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
        ResponsePomoCode responsePomoCode =(ResponsePomoCode) obj;
        if (selectedData == null)
            selectedData = new ArrayList<>();
        //Toast.makeText(this, ""+responsePomoCode.getData().size(), Toast.LENGTH_SHORT).show();
        selectPromoCodeAdapter = new SelectPromoCodeAdapter(responsePomoCode.getData(), selectedData,0, R.layout.item_service_list, false, this);
        rvServices.setAdapter(selectPromoCodeAdapter);

    }

    @Override
    public void _onNext1(Object obj) {

    }
}
