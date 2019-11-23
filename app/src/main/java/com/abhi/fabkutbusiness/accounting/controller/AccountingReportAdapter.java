package com.abhi.fabkutbusiness.accounting.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.accounting.model.ResponseAccountReportData;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmListData;
import com.abhi.fabkutbusiness.crm.view.CrmHistory1;
import com.abhi.fabkutbusiness.crm.view.CrmTab;

import java.util.ArrayList;


public class AccountingReportAdapter extends
        RecyclerView.Adapter<AccountingReportAdapter.MyViewHolder>{

    private ArrayList<ResponseAccountReportData> dataItem;
    private Context mContext;
    private LayoutInflater inflater;




    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView ledgerBal,closed,poAmount,advanceAmount,voucherAmount,finalAmount,drawAmount,BookingAmount,tvdate;




        public MyViewHolder(View view) {
            super(view);
            tvdate = (TextView) view.findViewById(R.id.tv_date);
            ledgerBal = (TextView) view.findViewById(R.id.ledgerAmount);
            closed = (TextView) view.findViewById(R.id.closeAmount);
            poAmount = (TextView) view.findViewById(R.id.poAmount);
            finalAmount = (TextView) view.findViewById(R.id.finalAmount);
            advanceAmount= (TextView) view.findViewById(R.id.advanceAmount);
            voucherAmount = (TextView)view.findViewById(R.id.voucherAmount);
            drawAmount = (TextView)view.findViewById(R.id.drawAmount);
            BookingAmount = (TextView)view.findViewById(R.id.bookingAmount);


        }



    }


    public AccountingReportAdapter(Context mContext, ArrayList<ResponseAccountReportData> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseAccountReportData c = dataItem.get(position);
        holder.ledgerBal.setText(c.getLedgerBalance());
        holder.closed.setText(c.getCloseAmount());
        holder.finalAmount.setText(c.getFinalAmount());
        holder.advanceAmount.setText(""+c.getAdvanceAmount());
        holder.voucherAmount.setText(c.getVoucherAmount());
        holder.poAmount.setText(c.getPoAmount());
        holder.drawAmount.setText(c.getDrawAmount());
        holder.finalAmount.setText(c.getFinalAmount());
        holder.tvdate.setText(c.getAddDate());

        holder.BookingAmount.setText(c.getBookingAmount());


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pettycash_list,parent, false);

        return new MyViewHolder(v);
    }
}



