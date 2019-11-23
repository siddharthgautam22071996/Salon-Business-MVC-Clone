/*
package com.abhi.fabkutbusiness.accounting.controller;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.model.ModelPettyCash;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsertData;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.accounting.view.AccountingActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;



public class PettyCashAdapter extends ArrayAdapter<ResponseLedgerInsertData> {
    Context context;
    RetrofitApi.ResponseListener responseListener;





    private static class ViewHolder {
        TextView name,  date, num, advance, email,advanceSubmit;
        LinearLayout llTotal;

    }

    public PettyCashAdapter(Context context, ArrayList<ResponseLedgerInsertData> modelPettyCash) {
        super(context, R.layout.item_pettycash_list, modelPettyCash);
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)  {
        ViewHolder viewHolder; // view lookup cache stored in tag

        final ResponseLedgerInsertData modelPettyCash = getItem(position);
        final int businessId = Integer.parseInt(Utility.getPreferences(context, Constants.keySalonBusinessId));

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_pettycash_list, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_petty_cash_date);

            viewHolder.advanceSubmit = (TextView) convertView.findViewById(R.id.tv_amount);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.name.setText(""+modelPettyCash.getLedger_date() + " " );
        viewHolder.advanceSubmit.setText(" "+modelPettyCash.getLedger_balance()+" ");




        return convertView;
    }
}
*/
