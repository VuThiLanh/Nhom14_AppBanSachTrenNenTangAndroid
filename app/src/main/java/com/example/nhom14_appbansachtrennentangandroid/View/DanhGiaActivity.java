package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DanhGiaAdapter;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDanhGiaBinding;
import com.example.nhom14_appbansachtrennentangandroid.model.DanhGia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DanhGiaActivity extends AppCompatActivity {

    ActivityDanhGiaBinding binding;
    String maSP="";
    DanhGiaAdapter danhGiaAdapter;
    List<DanhGia> danhGiaList;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(DanhGiaActivity.this, R.layout.activity_danh_gia);

        setSupportActionBar(binding.toolbarSp);
        getSupportActionBar().setTitle("Đánh giá");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent=getIntent();
        maSP=intent.getStringExtra("maSP");



        binding.recDanhGia.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        danhGiaList=new ArrayList<>();
        reference.child("danhgia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                danhGiaList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DanhGia danhGia= dataSnapshot.getValue(DanhGia.class);
                    if(danhGia.getIdSp().equals(maSP)){
                        danhGiaList.add(danhGia);
                    }
                }
                danhGiaAdapter=new DanhGiaAdapter(danhGiaList, getApplicationContext());
                binding.recDanhGia.setAdapter(danhGiaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}