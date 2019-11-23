package com.abhi.fabkutbusiness.accounting.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;

import com.abhi.fabkutbusiness.accounting.model.ModelPettyCash;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsertData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by abhi on 16/06/17.
 */

public class ExpenseTrackerFragment extends Fragment implements View.OnClickListener,RetrofitApi.ResponseListener{
    TextView Ledger,pettyCash_Submit,date;
    EditText pettyCash,etRemark;
    int businessId;
    ListView listView;
    ArrayList<ResponseLedgerInsertData> responseLedgerInsertDatas = new ArrayList<>();
    ArrayList<ModelPettyCash> data =   new ArrayList<ModelPettyCash>();
    RetrofitApi.ResponseListener responseListener;
    RecyclerView rvStylist;
    SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_tracker, container, false);

        findViewById(view);
        businessId = Integer.parseInt(Utility.getPreferences(getActivity(), Constants.keySalonBusinessId));
       // PoItem.add("None");
        String B = String.valueOf(businessId);

      /*  ArrayList<ResponseLedgerSelectData> PettyCashData = Utility.getResponseModelPettyCash(getActivity(), Constants.keySalonPettyCashData).getData();
        Ledger.setText("Rs "+PettyCashData.get(0).getLedger_balance());*/
       // date.setText(PettyCashData.get(0).getLedger_date());

        return view;
    }

    private void findViewById(View view) {
        //Ledger = (TextView)view.findViewById(R.id.tv_ledger_balance);


        //date = (TextView)view.findViewById(R.id.tv_petty_cash_date);
        pettyCash_Submit = (TextView)view.findViewById(R.id.tv_petty_cash_submit);
        pettyCash_Submit.setOnClickListener(this);
        pettyCash = (EditText)view.findViewById(R.id.et_petty_cash);
        rvStylist = (RecyclerView) view.findViewById(R.id.rv_stylist);
        listView = (ListView)view.findViewById(R.id.ledgerList);
        etRemark = (EditText)view.findViewById(R.id.et_remark);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_petty_cash_submit:
               // Toast.makeText(getActivity(),"nknk",Toast.LENGTH_SHORT).show();
                //int ab = Integer.parseInt(Ledger.getText().toString().trim() + pettyCash.getText().toString().trim());
                String et_pettycash = pettyCash.getText().toString().trim();
                if (et_pettycash.length()>0){
                    RetrofitApi.getInstance().LedgerInsertApiMethod(getActivity(), this,businessId,et_pettycash);
                }else{
                    Utility.showSnackBar(getActivity(), "Please enter some Amount");
                }



                break;

        }
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

                Toast.makeText(getActivity(), "Petty Cash Update", Toast.LENGTH_SHORT).show();
                pettyCash.setText("");
                //refreshLedgerData();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
                getActivity().startActivity(new Intent(getActivity(),AccountingActivity.class));
                getActivity().finish();
                /**/

            } else {
                // Toast.makeText(getActivity(),modelLedgerInsert.getMESSAGE(),Toast.LENGTH_SHORT).show();
                //callRollback(modelLedgerSelect.getMESSAGE());
            }
        }
    }

    @Override
    public void _onNext1(Object obj) {


    }






}