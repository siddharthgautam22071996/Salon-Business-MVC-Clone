package com.admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.admin.Activity.ServicesActivity;
import com.admin.business.FormActivity;
import com.admin.business.MainActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView drawIcon;
    DrawerLayout mDrawerLayout;
    NavigationView home_nav_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        intView();
    }

    private void intView() {
        drawIcon =findViewById(R.id.drawIcon);
        home_nav_view =findViewById(R.id.home_nav_view);
        mDrawerLayout =findViewById(R.id.mDrawerLayout);
        drawIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawIcon:
                mDrawerLayout.openDrawer(home_nav_view);
                break;
        }
    }

    public void onClick_subscribe(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onClick_service(View view){
        startActivity(new Intent(this, ServicesActivity.class));
    }
}