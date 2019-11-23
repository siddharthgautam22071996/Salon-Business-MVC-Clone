package com.abhi.fabkutbusiness.accounting.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.model.ResponseGenerateVoucherNo;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelect;
import com.abhi.fabkutbusiness.accounting.model.ResponsePoInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo2;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodayInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodaysStatement;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.report.view.BookingHistoryActivity;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthgautam on 14/04/18.
 */

public class AccountingMainActivity extends AppCompatActivity implements  View.OnClickListener,RetrofitApi.ResponseListener,AdapterView.OnItemSelectedListener {
    CardView todatStatment, po, voucher, addvancePay ,report;
    TextView pettyCash_Submit, tvPettyCash,tv100,tv200,tv500, staff, other, voucherNo, VoucherSubmit;
    EditText pettyCash, etPoAmount;
    EditText eOD, etVoucher, Voucher_etRemark,  Advance_remark1,advance_amount;;
    TextView RegisterAmount, TodaysRevenue, AdvancePay, VouchersValues, PaymentAgainstPo, LedgerBal, closingBal, sumit;
    int Register_Amount, Todays_Revenue, Advance_Pay, Vouchers_Values, Payment_AgainstPo, Ledger_Bal, closing_Bal;
    int final_amount;
    TextView tvRegisterAmount;
    String empName, assignTo;
    Spinner emp,emm1, selectPo;
    TextView PoSubmit, PoBal, balAmount;
    List<String> PoItem = new ArrayList<>();
    List<Integer> PoItem_id = new ArrayList<>();
    RetrofitApi.ResponseListener responseListener;
    ImageView ivBack;
    AlertDialog deleteDialog;
    String empId, name, _amount, remark;
    ArrayList<ResponseModelEmployeeData> employeeDatas;
    List<String> Emp = new ArrayList<>();
    List<String> Emp_id = new ArrayList<>();
    TextView reponse ;
    AlertDialog deleteDialogg;
    int temp=0;

    List<PieEntry> pieEntryList =new ArrayList<>();


    int businessId, Po_Bal, bal_Amount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounting_activity);
        businessId = Integer.parseInt(Utility.getPreferences(this, Constants.keySalonBusinessId));

        findViewById();
        employeeDatas = Utility.getResponseModelEmployee(this, Constants.keySalonEmployeeData).getData();
        Emp_id.clear();
        Emp.clear();
        for (int i = 0; i < employeeDatas.size(); i++) {
            Emp.add(employeeDatas.get(i).getEmp_name());
            Emp_id.add(employeeDatas.get(i).getEmp_id());
        }
        initData();
    }

    private void initData() {
        RetrofitApi.getInstance().LedgerSelectApiMethod(AccountingMainActivity.this, this, String.valueOf(businessId));
        //RetrofitApi.getInstance().getTodaysStatementApiMethod(this, this, String.valueOf(businessId));

    }

    private void findViewById() {
        todatStatment = (CardView) findViewById(R.id.cv_closing);
        todatStatment.setOnClickListener(this);
        addvancePay = (CardView) findViewById(R.id.cv_advancePay);
        report = (CardView) findViewById(R.id.cv_report);
        report.setOnClickListener(this);
        addvancePay.setOnClickListener(this);
        po = (CardView) findViewById(R.id.cv_po);
        po.setOnClickListener(this);
        voucher = (CardView) findViewById(R.id.cv_voucher);
        voucher.setOnClickListener(this);
        tv100 = (TextView)findViewById(R.id.tv_100);
        tv100.setOnClickListener(this);
        tv500 = (TextView)findViewById(R.id.tv_500);
        tv500.setOnClickListener(this);
        tv200 = (TextView)findViewById(R.id.tv_200);
        tv200.setOnClickListener(this);

        pettyCash_Submit = (TextView) findViewById(R.id.tv_petty_cash_submit);
        pettyCash_Submit.setOnClickListener(this);
        tvPettyCash = (TextView) findViewById(R.id.tv_petty_cash);
        tvRegisterAmount = (TextView) findViewById(R.id.tv_registerAmount);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        pettyCash = (EditText) findViewById(R.id.et_petty_cash);
        pettyCash.setText("0");



        /*LedgerBal = (TextView)view.findViewById(R.id.tv_ledgerBalance);
        closingBal = (TextView)view.fixndViewById(R.id.tv_closingBal);*/


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.cv_closing:

                if (Utility.isInternetConnected(this)) {
                    todatStatment_dialog();
                    RetrofitApi.getInstance().getTodaysStatementApiMethod(this, this, String.valueOf(businessId));
                }


                break;
            case R.id.cv_advancePay:
                if (Utility.isInternetConnected(this)) {
                    addvancePay_dailog();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.cv_po:
                if (Utility.isInternetConnected(this)) {
                    po_dilog();
                    RetrofitApi.getInstance().SelectPoApiMethod(this, this, businessId);
                }


                break;
            case R.id.cv_voucher:
                voucherDialog();
                break;

            case R.id.tv_todayStatement_submit:
                if (Utility.isInternetConnected(this)) {
                    reponse.setVisibility(View.GONE);
                    String drawns = eOD.getText().toString().trim();
                    //int drawn = Integer.parseInt(drawns);
                    if (drawns.length() > 0) {
                        int draw = Integer.parseInt(drawns);
                        if (draw < final_amount) {
                            closing_Bal = final_amount - draw;
                            RetrofitApi.getInstance().TodaysStatementInsertApiMethod(this, this, String.valueOf(businessId), final_amount,
                                    Todays_Revenue, Advance_Pay, Vouchers_Values, Payment_AgainstPo, Ledger_Bal, closing_Bal, drawns);
                        } else {
                            reponse.setVisibility(View.VISIBLE);
                            reponse.setText("*Please enter lese then " + final_amount + " Amount \n or Add Petty Cash");

                        }
                    } else {
                        Utility.showToast(this, "please enter EOD");
                    }
                }
                break;
            case R.id.tv_petty_cash_submit:
                if (Utility.isInternetConnected(this)) {
                    String et_pettycash = pettyCash.getText().toString().trim();
                    if (et_pettycash.length() > 0) {
                        RetrofitApi.getInstance().LedgerInsertApiMethod(this, this, businessId, et_pettycash);
                    } else {
                        Utility.showSnackBar(this, "Please enter some Amount");
                    }
                }
                break;
            case R.id.rb_staff:
                assignTo = "1";
                emp.setEnabled(true);
                staff.setBackgroundResource(R.drawable.layout_17);
                staff.setTextColor(Color.WHITE);
                other.setBackgroundResource(R.drawable.layout_bg16);
                other.setTextColor(Color.GRAY);
                break;

            case R.id.rb_thirdParty:
                assignTo = "2";
                empName = "0";
                emp.setEnabled(false);
                staff.setBackgroundResource(R.drawable.layout_bg16);
                staff.setTextColor(Color.GRAY);
                other.setBackgroundResource(R.drawable.layout_17);
                other.setTextColor(Color.WHITE);
                break;

            case R.id.tv_po_submit:
                if (Utility.isInternetConnected(this)){
                    reponse.setVisibility(View.GONE);
                PoInsert();
                }
                break;

            case R.id.tv_Voucher_submit:
                if (Utility.isInternetConnected(this)) {
                    reponse.setVisibility(View.GONE);
                    VoucherInsert();
                    Utility.hideKeyboard(this);

                }
                break;

            case R.id.tv_advance_submit:
                if (Utility.isInternetConnected(this)) {
                    reponse.setVisibility(View.GONE);
                    addAdvance();
                }
                break;

            case R.id.tv_100:
                temp = Integer.parseInt(pettyCash.getText().toString())+100;
                pettyCash.setText(""+temp);
                break;

            case R.id.tv_200:
                 temp = Integer.parseInt(pettyCash.getText().toString())+200;
                pettyCash.setText(""+temp);
                break;

            case R.id.tv_500:
                 temp = Integer.parseInt(pettyCash.getText().toString())+500;
                pettyCash.setText(""+temp);
                break;
            case R.id.cv_report:
                if (Utility.isInternetConnected(this)) {
                    startActivity(new Intent(AccountingMainActivity.this, AccountingReport.class));
                }
                break;


        }
    }


    private void addAdvance() {
        String assignTo = "0";
        name = emm1.getSelectedItem().toString().trim();
        int nameId = (int) emm1.getSelectedItemId();
        empId = Emp_id.get(nameId);
        int tempRegisterAmount = Integer.parseInt(tvRegisterAmount.getText().toString());
        //_amount = amount.getText().toString().trim();

        if (etVoucher.getText().toString().trim().length()>0 && Voucher_etRemark.getText().toString().trim().length()>0) {
            int a = Integer.parseInt(etVoucher.getText().toString());
            if(tempRegisterAmount > a){
                remark = Voucher_etRemark.getText().toString().trim();

                RetrofitApi.getInstance().VoucherInsertApiMethod(this, this, businessId,
                        "0", a, assignTo, empId, remark);

            }else {
                reponse.setVisibility(View.VISIBLE);
                reponse.setText("*Please enter lese then "+tempRegisterAmount +" Amount \n or Add Petty Cash");

            }

        }else {
            Utility.showToast(this, "Please enter amount or remark");
        }
      /*  advancePayAdapter =new AdvancePayAdapter(getActivity(),datamodel);
        listView.setAdapter(advancePayAdapter);

*/
    }
    private void todatStatment_dialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.fragment_todays_statement2, null);
        deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        findViewById_todayStament(deleteDialogView);


        deleteDialog.show();
    }

    private void findViewById_todayStament(View view) {
        RegisterAmount = (TextView) view.findViewById(R.id.tv_registerAmount);
        TodaysRevenue = (TextView) view.findViewById(R.id.tv_todaysRevenue);
        AdvancePay = (TextView) view.findViewById(R.id.tv_advancePay);
        VouchersValues = (TextView) view.findViewById(R.id.tv_totalVaoucherValue);
        PaymentAgainstPo = (TextView) view.findViewById(R.id.tv_paymentAgainstPo);
        sumit = (TextView) view.findViewById(R.id.tv_todayStatement_submit);
        sumit.setOnClickListener(this);
        reponse = (TextView)view.findViewById(R.id.tv_response);

        eOD = (EditText) view.findViewById(R.id.et_eod_cash);


    }

    private void findViewById_Voucher(View view) {
        emp = (Spinner) view.findViewById(R.id.spinner_employeeName);
        emp.setOnItemSelectedListener(this);
        staff = (TextView) view.findViewById(R.id.rb_staff);
        other = (TextView) view.findViewById(R.id.rb_thirdParty);

        voucherNo = (TextView)view.findViewById(R.id.tv_voucher_no);
        other.setOnClickListener(this);
        staff.setOnClickListener(this);
        assignTo = "1";
        emp.setEnabled(true);
        staff.setBackgroundResource(R.drawable.layout_17);
        staff.setTextColor(Color.WHITE);
        other.setBackgroundResource(R.drawable.layout_bg16);
        other.setTextColor(Color.GRAY);
        VoucherSubmit = (TextView) view.findViewById(R.id.tv_Voucher_submit);
        VoucherSubmit.setOnClickListener(this);
        reponse = (TextView)view.findViewById(R.id.tv_response);

        TextView voucherNo = (TextView) view.findViewById(R.id.tv_voucher_no);
        //listView = (ListView) view.findViewById(R.id.vocherList);
        etVoucher = (EditText) view.findViewById(R.id.et_pamentAgainst_voucherAmount);
        Voucher_etRemark = (EditText) view.findViewById(R.id.et_remark);
        RetrofitApi.getInstance().GenerateVoucherNoApiMethod(this,this,businessId);
        Utility.spinner(this, emp, Emp);

    }

    private void findViewById_AddVancePay(View view) {

        TextView empNo, submit;

        final Spinner empName;
        final EditText remark1, amount;
        emm1 = (Spinner) view.findViewById(R.id.et_empName);
        Voucher_etRemark = (EditText) view.findViewById(R.id.et_remark);
        etVoucher = (EditText) view.findViewById(R.id.et_advanceAmount);
        submit = (TextView) view.findViewById(R.id.tv_advance_submit);
        submit.setOnClickListener(this);
        reponse = (TextView)view.findViewById(R.id.tv_response);

        Utility.spinner(this, emm1, Emp);

    }

    private void addvancePay_dailog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.fragment_advancepay, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        findViewById_AddVancePay(deleteDialogView);


        deleteDialog.show();
    }

    private void po_dilog() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.fragment_po, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        findViewById_po(deleteDialogView);


        deleteDialog.show();
    }

    private void findViewById_po(View view) {

        PoSubmit = (TextView) view.findViewById(R.id.tv_po_submit);
        PoSubmit.setOnClickListener(this);
        //pettyCash = (TextView)view.findViewById(R.id.tv_petty_cash);

        balAmount = (TextView) view.findViewById(R.id.tv_amount_balance);
        PoBal = (TextView) view.findViewById(R.id.tv_po_balance);
        reponse = (TextView)view.findViewById(R.id.tv_response);

        etPoAmount = (EditText) view.findViewById(R.id.et_poAmount);
        selectPo = (Spinner) view.findViewById(R.id.expense_select_po);
        selectPo.setOnItemSelectedListener(this);
    }

    private void voucherDialog() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.fragment_voucher, null);
        deleteDialogg = new AlertDialog.Builder(this).create();
        deleteDialogg.setView(deleteDialogView);
        findViewById_Voucher(deleteDialogView);
        deleteDialogg.show();

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseLedgerInsert) {
            ResponseLedgerInsert modelLedgerInsert = (ResponseLedgerInsert) obj;
            if (modelLedgerInsert.getSTATUS().equals("200")) {

                Toast.makeText(this, "Petty Cash Update", Toast.LENGTH_SHORT).show();
                pettyCash.setText("");
                //refreshLedgerData();
                startActivity(new Intent(AccountingMainActivity.this, AccountingMainActivity.class));
                finish();
                /**/

            } else {
                // Toast.makeText(getActivity(),modelLedgerInsert.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        } else if (obj instanceof ResponseLedgerSelect) {
            ResponseLedgerSelect modelLedgerSelect = (ResponseLedgerSelect) obj;
            if (modelLedgerSelect.getSTATUS().equals("200")) {

                Utility.addPreferencesPettyCashData(AccountingMainActivity.this, Constants.keySalonPettyCashData, modelLedgerSelect);
                tvPettyCash.setText(modelLedgerSelect.getData().get(0).getLedger_balance());
                tvRegisterAmount.setText(modelLedgerSelect.getData().get(0).getRegisterAmount());


            } else {
                // Toast.makeText(getActivity(),modelLedgerSelect.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        } else if (obj instanceof ResponseTodayInsert) {
            ResponseTodayInsert responseTodayInsert = (ResponseTodayInsert) obj;
            if (responseTodayInsert.getSTATUS().equalsIgnoreCase("200")) {
                eOD.setText("");

                Utility.showToast(this, responseTodayInsert.getMESSAGE());
                 deleteDialog.dismiss();

            }

        } else if (obj instanceof ResponseTodaysStatement) {
            ResponseTodaysStatement modelgetTodyasStatement = (ResponseTodaysStatement) obj;
            if (modelgetTodyasStatement.getSTATUS().equals("200")) {
               /*pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getRegisterAmount(),"Register Amount"));
               pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getBooking_Amount(),"Todays Revenue"));
               pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getLedger_balance(),"Ledger Bal"));
               pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getAdvancePay()," Advance Pay"));
               pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getPOAmount(),"PO"));
               pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getVoucherAmount(),"Voucher"));
               pieEntryList.add(new PieEntry(modelgetTodyasStatement.getData().get(0).getCloseAmount(),"Closing"));*/

                Register_Amount = modelgetTodyasStatement.getData().get(0).getRegisterAmount();
                Todays_Revenue = modelgetTodyasStatement.getData().get(0).getBooking_Amount();
                Advance_Pay =modelgetTodyasStatement.getData().get(0).getAdvancePay();
                Ledger_Bal = modelgetTodyasStatement.getData().get(0).getLedger_balance();
                Payment_AgainstPo = modelgetTodyasStatement.getData().get(0).getPOAmount();
                Vouchers_Values = modelgetTodyasStatement.getData().get(0).getVoucherAmount();
                closing_Bal = modelgetTodyasStatement.getData().get(0).getCloseAmount();


                //Toast.makeText(this, ""+Register_Amount, Toast.LENGTH_SHORT).show();
                RegisterAmount.setText("" + Register_Amount);
                TodaysRevenue.setText("" + Todays_Revenue);
                AdvancePay.setText("" + Advance_Pay);
                //LedgerBal.setText("Rs "+Ledger_Bal);
                PaymentAgainstPo.setText("" + Payment_AgainstPo);
                VouchersValues.setText("" + Vouchers_Values);
                //closingBal.setText("Rs "+closing_Bal);

                final_amount = (Register_Amount + Todays_Revenue - Payment_AgainstPo - Vouchers_Values - Advance_Pay);

               //setupPiChart();

            } else {
                Utility.showToast(this, "Today Satement " + modelgetTodyasStatement.getMESSAGE());

            }

        } else if (obj instanceof ResponsePoInsert) {

            ResponsePoInsert modelPoInsert = (ResponsePoInsert) obj;
            if (modelPoInsert.getSTATUS().equals("200")) {

                Po_Bal = Integer.parseInt(modelPoInsert.getData().get(0).getTotalAmount());
                bal_Amount = Integer.parseInt(modelPoInsert.getData().get(0).getAmountBalance().toString());
                PoBal.setText("Rs " + Po_Bal);
                balAmount.setText("Rs " + bal_Amount);
                etPoAmount.setText("");
                Utility.showToast(this, modelPoInsert.getMESSAGE());

            } else {
                Utility.showToast(this, modelPoInsert.getMESSAGE());
                //callRollback(modelLedgerSelect.getMESSAGE());
            }


        } else if (obj instanceof ResponseSelectPo) {
            ResponseSelectPo modelSelectPo = (ResponseSelectPo) obj;
            if (modelSelectPo.getSTATUS().equals("200")) {
                PoItem.clear();
                PoItem_id.clear();

                for (int i = 0; i < modelSelectPo.getData().size(); i++) {
                    PoItem.add(modelSelectPo.getData().get(i).getPONO());
                    PoItem_id.add(modelSelectPo.getData().get(i).getOrder_id());
                }

                Utility.spinner(this, selectPo, PoItem);


            } else {
                Utility.showToast(this, modelSelectPo.getMESSAGE());
                // Toast.makeText(getActivity(),modelSelectPo.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        } else if (obj instanceof ResponseGenerateVoucherNo) {
            ResponseGenerateVoucherNo modelGenerateVoucherNo = (ResponseGenerateVoucherNo) obj;
            if (modelGenerateVoucherNo.getSTATUS().equals("200")) {

                voucherNo.setText(modelGenerateVoucherNo.getData().get(0).getVoucherNo());

            } else {
                Utility.showToast(this,modelGenerateVoucherNo.getMESSAGE());
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        }else if (obj instanceof ResponseVoucherInsert){

            ResponseVoucherInsert modelVoucherInsert = (ResponseVoucherInsert) obj;
            if (modelVoucherInsert.getSTATUS().equals("200")) {

                etVoucher.setText("");
                Voucher_etRemark.setText("");
                Utility.showToast(this,modelVoucherInsert.getMESSAGE());

                RetrofitApi.getInstance().GenerateVoucherNoApiMethod(this,this,businessId);
                deleteDialogg.dismiss();
                /*startActivity(new Intent(getActivity() ,AccountingActivity.class));
                getActivity().finish();*/



            } else {
                Utility.showToast(this,modelVoucherInsert.getMESSAGE());
                //callRollback(modelLedgerSelect.getMESSAGE());
            }


        }

    }

    @Override
    public void _onNext1(Object obj) {
        if (obj instanceof ResponseSelectPo2) {
            ResponseSelectPo2 modelSelectPo2 = (ResponseSelectPo2) obj;
            if (modelSelectPo2.getSTATUS().equals("200")) {
                Po_Bal = Integer.parseInt(modelSelectPo2.getData().get(0).getPOAmount());
                bal_Amount = Integer.parseInt(modelSelectPo2.getData().get(0).getBalanceAmt());
                PoBal.setText("Rs " + Po_Bal);
                balAmount.setText("Rs " + bal_Amount);


            } else {
                Toast.makeText(this, modelSelectPo2.getMESSAGE(), Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {

            case R.id.spinner_employeeName:
                int empid = (int) emp.getSelectedItemId();
                empName = Emp_id.get(empid);
                break;

            case R.id.expense_select_po:
                int po = (int) selectPo.getSelectedItemId();
                int Order = PoItem_id.get(po);
                //Toast.makeText(getActivity(), ""+Order, Toast.LENGTH_SHORT).show();
                RetrofitApi.getInstance().selectPoApiMethod(this, this, businessId, Order);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void PoInsert() {

        int PoNO = (int) selectPo.getSelectedItemId();
        Integer PoNoId = PoItem_id.get(PoNO);


        String mode = "insert";
        String status = "0";
        String AutoID = "0";

        // balAmount
        int tempRegisterAmount = Integer.parseInt(tvRegisterAmount.getText().toString());
        if (etPoAmount.getText().toString().trim().length()> 0) {
            int Paidamount = Integer.parseInt(etPoAmount.getText().toString().trim());
            if (Paidamount <=bal_Amount) {
                if (Paidamount < tempRegisterAmount) {
                    RetrofitApi.getInstance().PoInsertApiMethod(this, this,
                            businessId, etPoAmount.getText().toString().trim(), PoNoId, mode, status, AutoID, Po_Bal, bal_Amount);
                }else{
                    reponse.setVisibility(View.VISIBLE);
                    reponse.setText("*Please enter lese then "+tempRegisterAmount +" Amount \n or Add Petty Cash");

                }
            } else {
                Utility.showToast(this, "Please Enter Amount less than po balance");
            }
        } else {
            Utility.showToast(this, "Please Enter Some Amount ");
        }
    }

    private void VoucherInsert() {


        if (etVoucher.getText().toString().trim().length() > 0 && Voucher_etRemark.getText().toString().trim().length() > 0 ) {
            int voucherPaid = Integer.parseInt(etVoucher.getText().toString());
            int tempRegisterAmount = Integer.parseInt(tvRegisterAmount.getText().toString());
            if ( tempRegisterAmount > voucherPaid) {

                if (VoucherSubmit.getText().equals("Update")) {

                    RetrofitApi.getInstance().VoucherUpdateApiMethod(this, this,
                            businessId, voucherNo.getText().toString().trim(), etVoucher.getText().toString().trim(),
                            assignTo, empName, Voucher_etRemark.getText().toString().trim());

                } else {


                    new android.support.v7.app.AlertDialog.Builder(this).setMessage("are you sure ?")
                            .setTitle("Alert!!").setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            RetrofitApi.getInstance().VoucherInsertApiMethod(AccountingMainActivity.this, AccountingMainActivity.this,
                                    businessId, voucherNo.getText().toString().trim(), Integer.parseInt(etVoucher.getText().toString().trim()),
                                    assignTo, empName, Voucher_etRemark.getText().toString().trim());


                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                            deleteDialogg.dismiss();

                        }
                    }).show();

                }
            } else {

                reponse.setVisibility(View.VISIBLE);
                reponse.setText("*Please enter lese then "+tempRegisterAmount +" Amount \n or Add Petty Cash");

            }


        } else {
            Utility.showToast(this, "Please enter amount or remark");

        }
    }

/*


    private void setupPiChart() {
        PieDataSet pieDataSet =new PieDataSet(pieEntryList,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        PieData pieData =new PieData(pieDataSet);


        final PieChart pieChart = (PieChart)findViewById(R.id.chart);
      */
/*  Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);*//*

        pieChart.setDrawEntryLabels(false);


        pieChart.setCenterTextColor(R.color.colorBlue4);
        pieChart.setData(pieData);





        pieChart.animateY(1500);
        pieChart.invalidate();

    }
*/




}
