package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nhom14_appbansachtrennentangandroid.R;

public class GioHangActivity extends AppCompatActivity {
    TextView tvMuaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        tvMuaHang=findViewById(R.id.tvMuaHang);
        tvMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                startActivity(intent);
            }
        });
    }
}