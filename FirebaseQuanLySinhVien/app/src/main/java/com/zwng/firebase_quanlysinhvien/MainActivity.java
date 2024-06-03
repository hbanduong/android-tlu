package com.zwng.firebase_quanlysinhvien;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    EditText edtDate, edtId, edtName, edtClassroom;
    ListView lvStudent;
    List<StudentModel> studentList;
    FirebaseDatabaseHelper firebaseDatabaseHelper;
    StudentAdapter studentAdapter;
    Button btnAdd, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtDate = findViewById(R.id.edtDate);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtClassroom = findViewById(R.id.edtClassroom);

        lvStudent = findViewById(R.id.lvStudent);
        studentList = new ArrayList<>();
        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        studentAdapter = new StudentAdapter(this, studentList);
        lvStudent.setAdapter(studentAdapter);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }
        };

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        firebaseDatabaseHelper.loadStudent(studentList, studentAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtId.getText().toString().isEmpty() &&
                        !edtName.getText().toString().isEmpty() &&
                        !edtClassroom.getText().toString().isEmpty() &&
                        !edtDate.getText().toString().isEmpty()) {
                    StudentModel student = new StudentModel(edtId.getText().toString(),
                            edtName.getText().toString(),
                            edtDate.getText().toString(),
                            edtClassroom.getText().toString());
                    firebaseDatabaseHelper.addStudent(student, v);
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabaseHelper.updateStudent(
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtDate.getText().toString(),
                        edtClassroom.getText().toString(),
                        v
                );
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabaseHelper.deleteStudent(edtId.getText().toString(), v);
            }
        });

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel selectedStudent = studentList.get(position);
                edtId.setText(selectedStudent.getId());
                edtName.setText(selectedStudent.getName());
                edtDate.setText(selectedStudent.getDate());
                edtClassroom.setText(selectedStudent.getClassroom());
            }
        });
    }

    public void updateDateLabel() {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        edtDate.setText(simpleDateFormat.format(calendar.getTime()));
    }
}