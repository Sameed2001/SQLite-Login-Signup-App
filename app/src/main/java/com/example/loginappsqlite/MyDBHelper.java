package com.example.loginappsqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class MyDBHelper extends SQLiteOpenHelper {
    Context context;
    private ByteArrayOutputStream byteArrayOutputStream;
    byte[] imageInBytes;

    public MyDBHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String fullname, String username, String email, String address, String phone, String dob, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_NAME, fullname);
        contentValues.put(Constants.C_USERNAME, username);
        contentValues.put(Constants.C_EMAIL, email);
        contentValues.put(Constants.C_ADDRESS, address);
        contentValues.put(Constants.C_PHONE, phone);
        contentValues.put(Constants.C_DOB, dob);
        contentValues.put(Constants.C_PASSWORD, password);

        long id = db.insert(Constants.TABLE_NAME, null, contentValues);

        if(id == -1) return false;
        else
            return true;
    }

    public void storeImage(ModelClass objModelClass){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Bitmap imageToStoreBitmap = objModelClass.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.C_IMAGE, imageInBytes);
        long id = MyDB.insert(Constants.TABLE_NAME,Constants.C_IMAGE,contentValues);
        if(id != -1)
        {
            Toast.makeText(context, "Image added to database", Toast.LENGTH_SHORT).show();
            MyDB.close();
        }
        else {
            Toast.makeText(context, "Failed to add image", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + Constants.TABLE_NAME + " where " + Constants.C_USERNAME + " = ?", new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + Constants.TABLE_NAME + " where " + Constants.C_USERNAME + " = ? and " + Constants.C_PASSWORD + " = ?", new String[] {username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public String getSpecificFullName(String username)
    {
        String fullname = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_USERNAME+" =?", new String[]{username});
        if(c.getCount() > 0)
        {
            c.moveToFirst();
            fullname = c.getString(c.getColumnIndex(Constants.C_NAME));
        }
        else {
            fullname = " ";
        }
        return fullname;
    }

    @SuppressLint("Range")
    public String getSpecificEmail(String username)
    {
        String email = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_USERNAME+" =?", new String[]{username});
        if(c.getCount() > 0)
        {
            c.moveToFirst();
            email = c.getString(c.getColumnIndex(Constants.C_EMAIL));
        }
        else {
            email = " ";
        }
        return email;
    }

    @SuppressLint("Range")
    public String getSpecificPhone(String username)
    {
        String phone = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_USERNAME+" =?", new String[]{username});
        if(c.getCount() > 0)
        {
            c.moveToFirst();
            phone = c.getString(c.getColumnIndex(Constants.C_PHONE));
        }
        else {
            phone = " ";
        }
        return phone;
    }

    @SuppressLint("Range")
    public String getSpecificAddress(String username)
    {
        String address = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_USERNAME+" =?", new String[]{username});
        if(c.getCount() > 0)
        {
            c.moveToFirst();
            address = c.getString(c.getColumnIndex(Constants.C_ADDRESS));
        }
        else {
            address = " ";
        }
        return address;
    }

    @SuppressLint("Range")
    public String getSpecificDOB(String username)
    {
        String dob = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_USERNAME+" =?", new String[]{username});
        if(c.getCount() > 0)
        {
            c.moveToFirst();
            dob = c.getString(c.getColumnIndex(Constants.C_DOB));
        }
        else {
            dob = " ";
        }
        return dob;
    }

}
