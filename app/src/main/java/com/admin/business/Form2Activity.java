package com.admin.business;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.admin.Model.Form2Model;
import com.admin.R;
import com.admin.Retrofit.RetrofitApi;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class Form2Activity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    private static final int AUTOCOMPLETE_REQUEST_CODE = 201;
    private static final String TAG = "locationFind";
    private EditText mEditTextNoOfChairs;
    private EditText mEditTextNoOfEmployee;
    private EditText mEditTextOpenHours;
    private EditText mEditTextClosingHours;
    private Button mSubmitForm2btn;
    private String noOfEmployee, noOfChairs, openingHours, closingHours, business_Id, ltd, lgt;
    private TextView mAddressInput;
    private PlacesClient placesClient;
    private boolean selectedAddress = false;
    private TextInputLayout mOpenHours;
    private TextInputLayout mCloseHours;
    private ImageView mSelectOpenHours;
    private ImageView mSelectCloseHours;
    String timePeriod = "AM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        initView();
        Intent intent = getIntent();
        try {
            business_Id = intent.getStringExtra("business_Id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
            placesClient = Places.createClient(this);
        }
    }

    private void initView() {
        mEditTextNoOfChairs = (EditText) findViewById(R.id.editTextNoOfChairs);
        mEditTextNoOfEmployee = (EditText) findViewById(R.id.editTextNoOfEmployee);
        mEditTextOpenHours = (EditText) findViewById(R.id.editTextOpenHours);
        mEditTextClosingHours = (EditText) findViewById(R.id.editTextClosingHours);
        mSubmitForm2btn = (Button) findViewById(R.id.form2btn_submit);
        mSubmitForm2btn.setOnClickListener(this);
        mAddressInput = (TextView) findViewById(R.id.input_address);
        mAddressInput.setOnClickListener(this);
        mSelectOpenHours = (ImageView) findViewById(R.id.selectOpenHours);
        mSelectOpenHours.setOnClickListener(this);
        mSelectCloseHours = (ImageView) findViewById(R.id.selectCloseHours);
        mSelectCloseHours.setOnClickListener(this);
    }

    public void onSearchCalled() {
        // Set the fields to specify which types of place data to return.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields).setCountry("IN") //INDIA
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.form2btn_submit:
                // TODO 20/09/13
                if (mEditTextNoOfChairs.getText().toString().isEmpty()) {
                    mEditTextNoOfChairs.setError("Can't be empty");
                } else if (mEditTextNoOfEmployee.getText().toString().isEmpty()) {
                    mEditTextNoOfEmployee.setError("Can't be empty");
                } else if (mEditTextOpenHours.getText().toString().isEmpty()) {
                    mEditTextOpenHours.setError("Can't be empty");
                } else if (mEditTextClosingHours.getText().toString().isEmpty()) {
                    mEditTextClosingHours.setError("Can't be empty");
                } else if (!selectedAddress) {
                    mAddressInput.setError("Kindly Select your business Location.");
                } else {
                    noOfChairs = mEditTextNoOfChairs.getText().toString();
                    noOfEmployee = mEditTextNoOfEmployee.getText().toString();
                    openingHours = mEditTextOpenHours.getText().toString();
                    closingHours = mEditTextClosingHours.getText().toString();
                    RetrofitApi.getInstance().addForm2(Form2Activity.this, Form2Activity.this, business_Id, noOfChairs, noOfEmployee, "0", "0", ltd, lgt);
                }
                break;
            case R.id.input_address:// TODO 20/09/16
                onSearchCalled();
                break;
            case R.id.selectOpenHours:// TODO 20/09/17
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (hourOfDay > 12){
                                    hourOfDay -= 12;
                                    timePeriod = "PM";
                                } else {
                                    timePeriod = "AM";
                                }
                                mEditTextOpenHours.setText(hourOfDay + ":" + minute + " " + timePeriod);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
            case R.id.selectCloseHours:// TODO 20/09/17
                // Get Current Time
                final Calendar c1 = Calendar.getInstance();
                mHour = c1.get(Calendar.HOUR_OF_DAY);
                mMinute = c1.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (hourOfDay > 12){
                                    hourOfDay -= 12;
                                    timePeriod = "PM";
                                } else {
                                    timePeriod = "AM";
                                }
                                mEditTextClosingHours.setText(hourOfDay + ":" + minute+ " "+timePeriod);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog1.show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());
                String address = place.getAddress();
                mAddressInput.setText(place.getName());
                if (place.getLatLng() != null) {
                    ltd = String.valueOf(place.getLatLng().latitude);
                    lgt = String.valueOf(place.getLatLng().longitude);
                    selectedAddress = true;
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {
        Toast.makeText(this, "Error on loading", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof Form2Model) {
            try {
                Form2Model form2Model = (Form2Model) obj;
                if (form2Model.getMESSAGE().equalsIgnoreCase("SUCCESS")) {
                    Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Form2Activity.this,MainActivity.class));
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}