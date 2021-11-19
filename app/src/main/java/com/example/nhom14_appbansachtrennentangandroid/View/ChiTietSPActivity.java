package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityChiTietSpactivityBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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