package com.abhi.fabkutbusiness.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;

/**
 * Created by siddharthgautam on 06/12/18.
 */

public class ConatctUsActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvBdoCall,tvManagerCall,tvManagerMail,tvBdoMail,tvCustomerCareCall,tvCustomerCareMail;
    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setActionBarView();
        findViewById();

    }

    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Contact Us");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);
        tvBdoCall = (TextView)findViewById(R.id.bdoCall);
        tvBdoCall.setOnClickListener(this);
        tvManagerCall = (TextView)findViewById(R.id.managerCall);
        tvManagerCall.setOnClickListener(this);
        tvManagerMail = (TextView)findViewById(R.id.managerMail);
        tvManagerMail.setOnClickListener(this);

        tvCustomerCareCall= (TextView)findViewById(R.id.customerCareCall);
        tvCustomerCareMail= (TextView)findViewById(R.id.customerCareMail);
        tvBdoMail= (TextView)findViewById(R.id.bdoMail);
        tvCustomerCareCall.setOnClickListener(this);
        tvCustomerCareMail.setOnClickListener(this);
        tvBdoMail.setOnClickListener(this);


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

            case R.id.bdoCall:
                Utility.openDialerPaidWithNumber(this,tvBdoCall.getText().toString().trim());
                break;

            case R.id.managerCall:
                Utility.openDialerPaidWithNumber(this,tvManagerCall.getText().toString().trim());
                break;

            case R.id.managerMail:
                //Utility.sendMail(this,tvManagerMail.getText().toString().trim());
                break;

            case R.id.customerCareCall:
                Utility.openDialerPaidWithNumber(this,tvCustomerCareCall.getText().toString().trim());
                break;

            case R.id.customerCareMail:
                //Utility.sendMail(this,tvCustomerCareMail.getText().toString().trim());
                break;
            case R.id.bdoMail:
                //Utility.sendMail(this,tvBdoMail.getText().toString().trim());
                break;




            case R.id.actionbar_view_icon_left:
                finish();
                break;
        }
    }
}
