package com.abhi.fabkutbusiness.inventory.order.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.inventory.itemMaster.view.ItemCreation;
import com.abhi.fabkutbusiness.inventory.order.controller.ItemsAdapter;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieve;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData1;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class SelectItemActivity extends AppCompatActivity implements  RetrofitApi.ResponseListener, View.OnClickListener, TextWatcher,RecyclerView.OnItemTouchListener{

    View actionBarView;
    TextView tvTitle, tvRight, tvAddItem;
    ImageView iconLeft, iconRight;
    RecyclerView rvServices;
    ItemsAdapter mAdapter;
    EditText etSearch;
    ArrayList<ResponseOrderRecieveData> selectedData;
    ArrayList<ResponseOrderRecieveData> searchedData;
    ArrayList<ResponseOrderRecieveData> responseOrderRecieveData =new ArrayList<>();
    ArrayList<ResponseOrderRecieveData> selected  =new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_services);

        setActionBarView();
        findViewById();
        String businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);
        RetrofitApi.getInstance().itemApi(this,this,businessId);
        initUi();

    }

    private void initUi() {
         selectedData = getIntent().getParcelableArrayListExtra("data");

        if (selectedData == null) {
            selectedData = new ArrayList<>();
        }else {
           //Toast.makeText(this, "" + selectedData.get(0).getProduct_code(), Toast.LENGTH_SHORT).show();
        }


    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvAddItem = (TextView)findViewById(R.id.tv_addItem);
        tvAddItem.setOnClickListener(this);
        tvTitle.setText("Select Item");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.close));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);

        tvRight = (TextView) actionBarView.findViewById(R.id.actionbar_view_tv_right);
        tvRight.setTextColor(getResources().getColor(R.color.colorGreen));
        tvRight.setText("DONE");
        tvRight.setOnClickListener(this);

        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.addTextChangedListener(this);

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

            case R.id.tv_addItem:
                startActivity(new Intent(SelectItemActivity.this, ItemCreation.class));
                finish();
                break;

            case R.id.actionbar_view_tv_right:
                ArrayList<ResponseOrderRecieveData> dataList = mAdapter.getSelectedRateDataList();
                Intent intent = new Intent();

                intent.putExtra("dataList", dataList);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
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
            for (ResponseOrderRecieveData data : responseOrderRecieveData) {
                if (data.getItem_name().toLowerCase().contains(s.toString().toLowerCase())) {
                    searchedData.add(data);
                }
            }
        } else {
            searchedData.addAll(responseOrderRecieveData);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        ResponseOrderRecieve responseOrderRecieve = (ResponseOrderRecieve) obj;
        if (responseOrderRecieve.getSTATUS().equals("200")){
            searchedData = new ArrayList<>();
            searchedData.addAll(responseOrderRecieve.getData());

            mAdapter = new ItemsAdapter(searchedData, selectedData, R.layout.item_service_list, false, this);
            rvServices.setAdapter(mAdapter);
        }else{
            Toast.makeText(this, ""+responseOrderRecieve.getMESSAGE(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void _onNext1(Object obj) {

    }
}
