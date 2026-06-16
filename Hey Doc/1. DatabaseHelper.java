package com.example.heydoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HeyDoc.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname TEXT," +
                "email TEXT," +
                "password TEXT)");

        db.execSQL("CREATE TABLE appointments(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "patient TEXT," +
                "doctor TEXT," +
                "date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS appointments");
        onCreate(db);
    }

    public boolean registerUser(String fullname,
                                String email,
                                String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("fullname", fullname);
        values.put("email", email);
        values.put("password", password);

        long result = db.insert("users", null, values);

        return result != -1;
    }

    public boolean loginUser(String email,
                             String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password});

        return cursor.getCount() > 0;
    }

    public boolean bookAppointment(String patient,
                                   String doctor,
                                   String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("patient", patient);
        values.put("doctor", doctor);
        values.put("date", date);

        long result = db.insert("appointments", null, values);

        return result != -1;
    }
}