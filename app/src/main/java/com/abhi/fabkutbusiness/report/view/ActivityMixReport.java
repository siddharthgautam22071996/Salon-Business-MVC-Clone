package com.abhi.fabkutbusiness.report.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abhi.fabkutbusiness.R;

/**
 * Created by siddharthgautam on 07/12/18.
 */

public class ActivityMixReport extends AppCompatActivity implements View.OnClickListener {
    CardView cvBooking,cvCustomer,cvEmployee,cvInventory,cvAcounting,cvPromotion;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my_apps);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        findViewById();

    }

    private void findViewById() {
        cvBooking = (CardView)findViewById(R.id.cvBooking);
        cvCustomer = (CardView)findViewById(R.id.cvCustomer);
        cvEmployee = (CardView)findViewById(R.id.cvEmployee);
        cvInventory = (CardView)findViewById(R.id.cvInventory);
        cvAcounting = (CardView)findViewById(R.id.cvAccounting);
        cvPromotion = (CardView)findViewById(R.id.cvPromotion);
        cvBooking.setOnClickListener(this);
        cvCustomer.setOnClickListener(this);
        cvEmployee.setOnClickListener(this);
        cvInventory.setOnClickListener(this);
        cvAcounting.setOnClickListener(this);
        cvPromotion.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id ==  android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvBooking:
                startActivity(new Intent(this,ActivityBookingReport.class));
                break;
            case R.id.cvCustomer:
                break;
            case R.id.cvAccounting:
                break;
            case R.id.cvPromotion:
                break;
            case R.id.cvEmployee:
                break;
            case R.id.cvInventory:
                break;

        }
    }
}
