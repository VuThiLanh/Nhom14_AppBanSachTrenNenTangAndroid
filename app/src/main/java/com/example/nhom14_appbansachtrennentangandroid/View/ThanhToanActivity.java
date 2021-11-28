package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.model.DonHang;
import com.example.nhom14_appbansachtrennentangandroid.model.TaiKhoan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        if(donHangList == null){
            donHangList = new ArrayList<>();
        }
        /*if(MainActivity.listGioHang.size() > 0) {
            AdapterDatHang adapterProduct = new AdapterDatHang(getApplicationContext(), MainActivity.mangGioHang);
            lv_Product_DH.setAdapter(adapterProduct);
            tong();
            tvDatHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    luuDon();
                    MainActivity.mangGioHang.clear();
                    MainActivity.mangMuaNgay.clear();
                    databaseReference.child("GioHang").child(user.getUid()).removeValue();
                    Intent intent = new Intent(getBaseContext(), DanhSachDonDangGiao.class);
                    startActivity(intent);
                    finish();
                    GioHangActivity.setGioHang();
                    GioHangActivity.SoLuongGioHang();
                }
            });
        }*/
        tvName.setText(GioHangActivity.ThongTinCaNhan.getUsername()+"");
        tvSdt.setText(GioHangActivity.ThongTinCaNhan.getSdt()+"");
        tvDiaChi.setText(GioHangActivity.ThongTinCaNhan.getDiachi()+"");
    }
    private List<DonHang> getDonDagnGiao(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user !=null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("DonDangGiao").child(user.getUid());

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        DonHang donHang = data.getValue(DonHang.class);
                        donHangList.add(donHang);
                    }
                    //Toast.makeText(getApplicationContext(),mangDonDangGiao.size()+"",Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        return donHangList;
    }
}