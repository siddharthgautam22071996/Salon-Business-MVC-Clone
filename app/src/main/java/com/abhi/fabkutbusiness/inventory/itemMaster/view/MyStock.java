package com.abhi.fabkutbusiness.inventory.itemMaster.view;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.inventory.itemMaster.controller.MyStockAdapter;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseMyStockData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthgautam on 03/04/18.
 */

public class MyStock extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener{

    View actionBarView;
    RecyclerView listView;
    ImageView back;
    TextView new_item,tvTitle;
    MyStockAdapter myStockAdapter;
    ArrayList<ResponseMyStockData> responseMyStockData  = new ArrayList<>();

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
        setContentView(R.layout.my_stock);
        setActionBarView();
        findViewById();

    }
    private void insertAdsInMenuItems() {
        if (mNativeAds.size() <= 0) {
            return;

        }

//        int offset = (mRecyclerViewItems.size() / mNativeAds.size()) + 1;
        int index = 5;
        UnifiedNativeAd ad=mNativeAds.get(1);
        for (UnifiedNativeAd add:mNativeAds){
            if(mRecyclerViewItems.size()>index){
                mRecyclerViewItems.add(index,add);

                index=index+5;
            }
        }
        if(mRecyclerViewItems.size()<=8){
            mRecyclerViewItems.add(ad);
        }


        listView.setHasFixedSize(true);

        // Specify a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        System.out.println(" Data check :: "+mRecyclerViewItems.toString() );


        myStockAdapter = new MyStockAdapter(getApplicationContext(),mRecyclerViewItems);
        listView.setAdapter(myStockAdapter);



    }

    private void loadNativeAds() {

        AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110");
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems();
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
                            insertAdsInMenuItems();
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }



    private void findViewById() {
        listView = findViewById(R.id.listView);
        back = (ImageView)actionBarView.findViewById(R.id.actionbar_view_icon_left);
        back.setVisibility(View.VISIBLE);
        back.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        back.setOnClickListener(this);
        tvTitle = (TextView)actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("My Stock");
        mRecyclerViewItems.clear();
        ResponseMyStockData responseMyStockData = new ResponseMyStockData();

        for (int i =0 ; i<10 ;i++ ){

            responseMyStockData.setExpiryDate("12/23/12 23:00");
            responseMyStockData.setProduct_name("Plum Sheer Matte Day Cream SPF50, Chamomile and White Tea");
            responseMyStockData.setQty(""+293);
            responseMyStockData.setUnit("ml");




//
//            responseMyStockData.add(new ResponseMyStockData(""+293,"Plum Sheer Matte Day Cream SPF50, Chamomile and White Tea",
//                    "ml","12/23/12 23:00"));



            mRecyclerViewItems.add(responseMyStockData);
        }





        if(Utility.getPreferences(this,"admob").equalsIgnoreCase("1")) {

            loadNativeAds();

        }else {

            myStockAdapter = new MyStockAdapter(getApplicationContext(),mRecyclerViewItems);
            listView.setAdapter(myStockAdapter);


        }

//        myStockAdapter = new MyStockAdapter(getApplicationContext(),responseMyStockData);
//        listView.setAdapter(myStockAdapter);
    }

    private void setActionBarView() {



        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.actionbar_view_icon_left:
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

    }

    @Override
    public void _onNext1(Object obj) {

    }
}
