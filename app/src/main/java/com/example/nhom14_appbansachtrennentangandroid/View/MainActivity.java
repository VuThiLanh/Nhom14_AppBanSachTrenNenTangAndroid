package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.ViewPagerAdapTer;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapTer adapter;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        setSupportActionBar(binding.toolbarMain);
        getSupportActionBar().setTitle("");

        binding.tablayoutMain.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                binding.viewpagerMain.setCurrentItem(position);
                return true;
            }
        });
        binding.viewpagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                binding.tablayoutMain.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        display();
    }

    private void display(){
        adapter=new ViewPagerAdapTer(getSupportFragmentManager());
        binding.viewpagerMain.setAdapter(adapter);
        binding.viewpagerMain.setPagingEnabled(true);
        AHBottomNavigationItem item1= new AHBottomNavigationItem("Home",R.drawable.iconhome, R.color.red);
        AHBottomNavigationItem item2= new AHBottomNavigationItem("Giỏ hàng",R.drawable.icongiohang, R.color.red );
        AHBottomNavigationItem item3= new AHBottomNavigationItem("Chat", R.drawable.iconnhantin, R.color.red);
        AHBottomNavigationItem item4= new AHBottomNavigationItem("Thông báo", R.drawable.iconthongbao, R.color.red);
        binding.tablayoutMain.addItem(item1);
        binding.tablayoutMain.addItem(item2);
        binding.tablayoutMain.addItem(item3);
        binding.tablayoutMain.addItem(item4);
    }
}