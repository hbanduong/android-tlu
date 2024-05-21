package com.example.controlnangcao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarNoteActivity extends AppCompatActivity {

    TextView tvCurrentDate;
    EditText edtWork, edtHour, edtMinute;
    Button btnAddWork;
    ListView lvWork;
    ArrayList<String> arrayListWork;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        edtWork = findViewById(R.id.edtWork);
        edtHour = findViewById(R.id.edtHour);
        edtMinute = findViewById(R.id.edtMinute);
        btnAddWork = findViewById(R.id.btnAddWork);
        lvWork= findViewById(R.id.lvWork);

        //set array adapter
        arrayListWork = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListWork);
        lvWork.setAdapter(arrayAdapter);

        //set current date
        Date currentDate = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvCurrentDate.setText("Current Date: " + simpleDateFormat.format(currentDate));

        btnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtWork.getText().toString().equals("") || edtHour.getText().toString().equals("") || edtMinute.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalendarNoteActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please fill all the fields");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
                else {
                    arrayListWork.add(edtWork.getText().toString() + " - " + edtHour.getText().toString() + ":" + edtMinute.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    edtWork.setText("");
                    edtHour.setText("");
                    edtMinute.setText("");
                }
            }
        });

        lvWork.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayListWork.remove(position);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}