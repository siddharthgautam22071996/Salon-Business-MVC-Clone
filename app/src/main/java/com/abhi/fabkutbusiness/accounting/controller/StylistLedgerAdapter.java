/*
package com.abhi.fabkutbusiness.accounting.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsertData;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.EditBookingServiceActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class StylistLedgerAdapter extends RecyclerView.Adapter<StylistLedgerAdapter.MyViewHolder> {

    private List<ResponseLedgerInsertData> ledgerInsertDatas;
    private Activity context;
    private int selectedIndex = -1;
    private String selectedEmpId = "-1";

    public ResponseLedgerInsertData getSelectedStylistDataList() {
        if (selectedIndex == -1)
            return null;
        else
            return ledgerInsertDatas.get(selectedIndex);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate ,tvLedger;



        MyViewHolder(View view) {
            super(view);
            tvDate = (TextView) view.findViewById(R.id.tv_petty_cash_date);
            tvLedger = (TextView) view.findViewById(R.id.tv_amount);

        }
    }


    public StylistLedgerAdapter(List<ResponseLedgerInsertData> ledgerInsertDatas, Activity context) {
        this.ledgerInsertDatas = ledgerInsertDatas;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pettycash_list, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ResponseLedgerInsertData employeeData = ledgerInsertDatas.get(position);


        holder.tvDate.setText(employeeData.getLedger_date());
        holder.tvLedger.setText(employeeData.getLedger_balance());


    }




    @Override
    public int getItemCount() {
        return ledgerInsertDatas.size();
    }


}*/
