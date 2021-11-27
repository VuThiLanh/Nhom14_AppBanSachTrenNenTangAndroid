package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nhom14_appbansachtrennentangandroid.R;

import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamAdapter;
import com.example.nhom14_appbansachtrennentangandroid.adapter.ViewPagerAdapTer;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityMainBinding;
import com.example.nhom14_appbansachtrennentangandroid.model.GioHang;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static ArrayList<SanPham> listSanPham;
    public static ArrayList<GioHang> listGioHang;
    int i=0;
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
        if(listSanPham == null){
            listSanPham = new ArrayList<>();
        }
        if(listGioHang == null){
            listGioHang = new ArrayList<>();
        }
        chuyenTrang();
        getSanPham();
        getGioHang();
    }

    private void chuyenTrang(){
        int i =0;
        Intent intent=getIntent();
        i=intent.getIntExtra("trang",0);
        binding.viewpagerMain.setCurrentItem(i);
        binding.bottomNavigation.getMenu().findItem(R.id.nav_chat).setChecked(true);
    }
    private void getSanPham( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sanpham");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSanPham.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    listSanPham.add(sanPham);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getGioHang( ) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("giohang").child(user.getUid());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listGioHang.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                        listGioHang.add(gioHang);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplication(), "Get Book Fail!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            listGioHang.clear(); ;
        }
    }
}
