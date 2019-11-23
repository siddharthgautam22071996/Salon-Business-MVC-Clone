package com.abhi.fabkutbusiness.accounting.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.TodaysStatementAdapter;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelect;
import com.abhi.fabkutbusiness.accounting.model.ResponsePoInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodayInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodaysStatement;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodaysStatementData;
import com.abhi.fabkutbusiness.billing.controller.BillingAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.billing.view.BillNowActivity;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by abhi on 16/06/17.
 */

public class TodaysStatementFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener , RetrofitApi.ResponseListener {

    ListView lvStatements;
    TodaysStatementAdapter adapter;
    ArrayList<ResponseModelAppointmentsData> billingData;
    TextView tvDate;
    String businessId;
    TextView tvTotalSale, tvTotalServices;
    EditText eOD;
    TextView RegisterAmount,TodaysRevenue,AdvancePay,VouchersValues,PaymentAgainstPo,LedgerBal,closingBal ,sumit;
    int Register_Amount,Todays_Revenue,Advance_Pay,Vouchers_Values,Payment_AgainstPo,Ledger_Bal,closing_Bal ;
    int final_amount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todays_statement2, container, false);

        findViewById(view);
        businessId = (Utility.getPreferences(getActivity(), Constants.keySalonBusinessId)).toString();
        // PoItem.add("None");

        RetrofitApi.getInstance().getTodaysStatementApiMethod(getActivity(),this,businessId);


        initData();
        return view;
    }

    private void initUi() {
       /* billingData = new ArrayList<>();

        adapter = new TodaysStatementAdapter(getActivity(), billingData);
        lvStatements.setAdapter(adapter);*/
    }

    private void findViewById(View view) {
        /*lvStatements = (ListView) view.findViewById(R.id.list_statements);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvDate.setOnClickListener(this);
        tvTotalSale = (TextView) view.findViewById(R.id.tv_total_sale);
        tvTotalServices = (TextView) view.findViewById(R.id.tv_total_services);*/

        RegisterAmount = (TextView)view.findViewById(R.id.tv_registerAmount);
        TodaysRevenue = (TextView)view.findViewById(R.id.tv_todaysRevenue);
        AdvancePay = (TextView)view.findViewById(R.id.tv_advancePay);
        VouchersValues = (TextView)view.findViewById(R.id.tv_totalVaoucherValue);
        PaymentAgainstPo = (TextView)view.findViewById(R.id.tv_paymentAgainstPo);
        /*LedgerBal = (TextView)view.findViewById(R.id.tv_ledgerBalance);
        closingBal = (TextView)view.findViewById(R.id.tv_closingBal);*/
        sumit = (TextView)view.findViewById(R.id.tv_todayStatement_submit);
        sumit.setOnClickListener(this);
        eOD = (EditText)view.findViewById(R.id.et_eod_cash);
    }

    private void initData() {

       /* tvDate.setText(currDate);
        tvTotalSale.setText(Utility.getTotalSale(getActivity(), currDate));
        tvTotalServices.setText(Utility.getTotalService(getActivity(), currDate));

        billingData.clear();

        ResponseModelBillingList _data = Utility.getResponseModelBillingListData(getActivity(), Constants.keySalonBillingListData);

        if (_data.getDATA().containsKey(currDate)) {
            billingData.addAll(_data.getDATA().get(currDate));
        }

        adapter.notifyDataSetChanged();
*/


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_date:
                Utility.datePickerDialogBackDate(getActivity(), this);
                break;

            case R.id.tv_todayStatement_submit:

                String drawns = eOD.getText().toString().trim();
                //int drawn = Integer.parseInt(drawns);
                if (drawns.length()>0) {
                    int draw = Integer.parseInt(drawns);
                    closing_Bal = final_amount-draw;
                    RetrofitApi.getInstance().TodaysStatementInsertApiMethod(getActivity(), this, businessId, final_amount,
                            Todays_Revenue, Advance_Pay, Vouchers_Values, Payment_AgainstPo, Ledger_Bal, closing_Bal, drawns);
                }else{
                    Utility.showSnackBar(getActivity(),"please enter EOD");
                }
                break;

        }
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();

        //initData(Utility.formatDateForDisplay(date, "MM-dd-yyyy", Constants.displayDateFormat));
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
         if (obj instanceof ResponseTodayInsert){
            ResponseTodayInsert responseTodayInsert = (ResponseTodayInsert) obj;
            if (responseTodayInsert.getSTATUS().equalsIgnoreCase("200")){
                eOD.setText("");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
                Utility.showSnackBar(getActivity(),responseTodayInsert.getMESSAGE());

            }

        }else if (obj instanceof ResponseTodaysStatement){
             ResponseTodaysStatement modelgetTodyasStatement = (ResponseTodaysStatement) obj;
             if (modelgetTodyasStatement.getSTATUS().equals("200")) {

                 Register_Amount = modelgetTodyasStatement.getData().get(0).getRegisterAmount();
                 Todays_Revenue = modelgetTodyasStatement.getData().get(0).getBooking_Amount();
                 Advance_Pay = modelgetTodyasStatement.getData().get(0).getAdvancePay();
                 Ledger_Bal = modelgetTodyasStatement.getData().get(0).getLedger_balance();
                 Payment_AgainstPo = modelgetTodyasStatement.getData().get(0).getPOAmount();
                 Vouchers_Values = modelgetTodyasStatement.getData().get(0).getVoucherAmount();
                 closing_Bal = modelgetTodyasStatement.getData().get(0).getCloseAmount();

                 RegisterAmount.setText("Rs "+Register_Amount);
                 TodaysRevenue.setText("Rs "+Todays_Revenue);
                 AdvancePay.setText("Rs "+Advance_Pay);
                 //LedgerBal.setText("Rs "+Ledger_Bal);
                 PaymentAgainstPo.setText("Rs "+Payment_AgainstPo);
                 VouchersValues.setText("Rs "+Vouchers_Values);
                 //closingBal.setText("Rs "+closing_Bal);

                 final_amount = (Register_Amount + Todays_Revenue - Payment_AgainstPo - Vouchers_Values - Advance_Pay);

             } else {
                 Utility.showToast(getActivity(),"Today Satement "+modelgetTodyasStatement.getMESSAGE());

             }

         }

    }

    @Override
    public void _onNext1(Object obj) {

    }
}