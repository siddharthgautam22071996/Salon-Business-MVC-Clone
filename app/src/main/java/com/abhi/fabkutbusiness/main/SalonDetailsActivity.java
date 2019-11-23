package com.abhi.fabkutbusiness.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.BusinessLocalData;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelLoginData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.marketing.controller.MarketingTestAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by siddharthgautam on 01/08/18.
 */

public class SalonDetailsActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private static final Integer[] XMEN = {R.mipmap.slider1, R.mipmap.slider2, R.mipmap.slider3,};
    private static ViewPager mPager;
    private static int currentPage = 0;
    ArrayList<ResponseModelRateInfoData> rateData;
    ArrayList<ResponseModelRateInfoData> searchedData;
    ArrayList<ResponseModelLoginData> salonDta;
    ServicesAdapter mAdapter;
    TextView tvTitle;
    ImageView iconLeft, iconRight;
    RecyclerView rv_rateCard;
    View actionBarView;
    EditText etSearch;
    TextView email, mobile, onlineChair, offlineChair;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();



    // The number of native ads to load.
    public static final int NUMBER_OF_ADS = 5;

    // The AdLoader used to load ads.
    private AdLoader adLoader;

    // List of MenuItems and native ads that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();

    // List of native ads that have been successfully loaded.
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_details);
        setActionBarView();
        findViewById();
        iniSlider();
        iniData();


    }


    private void iniSlider() {
        for (int i = 0; i < XMEN.length; i++) {
            XMENArray.add(XMEN[i]);

        }

        mPager.setAdapter(new MarketingTestAdapter(SalonDetailsActivity.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

    }

    private void iniData() {
        mRecyclerViewItems.clear();

        Date d = null;
        salonDta = Utility.getResponseModelLogin(this, Constants.keySalonProfileData).getData();

        tvTitle.setText(salonDta.get(0).getBusiness_name());

//        email.setText(salonDta.get(0).getBusiness_email_id());
        //      mobile.setText(salonDta.get(0).getContact_no());
        onlineChair.setText("Opening Time : " + Utility.timeFormat(salonDta.get(0).getOpening_hours()));
        offlineChair.setText("Closing Time : " + Utility.timeFormat(salonDta.get(0).getClosing_hours()));

        searchedData = new ArrayList<>();
        rateData = Utility.getResponseModelRateInfo(this, Constants.keySalonRateInfoData).getData();
        searchedData.addAll(rateData);
        ArrayList<ResponseModelRateInfoData> selectedData = getIntent().getParcelableArrayListExtra("data");

        if (selectedData == null)
            selectedData = new ArrayList<>();

        mRecyclerViewItems.clear();
        for (int i =0;i<rateData.size();i++){

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




        System.out.println("asdfaa:"+searchedData.toString());

        if(Utility.getPreferences(this,"admob").equalsIgnoreCase("1")) {

            loadNativeAds(selectedData);

        }else {

            mAdapter = new ServicesAdapter(mRecyclerViewItems, selectedData, 121, R.layout.item_service_list, false, this);
            rv_rateCard.setAdapter(mAdapter);

        }




//

    }


    private void insertAdsInMenuItems(ArrayList<ResponseModelRateInfoData> selectedData) {
        if (mNativeAds.size() <= 0) {
            return;

        }

//        int offset = (mRecyclerViewItems.size() / mNativeAds.size()) + 1;
        int index = 6;
        UnifiedNativeAd ad=mNativeAds.get(1);
        for (UnifiedNativeAd add:mNativeAds){
            if(mRecyclerViewItems.size()>index){
                mRecyclerViewItems.add(index,add);

                index=index+6;
            }
        }
        if(mRecyclerViewItems.size()<=8){
            mRecyclerViewItems.add(ad);
        }


        rv_rateCard.setHasFixedSize(true);

        // Specify a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_rateCard.setLayoutManager(layoutManager);
        System.out.println(" Data check :: "+mRecyclerViewItems.toString() );



        mAdapter = new ServicesAdapter(mRecyclerViewItems, selectedData, 121, R.layout.item_service_list, false, this);
        rv_rateCard.setAdapter(mAdapter);



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




    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void findViewById() {
        mPager = (ViewPager) findViewById(R.id.pager);
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);


        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_sync));

        iconLeft.setVisibility(View.VISIBLE);
        iconRight.setVisibility(View.VISIBLE);

        iconLeft.setOnClickListener(this);
        iconRight.setOnClickListener(this);

        rv_rateCard = (RecyclerView) findViewById(R.id.rv_services);
        //email = (TextView)findViewById(R.id.email);
        //mobile = (TextView)findViewById(R.id.mobile);
        onlineChair = (TextView) findViewById(R.id.online_chair);
        offlineChair = (TextView) findViewById(R.id.offline_chair);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.addTextChangedListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionbar_view_icon_left:
                finish();
                break;
            case R.id.actionbar_view_icon_right:

                if (Utility.isInternetConnected(SalonDetailsActivity.this)) {
                    SyncCuctomer();

                    BusinessLocalData businessLocalData = new BusinessLocalData(this);
                    businessLocalData.getBusinessData(this);
                   /* BusinessSyncData businessSyncData = new BusinessSyncData(this);
                    businessSyncData.syncCustomer();*/

                }

                break;
        }
    }


    private void SyncCuctomer() {
        Toast.makeText(this, "sync..", Toast.LENGTH_SHORT).show();
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
            iniData();
        }


        mAdapter.notifyDataSetChanged();
    }
}
