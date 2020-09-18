package com.admin.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.admin.Model.Form1Model;
import com.admin.R;
import com.admin.Retrofit.RetrofitApi;

public class FormActivity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    private EditText mEditTextTextPersonName;
    private EditText mEditTextTextPersonName2;
    private EditText mEditTextPhone;
    private EditText mEditTextTextEmailAddress;
    private EditText mEditTextTextPassword;
    private Button mButton;
    private String business_Name,contact_Person,contact_No,business_email_id,salon_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initView();
    }

    private void initView() {
        mEditTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        mEditTextTextPersonName2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);
        mEditTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        mEditTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                // TODO 20/09/11
                if (mEditTextTextPersonName.getText().toString().isEmpty()) {
                    mEditTextTextPersonName.setError("Can't be empty");
                } else if (mEditTextTextPersonName2.getText().toString().isEmpty()) {
                    mEditTextTextPersonName2.setError("Can't be empty");
                } else if (mEditTextPhone.getText().toString().isEmpty()) {
                    mEditTextPhone.setError("Can't be empty");
                } else if (mEditTextTextEmailAddress.getText().toString().isEmpty()) {
                    mEditTextTextEmailAddress.setError("Can't be empty");
                } else if (mEditTextTextPassword.getText().toString().isEmpty()) {
                    mEditTextTextPassword.setError("Can't be empty");
                } else {
                    business_Name = mEditTextTextPersonName.getText().toString();
                    business_email_id = mEditTextTextEmailAddress.getText().toString();
                    contact_No = mEditTextPhone.getText().toString();
                    contact_Person = mEditTextTextPersonName2.getText().toString();
                    salon_password = mEditTextTextPassword.getText().toString();
                    RetrofitApi.getInstance().addForm1(FormActivity.this,FormActivity.this,business_Name,contact_Person,contact_No,business_email_id,salon_password);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {
        e.printStackTrace();
        System.out.println("On Error block");
    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof Form1Model){
            try {
                System.out.println("onNext block");
                Form1Model form1Model = (Form1Model) obj;
                Intent intent = new Intent(FormActivity.this, Form2Activity.class);
                intent.putExtra("business_Id",form1Model.getData().get(0).getBusiness_id());
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}