package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nhom14_appbansachtrennentangandroid.View.fragment.ChoFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.DaNhanFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.DangFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.HuyFragment;

public class DonHangViewPager extends FragmentPagerAdapter {
    public DonHangViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChoFragment();
            case 1:
                return new DangFragment();
            case 2:
                return  new DaNhanFragment();
            case 3:
                return new HuyFragment();
            default: return new DangFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
