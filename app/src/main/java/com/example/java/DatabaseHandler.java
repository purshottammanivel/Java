package com.example.java;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "javadb";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_NAME = "Employees";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_EMPLOYEE_NAME = "employee_name";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + "id INTEGER PRIMARY KEY," +
                "employee_id INTEGER," +
                "employee_name TEXT" +
                ")";
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(CREATE_TABLE);
         */
        /** CREATE TABLE javadb(id INTEGER PRIMARY KEY AUTOINCREMENT,employee_id INTEGER,employee_name TEXT) **/
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_EMPLOYEE_ID + " INTEGER," +
                COLUMN_EMPLOYEE_NAME + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addEmployee(int employeeId, String employeeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_ID, employeeId);
        values.put(COLUMN_EMPLOYEE_NAME, employeeName);

        long newRowId = db.insert(TABLE_NAME, null, values);
        return newRowId;
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int employeeId = cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID));
                @SuppressLint("Range") String employeeName = cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_NAME));
                Employee employee = new Employee(employeeId, employeeName);
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employeeList;
    }
}
