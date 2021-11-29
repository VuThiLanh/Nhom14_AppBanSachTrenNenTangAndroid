package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DonHangAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.DonHang;
import com.example.nhom14_appbansachtrennentangandroid.model.TaiKhoan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity {
    private List<DonHang> donHangList;
    ListView lvSanPham_TT;
    TextView tvName, tvSdt, tvDiaChi, tvTongTien;
    TextView tvTongTienHang, tvDatHang;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;
    long TongTien_1, TongTienHang_1;
    RadioButton rdThanhToanKhiNhanHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        lvSanPham_TT = findViewById(R.id.lvSanPham_TT);
        tvName = findViewById(R.id.tvName);
        tvSdt = findViewById(R.id.tvSdt);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvTongTien = findViewById(R.id.tvTongTien);
        tvTongTienHang = findViewById(R.id.tvTongTienHang);
        tvDatHang = findViewById(R.id.tvDatHang);
        rdThanhToanKhiNhanHang = findViewById(R.id.rdThanhToanKhiNhanHang);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        if(donHangList == null){
            donHangList = new ArrayList<>();
        }
        if(MainActivity.listGioHang.size() > 0) {
            DonHangAdapter adapterProduct = new DonHangAdapter(getApplicationContext(), MainActivity.listGioHang);
            lvSanPham_TT.setAdapter(adapterProduct);
            tong();
            tvDatHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rdThanhToanKhiNhanHang.isChecked()){
                        luuDon();
                        MainActivity.listGioHang.clear();
                        MainActivity.listGioHang.clear();
                        //databaseReference.child("GioHang").child(user.getUid()).removeValue();
                        //Intent intent = new Intent(getBaseContext(), DanhSachDonDangGiao.class);
                        //startActivity(intent);
                        //finish();
                        GioHangActivity.setGioHang();
                        GioHangActivity.SoLuongGioHang();
                    }

                }
            });
        }
        tvName.setText(GioHangActivity.ThongTinCaNhan.getUsername()+"");
        tvSdt.setText(GioHangActivity.ThongTinCaNhan.getSdt()+"");
        tvDiaChi.setText(GioHangActivity.ThongTinCaNhan.getDiachi()+"");
    }
    public void tong() {
        DecimalFormat formatPrice = new DecimalFormat("###,###,###");
        long tongtien = 0;
        for (int i = 0; i < MainActivity.listGioHang.size(); i++) {
            tongtien += (MainActivity.listGioHang.get(i).getDonGia() * MainActivity.listGioHang.get(i).getSoluong());
        }
        tvTongTienHang.setText(formatPrice.format(tongtien) + "đ");
        long tong = tongtien+25000;
        TongTien_1 = tong;
        TongTienHang_1 = tongtien;
        tvTongTien.setText(tong +"đ");
    }
    private void luuDon(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss ");
        String date = df.format(Calendar.getInstance().getTime());
        //Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
//        DonHang DonHang = new DonHang("DH1",MainActivity.listGioHang,TongTien_1, TongTienHang_1, date, "Chờ xác nhận");
//        databaseReference.child("donhang").child(user.getUid()).child("DH1").setValue(DonHang);
        databaseReference.child("donhang").child(user.getUid()).child("DH1").child("ThongTinNhanHang").child("diachi").setValue(GioHangActivity.ThongTinCaNhan.getDiachi());
        databaseReference.child("donhang").child(user.getUid()).child("DH1").child("ThongTinNhanHang").child("username").setValue(GioHangActivity.ThongTinCaNhan.getUsername());
        databaseReference.child("donhang").child(user.getUid()).child("DH1").child("ThongTinNhanHang").child("sdt").setValue(GioHangActivity.ThongTinCaNhan.getSdt());
        databaseReference.child("donhang").child(user.getUid()).child("DH1").child("ThongTinNhanHang").child("id_User").setValue(GioHangActivity.ThongTinCaNhan.getId_User());


    }
}