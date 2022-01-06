package com.example.loginappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView titleUsername;
    TextView profile_fullname, profile_email,profile_phone, profile_address,profile_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        titleUsername = findViewById(R.id.titleUsername);
        profile_fullname = findViewById(R.id.profile_fullname);
        profile_email = findViewById(R.id.profile_email);
        profile_phone = findViewById(R.id.profile_phone);
        profile_address = findViewById(R.id.profile_address);
        profile_dob = findViewById(R.id.profile_dob);
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("username");
        String full_name = intent.getStringExtra("fullname");
        String e_mail = intent.getStringExtra("email");
        String p_hone = intent.getStringExtra("phone");
        String a_ddress = intent.getStringExtra("address");
        String d_ob = intent.getStringExtra("dob");
        titleUsername.setText(user_name);
        profile_fullname.setText(full_name);
        profile_email.setText(e_mail);
        profile_phone.setText(p_hone);
        profile_address.setText(a_ddress);
        profile_dob.setText(d_ob


        );
    }
}