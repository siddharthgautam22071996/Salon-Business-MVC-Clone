package com.abhi.fabkutbusiness.booking.view;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.booking.controller.SlotAdapter;
import com.abhi.fabkutbusiness.booking.controller.StylistAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 17/04/17.
 */

public class BookNowActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, RetrofitApi.ResponseListener {

    // The number of native ads to load.
    public static final int NUMBER_OF_ADS = 5;
    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft, profile_image;
    LinearLayout llMain, llDetails;
    TextView tvName, tvEmail, tvGender, tvMobile, tvTotalVisits, tvTotalRevenue, tvDesc, tvBookNow;
    ResponseModelAppointmentsData dataAppointment = new ResponseModelAppointmentsData();
    String seatNum;
    int businessId;
    TextView tvSelectService;
    TextView tvTime, tvTotal;
    RecyclerView rvServices, rvStylist, rvSlots;
    StylistAdapter mAdapterStylist;
    TextView tvMorning, tvAfternoon, tvEvening;
    ServicesAdapter mAdapterServices;
    SlotAdapter mAdapterSlots;
    ArrayList<ResponseModelRateInfoData> rateInfoDatas;
    ArrayList<String> slots, elapsedSlots;
    int totalTime = 0, totalCost = 0;
    ArrayList<String> bookedSlots = new ArrayList<>();
    TextView tvDate;
    ResponseModelAppointmentsData previousAppointmentData;
    int reschedulePos;
    boolean isEdit;
    int activePosition = 0;
    ScrollView scroll;
    private String currentDate;
    private ResponseModelAppointments responseModelAppointments;
    // The AdLoader used to load ads.
    private AdLoader adLoader;

    // List of MenuItems and native ads that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();

    // List of native ads that have been successfully loaded.
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_now);
        businessId = Integer.parseInt(Utility.getPreferences(BookNowActivity.this, Constants.keySalonBusinessId));
        setActionBarView();
        findViewById();
        initData();
    }


    private void initData() {

        isEdit = getIntent().getBooleanExtra("isEdit", false);


        if (isEdit) {
            previousAppointmentData = getIntent().getParcelableExtra("data");
            reschedulePos = getIntent().getIntExtra("pos", -1);
            setEditData(previousAppointmentData);
            tvDate.setText(currentDate);
            rateInfoDatas = new ArrayList<>();
            rateInfoDatas.addAll(previousAppointmentData.getServices());
            System.out.println("sdafasfasd :" + previousAppointmentData.getServices().size());

            //releaseEditData(reschedulePos);

        } else {
            ResponseModelCustomerData responseModelCustomerData = (ResponseModelCustomerData) getIntent().getSerializableExtra("data");
            setData(responseModelCustomerData);
            tvDate.setText(currentDate);
            rateInfoDatas = new ArrayList<>();

        }

        currentDate = Utility.getCurrentDate(Constants.displayDateFormat);
        tvDate.setText(currentDate);


//        for (int i = 0; i < rateInfoDatas.size(); i++) {
//
//            ResponseModelRateInfoData responseModelRateInfoData = new ResponseModelRateInfoData();
//            responseModelRateInfoData.setBusiness_id(rateInfoDatas.get(i).getBusiness_id());
//            responseModelRateInfoData.setBusiness_Name(rateInfoDatas.get(i).getBusiness_Name());
//            responseModelRateInfoData.setEmployee_id(rateInfoDatas.get(i).getEmployee_id());
//            responseModelRateInfoData.setEmployee_name(rateInfoDatas.get(i).getEmployee_name());
//            responseModelRateInfoData.setEta(rateInfoDatas.get(i).getEta());
//            responseModelRateInfoData.setId(rateInfoDatas.get(i).getId());
//            responseModelRateInfoData.setRate(rateInfoDatas.get(i).getRate());
//            responseModelRateInfoData.setSub_service_name(rateInfoDatas.get(i).getSub_service_name());
//
//            mRecyclerViewItems.add(responseModelRateInfoData);
//
//        }


        System.out.println("asdfaa:" + rateInfoDatas.toString());
//
//        if(Utility.getPreferences(this,"admob").equalsIgnoreCase("1")) {
//
//            loadNativeAds( new ArrayList<ResponseModelRateInfoData>());
//
//        }else {
//

        mAdapterServices = new ServicesAdapter(mRecyclerViewItems, new ArrayList<ResponseModelRateInfoData>(), 0, R.layout.item_selected_service_list, true, this);
        rvServices.setAdapter(mAdapterServices);


//        }


//        mAdapterServices = new ServicesAdapter(rateInfoDatas, new ArrayList<ResponseModelRateInfoData>(),0, R.layout.item_selected_service_list, true, this);
//        rvServices.setAdapter(mAdapterServices);


        slots = new ArrayList<>();
        ArrayList<String> selectedSlots = new ArrayList<>();

        elapsedSlots = Utility.getElapsedSlots(this);
        if (elapsedSlots.size() == 0) {
            Utility.showToast(this, "Sorry! Can't book services after closing time.");

        }

        mAdapterSlots = new SlotAdapter(this, R.layout.item_slots, slots, elapsedSlots, selectedSlots, bookedSlots, false, currentDate);
        rvSlots.setAdapter(mAdapterSlots);

        initSlotUi(Utility.getCurrentDate("dd/MM/yyyy"));

        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(this, Constants.keySalonEmployeeData).getData();
        mAdapterStylist = new StylistAdapter(employeeDatas, this);
        rvStylist.setAdapter(mAdapterStylist);


        setServiceTotal(totalCost, totalTime);

    }


//    private void insertAdsInMenuItems(ArrayList<ResponseModelRateInfoData> selectedData) {
//        if (mNativeAds.size() <= 0) {
//            return;
//
//        }
//
////        int offset = (mRecyclerViewItems.size() / mNativeAds.size()) + 1;
//        int index = 6;
//        UnifiedNativeAd ad=mNativeAds.get(1);
//        for (UnifiedNativeAd add:mNativeAds){
//            if(mRecyclerViewItems.size()>index){
//                mRecyclerViewItems.add(index,add);
//
//                index=index+6;
//            }
//        }
//        if(mRecyclerViewItems.size()<=8){
//            mRecyclerViewItems.add(ad);
//        }
//
//
//        rvServices.setHasFixedSize(true);
//
//        // Specify a linear layout manager.
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        rvServices.setLayoutManager(layoutManager);
//        System.out.println(" Data check :: "+mRecyclerViewItems.toString() );
//
//
//        mAdapterServices = new ServicesAdapter(mRecyclerViewItems, selectedData,0, R.layout.item_selected_service_list, true, this);
//        rvServices.setAdapter(mAdapterServices);
//
//
//
//    }
//
//    private void loadNativeAds(final ArrayList<ResponseModelRateInfoData> selectedData) {
//
//        AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110");
//        adLoader = builder.forUnifiedNativeAd(
//                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                    @Override
//                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                        // A native ad loaded successfully, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
//                        mNativeAds.add(unifiedNativeAd);
//                        if (!adLoader.isLoading()) {
//                            insertAdsInMenuItems(selectedData);
//                        }
//                    }
//                }).withAdListener(
//                new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(int errorCode) {
//                        // A native ad failed to load, check if the ad loader has finished loading
//                        // and if so, insert the ads into the list.
//                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
//                                + " load another.");
//                        if (!adLoader.isLoading()) {
//                            insertAdsInMenuItems(selectedData);
//                        }
//                    }
//                }).build();
//
//        // Load the Native ads.
//        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
//    }


    private void initSlotUi(String date) {

        elapsedSlots = Utility.getElapsedSlots(this);
//        Toast.makeText(this, ""+elapsedSlots.get(elapsedSlots.size()), Toast.LENGTH_SHORT).show();

        String openTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonOpenTime);
        ArrayList<String> morningSlots = Utility.getFormattedTimeSlots(date, openTime, Constants.timeStartAfternoon, Constants.displayDateFormatWithTime);

        if (elapsedSlots.containsAll(morningSlots)) {

            tvMorning.setTextColor(getResources().getColor(R.color.colorGrey));
            tvMorning.setEnabled(false);

            ArrayList<String> afternoonSlots = Utility.getFormattedTimeSlots(date, Constants.timeStartAfternoon, Constants.timeEndAfternoon, Constants.displayDateFormatWithTime);

            if (elapsedSlots.containsAll(afternoonSlots)) {

                tvAfternoon.setTextColor(getResources().getColor(R.color.colorGrey));
                tvAfternoon.setEnabled(false);
                String closeTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonCloseTime);
                ArrayList<String> eveningSlots = Utility.getFormattedTimeSlots(date, Constants.timeEndAfternoon, closeTime, Constants.displayDateFormatWithTime);

                if (elapsedSlots.containsAll(eveningSlots)) {
                    tvEvening.setTextColor(getResources().getColor(R.color.colorGrey));
                    tvEvening.setEnabled(false);
                } else {
                    tvEvening.setEnabled(true);
                    tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvEvening.performClick();
                }
            } else {
                tvAfternoon.setEnabled(true);
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setEnabled(true);
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.performClick();
            }

        } else {
            tvMorning.setEnabled(true);
            tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
            tvAfternoon.setEnabled(true);
            tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
            tvEvening.setEnabled(true);
            tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
            tvMorning.performClick();
        }
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Book Now");
        scroll = (ScrollView) findViewById(R.id.book_now_scroll);

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        llMain = (LinearLayout) findViewById(R.id.book_now_ll_main);
        llDetails = (LinearLayout) findViewById(R.id.book_now_ll_details);

        tvName = (TextView) findViewById(R.id.tv_name);
        profile_image = (ImageView) findViewById(R.id.profile_image);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvMobile = (TextView) findViewById(R.id.tv_mobile);
        tvTotalVisits = (TextView) findViewById(R.id.tv_total_visits);
        tvTotalRevenue = (TextView) findViewById(R.id.tv_total_revenue);
        tvDesc = (TextView) findViewById(R.id.tv_description);

        tvBookNow = (TextView) findViewById(R.id.tv_bookNow);
        tvBookNow.setOnClickListener(this);

        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setOnClickListener(this);

        tvSelectService = (TextView) findViewById(R.id.tv_select_service);
        tvSelectService.setOnClickListener(this);


        tvTime = (TextView) findViewById(R.id.tv_time);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        rvServices = (RecyclerView) findViewById(R.id.rv_services);
        rvServices.setNestedScrollingEnabled(false);

        rvStylist = (RecyclerView) findViewById(R.id.rv_stylist);

        tvMorning = (TextView) findViewById(R.id.tv_morning);
        tvMorning.setOnClickListener(this);
        tvAfternoon = (TextView) findViewById(R.id.tv_afternoon);
        tvAfternoon.setOnClickListener(this);
        tvEvening = (TextView) findViewById(R.id.tv_evening);
        tvEvening.setOnClickListener(this);
        rvSlots = (RecyclerView) findViewById(R.id.rv_slots);

    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;


            case R.id.tv_bookNow:
                if (isValidated()) {

                    dataAppointment.setTotalAmount("" + totalCost);
                    dataAppointment.setTotalTime("" + totalTime);
                    dataAppointment.setBookingDate(currentDate);
                    dataAppointment.setBookingTime(mAdapterSlots.getSelectedSlotList().get(0));
                    dataAppointment.setServices(rateInfoDatas);
                    dataAppointment.setEmployee(mAdapterStylist.getSelectedStylistDataList());
                    dataAppointment.setSlots(mAdapterSlots.getSelectedSlotList());


                    dataAppointment.setBusinessId(String.valueOf(businessId));

                    seatNum = String.valueOf(Utility.getAvailableSeat(BookNowActivity.this, dataAppointment.getSlots().get(0), dataAppointment.getSlots().get(dataAppointment.getSlots().size() - 1)));
                    dataAppointment.setSeatNumber(seatNum);

                    for (ResponseModelRateInfoData rateData : dataAppointment.getServices()) {
                        rateData.setEmployee_name(dataAppointment.getEmployee().getEmp_name());
                        rateData.setEmployee_id(dataAppointment.getEmployee().getEmp_id());
                    }

                    responseModelAppointments = Utility.getResponseModelAppointments(BookNowActivity.this, Constants.keySalonAppointmentsData);
                    int assignBook = 1;
                    if (Utility.isCurrentBooking(mAdapterSlots.getSelectedSlotList().get(0))) {
                        assignBook = 0;
                    }

                    // Toast.makeText(this, ""+dataAppointment.getBookingDate(), Toast.LENGTH_SHORT).show();
                    RetrofitApi.getInstance().bookingApiMethod(this, this, dataAppointment, assignBook, "" + dataAppointment.getBookingDate());


                }

                break;

            case R.id.tv_select_service:
                startActivityForResult(new Intent(BookNowActivity.this, SelectServiceActivity.class)
                        .putExtra("data", rateInfoDatas), 101);
                break;

            case R.id.tv_morning:
                setSlot(0);
                break;

            case R.id.tv_afternoon:
                setSlot(1);
                break;

            case R.id.tv_evening:
                setSlot(2);
                break;

            case R.id.tv_date:
                Utility.datePickerDialog(BookNowActivity.this, this);
                break;

        }
    }


    private void releaseEditData(ResponseModelAppointmentsData dataAppointment) {

        Utility.releaseEmployeeSelectedSlots(this, previousAppointmentData.getEmployee().getEmp_id(), previousAppointmentData.getSlots());
        Utility.releaseSeatReschedule(this, previousAppointmentData.getSeatNumber(), previousAppointmentData.getSlots());

        if (responseModelAppointments != null) {
            if (reschedulePos >= 0) {
                Object o = responseModelAppointments.getData().remove(dataAppointment);
                Utility.showToast(this, "" + o);

            }
        }

    }

    private boolean isValidated() {

        if (rateInfoDatas == null) {
            Utility.showToast(BookNowActivity.this, "Please select services");
            return false;
        }

        if (rateInfoDatas.size() == 0) {
            Utility.showToast(BookNowActivity.this, "Please select services");
            return false;
        }

        if (mAdapterStylist.getSelectedStylistDataList() == null) {
            Utility.showToast(BookNowActivity.this, "Please select a Stylist");
            return false;
        }


        if (mAdapterSlots.getSelectedSlotList() == null) {
            Utility.showToast(BookNowActivity.this, "Please select Time Slots");
            return false;
        }

        if (mAdapterSlots.getSelectedSlotList().size() == 0) {
            Utility.showToast(BookNowActivity.this, "Please select Time Slots");
            return false;
        }

        return true;
    }

    private void saveData(ResponseModelAppointmentsData dataAppointment) {
        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(BookNowActivity.this, Constants.keySalonAppointmentsData);

        if (Utility.isCurrentBooking(mAdapterSlots.getSelectedSlotList().get(0))) {

            dataAppointment.setBookingStatus(Constants.BOOKING_STATUS_CONFIRM);

            Utility.bookSeat(BookNowActivity.this, dataAppointment);
            Utility.updateSeatSlots(BookNowActivity.this, dataAppointment.getSeatNumber(), dataAppointment.getSlots());

        } else {
            dataAppointment.setBookingStatus(Constants.BOOKING_STATUS_WAITING);

            if (responseModelAppointments != null) {
                responseModelAppointments.getData().add(dataAppointment);
            }

        }

        Utility.addPreferencesAppointmentsData(BookNowActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);

        Utility.setEmployeeSelectedSlots(BookNowActivity.this, dataAppointment.getEmployee().getEmp_id(), dataAppointment.getSlots());

        Utility.updateSeatSlots(BookNowActivity.this, dataAppointment.getSeatNumber(), dataAppointment.getSlots());

        finish();
    }


    private void setSlot(int slot) {
        slots.clear();

        switch (slot) {

            case 0:
                // Morning Slot

                tvMorning.setTextColor(getResources().getColor(R.color.colorBlue2));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                String openTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonOpenTime);
                slots.addAll(Utility.getTimeSlots(openTime, Constants.timeStartAfternoon));
                mAdapterSlots.notifyDataSetChanged();
                activePosition = Utility.getActiveSlotPosition(currentDate, elapsedSlots, slots);
                rvSlots.scrollToPosition(activePosition);
                break;

            case 1:
                // Afternoon Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlue2));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                slots.addAll(Utility.getTimeSlots(Constants.timeStartAfternoon, Constants.timeEndAfternoon));
                mAdapterSlots.notifyDataSetChanged();
                activePosition = Utility.getActiveSlotPosition(currentDate, elapsedSlots, slots);
                rvSlots.scrollToPosition(activePosition);
                break;

            case 2:
                // Evening Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlue2));
                String closeTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonCloseTime);
                slots.addAll(Utility.getTimeSlots(Constants.timeEndAfternoon, closeTime));
                mAdapterSlots.notifyDataSetChanged();
                activePosition = Utility.getActiveSlotPosition(currentDate, elapsedSlots, slots);
                rvSlots.scrollToPosition(activePosition);
                break;

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 101:

                if (resultCode == RESULT_OK) {

                    ArrayList<ResponseModelRateInfoData> rateInfoDatas = data.getParcelableArrayListExtra("dataList");
                    if (rateInfoDatas.size() == 0)
                        setServiceTotal(0, 0);
                    this.rateInfoDatas.clear();
                    this.rateInfoDatas.addAll(rateInfoDatas);


                    mRecyclerViewItems.clear();

                    for (int i = 0; i <  this.rateInfoDatas.size(); i++) {

                        ResponseModelRateInfoData responseModelRateInfoData = new ResponseModelRateInfoData();

                        responseModelRateInfoData.setBusiness_id( this.rateInfoDatas.get(i).getBusiness_id());
                        responseModelRateInfoData.setBusiness_Name( this.rateInfoDatas.get(i).getBusiness_Name());
                        responseModelRateInfoData.setEmployee_id( this.rateInfoDatas.get(i).getEmployee_id());
                        responseModelRateInfoData.setEmployee_name( this.rateInfoDatas.get(i).getEmployee_name());
                        responseModelRateInfoData.setEta( this.rateInfoDatas.get(i).getEta());
                        responseModelRateInfoData.setId( this.rateInfoDatas.get(i).getId());
                        responseModelRateInfoData.setRate( this.rateInfoDatas.get(i).getRate());
                        responseModelRateInfoData.setSub_service_name( this.rateInfoDatas.get(i).getSub_service_name());

                        mRecyclerViewItems.add(responseModelRateInfoData);

                    }



                    mAdapterServices.notifyDataSetChanged();

                    scroll.post(new Runnable() {
                        public void run() {
                            scroll.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }

                break;


        }
    }

    public void setServiceTotal(int totalCost, int totalTime) {
        this.totalTime = totalTime;
        this.totalCost = totalCost;
        tvTime.setText("Time : " + totalTime + "min");
        tvTotal.setText("Total : " + totalCost + "Rs.");
        mAdapterSlots.setSlotSelection(getSelectedSlotCount(totalTime));
    }

    private int getSelectedSlotCount(int totalTime) {
        int count = 0;

        if (totalTime != 0)
            count = (int) Math.ceil(totalTime / Constants.slotDifference);

        return count;
    }


    public void setData(ResponseModelCustomerData responseModelCustomerData) {
        llMain.setVisibility(View.VISIBLE);
        llDetails.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(responseModelCustomerData.getCustomerProfileImage())
                .placeholder(R.drawable.dummy_profile)
                .into(profile_image);
// Picasso.with(this)
//                .load(responseModelCustomerData.getCustomerProfileImage())
//                .placeholder(R.drawable.dummy_profile)
//                .into(profile_image);

        tvName.setText(responseModelCustomerData.getEndUser_FirstName() + " " + responseModelCustomerData.getEndUser_LastName());
        tvMobile.setText(responseModelCustomerData.getContact_no());
        tvEmail.setText(responseModelCustomerData.getEmail());
        tvGender.setText(responseModelCustomerData.getGender());
        tvTotalVisits.setText("" + Utility.getTotalVisits(this, responseModelCustomerData.getCustomerId()) + "");
        tvTotalRevenue.setText("" + Utility.getTotalRevenue(this, responseModelCustomerData.getCustomerId()) + "");
        if (responseModelCustomerData.getAllergies().length() > 0) {
            tvDesc.setText("Allergic to :\n" + responseModelCustomerData.getAllergies() + "");
        }

        dataAppointment = new ResponseModelAppointmentsData();
        dataAppointment.setCustomerId(responseModelCustomerData.getENDUSERCODE());
        String bookingID = businessId + "" + System.currentTimeMillis();
        dataAppointment.setBookingId(bookingID);
        dataAppointment.setCustomerEndUser_FirstName(responseModelCustomerData.getEndUser_FirstName());
        dataAppointment.setCustomerEndUser_LastName(responseModelCustomerData.getEndUser_LastName());
        dataAppointment.setBookingType(Constants.BOOKING_TYPE_OFFLINE);
        dataAppointment.setCustomerMobile(responseModelCustomerData.getContact_no());
        dataAppointment.setCustomerProfileImage(responseModelCustomerData.getCustomerProfileImage());

        //Toast.makeText(this, ""+responseModelCustomerData.getCustomerId(), Toast.LENGTH_SHORT).show();

        totalCost = 0;
        totalTime = 0;

    }

    public void setEditData(ResponseModelAppointmentsData responseModelAppointmentsData) {
        llMain.setVisibility(View.VISIBLE);
        llDetails.setVisibility(View.GONE);

        dataAppointment = new ResponseModelAppointmentsData();
        dataAppointment.setCustomerId(responseModelAppointmentsData.getCustomerId());
        dataAppointment.setBookingId(responseModelAppointmentsData.getBookingId());
        dataAppointment.setCustomerEndUser_FirstName(responseModelAppointmentsData.getCustomerEndUser_FirstName());
        dataAppointment.setCustomerEndUser_LastName(responseModelAppointmentsData.getCustomerEndUser_LastName());
        dataAppointment.setBookingType(responseModelAppointmentsData.getBookingType());
        dataAppointment.setCustomerMobile(responseModelAppointmentsData.getCustomerMobile());
        dataAppointment.setServices(responseModelAppointmentsData.getServices());
        dataAppointment.setCustomerProfileImage(responseModelAppointmentsData.getCustomerProfileImage());

        totalCost = Integer.parseInt(responseModelAppointmentsData.getTotalAmount());
        totalTime = Integer.parseInt(responseModelAppointmentsData.getTotalTime());
        //   responseModelAppointmentsData.gets

    }

    public void setDisabledSlotList(ArrayList<String> bookedSlots) {
        this.bookedSlots.clear();
        this.bookedSlots.addAll(bookedSlots);
        mAdapterSlots.setDisabledSlotList(bookedSlots);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();

        currentDate = Utility.formatDateForDisplay(date, "MM-dd-yyyy", Constants.displayDateFormat);
        tvDate.setText(currentDate);
        mAdapterSlots.setDate(currentDate);
        String formattedDate = Utility.formatDateForDisplay(date, "MM-dd-yyyy", "dd/MM/yyyy");
        initSlotUi(formattedDate);
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        ResponseModelAppointmentsData data = (ResponseModelAppointmentsData) obj;

        if (isEdit == true) {
            // Utility.showToast(this,""+isEdit);

            //----------- siddharth change below code for reschedule
            Utility.releaseEmployeeSelectedSlots(this, previousAppointmentData.getEmployee().getEmp_id(), previousAppointmentData.getSlots());
            Utility.releaseSeatReschedule(this, previousAppointmentData.getSeatNumber(), previousAppointmentData.getSlots());
            Utility.RemoveBooking(this, data, reschedulePos);
            //releaseEditData(data);
        }
        data.setSync(true);
        saveData(data);

    }

    @Override
    public void _onNext1(Object obj) {
        ResponseModelAppointmentsData data = (ResponseModelAppointmentsData) obj;
        if (isEdit == true) {
            //  Utility.showToast(this,""+isEdit);

            //----------- siddharth change below code for reschedule
            Utility.releaseEmployeeSelectedSlots(this, previousAppointmentData.getEmployee().getEmp_id(), previousAppointmentData.getSlots());
            Utility.releaseSeatReschedule(this, previousAppointmentData.getSeatNumber(), previousAppointmentData.getSlots());
            Utility.RemoveBooking(this, data, reschedulePos);
            //releaseEditData(data);
        }
        data.setSync(false);
        saveData(data);


    }
}
