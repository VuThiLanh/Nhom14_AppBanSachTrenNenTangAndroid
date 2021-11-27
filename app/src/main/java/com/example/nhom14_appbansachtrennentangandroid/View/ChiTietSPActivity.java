package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DanhGiaAdapter;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityChiTietSpactivityBinding;
import com.example.nhom14_appbansachtrennentangandroid.model.DanhGia;
import com.example.nhom14_appbansachtrennentangandroid.model.DongGioHang;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSPActivity extends AppCompatActivity {

    ActivityChiTietSpactivityBinding binding;
    int sl = 0;
    String maSP = "";
    DanhGiaAdapter danhGiaAdapter;
    List<DanhGia> danhGiaList;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ChiTietSPActivity.this, R.layout.activity_chi_tiet_spactivity);

//        SanPham sanPham=new SanPham(idSp, nxb, donGia, img, maDanhMuc, moTa, saoDanhGia, slCon, tenSP, tenTacGia);
//        reference.child("sanpham").child(idSp).setValue(sanPham).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void o
//            nComplete(@NonNull Task<Void> task) {
//
//            }
//        });

        Intent intent = getIntent();
        maSP = intent.getStringExtra("maSP");

        setSupportActionBar(binding.toolbarSp);
        getSupportActionBar().setTitle("Chi tiết sản phẩm");


        binding.recDanhGia.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        danhGiaList = new ArrayList<>();
        displayDanhGia();


        binding.btnXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietSPActivity.this, DanhGiaActivity.class);
                intent1.putExtra("maSP", maSP);
                startActivity(intent1);
            }
        });


        binding.tvXemAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietSPActivity.this, DanhGiaActivity.class);
                intent1.putExtra("maSP", maSP);
                startActivity(intent1);
            }
        });


        sl = Integer.parseInt(binding.edSl.getText().toString());
        binding.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl += 1;
                binding.edSl.setText(sl + "");
            }
        });

        List<DongGioHang> dongGioHangList = new ArrayList<>();
        reference.child("donggiohang").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dongGioHangList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DongGioHang dongGioHang = dataSnapshot.getValue(DongGioHang.class);
                    if (dongGioHang.getId().equals(maSP)) {
                        dongGioHangList.add(dongGioHang);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnThem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (sl > 0) {
                    if (dongGioHangList.size() > 0) {
                        DongGioHang dongGioHang1 = new DongGioHang(sl + dongGioHangList.get(0).getSl(), maSP);
                        reference.child("donggiohang").child(user.getUid()).child(dongGioHang1.getId()).setValue(dongGioHang1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                AlertDialog ad = new AlertDialog.Builder(ChiTietSPActivity.this).create();
                                ad.setTitle("Thông báo");
                                String msg = String.format("Thêm giỏ hàng thành công!");
                                ad.setMessage(msg);
                                ad.setIcon(android.R.drawable.ic_dialog_info);
                                ad.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                ad.show();
                            }
                        });
                    } else {
                        DongGioHang dongGioHang = new DongGioHang(sl, maSP);
                        reference.child("donggiohang").child(user.getUid()).child(dongGioHang.getId()).setValue(dongGioHang);
                    }
                } else {
                    AlertDialog ad = new AlertDialog.Builder(ChiTietSPActivity.this).create();
                    ad.setTitle("Thông báo");
                    String msg = String.format("Số lượng phải lớn hơn 0!");
                    ad.setMessage(msg);
                    ad.setIcon(android.R.drawable.ic_dialog_info);
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    ad.show();
                }

            }
        });


        binding.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl -= 1;
                if (sl < 0) {
                    sl = 0;
                }
                binding.edSl.setText(sl + "");
            }
        });


        binding.imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSPActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });


        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSPActivity.this, MainActivity.class);
                intent.putExtra("trang", 1);
                startActivity(intent);
            }
        });

        binding.imgQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        display();


        binding.tvXemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                binding.tvMota.setLayoutParams(lp);
                binding.tvAnbot.setVisibility(View.VISIBLE);
                binding.tvXemthem.setVisibility(View.GONE);
            }
        });


        binding.tvAnbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
                binding.tvMota.setLayoutParams(lp);
                binding.tvAnbot.setVisibility(View.GONE);
                binding.tvXemthem.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sp_chi_tiet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mn_ve_trang_chu:
                startActivity(new Intent(ChiTietSPActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void display() {
        reference.child("sanpham").child(maSP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SanPham sanPham = snapshot.getValue(SanPham.class);
                binding.tvGiaGoc.setText(sanPham.getDonGia() + "đ");
                binding.tvMota.setText(sanPham.getMoTa());
                binding.tvTenSP.setText(sanPham.getTenSP());
                binding.tvTacgia.setText(sanPham.getTenTacGia());
                binding.tvNxb.setText(sanPham.getNxb());
                Glide.with(getApplicationContext()).load(sanPham.getImg()).error(R.drawable.anhnen).into(binding.imgAnhHang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void displayDanhGia() {
        reference.child("danhgia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                danhGiaList.clear();
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DanhGia danhGia = dataSnapshot.getValue(DanhGia.class);
                    if (danhGia.getIdSp().equals(maSP)) {
                        danhGiaList.add(danhGia);
                        i++;
                    }
                    if (i == 3) {
                        break;
                    }
                }
                danhGiaAdapter = new DanhGiaAdapter(danhGiaList, getApplicationContext());
                binding.recDanhGia.setAdapter(danhGiaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}