package com.example.tipcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etBillAmount;

    TextView tvPercent, tvTip, tvTotal;

    Button btnDecrease, btnIncrease;

    private int _percent;
    private int _minPercent = 0;
    private int _maxPercent = 30;
    private int _percentStep = 5;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            update();
        }
    };

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

        etBillAmount = findViewById(R.id.etBillAmount);
        tvPercent = findViewById(R.id.tvPercent);
        tvTip = findViewById(R.id.tvTip);
        tvTotal = findViewById(R.id.tvTotal);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);

        etBillAmount.addTextChangedListener(textWatcher);

        _percent = Integer.parseInt(tvPercent.getText().toString());

        update();
    }

    public void decreasePercent(View view) {
        if (_percent > _minPercent) {
            _percent -= _percentStep;
            updatePercent();
        }
    }

    public void increasePercent(View view) {
        if (_percent < _maxPercent) {
            _percent += _percentStep;
            updatePercent();
        }
    }

    public void updatePercent() {
        tvPercent.setText(_percent + "%");
        btnDecrease.setEnabled(_percent > _minPercent);
        btnIncrease.setEnabled(_percent < _maxPercent);
        update();
    }

    public void update() {
        double _tip = 0;
        double _total = 0;
        if (!etBillAmount.getText().toString().isEmpty()) {
            double _billAmount = Double.parseDouble(String.valueOf(etBillAmount.getText()));
            _tip = _billAmount * _percent / 100;
            _total = _billAmount + _tip;
        }
        tvTotal.setText(String.format("$%.2f", _tip));
        tvTotal.setText(String.format("$%.2f", _total));
    }
}