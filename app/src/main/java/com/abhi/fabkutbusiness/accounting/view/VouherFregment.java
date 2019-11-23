package com.abhi.fabkutbusiness.accounting.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.AdvancePayAdapter;
import com.abhi.fabkutbusiness.accounting.controller.VoucherAdapter;
import com.abhi.fabkutbusiness.accounting.model.ResponseGenerateVoucherNo;
import com.abhi.fabkutbusiness.accounting.model.ResponseGetVoucherDetails;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherUpdate;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIDDHARTH on 11/1/2017.
 */

public class VouherFregment extends Fragment implements RetrofitApi.ResponseListener,View.OnClickListener,AdapterView.OnItemSelectedListener{

    LinearLayout po,petty;
    TextView Ledger,pettyCash_Submit,date,voucherNo,PoSubmit,PoBal,balAmount,VoucherSubmit;
    EditText pettyCash,etPoAmount,etVoucher,etRemark;
    RadioButton payment_aganist_po,payment_aganist_voucher,staff,other,rb_pettyCash;
    int businessId;
    RelativeLayout employee ,voucher;
    Spinner emp;
    private ProgressDialog mProgressDialog;
    List<String> PoItem = new ArrayList<>();
    List<Integer> PoItem_id = new ArrayList<>();
    List<String> Emp = new ArrayList<>();
    List<String> Emp_id = new ArrayList<>();
    RetrofitApi.ResponseListener responseListener;
    String empName ,assignTo,Po_Bal,bal_Amount ;
    ListView listView;
    int flag = 0;
    ServicesAdapter mAdapterServices;
    ArrayList<ResponseModelRateInfoData> rateInfoDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);

        findViewById(view);
        businessId = Integer.parseInt(Utility.getPreferences(getActivity(), Constants.keySalonBusinessId));
        // PoItem.add("None");

        initData();
        return view;
    }

    private void initData() {
        RetrofitApi.getInstance().GenerateVoucherNoApiMethod(getActivity(),this,businessId);
        String ab = String.valueOf(businessId);
        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(getActivity(), Constants.keySalonEmployeeData).getData();
        Emp_id.clear();
        Emp.clear();
        for (int i = 0; i <employeeDatas.size(); i++) {
            Emp.add(employeeDatas.get(i).getEmp_name());
            Emp_id.add(employeeDatas.get(i).getEmp_id());
        }
        Utility.spinner(getActivity(),emp,Emp);

        //RetrofitApi.getInstance().getVoucherDetailsApiMethod(getActivity(),this,ab);
        staff.setChecked(true);
        assignTo ="1";

    }

    private void findViewById(View view) {

        staff = (RadioButton) view.findViewById(R.id.rb_staff);
        other = (RadioButton) view.findViewById(R.id.rb_thirdParty);
        other.setOnClickListener(this);
        staff.setOnClickListener(this);
        VoucherSubmit = (TextView)view.findViewById(R.id.tv_Voucher_submit);
        VoucherSubmit.setOnClickListener(this);

        voucherNo = (TextView)view.findViewById(R.id.tv_voucher_no);
        //listView = (ListView) view.findViewById(R.id.vocherList);
        etVoucher = (EditText)view.findViewById(R.id.et_pamentAgainst_voucherAmount);
        etRemark = (EditText)view.findViewById(R.id.et_remark);
        emp = (Spinner)view.findViewById(R.id.spinner_employeeName);
        emp.setOnItemSelectedListener(this);

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        if (obj instanceof ResponseGenerateVoucherNo) {
            ResponseGenerateVoucherNo modelGenerateVoucherNo = (ResponseGenerateVoucherNo) obj;
            if (modelGenerateVoucherNo.getSTATUS().equals("200")) {

                voucherNo.setText(modelGenerateVoucherNo.getData().get(0).getVoucherNo());

            } else {
                Toast.makeText(getActivity(),modelGenerateVoucherNo.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        }else if (obj instanceof ResponseVoucherInsert){

            ResponseVoucherInsert modelVoucherInsert = (ResponseVoucherInsert) obj;
            if (modelVoucherInsert.getSTATUS().equals("200")) {

                etVoucher.setText("");
                etRemark.setText("");
                Toast.makeText(getActivity(),modelVoucherInsert.getData().get(0).getStatus(),Toast.LENGTH_SHORT).show();
                /*startActivity(new Intent(getActivity() ,AccountingActivity.class));
                getActivity().finish();*/
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();


            } else {
                Toast.makeText(getActivity(),modelVoucherInsert.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }


        }else if (obj instanceof ResponseVoucherUpdate){

            ResponseVoucherUpdate modelVoucherUpdate = (ResponseVoucherUpdate) obj;
            if (modelVoucherUpdate.getSTATUS().equals("200")) {

                etVoucher.setText("");
                etRemark.setText("");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
                Utility.showSnackBar(getActivity(),"Updated");


            } else {
                Toast.makeText(getActivity(),modelVoucherUpdate.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }


        }else if (obj instanceof ResponseGetVoucherDetails){

            final ResponseGetVoucherDetails modelgetVoucherDetails = (ResponseGetVoucherDetails) obj;
            if (modelgetVoucherDetails.getSTATUS().equals("200")) {

                VoucherAdapter ab =new VoucherAdapter(getActivity(),modelgetVoucherDetails.getData());
                listView.setAdapter(ab);
                listView.setOnTouchListener(new View.OnTouchListener() {
                    // Setting on Touch Listener for handling the touch inside ScrollView
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // Disallow the touch request for parent scroll on touch of child view
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        voucherNo.setText(modelgetVoucherDetails.getData().get(position).getVoucherNo());
                        etVoucher.setText(modelgetVoucherDetails.getData().get(position).getAmount());
                        etRemark.setText(modelgetVoucherDetails.getData().get(position).getRemark());
                        VoucherSubmit.setText("Update");
                        flag = 1;
                        Utility.showToast(getActivity(),""+modelgetVoucherDetails.getData().get(position).getEmp_name());
                    }
                });

            } else {
               // Toast.makeText(getActivity(),modelgetVoucherDetails.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }


        }


    }

    @Override
    public void _onNext1(Object obj) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_Voucher_submit:
                    VoucherInsert();
                break;

            case R.id.rb_staff:
                assignTo ="1";
                emp.setEnabled(true);
                break;

            case R.id.rb_thirdParty:
                assignTo = "2";
                empName = "0";
                emp.setEnabled(false);

                break;
        }

    }



    private void VoucherInsert() {




       Integer pettycash = Integer.parseInt(Utility.getResponseModelPettyCash(getActivity(), Constants.keySalonPettyCashData).getData().
                get(0).getLedger_balance());


        if (etVoucher.getText().toString().trim().length()>0 && etRemark.getText().toString().trim().length()>0) {
            int voucherPaid = Integer.parseInt(etVoucher.getText().toString());
            if(pettycash >voucherPaid){

                if (VoucherSubmit.getText().equals("Update")){

                    RetrofitApi.getInstance().VoucherUpdateApiMethod(getActivity(), this,
                            businessId, voucherNo.getText().toString().trim(), etVoucher.getText().toString().trim(),
                            assignTo, empName, etRemark.getText().toString().trim());
                }else{

                    RetrofitApi.getInstance().VoucherInsertApiMethod(getActivity(), this,
                            businessId, voucherNo.getText().toString().trim(), Integer.parseInt(etVoucher.getText().toString().trim()),
                            assignTo, empName, etRemark.getText().toString().trim());
                }
            }else {
                //Utility.showSnackBar(getActivity(),"Please Add Petty Cash");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());


                alertDialogBuilder.setTitle("Alert");
                alertDialogBuilder.setCancelable(true);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please Add Petty Cash")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               startActivity(new Intent(getActivity(), AccountingActivity.class));
                                getActivity().finish();
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();

                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();

            }
        }else{
            Utility.showSnackBar(getActivity(),"Please enter amount or remark");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

            case R.id.spinner_employeeName:
                int empid = (int) emp.getSelectedItemId();
                empName = Emp_id.get(empid);
                // Toast.makeText(getActivity(), ab, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
