package com.abhi.fabkutbusiness.inventory.vendor.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseCity;
import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseLocation;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIDDHARTH on 8/25/2017.
 */

public class VendorCreation extends Fragment implements RetrofitApi.ResponseListener {

    View v;
    Spinner deliveryMode,paymentMode,state,city,location;
    RetrofitApi.ResponseListener responseListener;
    List<String> brand = new ArrayList<>();
    List<String> cityName = new ArrayList<>();
    List<String> city_id = new ArrayList<>();
    List<String> locationName = new ArrayList<>();
    List<String> location_id = new ArrayList<>();

    ArrayAdapter<String> spinnerArrayAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v= inflater.inflate( R.layout.fragment_vendor_creation, container, false);

       findViewById();

        return v;
    }

    private void findViewById() {
        deliveryMode = (Spinner)v.findViewById(R.id.spinner_deliveryMode);
       // deliveryMode.setOnItemSelectedListener(this);
        paymentMode = (Spinner)v.findViewById(R.id.spinner_paymentMode);
       // paymentMode.setOnItemSelectedListener(this);
        state = (Spinner)v.findViewById(R.id.spinner_state);
        //state.setOnItemSelectedListener(this);
        city = (Spinner)v.findViewById(R.id.spinner_city);
        //city.setOnItemSelectedListener(this);
        location = (Spinner)v.findViewById(R.id.spinner_location);
        //location.setOnItemSelectedListener(this);
        RetrofitApi.getInstance().cityApiMethod(getActivity(),this);

    }



   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.spinner_deliveryMode:
                break;
            case R.id.spinner_paymentMode:
                break;
            case R.id.spinner_state:
                break;
            case R.id.spinner_location:
                break;
            case R.id.spinner_city:
              //  int getCityId = (int) city.getSelectedItemId();
              //  int cityId = Integer.parseInt(city_id.get(getCityId));
              // RetrofitApi.getInstance().locationApiMethod(getActivity(), this,cityId);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
*/
    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseCity){

            ResponseCity responseCity =(ResponseCity) obj;
            for (int i=0;i<responseCity.getData().size();i++){
                cityName.add(responseCity.getData().get(i).getCity_Name());
                city_id.add(responseCity.getData().get(i).getCity_Id());
            }
            Utility.spinner(getActivity(),city,cityName);
        }else if (obj instanceof ResponseLocation){
            ResponseLocation responseLocation=(ResponseLocation) obj;
            for (int i=0;i<responseLocation.getData().size();i++){
                locationName.add(responseLocation.getData().get(i).getCity_Name());
                location_id.add(responseLocation.getData().get(i).getCity_Id());
            }
            Utility.spinner(getActivity(),location,locationName);
        }

    }

    @Override
    public void _onNext1(Object obj) {

    }
}
