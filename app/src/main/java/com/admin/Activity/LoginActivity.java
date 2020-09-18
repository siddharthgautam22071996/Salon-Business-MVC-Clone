package com.admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.admin.DashboardActivity;
import com.admin.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText et_emailid;
    private TextInputEditText et_password;
    private Button btn_login;
    private String str_email;
    private String str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_emailid =(TextInputEditText)findViewById(R.id.email);
        et_password =(TextInputEditText)findViewById(R.id.password);
        btn_login =(Button)findViewById(R.id.login);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        str_email = et_emailid.getText().toString().trim();
        str_password = et_password.getText().toString().trim();
        startActivity(new Intent(this, DashboardActivity.class));
    }
}