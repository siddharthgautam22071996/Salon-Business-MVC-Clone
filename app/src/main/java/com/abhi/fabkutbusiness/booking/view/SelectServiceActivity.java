package com.abhi.fabkutbusiness.booking.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 17/04/17.
 */

public class SelectServiceActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    // The number of native ads to load.
    public static final int NUMBER_OF_ADS = 5;
    View actionBarView;
    TextView tvTitle, tvRight;
    ImageView iconLeft, iconRight;
    RecyclerView rvServices;
    ServicesAdapter mAdapter;
    EditText etSearch;
    ArrayList<ResponseModelRateInfoData> searchedData;
    ArrayList<ResponseModelRateInfoData> rateData;
    // The AdLoader used to load ads.
    private AdLoader adLoader;

    // List of MenuItems and native ads that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();

    // List of native ads that have been successfully loaded.
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_services);

        setActionBarView();
        findViewById();
        initUi();

    }

    private void initUi() {
        searchedData = new ArrayList<>();
        rateData = Utility.getResponseModelRateInfo(this, Constants.keySalonRateInfoData).getData();
        searchedData.addAll(rateData);
        ArrayList<ResponseModelRateInfoData> selectedData = getIntent().getParcelableArrayListExtra("data");

        if (selectedData == null)
            selectedData = new ArrayList<>();


        mRecyclerViewItems.clear();
        for (int i = 0; i < rateData.size(); i++) {

            ResponseModelRateInfoData responseModelRateInfoData = new ResponseModelRateInfoData();


            responseModelRateInfoData.setBusiness_id(rateData.get(i).getBusiness_id());
            responseModelRateInfoData.setBusiness_Name(rateData.get(i).getBusiness_Name());
            responseModelRateInfoData.setEmployee_id(rateData.get(i).getEmployee_id());
            responseModelRateInfoData.setEmployee_name(rateData.get(i).getEmployee_name());
            responseModelRateInfoData.setEta(rateData.get(i).getEta());
            responseModelRateInfoData.setId(rateData.get(i).getId());
            responseModelRateInfoData.setRate(rateData.get(i).getRate());
            responseModelRateInfoData.setSub_service_name(rateData.get(i).getSub_service_name());

            mRecyclerViewItems.add(responseModelRateInfoData);

        }


        System.out.println("asdfaa:" + searchedData.toString());

        if (Utility.getPreferences(this, "admob").equalsIgnoreCase("1")) {

            loadNativeAds(selectedData);

        } else {

            mAdapter = new ServicesAdapter(mRecyclerViewItems, selectedData, 0, R.layout.item_service_list, false, this);
            rvServices.setAdapter(mAdapter);

        }


//        mAdapter = new ServicesAdapter(searchedData, selectedData,0, R.layout.item_service_list, false, this);
//        rvServices.setAdapter(mAdapter);
    }


    private void insertAdsInMenuItems(ArrayList<ResponseModelRateInfoData> selectedData) {
        if (mNativeAds.size() <= 0) {
            return;

        }

//        int offset = (mRecyclerViewItems.size() / mNativeAds.size()) + 1;
        int index = 6;
        UnifiedNativeAd ad = mNativeAds.get(1);
        for (UnifiedNativeAd add : mNativeAds) {
            if (mRecyclerViewItems.size() > index) {
                mRecyclerViewItems.add(index, add);

                index = index + 6;
            }
        }
        if (mRecyclerViewItems.size() <= 8) {
            mRecyclerViewItems.add(ad);
        }


        rvServices.setHasFixedSize(true);

        // Specify a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvServices.setLayoutManager(layoutManager);
        System.out.println(" Data check :: " + mRecyclerViewItems.toString());


        mAdapter = new ServicesAdapter(mRecyclerViewItems, selectedData, 0, R.layout.item_service_list, false, this);
        rvServices.setAdapter(mAdapter);


    }

    private void loadNativeAds(final ArrayList<ResponseModelRateInfoData> selectedData) {

        AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110");
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems(selectedData);
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems(selectedData);
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }


    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Select Services");

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

            case R.id.actionbar_view_tv_right2:
                ArrayList<ResponseModelRateInfoData> dataList = mAdapter.getSelectedRateDataList();
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

            mRecyclerViewItems.clear();
            for (int i = 0; i < rateData.size(); i++) {

                ResponseModelRateInfoData responseModelRateInfoData = new ResponseModelRateInfoData();


                responseModelRateInfoData.setBusiness_id(rateData.get(i).getBusiness_id());
                responseModelRateInfoData.setBusiness_Name(rateData.get(i).getBusiness_Name());
                responseModelRateInfoData.setEmployee_id(rateData.get(i).getEmployee_id());
                responseModelRateInfoData.setEmployee_name(rateData.get(i).getEmployee_name());
                responseModelRateInfoData.setEta(rateData.get(i).getEta());
                responseModelRateInfoData.setId(rateData.get(i).getId());
                responseModelRateInfoData.setRate(rateData.get(i).getRate());
                responseModelRateInfoData.setSub_service_name(rateData.get(i).getSub_service_name());



                if (responseModelRateInfoData.getSub_service_name().toLowerCase().contains(s.toString().toLowerCase())) {

                    mRecyclerViewItems.add(responseModelRateInfoData);
                }
            }


//            for (ResponseModelRateInfoData data : rateData) {
//                if (data.getSub_service_name().toLowerCase().contains(s.toString().toLowerCase())) {
//                    searchedData.add(data);
//                }
//            }
        } else {
            initUi();
        }
        mAdapter.notifyDataSetChanged();
    }
}
