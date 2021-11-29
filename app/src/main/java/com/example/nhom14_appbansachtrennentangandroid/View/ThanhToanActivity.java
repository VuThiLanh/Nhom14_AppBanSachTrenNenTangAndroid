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
import android.widget.Toast;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DonHangAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.DonHang;
import com.example.nhom14_appbansachtrennentangandroid.model.GioHang;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.example.nhom14_appbansachtrennentangandroid.model.TaiKhoan;
import com.example.nhom14_appbansachtrennentangandroid.model.ThongTinNhanHang;
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
    ListView lvSanPham_TT;
    TextView tvName, tvSdt, tvDiaChi, tvTongTien;
    TextView tvTongTienHang, tvDatHang;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;
    long TongTien_1, TongTienHang_1;
    RadioButton rdThanhToanKhiNhanHang;
    private ArrayList<DonHang> listDonHang;
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
        getDonHang();
        if(listDonHang == null){
            listDonHang = new ArrayList<>();
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
                        databaseReference.child("giohang").child(user.getUid()).removeValue();
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
        ThongTinNhanHang thongTinNhanHang = new ThongTinNhanHang(tvDiaChi.getText()+"", GioHangActivity.ThongTinCaNhan.getId_User(),GioHangActivity.ThongTinCaNhan.getNgaysinh(),tvSdt.getText()+"", tvName.getText()+"");
        DonHang DonHang = new DonHang("544768437DH"+(listDonHang.size()+1),thongTinNhanHang,MainActivity.listGioHang,TongTien_1, TongTienHang_1, date, "Chờ xác nhận");
        databaseReference.child("donhang").child(user.getUid()).child("544768437DH"+(listDonHang.size()+1)).setValue(DonHang);

    }
    private void getDonHang( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = database.getReference("donhang").child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDonHang.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
                    listDonHang.add(donHang);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}