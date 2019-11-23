package com.abhi.fabkutbusiness.marketing.view;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.controller.CrmAdapter;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.marketing.controller.BirthdayListAdapter;
import com.abhi.fabkutbusiness.marketing.controller.MarketingTestAdapter;
import com.abhi.fabkutbusiness.marketing.controller.OffersListAdapter;
import com.abhi.fabkutbusiness.marketing.model.MarketingTestModel;
import com.abhi.fabkutbusiness.marketing.model.ResponseLatestOfferList;
import com.abhi.fabkutbusiness.marketing.model.ResponseUpComingBirthday;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.itextpdf.text.pdf.PdfName.ca;

/**
 * Created by siddharthgautam on 04/04/18.
 */

public class Marketing_Main extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener {

    //------ slider -----------------------
    private static ViewPager mPager;
    private static int currentPage = 0;
    //private static final Integer[] XMEN= {R.mipmap.banner_market,R.mipmap.birday,R.mipmap.banner,};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    RecyclerView birthday,annivasry,deal,offer;
    ArrayList<MarketingTestModel> marketingTestModels = new ArrayList<>();
    MarketingTestAdapter marketingTestAdapter;
    BirthdayListAdapter birthdayListAdapter;
    OffersListAdapter offersListAdapter;
    RecyclerView rv_birthday,rv_offer,rv_ani;
    ImageView back;
    TextView addCampaings,tv_runCampaigns;
    LinearLayoutManager mLinearLayoutManager,mLinearLayoutManager1;
    String business_id;
    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_marketing);
        findViewById();
        business_id = Utility.getPreferences(this,Constants.keySalonBusinessId);
        RetrofitApi.getInstance().upComingBirthdayOfferApi(this,this,business_id);
        RetrofitApi.getInstance().upComingAniApi(this,this,business_id);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //RetrofitApi.getInstance().latestOfferApi(this,this,business_id);
        //initData();
    }

    private void findViewById() {


        back = (ImageView)findViewById(R.id.back);
        addCampaings = (TextView)findViewById(R.id.tv_addCampaings);
        tv_runCampaigns = (TextView)findViewById(R.id.tv_runCampaigns);
        addCampaings.setOnClickListener(this);
        tv_runCampaigns.setOnClickListener(this);
        back.setOnClickListener(this);
        rv_birthday = (RecyclerView)findViewById(R.id.rv_birthday);
        //rv_offer = (RecyclerView)findViewById(R.id.rv_offers);
        rv_ani = (RecyclerView)findViewById(R.id.rv_ani);




    }

   /* private void initData() {
       // ArrayList<ResponseModelCustomerData> responseModelCustomerData = Utility.getResponseModelCustomer(this, Constants.keySalonCustomerData).getData();


        for(int i=0;i<XMEN.length;i++) {
            XMENArray.add(XMEN[i]);
        }

        //birthdayListAdapter = new BirthdayListAdapter(this,responseModelCustomerData);
        //offersListAdapter =new OffersListAdapter(this,responseModelCustomerData);
        rv_birthday.setAdapter(birthdayListAdapter);
        rv_offer.setAdapter(offersListAdapter);
        mPager.setAdapter(new MarketingTestAdapter(Marketing_Main.this,XMENArray));
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
*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_addCampaings:
                startActivity(new Intent(Marketing_Main.this,CreatePromotion.class));
                break;
            case R.id.tv_runCampaigns:
                startActivity(new Intent(Marketing_Main.this,RunPromotion.class).putExtra("flag","1"));
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

        if (obj instanceof ResponseLatestOfferList) {
            final ResponseLatestOfferList responseCrmList = (ResponseLatestOfferList) obj;

            if (responseCrmList.getSTATUS().equalsIgnoreCase("200")) {

           /* LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            lv.setLayoutManager(llm);*/



                // CrmAdapter ca = new CrmAdapter(getApplicationContext(), crmList_models);

            } else {

                Toast.makeText(this, responseCrmList.getMESSAGE(), Toast.LENGTH_LONG).show();
            }
        }else if (obj instanceof ResponseUpComingBirthday) {
            final ResponseUpComingBirthday responseUpComingBirthday = (ResponseUpComingBirthday) obj;

            if (responseUpComingBirthday.getSTATUS().equalsIgnoreCase("200")) {

                birthdayListAdapter = new BirthdayListAdapter(this,responseUpComingBirthday.getData());
                rv_birthday.setAdapter(birthdayListAdapter);

            } else {

                Toast.makeText(this, responseUpComingBirthday.getMESSAGE(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void _onNext1(Object obj) {

        if (obj instanceof ResponseUpComingBirthday) {
            final ResponseUpComingBirthday responseUpComingBirthday = (ResponseUpComingBirthday) obj;

            if (responseUpComingBirthday.getSTATUS().equalsIgnoreCase("200")) {

                birthdayListAdapter = new BirthdayListAdapter(this,responseUpComingBirthday.getData());
                rv_ani.setAdapter(birthdayListAdapter);

            } else {

                Toast.makeText(this, responseUpComingBirthday.getMESSAGE(), Toast.LENGTH_LONG).show();
            }
        }

    }
}
