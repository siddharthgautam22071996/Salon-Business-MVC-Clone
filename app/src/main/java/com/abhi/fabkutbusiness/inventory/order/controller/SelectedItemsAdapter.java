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

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData;
import com.abhi.fabkutbusiness.inventory.order.view.SelectItemActivity;

import java.util.ArrayList;
import java.util.List;


public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.MyViewHolder> {

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
        TextView tvName, qty,expiry,unit;
        ImageView ivTick;
        View convertView;


        MyViewHolder(View view) {
            super(view);
            convertView = view;

           // ivTick = (ImageView) view.findViewById(R.id.iv_service_check);
            tvName = (TextView) view.findViewById(R.id.name);
            qty = (TextView) view.findViewById(R.id.qty);
            unit = (TextView) view.findViewById(R.id.unit);
            expiry = (TextView) view.findViewById(R.id.expiry);

        }
    }


    public SelectedItemsAdapter(ArrayList<ResponseOrderRecieveData> orderRecieveData, ArrayList<ResponseOrderRecieveData> selectedData, int mLayoutResourceId, Boolean isSelectedLayout, Activity context) {
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
        holder.expiry.setText("" + orderRecieveData.getProduct_code());
        holder.unit.setText("" + orderRecieveData.getUnitName());
        holder.qty.setText("" + orderRecieveData.getQty());

        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectedLayout) {
                    if (holder.ivTick.getVisibility() == View.GONE) {

                        showDialog(((SelectItemActivity) context), position,holder.ivTick, orderRecieveData, orderRecieveData.getItem_name());

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

    } private void showDialog(final Activity activity, final int pos, final ImageView imageView , final ResponseOrderRecieveData orderRecieveData , String name) {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View deleteDialogView = factory.inflate(R.layout.order_recieve_qty_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(activity).create();
        deleteDialog.setView(deleteDialogView);

        TextView tvName = (TextView) deleteDialogView.findViewById(R.id.tv_item_name);
        tvName.setText(name);

        final EditText etReason = (EditText) deleteDialogView.findViewById(R.id.et_remark);
        final EditText etQty = (EditText) deleteDialogView.findViewById(R.id.et_qty);


        TextView tvSubmit = (TextView) deleteDialogView.findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strQty = etQty.getText().toString().trim();
                if (strQty.length() > 0) {


                    selectedDataList.add(orderRecieveData);
                    imageView.setVisibility(View.VISIBLE);
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