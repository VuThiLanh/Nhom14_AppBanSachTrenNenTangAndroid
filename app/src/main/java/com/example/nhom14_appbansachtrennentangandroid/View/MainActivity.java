package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.ChangePassFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.GioHangFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.HomeFragment;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.SanPhamBanChayFragment;
import com.example.nhom14_appbansachtrennentangandroid.adapter.ViewPagerAdapTer;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPagerAdapTer adapter;
    ActivityMainBinding binding;
    int i=0;
    private static  final  int Fragment_home = 0;
    private int mCurrentFragmet = Fragment_home;
    private static  final  int Fragment_sanphambanchay = 1;
    private static  final  int Fragment_giohang = 2;
    private static  final  int Fragment_changepass = 3;
    final private ChangePassFragment mfragmentChangePass = new ChangePassFragment();
    DrawerLayout mdrawerLayout;
    public static  final int MY_REQUEST_CODE=10;
    private ImageView imgAvatar;
    private TextView tv_name,tv_email;
    private NavigationView mNavigationView;
    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Intent intent = result.getData();
                if(intent==null){
                    return;
                }
                Uri uri = intent.getData();
                mfragmentChangePass.setmUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    mfragmentChangePass.setBitMapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);




        setSupportActionBar(binding.toolbarMain);
        getSupportActionBar().setTitle("");
        initUI();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbarMain, 0,0);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        showInformation();

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
        Intent intent=getIntent();
        i=intent.getIntExtra("trang",0);
        binding.viewpagerMain.setCurrentItem(i);
        binding.tablayoutMain.setCurrentItem(i);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_home){
            if(mCurrentFragmet!= Fragment_home){
                replaceFragment(new HomeFragment());
                mCurrentFragmet = Fragment_home;
            }
        }
        else if(id==R.id.nav_favourite){
            if(mCurrentFragmet!=Fragment_sanphambanchay){
                replaceFragment(new SanPhamBanChayFragment());
                mCurrentFragmet = Fragment_sanphambanchay;
            }
        }
        else if(id==R.id.nav_taikhoan){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, DangNhap.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.navgiohang){
            if(mCurrentFragmet!=Fragment_giohang){
                replaceFragment(new GioHangFragment());
                mCurrentFragmet = Fragment_giohang;
            }
        }
        else if(id==R.id.nav_changepassword){
            if(mCurrentFragmet!=Fragment_changepass){
                replaceFragment(new ChangePassFragment());
                mCurrentFragmet = Fragment_giohang;
            }
        }
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private  void  initUI(){
        mNavigationView = findViewById(R.id.navigation_view);
        imgAvatar=mNavigationView.getHeaderView(0).findViewById(R.id.avt_header);
        tv_name=mNavigationView.getHeaderView(0).findViewById(R.id.user_header);
        tv_email=mNavigationView.getHeaderView(0).findViewById(R.id.email_header);
    }
    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.viewpager_main,fragment);
        transaction.commit();
    }
    public   void showInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        else{
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            if (name==null){
                tv_name.setVisibility(View.GONE);
            }
            else {
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(name);
            }
            tv_email.setText(email);
            Glide.with(this).load(photoUrl).error(R.drawable.avatardefault).into(imgAvatar);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGallary();
            }
        }
    }
    public  void openGallary(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Chọn ảnh"));
    }
}