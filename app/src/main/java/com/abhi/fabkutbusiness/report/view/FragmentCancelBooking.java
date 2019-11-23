package com.abhi.fabkutbusiness.report.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fabkutbusiness.R;

/**
 * Created by siddharthgautam on 07/12/18.
 */

public class FragmentCancelBooking extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_booking_report, container, false);


        return rootView;
    }
}
