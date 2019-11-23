package com.abhi.fabkutbusiness.employee.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpAttendence;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpCheckIn;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpCheckInData;
import com.abhi.fabkutbusiness.employee.view.Employee_attendence;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class EmployeeAttendenceAdapter extends
        RecyclerView.Adapter<EmployeeAttendenceAdapter.MyViewHolder> implements RetrofitApi.ResponseListener {

    public Context mContext;
    public Context context;
    String bId;
    String check;
    SimpleDateFormat simpleTimeFormat;
    RetrofitApi.ResponseListener responseListener;
    private ArrayList<ResponseEmpCheckInData> dataItem;
    private LayoutInflater inflater;

    public EmployeeAttendenceAdapter(Context mContext, ArrayList<ResponseEmpCheckInData> dataItem, String check) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.dataItem = dataItem;
        this.check = check;


    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {
        Utility.showToast(mContext, "" + e);
        Log.i("Error >>", e.toString());

    }

    @Override
    public void _onNext(Object obj) {

        if (obj instanceof ResponseEmpAttendence) {
            ResponseEmpAttendence responseEmpAttendence = (ResponseEmpAttendence) obj;
            if (responseEmpAttendence.getSTATUS().equalsIgnoreCase("200")) {


                RetrofitApi.getInstance().GetEmployeCheckInApiMethod(mContext, this,
                        bId);

                notifyDataSetChanged();
//                mContext.startActivity(new Intent(mContext,Employee_attendence.class));


            } else {
                Utility.showToast(mContext, responseEmpAttendence.getMESSAGE());
            }
        } else if (obj instanceof ResponseEmpCheckIn) {

            ResponseEmpCheckIn responseEmpCheckIn = (ResponseEmpCheckIn) obj;
            if (responseEmpCheckIn.getSTATUS().equalsIgnoreCase("200")) {
                dataItem.clear();
                dataItem.addAll(responseEmpCheckIn.getData());
                notifyDataSetChanged();
                Utility.showToast(mContext, responseEmpCheckIn.getMESSAGE());
            }
        }


    }

    @Override
    public void _onNext1(Object obj) {
        if (obj instanceof ResponseEmpAttendence) {

            ResponseEmpAttendence responseEmpAttendence = (ResponseEmpAttendence) obj;
            if (responseEmpAttendence.getSTATUS().equalsIgnoreCase("200")) {

                RetrofitApi.getInstance().GetEmployeCheckOutApiMethod(mContext, this,
                        bId);
                notifyDataSetChanged();

//              mContext.startActivity(new Intent(mContext,Employee_attendence.class));

            } else {
                Utility.showToast(mContext.getApplicationContext(), responseEmpAttendence.getMESSAGE());
            }
        }


    }

    private void alertDiaolog(final Context context, final String opertaion, final int position, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);


        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setCancelable(true);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       /* Utility.clearPreferenceData(NavigationMainActivity.this);
                        startActivity(new Intent(NavigationMainActivity.this, LoginActivity.class));
                        finishAffinity();*/
                        dialog.dismiss();

                        if (opertaion.equalsIgnoreCase("Check In")) {
                            insertAttendence(position, context);

                        } else if (opertaion.equalsIgnoreCase("Check Out")) {
                            updateCheckToCheckOut(position, context);

                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });

        // create alert dialog
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }


    private void updateCheckToCheckOut(int i, Context context) {
        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        RetrofitApi.getInstance().employeCheckOutApiMethod(context, this,
                "" + dataItem.get(i).getAttid(), "" + bId,
                "" + dataItem.get(i).getEmp_id(), "" + simpleTimeFormat.format(Calendar.getInstance().getTime()), "" + simpleTimeFormat.format(Calendar.getInstance().getTime()),
                "" + SimpleDateFormat.getDateInstance().format(new Date()), "");

    }

    private void insertAttendence(int i, Context context) {

        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        RetrofitApi.getInstance().employeCheckInApiMethod(context, this, "" + bId,
                "" + dataItem.get(i).getEmp_id(), "" + simpleTimeFormat.format(Calendar.getInstance().getTime()), "00:00",
                "" + SimpleDateFormat.getDateInstance().format(new Date()), "");

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResponseEmpCheckInData c = dataItem.get(position);
        holder.name.setText(c.getEmp_name());
        holder.contact.setText(c.getEmp_contact_No());
        holder.tvCheck.setText(check);
        Picasso.get()
                .load(c.getEmp_profile_image())
                .placeholder(R.drawable.dummy_profile)
                .into(holder.profile);

        System.out.println("hsjlkjjkjkj :" + c.getEmp_profile_image());
//Picasso.with(mContext)
//                .load(c.getEmp_profile_image())
//                .placeholder(R.drawable.dummy_profile)
//                .into(holder.profile);

        //holder.email.setText(c.);


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_emp_list, parent, false);

        return new MyViewHolder(v);
    }

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView contact;
        public ImageView profile;
        public TextView email, tvCheck;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_empName);
            contact = (TextView) view.findViewById(R.id.tv_empNo);
            profile = (ImageView) view.findViewById(R.id.iv_profile_image);
            bId = Utility.getPreferences(mContext, Constants.keySalonBusinessId);
            tvCheck = (TextView) view.findViewById(R.id.tv_check);
            // presnt = (RelativeLayout)view.findViewById(R.id.presentBt);


            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + dataItem.get(getLayoutPosition()).getEmp_contact_No()));
                    context.startActivity(intent);
                }
            });
            tvCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context = view.getContext();


                    if (Utility.isInternetConnected(((Employee_attendence) context))) {

                        if (tvCheck.getText().equals("Check In")) {
                            alertDiaolog(context, "Check In", getLayoutPosition(),
                                    dataItem.get(getLayoutPosition()).getEmp_name().toUpperCase() + " do you want to check in ?");
                        } else if (tvCheck.getText().equals("Check Out")) {
                            alertDiaolog(context, "Check Out", getLayoutPosition(),
                                    dataItem.get(getLayoutPosition()).getEmp_name() + " do you want to check Out ?");
                            // Utility.showToast(context,dataItem.get(getLayoutPosition()).getAttid());
                        }
                    }


                }
            });


        }


    }


}



