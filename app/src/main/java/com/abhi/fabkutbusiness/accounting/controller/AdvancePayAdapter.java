package com.abhi.fabkutbusiness.accounting.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.model.AdvancePayModel;
import com.abhi.fabkutbusiness.accounting.model.ResponseGetVoucherDetails;
import com.abhi.fabkutbusiness.accounting.model.ResponseGetVoucherDetailsData;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.accounting.view.AccountingActivity;
import com.abhi.fabkutbusiness.accounting.view.AdvancePayFragment;
import com.abhi.fabkutbusiness.billing.view.BillDetailActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class AdvancePayAdapter extends ArrayAdapter<ResponseGetVoucherDetailsData> {
    Context context;
    RetrofitApi.ResponseListener responseListener;



    private static class ViewHolder {
        TextView Empname,amount,voucher,assignTo,  date, num, advance, email,advanceSubmit;
        LinearLayout llTotal;

    }

    public AdvancePayAdapter(Context context, ArrayList<ResponseGetVoucherDetailsData> advancePayModel) {
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
            viewHolder.amount = (TextView) convertView.findViewById(R.id.amount);
            viewHolder.voucher = (TextView) convertView.findViewById(R.id.voucherNo);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.assignTo.setText(""+advancePayModel.getAssignTo() + " " );
        viewHolder.voucher.setText(""+advancePayModel.getVoucherNo() + " " );
        viewHolder.Empname.setText(""+advancePayModel.getEmp_name() + " " );
        viewHolder.amount.setText(""+advancePayModel.getAmount() + " " );
        //viewHolder.advance.setText("Rs. "+advancePayModel.getAdvance()+" ");




        return convertView;
    }
}
