package com.abhi.fabkutbusiness.inventory.order.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.booking.view.EditBookingServiceActivity;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieve;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData1;
import com.abhi.fabkutbusiness.inventory.order.view.OrderRecieve;
import com.abhi.fabkutbusiness.inventory.order.view.SelectItemActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;
import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private Activity context;
    private List<ResponseOrderRecieveData> responseOrderRecieveDataList;
    private List<ResponseOrderRecieveData> searchItem;
    private ArrayList<ResponseOrderRecieveData> selectedDataList;

    private int mLayoutResourceId;
    private Boolean isSelectedLayout;
    private int totalTime = 0, totalCost = 0;


    public ArrayList<ResponseOrderRecieveData> getSelectedRateDataList() {
        return selectedDataList;
    }



    public void filterData(ArrayList<ResponseOrderRecieveData> searchedData) {
        responseOrderRecieveDataList.clear();
        responseOrderRecieveDataList.addAll(searchItem);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQty,tvPrice;
        ImageView ivTick;
        View convertView;


        MyViewHolder(View view) {
            super(view);
            convertView = view;

            ivTick = (ImageView) view.findViewById(R.id.iv_service_check);
            tvName = (TextView) view.findViewById(R.id.tv_service_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_service_price);
            tvQty = (TextView) view.findViewById(R.id.tv_qty);

        }
    }


    public ItemsAdapter(ArrayList<ResponseOrderRecieveData> orderRecieveData, ArrayList<ResponseOrderRecieveData> selectedData, int mLayoutResourceId, Boolean isSelectedLayout, Activity context) {
        this.responseOrderRecieveDataList = orderRecieveData;
        this.mLayoutResourceId = mLayoutResourceId;
        this.isSelectedLayout = isSelectedLayout;
        this.context = context;
        selectedDataList = selectedData;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);
        //selectedDataList.clear();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final ResponseOrderRecieveData orderRecieveData = responseOrderRecieveDataList.get(position);

        if (position == 0) {

        }
        if (!isSelectedLayout) {
            holder.ivTick.setVisibility(View.GONE);
            for (ResponseOrderRecieveData data : selectedDataList) {
                if (data.getItem_name().equals(orderRecieveData.getItem_name())) {
                    holder.ivTick.setVisibility(View.VISIBLE);
                }
            }
        }

        holder.tvName.setText(orderRecieveData.getItem_name());
        holder.tvPrice.setText("" + orderRecieveData.getType_Code());

        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectedLayout) {
                    if (holder.ivTick.getVisibility() == View.GONE) {

                        showDialog(((SelectItemActivity) context), position,holder.ivTick,holder.tvQty ,orderRecieveData.getUnitName() ,orderRecieveData, orderRecieveData.getItem_name());

                       // Toast.makeText(context, ""+selectedDataList.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        for (ResponseOrderRecieveData data : selectedDataList) {
                            if (data.getItem_name().equals(orderRecieveData.getItem_name())) {
                                selectedDataList.remove(data);
                                break;
                            }
                        }
                        holder.ivTick.setVisibility(View.GONE);
                    }
                    // notifyItemChanged(position);
                }
            }
        });



        if (isSelectedLayout) {

            if (position == (responseOrderRecieveDataList.size() - 1)) {
               }
        }

    } private void showDialog(final Activity activity, final int pos, final ImageView imageView , final TextView qty, final String unit , final ResponseOrderRecieveData orderRecieveData , String name) {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View deleteDialogView = factory.inflate(R.layout.order_recieve_qty_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(activity).create();
        deleteDialog.setView(deleteDialogView);

        TextView tvName = (TextView) deleteDialogView.findViewById(R.id.tv_item_name);
        TextView tvUnit = (TextView) deleteDialogView.findViewById(R.id.tv_unit);
        tvName.setText(name);
        tvUnit.setText(unit);

        final EditText etRemark = (EditText) deleteDialogView.findViewById(R.id.et_remark);
        final EditText etQty = (EditText) deleteDialogView.findViewById(R.id.et_qty);


        TextView tvSubmit = (TextView) deleteDialogView.findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strQty = etQty.getText().toString().trim();
                if (strQty.length() > 0) {


                    selectedDataList.add(orderRecieveData);
                    imageView.setVisibility(View.VISIBLE);
                    qty.setVisibility(View.VISIBLE);
                    selectedDataList.get(pos).setQty(strQty);
                    selectedDataList.get(pos).setRemark(etRemark.getText().toString());
                    qty.setText("Qty : "+strQty +" "+unit);

                    deleteDialog.dismiss();




                } else {
                    Utility.showToast(context, "Please enter Qty");
                }


            }
        });

        deleteDialog.show();

    }


    @Override
    public int getItemCount() {
        return responseOrderRecieveDataList.size();
    }


}