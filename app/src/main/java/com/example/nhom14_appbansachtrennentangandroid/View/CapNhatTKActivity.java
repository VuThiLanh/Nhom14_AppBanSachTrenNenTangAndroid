package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityCapNhatTkactivityBinding;

public class CapNhatTKActivity extends AppCompatActivity {
    ActivityCapNhatTkactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(CapNhatTKActivity.this, R.layout.activity_cap_nhat_tkactivity);
    }
}