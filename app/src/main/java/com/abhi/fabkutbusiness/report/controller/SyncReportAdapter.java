package com.abhi.fabkutbusiness.report.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;

import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class SyncReportAdapter extends ArrayAdapter<ResponseModelCustomerData> {
    Context context;



    private static class ViewHolder {
        TextView name,date,service,mob;
        LinearLayout llTotal;
        RelativeLayout relativeLayout;

    }

    public SyncReportAdapter(Context context, ArrayList<ResponseModelCustomerData> responseMyStockData) {
        super(context, R.layout.crm_list_item, responseMyStockData);
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)  {
        ViewHolder viewHolder; // view lookup cache stored in tag

        final ResponseModelCustomerData responseModelAppointmentsData = getItem(position);

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.crm_list_item, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.date = (TextView) convertView.findViewById(R.id.tv_email);
            viewHolder.service = (TextView) convertView.findViewById(R.id.tv_bill_now);
            viewHolder.mob = (TextView) convertView.findViewById(R.id.crmlist_mobileNo_);
            viewHolder.llTotal = (LinearLayout)convertView.findViewById(R.id.ll);
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl_editProfile);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.name.setText(""+responseModelAppointmentsData.getEndUser_FirstName() + " "+responseModelAppointmentsData.getEndUser_LastName() );
        viewHolder.relativeLayout.setVisibility(View.GONE);
        viewHolder.llTotal.setVisibility(View.VISIBLE);

        viewHolder.service.setText(" "+responseModelAppointmentsData.isSync() + " " );
        viewHolder.date.setText(""+responseModelAppointmentsData.getEmail() + " " );
        viewHolder.mob.setText(""+responseModelAppointmentsData.getContact_no() + " " );





        return convertView;
    }
}
