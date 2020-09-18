package com.admin.business;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.admin.Adapter.SaloonListAdapter;
import com.admin.Model.AllSaloonModel;
import com.admin.R;
import com.admin.Retrofit.RetrofitApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRvSaloonList;
    private SwipeRefreshLayout mSwiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mSwiperefresh.setRefreshing(true);
        RetrofitApi.getInstance().getSaloonList(MainActivity.this, MainActivity.this);
        mFloatingActionButton.setImageBitmap(textAsBitmap("ADD", 40, Color.WHITE));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvSaloonList.setLayoutManager(linearLayoutManager);
        mSwiperefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        RetrofitApi.getInstance().getSaloonList(MainActivity.this, MainActivity.this);
                    }
                }
        );
    }

    private void initView() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(this);
        mRvSaloonList = (RecyclerView) findViewById(R.id.RvSaloonList);
        mSwiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton:
                // TODO 20/09/11
                startActivity(new Intent(MainActivity.this, FormActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void _onCompleted() {
        mSwiperefresh.setRefreshing(false);
    }

    @Override
    public void _onError(Throwable e) {
        System.out.println("OnErrorAllSaloonList");
        e.printStackTrace();
    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof AllSaloonModel) {
            try {
                AllSaloonModel allSaloonModel = (AllSaloonModel) obj;
                SaloonListAdapter saloonListAdapter = new SaloonListAdapter(MainActivity.this, allSaloonModel.getData(), 1);
                mRvSaloonList.setAdapter(saloonListAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}