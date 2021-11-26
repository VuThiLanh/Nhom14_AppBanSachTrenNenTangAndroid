package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.TimKiemDonActivity;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DonHangViewPager;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDonHangBinding;

public class DonHangActivity extends AppCompatActivity {

    ActivityDonHangBinding binding;
    DonHangViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(DonHangActivity.this, R.layout.activity_don_hang);


        setSupportActionBar(binding.toolbarSp);
        getSupportActionBar().setTitle("Đơn hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        binding.bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                binding.viewpager.setCurrentItem(position);
                return true;
            }
        });


        binding.viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.bottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        binding.imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, MainActivity.class);
                intent.putExtra("trang", 1);
                startActivity(intent);
            }
        });


        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, TimKiemDonActivity.class);
                startActivity(intent);
            }
        });




    }
    private void init(){
        viewPager=new DonHangViewPager(getSupportFragmentManager());
        binding.viewpager.setAdapter(viewPager);
        binding.viewpager.setPagingEnabled(true);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Chờ xử lý",R.drawable.cho, R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Đang giao",R.drawable.ic_giaohang, R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Đã nhận",R.drawable.dacnhan, R.color.black);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Đã hủy",R.drawable.huy, R.color.black);

        binding.bottomNavigation.addItem(item1);
        binding.bottomNavigation.addItem(item2);
        binding.bottomNavigation.addItem(item3);
        binding.bottomNavigation.addItem(item4);

        binding.bottomNavigation.setAccentColor(Color.parseColor("#F80303"));
//        binding.bottomNavigation.setInactiveColor(Color.parseColor("#F80303"));

    }

}