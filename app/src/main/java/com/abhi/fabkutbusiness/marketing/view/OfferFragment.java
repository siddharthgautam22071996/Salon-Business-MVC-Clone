package com.abhi.fabkutbusiness.marketing.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.marketing.controller.OffersListAdapter;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotion;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

/**
 * Created by siddharthgautam on 17/12/18.
 */

public class OfferFragment extends Fragment implements RetrofitApi.ResponseListener {

    View v;
    RecyclerView rvPromotion;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recycler_view, container, false);


        findViewById();
//

        iniData();

        return v;
    }

    private void iniData() {
        RetrofitApi.getInstance().upCreateOfferApi(getActivity(),this,
                "view_offer",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""+ Utility.getPreferences(getActivity(), Constants.keySalonBusinessId));
    }

    private void findViewById() {
        rvPromotion = (RecyclerView)v.findViewById(R.id.rv_employee);
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        ResponseCreatePromotion responseCreatePromotion = (ResponseCreatePromotion) obj;
        if (responseCreatePromotion.getSTATUS().equalsIgnoreCase("200")){
            OffersListAdapter offersListAdapter = new OffersListAdapter(getActivity(),responseCreatePromotion.getData(),((RunPromotion)getContext()).getViewStatus());
            rvPromotion.setAdapter(offersListAdapter);
        }
    }

    @Override
    public void _onNext1(Object obj) {

    }
}
