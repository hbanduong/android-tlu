package com.zwng.ktgk;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zwng.ktgk.adapter.DepartmentAdapter;
import com.zwng.ktgk.model.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

public class DepartmentActivity extends AppCompatActivity {

    EditText edtId, edtName, edtEmail, edtWebsite, edtAddress, edtPhoneNumber, edtIdParent;
    Button btnSearch, btnAdd, btnUpdate, btnDelete;
    ListView lvDepartment;
    DepartmentAdapter departmentAdapter;
    List<DepartmentModel> departmentList;
    FirebaseDatabaseHelper firebaseDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_department);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtWebsite = findViewById(R.id.edtWebsite);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtIdParent = findViewById(R.id.edtIdParent);
        btnSearch = findViewById(R.id.btnSearch);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvDepartment = findViewById(R.id.lvDepartment);

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        departmentList = new ArrayList<>();
        departmentAdapter = new DepartmentAdapter(this, departmentList);
        lvDepartment.setAdapter(departmentAdapter);
        firebaseDatabaseHelper.loadDepartment(departmentList, departmentAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtId.getText().toString().isEmpty()) {
                    DepartmentModel department = new DepartmentModel(edtId.getText().toString(),
                            edtName.getText().toString(),
                            edtEmail.getText().toString(),
                            edtWebsite.getText().toString(),
                            edtAddress.getText().toString(),
                            edtPhoneNumber.getText().toString(),
                            edtIdParent.getText().toString());
                    firebaseDatabaseHelper.addDepartment(department, v);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabaseHelper.deleteDepartment(edtId.getText().toString(), v);
            }
        });

        lvDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DepartmentModel department = departmentList.get(position);
                edtId.setText(department.getId());
                edtName.setText(department.getName());
                edtEmail.setText(department.getEmail());
                edtWebsite.setText(department.getWebsite());
                edtAddress.setText(department.getAddress());
                edtPhoneNumber.setText(department.getPhoneNumber());
                edtIdParent.setText(department.getIdParent());
            }
        });
    }
}