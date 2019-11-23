package com.abhi.fabkutbusiness.employee.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpAttendence;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmpSummary;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class EmployeeSummaryAdapter extends
        RecyclerView.Adapter<EmployeeSummaryAdapter.MyViewHolder> {

    private ArrayList<ResponseModelEmpSummary> dataItem;
    private Context mContext;
    String bId;
    String check;
    private LayoutInflater inflater;
    SimpleDateFormat simpleTimeFormat;
    RetrofitApi.ResponseListener responseListener;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView name,tv_totlaService,tv_revenue;
        public TextView checkIn;
        public ImageView profile;
        public TextView tvCheckOut;






        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_name);
            checkIn = (TextView) view.findViewById(R.id.tv_checkIn);
            tv_revenue = (TextView) view.findViewById(R.id.tv_revenue);
            tv_totlaService = (TextView) view.findViewById(R.id.tv_totlaService);
            profile = (ImageView) view.findViewById(R.id.iv_photo);
            bId  = Utility.getPreferences(mContext, Constants.keySalonBusinessId);
            tvCheckOut = (TextView)view.findViewById(R.id.tv_checkOut);
           // presnt = (RelativeLayout)view.findViewById(R.id.presentBt);




        }



    }





    public EmployeeSummaryAdapter(Context mContext, ArrayList<ResponseModelEmpSummary> dataItem ) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;



    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseModelEmpSummary c = dataItem.get(position);
        holder.name.setText(c.getEmp_name());
        holder.checkIn.setText(c.getInTime());
        holder.tvCheckOut.setText(c.getOutTime());
        holder.tv_revenue.setText(c.getTodayRevenue());
        holder.tv_totlaService.setText(c.getTotal_service());
        //holder.email.setText(c.);


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_emp_summary,parent, false);

        return new MyViewHolder(v);
    }
}



