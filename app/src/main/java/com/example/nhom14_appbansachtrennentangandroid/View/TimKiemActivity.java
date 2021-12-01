package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.View.fragment.HomeFragment;
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamDanhMucAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {
    ArrayList<SanPham> listTimKiem;
    SanPhamDanhMucAdapter sanPhamDanhMucAdapter;
    RecyclerView rcTimKiem;
    ImageView img_back;
    String dataTimKiem;
    TextView tv_TK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        rcTimKiem = findViewById(R.id.rcTimKiem);
        img_back = findViewById(R.id.img_back);
        dataTimKiem = HomeFragment.tv_TimKiem();
        tv_TK = findViewById(R.id.tv_TK);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcTimKiem.setLayoutManager(gridLayoutManager);

        listTimKiem = new ArrayList<>();
        sanPhamDanhMucAdapter = new SanPhamDanhMucAdapter(listTimKiem, this::onItemClick, getApplication());
        rcTimKiem.setAdapter(sanPhamDanhMucAdapter);
        tv_TK.setMaxLines(1);
        tv_TK.setEllipsize(TextUtils.TruncateAt.END);
        tv_TK.setText(dataTimKiem);
        getTimKiem();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                HomeFragment.tv_TimKiem.setText("");
            }
        });
    }
    private void getTimKiem(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("sanpham");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    if(sanPham.getTenSP().toLowerCase().contains(dataTimKiem.toLowerCase())){
                        listTimKiem.add(sanPham);
                        if(listTimKiem == null){
                            Toast.makeText(getApplication(),"Không có sách!",Toast.LENGTH_SHORT).show();
                        }
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