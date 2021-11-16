package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDangNhapBinding;

public class DangNhap extends AppCompatActivity {

    ActivityDangNhapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(DangNhap.this,R.layout.activity_dang_nhap );
        binding.tvQuenMKDN .setPaintFlags(binding.tvQuenMKDN.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        binding.tvDkiDN.setPaintFlags(binding.tvDkiDN.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });
    }

}