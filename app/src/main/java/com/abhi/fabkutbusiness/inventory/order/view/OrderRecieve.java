package com.abhi.fabkutbusiness.inventory.order.view;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.SelectServiceActivity;
import com.abhi.fabkutbusiness.inventory.itemMaster.controller.MyStockAdapter;
import com.abhi.fabkutbusiness.inventory.order.controller.ItemsAdapter;
import com.abhi.fabkutbusiness.inventory.order.controller.SelectedItemsAdapter;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieve;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 04/04/18.
 */

public class OrderRecieve extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener{
    View actionBarView;
    TextView tvTitle,selectItems,tvDate;
    ImageView iconLeft,iconRight;
    private String currentDate;
    RecyclerView rvItems;
    ArrayList<ResponseOrderRecieveData> responseOrderRecieveData1;
    SelectedItemsAdapter mAdapterItem;
    boolean isEdit;
    LinearLayout listTitleBar;
    ArrayList<ResponseOrderRecieveData> responseOrderRecieveData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_receive);
        setActionBarView();
        findViewById();


    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Order Receive");
        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setOnClickListener(this);
        selectItems = (TextView)findViewById(R.id.tv_select_item);
        selectItems.setOnClickListener(this);
        listTitleBar = (LinearLayout)findViewById(R.id.listBar);
        rvItems = (RecyclerView) findViewById(R.id.rv_services);
        rvItems.setNestedScrollingEnabled(false);



        mAdapterItem = new SelectedItemsAdapter(responseOrderRecieveData, new ArrayList<ResponseOrderRecieveData>(), R.layout.item_my_stock
                , true, this);
        rvItems.setAdapter(mAdapterItem);



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

            case R.id.tv_select_item:
               // startActivity(new Intent(OrderRecieve.this,SelectItemActivity.class));

                startActivityForResult(new Intent(OrderRecieve.this, SelectItemActivity.class)
                        .putExtra("data", responseOrderRecieveData1), 101);
                break;

            case R.id.tv_date:
                Utility.datePickerDialog(OrderRecieve.this, this);
                break;
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();

        currentDate = Utility.formatDateForDisplay(date, "MM-dd-yyyy", Constants.displayDateFormat);
        //tvDate.setText(currentDate);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 101:

                if (resultCode == RESULT_OK) {

                    responseOrderRecieveData1 =data.getParcelableArrayListExtra("dataList");
                    this.responseOrderRecieveData.clear();
                    if (responseOrderRecieveData1.size()>0){
                        listTitleBar.setVisibility(View.VISIBLE);
                    }else{
                        listTitleBar.setVisibility(View.GONE);
                    }
                    this.responseOrderRecieveData.addAll(responseOrderRecieveData1);
                   //rvItems.setAdapter(mAdapterItem);
                    mAdapterItem.notifyDataSetChanged();

                }

                break;


        }
    }

}
