package com.abhi.fabkutbusiness.marketing.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;

/**
 * Created by siddharthgautam on 16/12/18.
 */

public class CreatePromotion extends AppCompatActivity implements View.OnClickListener {
    CardView create;
    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    TextView iconRight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_promotion);
        setActionBarView();
        findViewById();
    }


    private void setActionBarView() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }
    private void findViewById() {
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Create Promotion");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconRight = (TextView) actionBarView.findViewById(R.id.actionbar_view_tv_right);
        iconRight.setVisibility(View.VISIBLE);
        iconRight.setOnClickListener(this);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);
        create = (CardView)findViewById(R.id.create);
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create:
                startActivity(new Intent(this,AddOffer.class).putExtra("isEdit",false));
                break;

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.actionbar_view_tv_right:
                startActivity(new Intent(this,RunPromotion.class).putExtra("flag","0"));
                break;

        }
    }
}
