package com.abhi.fabkutbusiness.inventory.itemMaster.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemBrand;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemCategory;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthgautam on 03/04/18.
 */

public class ItemCreation extends AppCompatActivity implements View.OnClickListener,RetrofitApi.ResponseListener {
    View actionBarView;
    ImageView back;
    TextView tvTitle;
    Spinner brand,type,color,category,unit;
    List<String> brand_name = new ArrayList<>();
    List<String> brand_id = new ArrayList<>();
    List<String> cat_name = new ArrayList<>();
    List<String> cat_id = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_master);
        setActionBarView();
        findViewById();
    }

    private void findViewById() {
        brand_id.clear();
        brand_name.clear();
        cat_id.clear();
        cat_name.clear();
        brand_name.add("Select Brand");
        brand_id.add("0");
        cat_name.add("Select Brand");
        cat_id.add("0");

        back = (ImageView)actionBarView.findViewById(R.id.actionbar_view_icon_left);
        back.setVisibility(View.VISIBLE);
        back.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        back.setOnClickListener(this);
        tvTitle = (TextView)actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Add New Item");

        brand = (Spinner)findViewById(R.id.spinner_brand);
        category = (Spinner)findViewById(R.id.spinner_itemCat);
        color = (Spinner)findViewById(R.id.spinner_itemColor);
        unit = (Spinner)findViewById(R.id.spinner_itemUnit);
        type = (Spinner)findViewById(R.id.spinner_itemType);
        intidata();


    }

    private void intidata() {
        RetrofitApi.getInstance().brandtApiMethod(this,this);
        RetrofitApi.getInstance().itemCategoryApiMethod(this,this);
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

        if (obj instanceof ResponseItemBrand){
            ResponseItemBrand responseItemBrand = (ResponseItemBrand) obj;
            if (responseItemBrand.getSTATUS().equalsIgnoreCase("200")){
                for(int i = 0 ;i<responseItemBrand.getData().size();i++){
                    brand_name.add(responseItemBrand.getData().get(i).getBrand());
                    brand_id.add(responseItemBrand.getData().get(i).getBrand_id());
                }

                Utility.spinner(ItemCreation.this,brand,brand_name);
            }else {
                Utility.showToast(getApplicationContext(),responseItemBrand.getMESSAGE());
            }

        }else if (obj instanceof ResponseItemCategory){
            ResponseItemCategory responseItemCategory = (ResponseItemCategory) obj;
            if (responseItemCategory.getSTATUS().equalsIgnoreCase("200")){
                for(int i = 0 ;i<responseItemCategory.getData().size();i++){
                    cat_name.add(responseItemCategory.getData().get(i).getTypee_name());
                    cat_id.add(responseItemCategory.getData().get(i).getType_code());
                }

                Utility.spinner(ItemCreation.this,category,cat_name);

            }
            else {
                Utility.showToast(getApplicationContext(),responseItemCategory.getMESSAGE());
            }
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }
}
