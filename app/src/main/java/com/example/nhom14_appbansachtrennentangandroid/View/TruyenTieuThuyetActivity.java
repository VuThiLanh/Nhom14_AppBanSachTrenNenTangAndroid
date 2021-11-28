package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamDanhMucAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TruyenTieuThuyetActivity extends AppCompatActivity {
    ArrayList<SanPham> listTruyenTieuThuyet;
    SanPhamDanhMucAdapter sanPhamDanhMucAdapter;
    RecyclerView rcTruyen_TieuThuyet;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_tieu_thuyet);
        rcTruyen_TieuThuyet = findViewById(R.id.rcTruyen_TieuThuyet);
        img_back = findViewById(R.id.img_back);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcTruyen_TieuThuyet.setLayoutManager(gridLayoutManager);

        listTruyenTieuThuyet = new ArrayList<>();
        sanPhamDanhMucAdapter = new SanPhamDanhMucAdapter(listTruyenTieuThuyet, this::onItemClick, getApplication());
        rcTruyen_TieuThuyet.setAdapter(sanPhamDanhMucAdapter);
        getTruyenTieuThuyet();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void getTruyenTieuThuyet(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("sanpham");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    if(sanPham.getMaDanhMuc().equals("dm02")){
                        listTruyenTieuThuyet.add(sanPham);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @NonNull
    public void onItemClick(SanPham sanPham) {
        Intent intent = new Intent(getApplication(), ChiTietSPActivity.class);
        intent.putExtra("maSP", sanPham.getIdSp()+"");
        startActivity(intent);
    }
}