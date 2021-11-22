package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom14_appbansachtrennentangandroid.R;

import com.example.nhom14_appbansachtrennentangandroid.adapter.ViewPagerAdapTer;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int i=0;
    public static  final int MY_REQUEST_CODE=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        ViewPagerAdapTer viewPagerAdapTer = new ViewPagerAdapTer(this);
        binding.viewpagerMain.setAdapter(viewPagerAdapTer);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.nav_home){
                    binding.viewpagerMain.setCurrentItem(0);
                }
                else if(id==R.id.nav_chat){
                    binding.viewpagerMain.setCurrentItem(1);
                }
                else if(id==R.id.navthongbao){
                    binding.viewpagerMain.setCurrentItem(2);
                }
                else if(id==R.id.nav_taikhoan){
                    binding.viewpagerMain.setCurrentItem(3);
                }
                return true;
            }
        });
        binding.viewpagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.nav_chat).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.navthongbao).setChecked(true);
                        break;
                    case 3:
                        binding.bottomNavigation.getMenu().findItem(R.id.nav_taikhoan).setChecked(true);
                        break;
                }
            }
        });
        chuyenTrang();
    }

    private void chuyenTrang(){
        Intent intent=getIntent();
        i=intent.getIntExtra("trang",0);
        binding.viewpagerMain.setCurrentItem(i);
        binding.bottomNavigation.getMenu().findItem(R.id.nav_chat).setChecked(true);
    }

}
