package com.example.loginappsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;

public class LoginActivity extends AppCompatActivity {
    EditText loginUserName, loginPassword;
    TextView txtSignup;
    Button btnLogin;
    MyDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtSignup = findViewById(R.id.signUp);
        loginUserName = findViewById(R.id.inputLoginUsername);
        loginPassword = findViewById(R.id.inputLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);

        dbHelper = new MyDBHelper(this);

        txtSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(view -> {
            String user = loginUserName.getText().toString();
            String pass = loginPassword.getText().toString();

            String specificFullName = dbHelper.getSpecificFullName(user);
            String specificEmail = dbHelper.getSpecificEmail(user);
            String specificPhone = dbHelper.getSpecificPhone(user);
            String specificAddress = dbHelper.getSpecificAddress(user);
            String specificDOB = dbHelper.getSpecificDOB(user);
            if(user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            }
            else{
                Boolean checkUserPass = dbHelper.checkUsernamePassword(user, pass);
                if(checkUserPass == true){
                    Toast.makeText(LoginActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Profile.class);
                    intent.putExtra("username", user);
                    intent.putExtra("fullname", specificFullName);
                    intent.putExtra("email", specificEmail);
                    intent.putExtra("phone", specificPhone);
                    intent.putExtra("address", specificAddress);
                    intent.putExtra("dob",specificDOB);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}