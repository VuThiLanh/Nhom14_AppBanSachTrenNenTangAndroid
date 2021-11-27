package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class VanHoaXaHoiLichSuActivity extends AppCompatActivity {
    ArrayList<SanPham> listVanHoaXHLS;
    SanPhamDanhMucAdapter sanPhamDanhMucAdapter;
    RecyclerView rcVanHoa_XH_LS;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_hoa_xa_hoi_lich_su);
        rcVanHoa_XH_LS = findViewById(R.id.rcVanHoa_XH_LS);
        img_back = findViewById(R.id.img_back);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcVanHoa_XH_LS.setLayoutManager(gridLayoutManager);

        listVanHoaXHLS = new ArrayList<>();
        sanPhamDanhMucAdapter = new SanPhamDanhMucAdapter(listVanHoaXHLS, getApplication());
        rcVanHoa_XH_LS.setAdapter(sanPhamDanhMucAdapter);
        getVanHoaXaHoiLichSu();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void getVanHoaXaHoiLichSu(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("sanpham");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    if(sanPham.getMaDanhMuc().equals("dm06")){
                        listVanHoaXHLS.add(sanPham);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}