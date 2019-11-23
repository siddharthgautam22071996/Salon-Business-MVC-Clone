package com.abhi.fabkutbusiness.accounting.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.view.BillDetailActivity;
import com.abhi.fabkutbusiness.billing.view.InvoiceActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.report.model.modelBookingDataForReport;
import com.abhi.fabkutbusiness.report.view.BookingHistoryActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 21/05/17.
 */


public class TodaysStatementAdapter extends RecyclerView.Adapter<TodaysStatementAdapter.MyViewHolder> {

    private List<ResponseModelAppointmentsData> appointmentsData;
    private int mLayoutResourceId;
    private int reportType;
    private int employee;
    Context mContext;
    int tempPrice = 0 ;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  name, status,time,num,employee,price,billNow;
        ImageView profile_image;
        LinearLayout llTotal;


        MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tv_name);
            status = (TextView) view.findViewById(R.id.tv_status);
            time = (TextView) view.findViewById(R.id.tv_time);
            num = (TextView) view.findViewById(R.id.tv_num);
            employee = (TextView) view.findViewById(R.id.tv_employee);
            price = (TextView) view.findViewById(R.id.tv_price);
            billNow = (TextView) view.findViewById(R.id.tv_bill_now);
            profile_image = (ImageView) view.findViewById(R.id.profile_image);
            billNow.setText("Details");
            llTotal = (LinearLayout) view.findViewById(R.id.ll_total);
            llTotal.setVisibility(View.VISIBLE);

        }
    }


    public TodaysStatementAdapter(Context context, List<ResponseModelAppointmentsData> rateDataList, int mLayoutResourceId, int reportType) {
        this.mContext = context;
        this.reportType = reportType;
        this.appointmentsData = rateDataList;
        this.mLayoutResourceId = mLayoutResourceId;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        final ResponseModelAppointmentsData billingData = appointmentsData.get(position);
        viewHolder.name.setText("" + billingData.getCustomerEndUser_FirstName() + " " + billingData.getCustomerEndUser_LastName());
        viewHolder.status.setText(" (" + Utility.getBookingTypeText(billingData.getBookingType()) + ") ");
        viewHolder.time.setText("" + Utility.getFormattedSlotTime(billingData.getSlots(), billingData.getBookingDate()) + " ");
        viewHolder.num.setText("" + billingData.getCustomerMobile() + " ");
        viewHolder.employee.setText("Services : ");


        //viewHolder.employee.append(" "+billingData.getServices().get(0).getSub_service_name()+" ");



            String employee = BookingHistoryActivity.getEmpName();

            if (reportType == 2){
                tempPrice = 0;
                for (int i=0;i<billingData.getServices().size();i++){

                    if (employee.equalsIgnoreCase(billingData.getServices().get(i).getEmployee_name())) {
                        String tempStr = viewHolder.employee.getText().toString().trim();
                        viewHolder.employee.setText(tempStr+", " + billingData.getServices().get(i).getSub_service_name() + " ");
                        tempPrice  = tempPrice + Integer.parseInt(billingData.getServices().get(i).getRate());
                    }

                }
                //viewHolder.price.setText("Rs. " + billingData.getBillPayment().getTotal() + " ");
                viewHolder.price.setText("Rs. " + tempPrice + " ");

            }else if (reportType == 1) {
                for (int i=0;i<billingData.getServices().size();i++){

                    viewHolder.employee.append(", " + billingData.getServices().get(i).getSub_service_name() + " ");
                }
                viewHolder.price.setText("Rs. " + billingData.getTotalAmount() + " ");
            }




            viewHolder.billNow.setVisibility(View.VISIBLE);

        //viewHolder.employee.setText("Employee : " + billingData.isSync() + " ");

        //viewHolder.price.setText("Rs. " + billingData.getBillPayment().isSync() + " ");
//        Picasso.with(mContext)
//                .load(billingData.getCustomerProfileImage())
//                .placeholder(R.drawable.dummy_profile)
//                .into(viewHolder.profile_image);
//
        Picasso.get()
                .load(billingData.getCustomerProfileImage())
                .placeholder(R.drawable.dummy_profile)
                .into(viewHolder.profile_image);

        viewHolder.billNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InvoiceActivity.class);
                intent.putExtra("data",billingData);
                mContext.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return appointmentsData.size();
    }


}


/*

public class TodaysStatementAdapter extends ArrayAdapter<ResponseModelAppointmentsData> {
    Context context;

    private static class ViewHolder {
        TextView name, status, time, num, billNow, employee, price;
        LinearLayout llTotal;
        ImageView profile_image;

    }

    public TodaysStatementAdapter(Context context, ArrayList<ResponseModelAppointmentsData> billingDatas) {
        super(context, R.layout.item_billing, billingDatas);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_billing, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.status = (TextView) convertView.findViewById(R.id.tv_status);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.num = (TextView) convertView.findViewById(R.id.tv_num);
            viewHolder.employee = (TextView) convertView.findViewById(R.id.tv_employee);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.billNow = (TextView) convertView.findViewById(R.id.tv_bill_now);
            viewHolder.profile_image = (ImageView) convertView.findViewById(R.id.profile_image);
            viewHolder.billNow.setText("Invoice");
            viewHolder.llTotal = (LinearLayout) convertView.findViewById(R.id.ll_total);
            viewHolder.llTotal.setVisibility(View.VISIBLE);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ResponseModelAppointmentsData billingData = getItem(position);

        viewHolder.name.setText("" + billingData.getCustomerEndUser_FirstName() + " " + billingData.getCustomerEndUser_LastName());
        viewHolder.status.setText(" (" + Utility.getBookingTypeText(billingData.getBookingType()) + ") ");
        viewHolder.time.setText("" + Utility.getFormattedSlotTime(billingData.getSlots(), billingData.getBookingDate()) + " ");
        viewHolder.num.setText("" + billingData.getCustomerMobile() + " ");
        viewHolder.employee.setText("Employee : " + billingData.getEmployee().getEmp_name() + " ");
        //viewHolder.employee.setText("Employee : " + billingData.isSync() + " ");
        viewHolder.price.setText("Rs. " + billingData.getBillPayment().getPaid() + " ");
        //viewHolder.price.setText("Rs. " + billingData.getBillPayment().isSync() + " ");
        Picasso.with(context)
                .load(billingData.getCustomerProfileImage())
                .placeholder(R.drawable.dummy_profile)
                .into(viewHolder.profile_image);

        viewHolder.billNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvoiceActivity.class);
                intent.putExtra("data",billingData);
                context.startActivity(intent);
            }
        });


        return convertView;
    }
}
*/
