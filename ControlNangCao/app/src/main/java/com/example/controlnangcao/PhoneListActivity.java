package com.example.controlnangcao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhoneListActivity extends AppCompatActivity {

    TextView tvSelectedPhone;
    ListView lvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvSelectedPhone = findViewById(R.id.tvSelectedPhone);
        lvPhone = findViewById(R.id.lvPhone);

        final String[] arrPhone = {"Iphone 11", "Oppo Reno 10", "Samsung S21", "Redmi note 13", "Iphone 11", "Oppo Reno 10", "Samsung S21", "Redmi note 13", "Iphone 11", "Oppo Reno 10", "Samsung S21", "Redmi note 13", "Iphone 11", "Oppo Reno 10", "Samsung S21", "Redmi note 13", "Iphone 11", "Oppo Reno 10", "Samsung S21", "Redmi note 13"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrPhone);

        lvPhone.setAdapter(arrayAdapter);

        lvPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvSelectedPhone.setText("Vị trí " + position + ": " + arrPhone[position]);
            }
        });
    }
}