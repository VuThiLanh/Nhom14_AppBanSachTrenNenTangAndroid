package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.GioHangAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    TextView tvMuaHang;
    DatabaseReference db;
    FirebaseDatabase firebaseDatabase;
    static ListView lv_GioHang;
    static TextView tvGioHangTrong;
    static TextView tvSoLuong;
    static TextView tvTongTien;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        tvMuaHang=findViewById(R.id.tvMuaHang);
        lv_GioHang = findViewById(R.id.lvGioHang);
        tvMuaHang = findViewById(R.id.tvMuaHang);
        tvTongTien= findViewById(R.id.tvTongTien);
        imgBack = findViewById(R.id.imgBack_GH);
        tvSoLuong = findViewById(R.id.tvSoLuongGioHang);
        tvGioHangTrong=findViewById(R.id.tvGioHAngTrong);
        tvMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebaseDatabase = FirebaseDatabase.getInstance();
                //db = firebaseDatabase.getReference();
                //Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                //startActivity(intent);
                //Toast.makeText(getApplication(),MainActivity.listSanPham.size()+"",Toast.LENGTH_SHORT).show();
                //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //db.child("giohang").child(user.getUid()).child("sp001").child("img").setValue(MainActivity.listSanPham.get(0).getImg());
            }
        });
        setGioHang();
        SoLuongGioHang();
        tongtien();
        Back();

    }
    public static void setGioHang(){
        if (MainActivity.listGioHang.size() > 0){
            GioHangAdapter adapterProduct = new GioHangAdapter(lv_GioHang.getContext(), MainActivity.listGioHang);
            lv_GioHang.setAdapter(adapterProduct);
            tvGioHangTrong.setVisibility(View.GONE);
        }
        else{
            lv_GioHang.setVisibility(View.GONE);
            tvGioHangTrong.setVisibility(View.VISIBLE);
        }
    }
    public static void SoLuongGioHang(){
        int SoLuong_GH =0 ;
        for(int i=0;i<MainActivity.listGioHang.size();i++){
            SoLuong_GH += MainActivity.listGioHang.get(i).getSoluong();
        }
        tvSoLuong.setText(SoLuong_GH+"");
    }
    public static void tongtien(){
        DecimalFormat formatPrice = new DecimalFormat("###,###,###");
        long tongtien =0;
        for(int i=0 ;i<MainActivity.listGioHang.size(); i++){
            tongtien += (MainActivity.listGioHang.get(i).getDongia()*MainActivity.listGioHang.get(i).getSoluong());
        }
        tvTongTien.setText(formatPrice.format(tongtien) +"đ");
    }
    private void Back(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}