package com.example.btth04_quan_ly_sinh_vien;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtMaLop, edtTenLop, edtSiSo;
    Button btnThem, btnSua, btnXoa, btnTruyVan;
    ListView lvSinhVien;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    SQLiteDatabase database;

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

        edtMaLop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        edtSiSo = findViewById(R.id.edtSiSo);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnTruyVan = findViewById(R.id.btnTruyVan);
        lvSinhVien = findViewById(R.id.lvSinhVien);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lvSinhVien.setAdapter(arrayAdapter);

        database = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);

        try {
            String sql = "CREATE TABLE tbLop(maLop TEXT PRIMARY KEY, tenLop TEXT, siSo INT)";
            database.execSQL(sql);
        }
        catch (Exception e) {
            Log.e("Lỗi", e.getMessage());
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop = edtMaLop.getText().toString();
                String tenLop = edtTenLop.getText().toString();
                int siSo = Integer.parseInt("0" + edtSiSo.getText().toString());
                if ( siSo<=0 || siSo>100 ) {
                    Toast.makeText(MainActivity.this, "Sĩ số không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put("maLop", maLop);
                values.put("tenLop", tenLop);
                values.put("siSo", siSo);
                String message = "";
                if (database.insert("tbLop", null, values) == -1) {
                    message = "Thêm dữ liệu thất bại";
                }
                else {
                    message = "Thêm dữ liệu thành công";

                }
                updateListView();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop = edtMaLop.getText().toString();
                int n = database.delete("tbLop", "maLop = ?", new String[]{maLop});
                String message = "";
                if (n == 0) {
                    message = "Xóa dữ liệu thất bại";
                }
                else {
                    message = "Xóa dữ liệu thành công";
                }
                updateListView();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siSo = Integer.parseInt("0" + edtSiSo.getText().toString());
                String tenLop = edtTenLop.getText().toString();
                String maLop = edtMaLop.getText().toString();
                if ( siSo<=0 || siSo>100 ) {
                    Toast.makeText(MainActivity.this, "Sĩ số không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put("siSo", siSo);
                values.put("tenLop", tenLop);
                int n = database.update("tbLop", values, "maLop = ?", new String[]{maLop});
                String message = "";
                if (n == 0) {
                    message = "Sửa dữ liệu thất bại";
                }
                else {
                    message = "Sửa dữ liệu thành công";
                }
                updateListView();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnTruyVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListView();
            }
        });
    }

    public void updateListView() {
        arrayList.clear();
        Cursor cursor = database.query("tbLop", null, null, null, null, null, null);
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            String data = cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getInt(2);
            arrayList.add(data);
            cursor.moveToNext();
        }
        cursor.close();
        arrayAdapter.notifyDataSetChanged();
    }
}