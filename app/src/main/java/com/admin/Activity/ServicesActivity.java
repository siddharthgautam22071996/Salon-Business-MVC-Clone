package com.admin.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.admin.Adapter.ServiceListAdapter;
import com.admin.Model.AllServicesModel;
import com.admin.R;
import com.admin.Retrofit.RetrofitApi;
import com.admin.business.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.admin.business.MainActivity.textAsBitmap;

public class ServicesActivity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    private RecyclerView mRvServicesList;
    private SwipeRefreshLayout mRefreshServices;
    private FloatingActionButton mAddServicesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        initView();
        mRefreshServices.setRefreshing(true);
        RetrofitApi.getInstance().getServicesList(ServicesActivity.this, ServicesActivity.this);
        mAddServicesBtn.setImageBitmap(textAsBitmap("ADD", 40, Color.WHITE));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvServicesList.setLayoutManager(linearLayoutManager);
        mRefreshServices.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        RetrofitApi.getInstance().getServicesList(ServicesActivity.this, ServicesActivity.this);
                    }
                }
        );

    }

    private void initView() {
        mRvServicesList = (RecyclerView) findViewById(R.id.RvServicesList);
        mRefreshServices = (SwipeRefreshLayout) findViewById(R.id.refreshServices);
        mAddServicesBtn = (FloatingActionButton) findViewById(R.id.addServicesBtn);
        mAddServicesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addServicesBtn:
                // TODO 20/09/17
                break;
            default:
                break;
        }
    }

    @Override
    public void _onCompleted() {
        mRefreshServices.setRefreshing(false);
    }

    @Override
    public void _onError(Throwable e) {
        System.out.println("onError ServicesActivity");
    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof AllServicesModel) {
            try {
                AllServicesModel allServicesModel = (AllServicesModel) obj;
                System.out.println("Data:::" + allServicesModel.getData());
                ServiceListAdapter serviceListAdapter = new ServiceListAdapter(ServicesActivity.this, allServicesModel.getData(), 1);
                mRvServicesList.setAdapter(serviceListAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}