package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.models.User;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    public DBManager(@Nullable Context context) {
        super(context, "project.db", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase dbRef) {
        dbRef.execSQL("create table if not exists profile (fname text,lname text,address text,marital_status text,image blob)");
        dbRef.execSQL("create table if not exists departments (name text)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase dbRef, int i, int i1) {
        dbRef.execSQL("create table if not exists User (username text primary key,password text)");
    }
    public void saveDepartment(String dname){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", dname);
        db.insertOrThrow("departments",
                null,
                values);
    }
    public void saveUser(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", userName);
        values.put("password", password);
        db.insertOrThrow("User", null, values);
    }
    public ArrayList<User> getAllUsersDetails(){
        ArrayList<User> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from User",
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            String userName = c.getString(0);
            String userPassword = c.getString(1);
            User u = new User(userName, userPassword);
//            u.userName = userName;
//            u.userPassword = userPassword;
            data.add(u);
            c.moveToNext();
        }
        c.close();
        return data;
    }
    public ArrayList<String> getAllUsers(){
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from User",
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            String userName = c.getString(0);
            String userPassword = c.getString(1);
            String details = "User Name : "+userName
                    + "\nPassword : " + userPassword;
            data.add(details);
            c.moveToNext();
        }
        c.close();
        return data;
    }
    public ArrayList<String> getAllDepartments(){
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select distinct name from departments",
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            String dname = c.getString(0);
            data.add(dname);
            c.moveToNext();
        }
        c.close();
        return data;
    }

    public boolean logIn(String uname, String password){
        SQLiteDatabase db  = getReadableDatabase();
        String[] args = {uname, password};
        Cursor c = db.rawQuery("select * from user where username = ? and password=?"
                ,args);
        c.moveToFirst();
        if(c.isAfterLast() == false){
            c.close();
            return  true;
        }else{
            c.close();
            return false;
        }
    }






}
