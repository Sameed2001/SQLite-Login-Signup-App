package com.example.loginappsqlite;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    CircleImageView objectImageView;
    EditText fullName, userName, email, phoneNumber, address, dob, password, confirmPassword;
    Button btnSignup;


    //permission constants
    private static final int PICK_IMAGE_REQUEST = 100;

     private Uri imageFilePath;
     Bitmap imageToStore;

     String full_name, user_name, e_mail, phone_number, user_address, date_of_birth, user_password;

     MyDBHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        objectImageView = findViewById(R.id.profileImg);
        fullName = findViewById(R.id.inputFullName);
        userName = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputEmail);
        phoneNumber = findViewById(R.id.inputPhone);
        address = findViewById(R.id.inputAddress);
        dob = findViewById(R.id.inputDOB);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);



        //db helper
        dbHelper = new MyDBHelper(this);

        objectImageView.setOnClickListener(view -> {

            try {
                Intent objectIntent = new Intent();
                objectIntent.setType("image/*");
                objectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        btnSignup.setOnClickListener(view -> {

            String user_fullname = fullName.getText().toString();
            String user_username = userName.getText().toString();
            String user_email = email.getText().toString();
            String user_phone = phoneNumber.getText().toString();
            String user_address = address.getText().toString();
            String user_dob = dob.getText().toString();
            String user_login_password = password.getText().toString();
            String user_confirmPassword = confirmPassword.getText().toString();


            if(user_fullname.isEmpty() || user_username.isEmpty() || user_email.isEmpty() || user_phone.isEmpty() || user_address.isEmpty() || user_dob.isEmpty() || user_login_password.isEmpty()|| user_confirmPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
            }
            else {

                if (user_login_password.equals(user_confirmPassword))
                {
                    Boolean checkuser = dbHelper.checkUsername(user_username);
                    if(checkuser == false){
                            inputData();
                        try {
                            dbHelper.storeImage(new ModelClass(imageToStore));
                        } catch (Exception e) {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_LONG).show();
                }

            }




        });
    }

    private void inputData() {
        //get Data
        full_name = fullName.getText().toString();
        user_name = userName.getText().toString();
        e_mail = email.getText().toString();
        user_address = address.getText().toString();
        phone_number = phoneNumber.getText().toString();
        date_of_birth = dob.getText().toString();
        user_password = password.getText().toString();

        //save to db
        Boolean insert = dbHelper.insertData(
                ""+full_name,
                ""+user_name,
                ""+e_mail,
                ""+user_address,
                ""+phone_number,
                ""+date_of_birth,
                ""+user_password);

        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null)
            {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                objectImageView.setImageBitmap(imageToStore);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}