package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDangKyBinding;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDangNhapBinding;

public class DangKyActivity extends AppCompatActivity {

    ActivityDangKyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        binding= DataBindingUtil.setContentView(DangKyActivity.this, R.layout.activity_dang_ky);
    }
}