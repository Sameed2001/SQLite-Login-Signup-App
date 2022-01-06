package com.example.loginappsqlite;

public class Constants {
    public static final String DB_NAME = "USERS.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "USERS_DATA";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_USERNAME = "USERNAME";
    public static final String C_EMAIL = "EMAIL";
    public static final String C_PHONE = "PHONE";
    public static final String C_DOB = "DOB";
    public static final String C_ADDRESS = "ADDRESS";
    public static final String C_PASSWORD = "PASSWORD";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_IMAGE + " BLOB,"
            + C_USERNAME + " TEXT,"
            + C_EMAIL + " TEXT,"
            + C_PHONE + " TEXT,"
            + C_DOB + " TEXT,"
            + C_ADDRESS + " TEXT,"
            + C_PASSWORD + " TEXT)";
}
