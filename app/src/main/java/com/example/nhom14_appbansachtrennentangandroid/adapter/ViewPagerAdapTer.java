package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nhom14_appbansachtrennentangandroid.View.fragment.ChatFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.GioHangFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.HomeFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.ThongBaoFragment;

public class ViewPagerAdapTer extends FragmentPagerAdapter {
    public ViewPagerAdapTer(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0: return new HomeFragment();
            case 1: return new GioHangFragment();
            case 2: return new ChatFragment();
            case 3: return  new ThongBaoFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
