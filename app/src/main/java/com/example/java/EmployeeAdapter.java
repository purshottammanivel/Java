package com.example.java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeesList = new ArrayList<>();

    public void setEmployeeAdapterData(ArrayList<Employee> empList) {
        this.employeesList = empList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_item_view, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        Employee employee = employeesList.get(position);
        holder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvEmployeeId;
        private TextView tvEmployeeName;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeId = itemView.findViewById(R.id.tvEmpId);
            tvEmployeeName = itemView.findViewById(R.id.tvEmpName);
        }

        public void bind(Employee employee) {
            tvEmployeeId.setText(String.valueOf(employee.getEmployeeId()));
            tvEmployeeName.setText(employee.getEmployeeName());
        }
    }
}
