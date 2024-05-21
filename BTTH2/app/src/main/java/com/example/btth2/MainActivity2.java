package com.example.btth2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    EditText edtNumberA, edtNumberB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNumberA = findViewById(R.id.edtNumberA);
        edtNumberB = findViewById(R.id.edtNumberB);
    }

    public void calculate(View view) {
        Intent intent = new Intent(MainActivity2.this, ChildActivity2.class);
        Bundle bundle = new Bundle();
        int a = Integer.parseInt(String.valueOf(edtNumberA.getText()));
        int b = Integer.parseInt(String.valueOf(edtNumberB.getText()));
        bundle.putInt("numberA", a);
        bundle.putInt("numberB", b);
        intent.putExtra("myBundle", bundle);
        startActivity(intent);
    }
}