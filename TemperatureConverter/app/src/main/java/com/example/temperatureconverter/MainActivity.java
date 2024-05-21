package com.example.temperatureconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edtCelsiusTemp, edtFahrenheitTemp;

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

        edtCelsiusTemp = findViewById(R.id.edtCelsiusTemp);
        edtFahrenheitTemp = findViewById(R.id.edtFahrenheitTemp);
    }

    public void convertToCelsius(View view) {
        try {
            float f = Float.parseFloat("0" + edtFahrenheitTemp.getText());
            DecimalFormat c = new DecimalFormat("#.00");
            edtCelsiusTemp.setText("" + c.format((f-32)*5/9));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void convertToFahrenheit(View view) {
        try {
            float c = Float.parseFloat("0" + edtCelsiusTemp.getText());
            DecimalFormat f = new DecimalFormat("#.00");
            edtFahrenheitTemp.setText("" + f.format(c*9/5+32));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clear(View view) {
        edtCelsiusTemp.setText("");
        edtFahrenheitTemp.setText("");
    }
}