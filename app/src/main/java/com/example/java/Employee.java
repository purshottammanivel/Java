package com.example.java;

public class Employee {

    private int employeeId;
    private String employeeName;

    public Employee(int empId, String empName) {
        this.employeeId = empId;
        this.employeeName = empName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int empId) {
        this.employeeId = empId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String empName) {
        this.employeeName = empName;
    }
}
