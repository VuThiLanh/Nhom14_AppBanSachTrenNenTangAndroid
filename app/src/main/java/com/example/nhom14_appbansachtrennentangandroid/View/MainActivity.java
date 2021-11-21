package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom14_appbansachtrennentangandroid.R;

import com.example.nhom14_appbansachtrennentangandroid.adapter.ViewPagerAdapTer;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    int i=0;
<<<<<<< Updated upstream
=======
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private static  final  int Fragment_home = 0;
    private int mCurrentFragmet = Fragment_home;
    private static  final  int Fragment_sanphambanchay = 1;
    private static  final  int Fragment_thongtinshop = 4;
    private static  final  int Fragment_chinhtripl= 5;
    private static  final  int Fragment_khoahoccnkt = 6;
    private static  final  int Fragment_vhnt = 7;
    private static  final  int Fragment_vhxhls = 8;
    private static  final  int Fragment_giaotrinh = 9;
    private static  final  int Fragment_tamlytltg = 10;
    private static  final  int Fragment_ttt = 11;
    private static  final  int Fragment_thieunhi = 12;

    final private ChangePassFragment mfragmentChangePass = new ChangePassFragment();
    DrawerLayout mdrawerLayout;
>>>>>>> Stashed changes
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
    }

<<<<<<< Updated upstream
=======
    private void display(){
        adapter=new ViewPagerAdapTer(getSupportFragmentManager());
        binding.viewpagerMain.setAdapter(adapter);
        binding.viewpagerMain.setPagingEnabled(true);
        AHBottomNavigationItem item1= new AHBottomNavigationItem("Home",R.drawable.iconhome, R.color.red);
        AHBottomNavigationItem item2= new AHBottomNavigationItem("Tài khoản",R.drawable.iconuser, R.color.red );
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
        else if(id==R.id.navgiohang){
            Intent intent = new Intent(this,DonHangActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.navdonhang){
            Intent intent = new Intent(this,GioHangActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.navthongtin){
            if(mCurrentFragmet!=Fragment_thongtinshop){
                replaceFragment(new ThongTinShopFragment());
                mCurrentFragmet = Fragment_thongtinshop;
            }
        }
        else if(id==R.id.nav_loai1){
            if(mCurrentFragmet!=Fragment_chinhtripl){
                replaceFragment(new ChinhTriPLFragment());
                mCurrentFragmet = Fragment_chinhtripl;
            }
        }
        else if(id==R.id.nav_loai2){
            if(mCurrentFragmet!=Fragment_khoahoccnkt){
                replaceFragment(new KHCNKTFragment());
                mCurrentFragmet = Fragment_khoahoccnkt;
            }
        }
        else if(id==R.id.nav_loai3){
            if(mCurrentFragmet!=Fragment_vhnt){
                replaceFragment(new VanHocNTFragment());
                mCurrentFragmet = Fragment_vhnt;
            }
        }
        else if(id==R.id.nav_loai4){
            if(mCurrentFragmet!=Fragment_vhxhls){
                replaceFragment(new VanHoaXHLSFragment());
                mCurrentFragmet = Fragment_vhxhls;
            }
        }
        else if(id==R.id.nav_loai5){
            if(mCurrentFragmet!=Fragment_giaotrinh){
                replaceFragment(new GiaoTrinhFragment());
                mCurrentFragmet = Fragment_giaotrinh;
            }
        }
        else if(id==R.id.nav_loai6){
            if(mCurrentFragmet!=Fragment_tamlytltg){
                replaceFragment(new TamLyTLTGFragment());
                mCurrentFragmet = Fragment_tamlytltg;
            }
        }
        else if(id==R.id.nav_loai7){
            if(mCurrentFragmet!=Fragment_ttt){
                replaceFragment(new TieuThuyetFragment());
                mCurrentFragmet = Fragment_ttt;
            }
        }
        else if(id==R.id.nav_loai8){
            if(mCurrentFragmet!=Fragment_thieunhi){
                replaceFragment(new ThieuNhiFragment());
                mCurrentFragmet = Fragment_thieunhi;
            }
        }

        else if(id==R.id.nav_taikhoan){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, DangNhap.class);
            startActivity(intent);
            finish();
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
        else{
            super.onBackPressed();
        }

    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
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
>>>>>>> Stashed changes
}