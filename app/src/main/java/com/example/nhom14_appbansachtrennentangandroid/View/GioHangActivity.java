package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
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
import com.example.nhom14_appbansachtrennentangandroid.model.TaiKhoan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    TextView tvMuaHang;
    public static TaiKhoan ThongTinCaNhan;
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
        getThongTinCaNhan();
        tvMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebaseDatabase = FirebaseDatabase.getInstance();
                //db = firebaseDatabase.getReference();
                if(ThongTinCaNhan == null || ThongTinCaNhan.getUsername() == null || ThongTinCaNhan.getDiachi() == null || ThongTinCaNhan.getEmail() == null|| ThongTinCaNhan.getSdt() == null){
                    Toast.makeText(getApplication(),"Vui lòng cập nhật đủ thông tin tài khoản để mua hàng",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(MainActivity.listGioHang.size() >0){
                    Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplication(),"Chưa có sản phẩm nòa trong giỏ hàng",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplication(),MainActivity.listSanPham.size()+"",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplication(),MainActivity.listGioHang.get(1).getTenSP()+"",Toast.LENGTH_SHORT).show();
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
            tongtien += (MainActivity.listGioHang.get(i).getDonGia()*MainActivity.listGioHang.get(i).getSoluong());
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
    private TaiKhoan getThongTinCaNhan() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("taikhoan").child(user.getUid());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ThongTinCaNhan = new TaiKhoan();
                    ThongTinCaNhan = snapshot.getValue(TaiKhoan.class);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        return ThongTinCaNhan;
    }
}