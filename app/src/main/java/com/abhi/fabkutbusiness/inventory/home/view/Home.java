package com.abhi.fabkutbusiness.inventory.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.inventory.itemMaster.view.MyStock;
import com.abhi.fabkutbusiness.inventory.order.view.OrderRecieve;
import com.abhi.fabkutbusiness.inventory.order.view.OrderTab;
import com.abhi.fabkutbusiness.inventory.vendor.view.VendorTab;


/**
 * Created by SIDDHARTH on 7/15/2017.
 */

public class Home  extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    public TextView tvTitle;
    ImageView iconLeft;
    TextView itemMaster, vendor, order ,report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.home);
        setActionBarView();
        findViewById();


    }
    private void setActionBarView() {

        /*getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View customView = getLayoutInflater().inflate(R.layout.actionbar_view_custom, null);
        getSupportActionBar().setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);*/

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Inventory");
        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        //iconLeft.setVisibility( View.VISIBLE );

        itemMaster = (TextView)findViewById(R.id.iv_master);
        itemMaster.setOnClickListener(this);

        vendor = (TextView) findViewById(R.id.iv_vendor);
        vendor.setOnClickListener(this);

        order = (TextView) findViewById(R.id.iv_order);
        order.setOnClickListener(this);
         report = (TextView) findViewById(R.id.iv_report);
        report.setOnClickListener(this);


    }


    public void stock() {
        Intent i = new Intent(getApplicationContext(), MyStock.class);
        startActivity(i);

    }

    public void vendor_tab() {
        Intent i = new Intent(getApplicationContext(), VendorTab.class);
        startActivity(i);

    }


    public void order() {

        Intent i = new Intent(getApplicationContext(), OrderRecieve.class);
        startActivity(i);
    }


    public void report(){
        Toast.makeText( this, "report", Toast.LENGTH_SHORT ).show();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.iv_master:
                stock();
                break;

            case R.id.iv_vendor:
                vendor_tab();
                break;

            case R.id.iv_order:
                order();
                break;

            case R.id.iv_report:
                report();
                break;


        }

    }




}