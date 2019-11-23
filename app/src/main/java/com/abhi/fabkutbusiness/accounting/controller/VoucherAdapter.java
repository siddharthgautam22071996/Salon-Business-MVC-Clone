package com.abhi.fabkutbusiness.accounting.controller;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.model.ResponseGetVoucherDetailsData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/*
public class VoucherAdapter extends
        RecyclerView.Adapter<VoucherAdapter.MyViewHolder> {

    private ArrayList<ResponseGetVoucherDetailsData> dataItem;
    Context mcontext;

    */
/**
     * View holder class
     * *//*

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Empname,amount,voucher,assignTo;

        public MyViewHolder(View view) {
            super(view);
            Empname = (TextView) view.findViewById(R.id.emp);
            amount = (TextView) view.findViewById(R.id.amount);
            voucher = (TextView) view.findViewById(R.id.voucherNo);}

    }

    public VoucherAdapter(ArrayList<ResponseGetVoucherDetailsData> dataItem) {
        this.dataItem = dataItem;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseGetVoucherDetailsData c = dataItem.get(position);
        holder.voucher.setText(""+c.getVoucherNo() + " " );
        holder.Empname.setText(""+c.getEmp_name() + " " );
        holder.amount.setText(""+c.getAmount() + " " );

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advance_pay,parent, false);

        return new MyViewHolder(v);
    }



}*/

public class VoucherAdapter extends ArrayAdapter<ResponseGetVoucherDetailsData> {
    Context context;
    RetrofitApi.ResponseListener responseListener;
    private ArrayList<ResponseGetVoucherDetailsData> dataItem;



    private static class ViewHolder {
        public TextView Empname,amount,voucher,assignTo;
        LinearLayout llTotal;

    }

    public VoucherAdapter(Context context, ArrayList<ResponseGetVoucherDetailsData> advancePayModel) {
        super(context, R.layout.item_advance_pay, advancePayModel);
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)  {
        ViewHolder viewHolder; // view lookup cache stored in tag

        final ResponseGetVoucherDetailsData advancePayModel = getItem(position);
        final int businessId = Integer.parseInt(Utility.getPreferences(context, Constants.keySalonBusinessId));

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_advance_pay, parent, false);
            viewHolder.Empname = (TextView) convertView.findViewById(R.id.emp);
            viewHolder.assignTo = (TextView) convertView.findViewById(R.id.assignTo);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.amount);
            viewHolder.voucher = (TextView) convertView.findViewById(R.id.voucherNo);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.assignTo.setText(""+advancePayModel.getAssignTo()+" ");
        viewHolder.voucher.setText(""+advancePayModel.getVoucherNo() + " " );
        viewHolder.Empname.setText(""+advancePayModel.getEmp_name() + " " );
        viewHolder.amount.setText("Rs. "+advancePayModel.getAmount() + " " );
        //viewHolder.advance.setText("Rs. "+advancePayModel.getAdvance()+" ");




        return convertView;
    }
}

