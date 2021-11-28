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

public class ChinhTriPhapLuatActivity extends AppCompatActivity {
    ArrayList<SanPham> listChinhTriPhapLuat;
    SanPhamDanhMucAdapter sanPhamDanhMucAdapter;
    RecyclerView rcChinhTri_PhapLuat;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_tri_phap_luat);
        rcChinhTri_PhapLuat = findViewById(R.id.rcChinhTri_PhapLuat);
        img_back = findViewById(R.id.img_back);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcChinhTri_PhapLuat.setLayoutManager(gridLayoutManager);

        listChinhTriPhapLuat = new ArrayList<>();
        sanPhamDanhMucAdapter = new SanPhamDanhMucAdapter(listChinhTriPhapLuat, this::onItemClick, getApplication());
        rcChinhTri_PhapLuat.setAdapter(sanPhamDanhMucAdapter);
        getChinhTriPhapLuat();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void getChinhTriPhapLuat(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("sanpham");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    if(sanPham.getMaDanhMuc().equals("dm03")){
                        listChinhTriPhapLuat.add(sanPham);
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