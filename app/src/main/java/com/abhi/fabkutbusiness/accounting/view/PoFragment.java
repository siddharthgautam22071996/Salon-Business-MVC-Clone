package com.abhi.fabkutbusiness.accounting.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsertData;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelectData;
import com.abhi.fabkutbusiness.accounting.model.ResponsePoInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo2;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIDDHARTH on 11/1/2017.
 */

public class PoFragment extends Fragment implements View.OnClickListener, RetrofitApi.ResponseListener,AdapterView.OnItemSelectedListener {
    TextView PoSubmit,PoBal,balAmount,pettyCash;
    EditText etPoAmount;
    int businessId;

    Spinner selectPo;
    private ProgressDialog mProgressDialog;
    List<String> PoItem = new ArrayList<>();
    List<Integer> PoItem_id = new ArrayList<>();
    RetrofitApi.ResponseListener responseListener;
    int Po_Bal,bal_Amount ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_po, container, false);

        findViewById(view);
        businessId = Integer.parseInt(Utility.getPreferences(getActivity(), Constants.keySalonBusinessId));
        // PoItem.add("None");
        //ArrayList<ResponseLedgerSelectData> PettyCashData = Utility.getResponseModelPettyCash(getActivity(), Constants.keySalonPettyCashData).getData();



//        pettyCash.setText(PettyCashData.get(0).getLedger_balance());
        //ArrayList<ResponseLedgerSelectData> petty_cash = Utility.getResponseModelPettyCash(getActivity(), Constants.keySalonPettyCashData).getData();
        //pettyCash.setText(petty_cash.get(0).getLedger_balance());

        RetrofitApi.getInstance().SelectPoApiMethod(getActivity(), this,businessId);
        // Toast.makeText(getActivity(),""+businessId,Toast.LENGTH_SHORT).show();

        return view;
    }

    private void findViewById(View view) {


        PoSubmit = (TextView)view.findViewById(R.id.tv_po_submit);
        PoSubmit.setOnClickListener(this);
        //pettyCash = (TextView)view.findViewById(R.id.tv_petty_cash);

        balAmount = (TextView)view.findViewById(R.id.tv_amount_balance);
        PoBal = (TextView)view.findViewById(R.id.tv_po_balance);

        etPoAmount = (EditText)view.findViewById(R.id.et_poAmount);
        selectPo = (Spinner)view.findViewById(R.id.expense_select_po);
        selectPo.setOnItemSelectedListener(this);
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponsePoInsert) {

            ResponsePoInsert modelPoInsert = (ResponsePoInsert) obj;
            if (modelPoInsert.getSTATUS().equals("200")) {

                etPoAmount.setText("");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
                Toast.makeText(getActivity(), modelPoInsert.getMESSAGE(), Toast.LENGTH_SHORT).show();
                /*startActivity(new Intent(getActivity(),AccountingActivity.class));
                getActivity().finish();
                */


            } else {
                Toast.makeText(getActivity(), modelPoInsert.getMESSAGE(), Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }


        }else if (obj instanceof ResponseSelectPo) {
            ResponseSelectPo modelSelectPo = (ResponseSelectPo) obj;
            if (modelSelectPo.getSTATUS().equals("200")) {
                PoItem.clear();
                PoItem_id.clear();
                for (int i = 0; i <modelSelectPo.getData().size(); i++) {
                    PoItem.add(modelSelectPo.getData().get(i).getPONO());
                    PoItem_id.add(modelSelectPo.getData().get(i).getOrder_id());
                }
                Utility.spinner(getActivity(),selectPo,PoItem);
                



            } else {
                // Toast.makeText(getActivity(),modelSelectPo.getMESSAGE(),Toast.LENGTH_SHORT).show();
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
                PoBal.setText("Rs "+Po_Bal);
                balAmount.setText("Rs "+bal_Amount);


            } else {
                Toast.makeText(getActivity(),modelSelectPo2.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_po_submit:

                PoInsert();
                break;
        }
    }

    private void PoInsert() {

        int PoNO = (int) selectPo.getSelectedItemId();
        Integer PoNoId = PoItem_id.get(PoNO);


        String mode = "insert";
        String status = "0";
        String AutoID = "0";

        // balAmount
        if (etPoAmount.getText().toString().trim().length()>0) {
            int Paidamount = Integer.parseInt(etPoAmount.getText().toString().trim());
            if(Paidamount<=bal_Amount) {
                RetrofitApi.getInstance().PoInsertApiMethod(getActivity(), this,
                        businessId, etPoAmount.getText().toString().trim(), PoNoId, mode, status, AutoID, Po_Bal, bal_Amount);
            }else {
                Utility.showSnackBar(getActivity(),"Please Enter Amount less than po balance");
            }
        }else {
            Utility.showSnackBar(getActivity(), "Please Enter Some Amount ");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.expense_select_po:
                int po = (int) selectPo.getSelectedItemId();
                int Order = PoItem_id.get(po);
                //Toast.makeText(getActivity(), ""+Order, Toast.LENGTH_SHORT).show();
                RetrofitApi.getInstance().selectPoApiMethod(getActivity(), this, businessId, Order);
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
