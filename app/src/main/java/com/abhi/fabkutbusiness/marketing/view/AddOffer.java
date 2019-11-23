package com.abhi.fabkutbusiness.marketing.view;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotion;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotionData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 26/10/18.
 */

public class AddOffer extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener,RetrofitApi.ResponseListener{
    View actionBarView;
    TextView startDate,endDate;
    String currDate;
    int flag = 0;
    RadioButton offer,cupon;
    Spinner ofeer_type;
    EditText etTitle,amount,code,details,minimumValue;
    TextView save,offerType;
    Boolean isEdit;
    int pos;
    String offer_id;
    ArrayList<ResponseCreatePromotionData>responseCreatePromotionData= new ArrayList<>();


    TextView tvTitle;
    ImageView iconLeft, iconRight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_offers_deal);
        setActionBarView();
        findViewById();
        isEdit = getIntent().getBooleanExtra("isEdit",false);
        if (isEdit){
            setData();
        }


    }

    private void setData() {
        responseCreatePromotionData = this.getIntent().getExtras().getParcelableArrayList("data");
        pos = this.getIntent().getExtras().getInt("pos");
        if (responseCreatePromotionData.get(pos).getFabkut_offer_type_id().equalsIgnoreCase("1")){
            cupon.setChecked(true);
        }else if (responseCreatePromotionData.get(pos).getFabkut_offer_type_id().equalsIgnoreCase("2")){
            offer.setChecked(true);
        }
        etTitle.setText(responseCreatePromotionData.get(pos).getFabkut_offer_name());
        code.setText(responseCreatePromotionData.get(pos).getFabkut_offer_code());
        startDate.setText(Utility.formatDateForDisplay(responseCreatePromotionData.get(pos).getFabkut_offer_start(),"YYYY-mm-dd hh:mm",""+ Constants.displayDateFormatWithTime));
        endDate.setText(Utility.formatDateForDisplay(responseCreatePromotionData.get(pos).getFabkut_offer_end(),"YYYY-mm-dd hh:mm",""+ Constants.displayDateFormatWithTime));
        details.setText(responseCreatePromotionData.get(pos).getFabkut_offer_desc());
        minimumValue.setText(responseCreatePromotionData.get(pos).getFabkut_offer_price());
        save.setText("UPDATE");
        offer_id=responseCreatePromotionData.get(pos).getFabkut_offer_id();


    }

    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Add Offer");
        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);
        startDate =(TextView)findViewById(R.id.fromDate);
        startDate.setOnClickListener(this);
        endDate =(TextView)findViewById(R.id.endDate);
        startDate.setText(Utility.getCurrentDate(Constants.displayDateFormat));
        endDate.setText(Utility.getCurrentDate(Constants.displayDateFormat));
        endDate.setOnClickListener(this);
        etTitle = (EditText) findViewById(R.id.et_title);
        code = (EditText) findViewById(R.id.code);
        amount = (EditText) findViewById(R.id.et_amount);
        amount.addTextChangedListener(new GenericTextWatcher(amount));
        details = (EditText) findViewById(R.id.et_details);
        minimumValue = (EditText) findViewById(R.id.et_min);
        save = (TextView) findViewById(R.id.tv_save);
        save.setOnClickListener(this);

        cupon = (RadioButton) findViewById(R.id.rb_coupn);
        offer = (RadioButton) findViewById(R.id.rb_offer);
        offerType = (TextView)findViewById(R.id.type);


        offer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    offerType.setText("%");
                    setName();
                }
            }
        });

        cupon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    offerType.setText("INR");
                   setName();
                }
            }
        });

        setName();
    }

    private void setName() {
        etTitle.setText(amount.getText().toString()+" "+offerType.getText().toString()+" - Off  "+endDate.getText().toString());
        code.setText("OFF"+amount.getText().toString());
    }


    boolean checkValidation(){
        boolean ret = true;
        if(!Utility.hasText(amount))ret=false;
        if(!Utility.hasText(etTitle))ret=false;
        if(!Utility.hasText(code))ret=false;
        if(!Utility.hasText(details))ret=false;

        return ret;
    }

    private void setActionBarView() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();
        currDate = Utility.formatDateForDisplay(date,"MM-dd-yyyy",""+ Constants.displayDateFormat);
        if (flag==1){
            startDate.setText(currDate);
        }else {
            endDate.setText(currDate);
        }

        setName();
        //serviceRefresh();



    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fromDate:
                flag=1;
                Utility.datePickerDialog(this, this);
                break;

            case R.id.endDate:
                flag=2;
                Utility.datePickerDialog(this, this);
                break;


            case R.id.actionbar_view_icon_left:
                finish();
                break;
            case R.id.tv_save:
                if (checkValidation()) {
                    if (isEdit){
                        update();
                    }else {
                        upload();

                    }
                }
                break;
        }
    }

    private void update() {
        String offerTypeId = "";
        if (offer.isChecked()){
            offerTypeId="2";
            RetrofitApi.getInstance().upCreateOfferApi(this,this,
                    "update_Offer",
                    ""+offer_id,
                    ""+offerTypeId,
                    ""+code.getText().toString().trim(),
                    ""+etTitle.getText().toString().trim(),
                    ""+details.getText().toString().trim(),
                    "",
                    ""+startDate.getText().toString().trim(),
                    ""+endDate.getText().toString().trim(),
                    ""+minimumValue.getText().toString().trim(),
                    "1",
                    ""+amount.getText().toString().trim(),
                    ""+Utility.getPreferences(this,Constants.keySalonBusinessId)
            );
        }else if (cupon.isChecked()){
            offerTypeId="1";
            RetrofitApi.getInstance().upCreateOfferApi(this,this,
                    "update_coupon",
                    ""+offer_id,
                    ""+offerTypeId,
                    ""+code.getText().toString().trim(),
                    ""+etTitle.getText().toString().trim(),
                    ""+details.getText().toString().trim(),
                    "",
                    ""+startDate.getText().toString().trim(),
                    ""+endDate.getText().toString().trim(),
                    ""+minimumValue.getText().toString().trim(),
                    "1",
                    ""+amount.getText().toString().trim(),
                    ""+Utility.getPreferences(this,Constants.keySalonBusinessId)
            );
        }
    }

    private void upload() {
        String offerTypeId = "";
        if (offer.isChecked()){
            offerTypeId="2";
            RetrofitApi.getInstance().upCreateOfferApi(this,this,
                    "insert_Offer",
                    "",
                    ""+offerTypeId,
                    ""+code.getText().toString().trim(),
                    ""+etTitle.getText().toString().trim(),
                    ""+details.getText().toString().trim(),
                    "",
                    ""+startDate.getText().toString().trim(),
                    ""+endDate.getText().toString().trim(),
                    ""+minimumValue.getText().toString().trim(),
                    "1",
                    ""+amount.getText().toString().trim(),
                    ""+Utility.getPreferences(this,Constants.keySalonBusinessId)
            );
        }else if (cupon.isChecked()){
            offerTypeId="1";
            RetrofitApi.getInstance().upCreateOfferApi(this,this,
                    "insert_coupon",
                    "",
                    ""+offerTypeId,
                    ""+code.getText().toString().trim(),
                    ""+etTitle.getText().toString().trim(),
                    ""+details.getText().toString().trim(),
                    "",
                    ""+startDate.getText().toString().trim(),
                    ""+endDate.getText().toString().trim(),
                    ""+minimumValue.getText().toString().trim(),
                    "1",
                    ""+amount.getText().toString().trim(),
                    ""+Utility.getPreferences(this,Constants.keySalonBusinessId)
            );
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
        ResponseCreatePromotion responseCreatePromotion = (ResponseCreatePromotion) obj;
        if (responseCreatePromotion.getSTATUS().equalsIgnoreCase("200")){
            Utility.showToast(this,responseCreatePromotion.getMESSAGE());
            startActivity(new Intent(this,RunPromotion.class).putExtra("flag","0"));
            finish();
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.et_amount:
                    setName();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
