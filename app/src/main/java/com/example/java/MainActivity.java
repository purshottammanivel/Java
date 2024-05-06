package com.example.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private SQLiteDatabase db;

    public EditText edtEmpId, edtEmpName;
    public Button btnSaveEmp;
    public RecyclerView rvEmployeeList;
    EmployeeAdapter employeeAdapter;
    ArrayList<Employee> employeesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        edtEmpId = findViewById(R.id.edtEmpId);
        edtEmpName = findViewById(R.id.edtEmpName);
        btnSaveEmp = findViewById(R.id.btnSaveEmp);
        rvEmployeeList = findViewById(R.id.rvEmployeeList);

        rvEmployeeList.setLayoutManager(new LinearLayoutManager(this));

        employeeAdapter = new EmployeeAdapter();
        rvEmployeeList.setAdapter(employeeAdapter);

        databaseHandler = new DatabaseHandler(this);

        btnSaveEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                empIdValue = edtEmpId.getText().toString();
                empNameValue = edtEmpName.getText().toString();

                databaseHandler = new DatabaseHandler(MainActivity.this);
                db = databaseHandler.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put("employee_id", empIdValue);
                contentValues.put("employee_name", empNameValue);

                long rowId = db.insertWithOnConflict("java", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

                if (rowId != -1) {
                    Log.d("QWERTY", "onClick: data stored successfully");
                } else {
                    Log.d("QWERTY", "onClick: Error while storing data");
                }
                 */
                String empIdValue = edtEmpId.getText().toString().trim();
                String empNameValue = edtEmpName.getText().toString().trim();

                if (!empIdValue.isEmpty() && !empNameValue.isEmpty()) {
                    int employeeIdInt = Integer.parseInt(empIdValue);

                    long newRowId = databaseHandler.addEmployee(employeeIdInt, empNameValue);

                    if (newRowId != -1) {
                        Log.d("QWERTY", "onClick: newRowId --> " + newRowId);
                        showToast("Employee added successfully", 0);
                        getAllEmployeesFromDb();
                    } else {
                        Log.d("QWERTY", "onClick: newRowId --> " + newRowId);
                        showToast("Please fill all the fields", 0);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllEmployeesFromDb();
    }

    private void getAllEmployeesFromDb() {
        employeesList = databaseHandler.getAllEmployees();
        employeeAdapter.setEmployeeAdapterData(employeesList);
    }

    private void showToast(String message, int length) {
        Toast.makeText(this, message, length).show();
    }
}