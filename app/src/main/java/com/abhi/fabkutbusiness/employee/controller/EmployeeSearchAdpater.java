/*
package com.abhi.fabkutbusiness.employee.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmListData;
import com.abhi.fabkutbusiness.crm.view.CrmHistory1;
import com.abhi.fabkutbusiness.crm.view.CrmList;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchAdpater extends ArrayAdapter<ResponseModelEmployeeData> {
    private final Context mContext;
    private final List<ResponseModelEmployeeData> responseModelEmployeeData;
    private final List<ResponseModelEmployeeData> responseModelEmployeeData_All;
    private final List<ResponseModelEmployeeData> responseModelEmployeeData_Suggestion;
    private final int mLayoutResourceId;

    public EmployeeSearchAdpater(Context context, int resource, List<ResponseModelEmployeeData> responseModelEmployeeData) {
        super(context, resource, responseModelEmployeeData);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.responseModelEmployeeData = new ArrayList<>(responseModelEmployeeData);
        this.responseModelEmployeeData_All = new ArrayList<>(responseModelEmployeeData);
        this.responseModelEmployeeData_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return responseModelEmployeeData.size();
    }

    public ResponseModelEmployeeData getItem(int position) {
        return responseModelEmployeeData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            final ResponseModelEmployeeData responseModelEmployeeData = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.textView);
            name.setText("" + responseModelEmployeeData.getEmp_name() + " (" + responseModelEmployeeData.getEmp_id() + ")");


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity activity = (CrmList) mContext;

                    String seatNum = Utility.getEmptySeatNum(activity);

                    activity.startActivity(new Intent(activity, CrmHistory1.class)
                            .putExtra("item", responseModelEmployeeData.getEnduser_id())
                            .putExtra("contact", re.getContact_no())
                            .putExtra("email", responseModelCustomerData.getEmail())
                            .putExtra("name", responseModelCustomerData.getEnduser_name()));
                    //    ((BookNowActivity) mContext).setData(responseModelCustomerData);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                //return ((ResponseModelCustomerData) resultValue).getEndUser_FirstName();
                return "";
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    responseModelCustomerDatas_Suggestion.clear();
                    for (ResponseCrmListData responseModelCustomerData : responseModelCustomerDatas_All) {
                        if (responseModelCustomerData.getEnduser_name().toLowerCase().startsWith(constraint.toString().toLowerCase())
                                || responseModelCustomerData.getContact_no().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            responseModelCustomerDatas_Suggestion.add(responseModelCustomerData);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = responseModelCustomerDatas_Suggestion;
                    filterResults.count = responseModelCustomerDatas_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                responseModelCustomerDatas.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using responseModelCustomerDatas.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof ResponseCrmListData) {
                            responseModelCustomerDatas.add((ResponseCrmListData) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    responseModelCustomerDatas.addAll(responseModelCustomerDatas_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}*/
