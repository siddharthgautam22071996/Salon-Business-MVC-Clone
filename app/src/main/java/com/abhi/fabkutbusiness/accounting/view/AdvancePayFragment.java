package com.abhi.fabkutbusiness.accounting.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.AdvancePayAdapter;
import com.abhi.fabkutbusiness.accounting.model.AdvancePayModel;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ng on 2/12/2017.
 */
public class AdvancePayFragment extends Fragment implements View.OnClickListener ,RetrofitApi.ResponseListener{

    TextView addAdvance;
    ListView listView;
    View view;
    public static String sql;
    ArrayList<AdvancePayModel> datamodel =new ArrayList<AdvancePayModel>();
    AdvancePayAdapter advancePayAdapter;
    ArrayList<String> employeeList= new ArrayList<String> ();
    ArrayList<String> employeeList_id = new ArrayList<String> ();
    ArrayAdapter<String> spinnerArrayAdapter;
    LinearLayoutManager mLinearLayoutManager;
     EditText  remark1,amount;
     Spinner empName;
     TextView empNo ,submit;
    int businessId;
    String empId,name,_amount,remark;
    List<String> Emp = new ArrayList<>();
    List<String> Emp_id = new ArrayList<>();

    String bId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advancepay, container, false);



        businessId = Integer.parseInt(Utility.getPreferences(getActivity(), Constants.keySalonBusinessId));
        bId = String.valueOf(businessId);
        findViewById();

        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(getActivity(), Constants.keySalonEmployeeData).getData();
        Emp_id.clear();
        Emp.clear();
        for (int i = 0; i <employeeDatas.size(); i++) {
            Emp.add(employeeDatas.get(i).getEmp_name());
            Emp_id.add(employeeDatas.get(i).getEmp_id());
        }

        Utility.spinner(getActivity(),empName,Emp);
        /*advancePayAdapter =new AdvancePayAdapter(getActivity(),datamodel);
        listView.setAdapter(advancePayAdapter);*/
        return view;
    }

    private void findViewById() {

       // addAdvance.setOnClickListener(this);

        //listView = (ListView)view.findViewById(R.id.list_advancePay);
        empName = (Spinner) view.findViewById(R.id.et_empName);
        remark1 = (EditText) view.findViewById(R.id.et_remark);
        amount = (EditText) view.findViewById(R.id.et_advanceAmount);
        submit = (TextView) view.findViewById(R.id.tv_advance_submit);
        submit.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_advance_submit:
                addAdvance();
                break;

        }
    }

    private void addAdvance() {
        String assignTo = "0";
        name = empName.getSelectedItem().toString().trim();
        int nameId = (int) empName.getSelectedItemId();
         empId = Emp_id.get(nameId);
        int pettycash = Integer.parseInt(Utility.getResponseModelPettyCash(getActivity(), Constants.keySalonPettyCashData).getData().
                get(0).getLedger_balance());

        remark = remark1.getText().toString().trim();
        //_amount = amount.getText().toString().trim();

         if (amount.getText().toString().trim().length()>0 && remark.length()>0) {
             int a = Integer.parseInt(amount.getText().toString());
             if(pettycash > a){


                 RetrofitApi.getInstance().VoucherInsertApiMethod(getActivity(), this, businessId,
                         "0", a, assignTo, empId, remark);

             }else {
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

         }else {
             Utility.showSnackBar(getActivity(), "Please enter amount or remark");
         }
      /*  advancePayAdapter =new AdvancePayAdapter(getActivity(),datamodel);
        listView.setAdapter(advancePayAdapter);

*/
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseVoucherInsert){
            ResponseVoucherInsert responseVoucherInsert = (ResponseVoucherInsert) obj;
            if (responseVoucherInsert.getSTATUS().equals("200")){
                Toast.makeText(getActivity() ,"Advance Pay "+responseVoucherInsert.getMESSAGE(),Toast.LENGTH_SHORT).show();
                amount.setText("");
                remark1.setText("");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
               // datamodel.clear();
            }
            }

    }

    @Override
    public void _onNext1(Object obj) {

    }
}
