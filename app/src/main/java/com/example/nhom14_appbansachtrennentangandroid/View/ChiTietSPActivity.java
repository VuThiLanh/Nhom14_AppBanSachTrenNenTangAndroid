package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityChiTietSpactivityBinding;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChiTietSPActivity extends AppCompatActivity {

    ActivityChiTietSpactivityBinding binding;
    int sl=0;
    String maSP="";
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ChiTietSPActivity.this, R.layout.activity_chi_tiet_spactivity);

        Intent intent=getIntent();
        maSP=intent.getStringExtra("maSP");
        maSP="sp001";

        setSupportActionBar(binding.toolbarSp);
        getSupportActionBar().setTitle("Chi tiết sản phẩm");



        sl=Integer.parseInt(binding.edSl.getText().toString());
        binding.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl+=1;
                binding.edSl.setText(sl+"");
            }
        });



        binding.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl-=1;
                if(sl<0){
                    sl=0;
                }
                binding.edSl.setText(sl+"");
            }
        });


        binding.imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChiTietSPActivity.this, MainActivity.class);
                intent.putExtra("trang", 1);
                startActivity(intent);
            }
        });


        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChiTietSPActivity.this, MainActivity.class);
                intent.putExtra("trang", 2);
                startActivity(intent);
            }
        });

        binding.imgQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        reference.child("sanpham").child("sp001").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SanPham sanPham=snapshot.getValue(SanPham.class);
                binding.tvGiaBan.setText(sanPham.getDonGia()*10/100 +"");
                binding.tvGiaGoc.setText(sanPham.getDonGia()+"");
                binding.tvMota.setText(sanPham.getMoTa());
                binding.tvTenSP.setText(sanPham.getTenSP());
                Glide.with(getApplicationContext()).load(sanPham.getImg()).error(R.drawable.anhnen).into(binding.imgAnhHang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        display();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sp_chi_tiet,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.mn_ve_trang_chu:
                startActivity(new Intent(ChiTietSPActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    private void display(){

    }
}