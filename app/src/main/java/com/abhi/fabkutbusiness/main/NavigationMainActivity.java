package com.abhi.fabkutbusiness.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.BusinessSyncData;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.view.AccountingMainActivity;
import com.abhi.fabkutbusiness.billing.view.BillNowActivity;
import com.abhi.fabkutbusiness.booking.controller.CustomerDataAdapter;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.crm.view.CrmList;
import com.abhi.fabkutbusiness.customer.view.AddCustomerActivity;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpAttendence;
import com.abhi.fabkutbusiness.employee.view.Employee_attendence;
import com.abhi.fabkutbusiness.inventory.itemMaster.view.MyStock;
import com.abhi.fabkutbusiness.main.controller.AppointmentsAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.marketing.view.Marketing_Main;
import com.abhi.fabkutbusiness.report.view.ActivityMixReport;
import com.abhi.fabkutbusiness.report.view.ProgressReportActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NavigationMainActivity extends AppCompatActivity implements View.OnClickListener {


    // The number of native ads to load.
    public static final int NUMBER_OF_ADS = 5;
    boolean doubleBackToExitPressedOnce = false;
    NavigationView navigationView;
    DrawerLayout drawer;
    View navHeader;
    ImageView app_bar_iv_appointments_dummy;
    TextView txtName, txtViewProfile;
    Toolbar toolbar;
    FloatingActionButton fab;
    TextView ivAppointments;
    RecyclerView listAppointments;
    ArrayList<ResponseModelAppointmentsData> appointmentsData = new ArrayList<>();
    TextView tvAccounting, tvInventory, tvCrm;
    AutoCompleteTextView actSearch;
    CustomerDataAdapter customerDataAdapter;
    TextView tvTotalSale, tvTotalServices, tvMarketing, tvStats, billing;
    ResponseEmpAttendence responseEmpAttendence = null;
    BusinessSyncData businessSyncData;
    ResponseModelAppointments responseModelAppointments;
    private AppointmentsAdapter adapter;
    private ArrayList<ImageView> seatImageViews;
    private LinearLayout llSeats;
    private int reschedulePos = -1;
    private ArrayList<ResponseModelCustomerData> data;
    // The AdLoader used to load ads.
    private AdLoader adLoader;
    // List of MenuItems and native ads that populate the RecyclerView.
    private List<Object> mRecyclerViewItems = new ArrayList<>();
    // List of native ads that have been successfully loaded.
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private ResponseModelAppointmentsData responseModelAppointmentsData;

    @Override
    protected void onResume() {
        super.onResume();
        Utility.hideKeyboard(NavigationMainActivity.this);
        refreshAppointmentData();

        seatImageViews = Utility.refreshSeats(this, llSeats, seatImageViews);

        tvTotalSale.setText(Utility.getTotalSale(NavigationMainActivity.this, Utility.getCurrentDate(Constants.displayDateFormat)));
        tvTotalServices.setText(Utility.getTotalService(NavigationMainActivity.this, Utility.getCurrentDate(Constants.displayDateFormat)));

        data.clear();
        data.addAll(Utility.getResponseModelCustomer(this, Constants.keySalonCustomerData).getData());
        customerDataAdapter.notifyDataSetChanged();

    }


    public void refreshAppointmentData() {

        Utility.hideKeyboard(this);

        responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

        if (responseModelAppointments != null) {

//            appointmentsData.clear();
//            appointmentsData.addAll(responseModelAppointments.getData());


            System.out.println("jhhjkhj :" + responseModelAppointments.getData().size());

            if (adapter != null) {
                adapter.notifyDataSetChanged();

            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._activity_navigation);
        MobileAds.initialize(this, "ca-app-pub-2344277930871952~5134142493");
        refreshAppointmentData();

        initToolbar();
        initNavigationDrawer();
        initFab();
        loadNavHeader();
        setUpNavigationView();

        findViewById();
        initUi();
        initData();

        //addEmployeToAttendence();


    }

    private void insertAdsInMenuItems() {
        if (mNativeAds.size() <= 0) {
            return;

        }

//        int offset = (mRecyclerViewItems.size() / mNativeAds.size()) + 1;
        int index = 8;
        UnifiedNativeAd ad = mNativeAds.get(1);
        for (UnifiedNativeAd add : mNativeAds) {
            if (mRecyclerViewItems.size() > index) {
                mRecyclerViewItems.add(index, add);

                index = index + 8;
            }
        }
        if (mRecyclerViewItems.size() <= 8) {
            mRecyclerViewItems.add(ad);
        }


        listAppointments.setHasFixedSize(true);

        // Specify a linear layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listAppointments.setLayoutManager(layoutManager);
        System.out.println(" Data check :: " + mRecyclerViewItems.toString());


        adapter = new AppointmentsAdapter(this, mRecyclerViewItems);
        listAppointments.setAdapter(adapter);


    }

    private void loadNativeAds() {

        AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110");
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems();
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems();
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }


    private void initUi() {

        seatImageViews = Utility.getCustomSeatImageViews(this, llSeats);
        seatImageViews = Utility.refreshSeats(this, llSeats, seatImageViews);

    }

    private void initData() {
        if (responseModelAppointments.getData().size() > 0) {

            for (int i = 0; i < responseModelAppointments.getData().size(); i++) {
                responseModelAppointmentsData = new ResponseModelAppointmentsData();

                responseModelAppointmentsData.setBillPayment(responseModelAppointments.getData().get(i).getBillPayment());
                responseModelAppointmentsData.setBookingDate(responseModelAppointments.getData().get(i).getBookingDate());
                responseModelAppointmentsData.setBookingId(responseModelAppointments.getData().get(i).getBookingId());
                responseModelAppointmentsData.setBookingStatus(responseModelAppointments.getData().get(i).getBookingStatus());
                responseModelAppointmentsData.setBookingTime(responseModelAppointments.getData().get(i).getBookingTime());
                responseModelAppointmentsData.setBookingType(responseModelAppointments.getData().get(i).getBookingType());
                responseModelAppointmentsData.setBusinessId(responseModelAppointments.getData().get(i).getBusinessId());
                responseModelAppointmentsData.setCancelReason(responseModelAppointments.getData().get(i).getCancelReason());
                responseModelAppointmentsData.setCustomerAllergies(responseModelAppointments.getData().get(i).getCustomerAllergies());
                responseModelAppointmentsData.setCustomerEmail(responseModelAppointments.getData().get(i).getCustomerEmail());
                responseModelAppointmentsData.setCustomerEndUser_FirstName(responseModelAppointments.getData().get(i).getCustomerEndUser_FirstName());
                responseModelAppointmentsData.setCustomerEndUser_LastName(responseModelAppointments.getData().get(i).getCustomerEndUser_LastName());
                responseModelAppointmentsData.setCustomerGender(responseModelAppointments.getData().get(i).getCustomerGender());
                responseModelAppointmentsData.setCustomerId(responseModelAppointments.getData().get(i).getCustomerId());
                responseModelAppointmentsData.setEmployee(responseModelAppointments.getData().get(i).getEmployee());
                responseModelAppointmentsData.setSeatNumber(responseModelAppointments.getData().get(i).getSeatNumber());
                responseModelAppointmentsData.setServices(responseModelAppointments.getData().get(i).getServices());
                responseModelAppointmentsData.setServiceTrackRecord(responseModelAppointments.getData().get(i).getServiceTrackRecord());
                responseModelAppointmentsData.setSlots(responseModelAppointments.getData().get(i).getSlots());
                responseModelAppointmentsData.setTotalTime(responseModelAppointments.getData().get(i).getTotalTime());
                responseModelAppointmentsData.setTotalAmount(responseModelAppointments.getData().get(i).getTotalAmount());
                responseModelAppointmentsData.setCustomerMobile(responseModelAppointments.getData().get(i).getCustomerMobile());
                responseModelAppointmentsData.setCustomerProfileImage(responseModelAppointments.getData().get(i).getCustomerProfileImage());
                responseModelAppointmentsData.setCustomerProfilePercentage(responseModelAppointments.getData().get(i).getCustomerProfilePercentage());
                responseModelAppointmentsData.setSync(responseModelAppointments.getData().get(i).isSync());


                mRecyclerViewItems.add(responseModelAppointmentsData);

                System.out.println("sadfsjadfkl :" + responseModelAppointmentsData.toString());
            }
        }

//        mRecyclerViewItems.add(appointmentsData);
        if (Utility.getPreferences(this, "admob").equalsIgnoreCase("1")) {

            loadNativeAds();

        } else {

            adapter = new AppointmentsAdapter(this, mRecyclerViewItems);
            listAppointments.setAdapter(adapter);

        }

//        adapter = new AppointmentsAdapter(NavigationMainActivity.this, appointmentsData);
//        listAppointments.setAdapter(adapter);

        data = Utility.getResponseModelCustomer(this, Constants.keySalonCustomerData).getData();
        customerDataAdapter = new CustomerDataAdapter(this, R.layout.simple_text_view, data);
        actSearch.setThreshold(1);
        actSearch.setAdapter(customerDataAdapter);
        actSearch.setText("");
        actSearch.clearFocus();
    }

    private void findViewById() {
        app_bar_iv_appointments_dummy = findViewById(R.id.app_bar_iv_appointments_dummy);
        app_bar_iv_appointments_dummy.setOnClickListener(this);


        tvAccounting = (TextView) findViewById(R.id.tv_accounting);
        tvAccounting.setOnClickListener(this);

        billing = (TextView) findViewById(R.id.fab);
        billing.setOnClickListener(this);

        /*tvCrm = (TextView) findViewById(R.id.tv_crm);
        tvCrm.setOnClickListener(this);
*/
        tvMarketing = (TextView) findViewById(R.id.tv_marketing);
        tvStats = (TextView) findViewById(R.id.tv_stats);
        tvStats.setOnClickListener(this);
        tvMarketing.setOnClickListener(this);
        tvTotalSale = (TextView) findViewById(R.id.tv_total_sale);
        tvTotalServices = (TextView) findViewById(R.id.tv_total_services);

        listAppointments = findViewById(R.id.list_appointments);

        ivAppointments = (TextView) findViewById(R.id.tv_addCustomer);
        ivAppointments.setOnClickListener(this);

        /*tvInventory = (TextView) findViewById(R.id.tv_emp);
        tvInventory.setOnClickListener(this);*/

        llSeats = (LinearLayout) findViewById(R.id.ll_seats);

        actSearch = (AutoCompleteTextView) findViewById(R.id.act_search);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  ivAppointments = (TextView) toolbar.findViewById(R.id.app_bar_iv_appointments);
        ivAppointments.setOnClickListener(this);*/
    }


    private void initNavigationDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.nav_header_tv_customer_name);
        txtViewProfile = (TextView) navHeader.findViewById(R.id.nav_header_tv_viewProfile);
    }

    private void initFab() {

    }


    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText(Utility.getPreferences(this, Constants.keySalonBusinessName));
        txtViewProfile.setText(Utility.getResponseModelLogin(this, Constants.keySalonProfileData).getData().get(0).getBusiness_email_id());
        //ArrayList<ResponseModelLoginData> responseModelLoginData =

    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_inventory:
                        //Utility.showDevelopmentToast(NavigationMainActivity.this);
                        startActivity(new Intent(getApplicationContext(), MyStock.class));
                        break;

                    case R.id.nav_report:
                        startActivity(new Intent(NavigationMainActivity.this, ActivityMixReport.class));
                        break;

                    case R.id.nav_contact_us:
                        startActivity(new Intent(NavigationMainActivity.this, ConatctUsActivity.class));
                        break;

                    /*case R.id.nav_booking:
                        startActivity(new Intent(NavigationMainActivity.this, ActivityBookingReport.class));
                        break;*/
                    case R.id.nav_customer:
                        if (Utility.isInternetConnected(NavigationMainActivity.this)) {
                            startActivity(new Intent(NavigationMainActivity.this, CrmList.class));
                        }
                        break;
                    case R.id.nav_stylist:
                        //startActivity(new Intent(NavigationMainActivity.this, ReportUnpaid.class));
                        startActivity(new Intent(getApplicationContext(), Employee_attendence.class));
                        break;


                    case R.id.nav_my_salon:
                        startActivity(new Intent(NavigationMainActivity.this, SalonDetailsActivity.class));
                        break;

                    case R.id.nav_lang:
                        chooseLang();
                        break;

                    case R.id.nav_logout:
                        if (Utility.isInternetConnected(NavigationMainActivity.this)) {
                            businessSyncData = new BusinessSyncData(NavigationMainActivity.this, true);
                            Utility.showToast(NavigationMainActivity.this, "  Sync.... ");
                            businessSyncData.syncCustomer();
                        }

                        //Utility.clearPreferenceData(NavigationMainActivity.this);
                       /* startActivity(new Intent(NavigationMainActivity.this, LoginActivity.class));
                        finishAffinity();*/
                        break;

                    case R.id.menu_sync:
                        if (Utility.isInternetConnected(NavigationMainActivity.this)) {
                            businessSyncData = new BusinessSyncData(NavigationMainActivity.this, false);
                            Utility.showToast(NavigationMainActivity.this, "  Sync.... ");
                            businessSyncData.syncCustomer();
                        }
                        break;

                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });


//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
//                super.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
//                super.onDrawerOpened(drawerView);
//            }
//        };
//
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu, getTheme());
//
//        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);
//        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START);
//                } else {
//                    drawer.openDrawer(GravityCompat.START);
//                }
//            }
//        });
//
//
//        //Setting the actionbarToggle to drawer layout
//        drawer.setDrawerListener(actionBarDrawerToggle);
//
//        //calling sync state is necessary or else your hamburger icon wont show up
//        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                ArrayList<ResponseModelAppointmentsData> billingData = Utility.getResponseModelBookings(NavigationMainActivity.this, Constants.keySalonBookingData).getData();
                if (billingData.size() > 0)
                    startActivity(new Intent(NavigationMainActivity.this, BillNowActivity.class));
                else
                    Utility.showToast(this, "No Billing available");


                break;

            case R.id.tv_addCustomer:
                startActivity(new Intent(NavigationMainActivity.this, AddCustomerActivity.class));
                break;


            case R.id.tv_accounting:
                if (Utility.isInternetConnected(this)) {
                    startActivity(new Intent(NavigationMainActivity.this, AccountingMainActivity.class));
                }
                break;


           /* case R.id.tv_emp:
                //Utility.showDevelopmentToast(NavigationMainActivity.this);
                if (Utility.isInternetConnected(this)) {
                    startActivity(new Intent(getApplicationContext(), Employee_attendence.class));
                }
                break;*/

            /*case R.id.tv_crm:
                if (Utility.isInternetConnected(this)) {
                startActivity(new Intent(NavigationMainActivity.this, CrmList.class));
                }

                break;*/

            case R.id.tv_marketing:
                //Utility.showDevelopmentToast(NavigationMainActivity.this);
                startActivity(new Intent(NavigationMainActivity.this, Marketing_Main.class));
                break;

            case R.id.tv_stats:
                startActivity(new Intent(NavigationMainActivity.this, ProgressReportActivity.class));
                break;

            case R.id.app_bar_iv_appointments_dummy:

                drawer.openDrawer(GravityCompat.START);
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 201:

                if (resultCode == RESULT_OK) {
                    if(adapter!=null){
                        adapter.notifyDataSetChanged();

                    }

                  /*  ResponseModelAppointmentsData _dataAppointment = data.getParcelableExtra("data");

                    ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

                    if (responseModelAppointments != null) {

                        responseModelAppointments.getData().set(reschedulePos, _dataAppointment);
                        Utility.addPreferencesAppointmentsData(NavigationMainActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);
                    }
*/

                }

                break;
        }

    }


    public void bookWaitingCustomer(int position) {


        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

        if (responseModelAppointments != null) {

            ResponseModelAppointmentsData _dataAppointment = responseModelAppointments.getData().get(position);
            if (Utility.isSeatAvailable(this, Integer.parseInt(_dataAppointment.getSeatNumber()))) {
                if (Utility.checkSlot(this, _dataAppointment.getSlots())) {
                    Utility.bookSeat(NavigationMainActivity.this, _dataAppointment);
                    responseModelAppointments.getData().remove(position);
                    Utility.addPreferencesAppointmentsData(NavigationMainActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);

                    refreshAppointmentData();

                    seatImageViews = Utility.refreshSeats(this, llSeats, seatImageViews);
                }
            } else {
                Utility.showToast(NavigationMainActivity.this, "No seat available");
            }
        }
    }

    public void rescheduleWaitingCustomer(int position) {

        reschedulePos = position;

        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

        if (responseModelAppointments != null) {

            startActivity(new Intent(NavigationMainActivity.this, BookNowActivity.class)
                    .putExtra("data", responseModelAppointments.getData().get(position))
                    .putExtra("isEdit", true)
                    .putExtra("pos", reschedulePos));


          /*  startActivityForResult(new Intent(NavigationMainActivity.this, EditBookingServiceActivity.class)
                    .putExtra("data", responseModelAppointments.getData().get(position))
                    .putExtra("pos", reschedulePos), 201);
*/
        }
    }


    public void chooseLang() // replcae method name with chooseProfilePic
    {
        final CharSequence[] items = {"English", "हिंदी", "ಕನ್ನಡ", "ತಮಿಳು"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.choose));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(NavigationMainActivity.this);

                if (items[item].equals("English")) {
                    changeLang("en");

                } else if (items[item].equals("हिंदी")) {
                    changeLang("hi");

                } else if (items[item].equals("ಕನ್ನಡ")) {
                    changeLang("kn");
                } else if (items[item].equals("ತಮಿಳು")) {
                    changeLang("ta");

                }
            }
        });
        builder.show();
    }

    public void changeLang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        startActivity(new Intent(NavigationMainActivity.this, SplashActivity.class));
        finish();
    }

}