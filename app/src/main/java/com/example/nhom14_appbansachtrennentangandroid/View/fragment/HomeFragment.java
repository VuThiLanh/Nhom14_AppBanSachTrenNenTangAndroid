package com.example.nhom14_appbansachtrennentangandroid.View.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DanhGiaAdapter;
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.Anh;
import com.example.nhom14_appbansachtrennentangandroid.model.DanhGia;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<SanPham> listSanPham;
    private ArrayList<SanPham> listSanPhamBanChay;
    private ArrayList<Anh> listAnh;
    SanPhamAdapter sanPhamAdapter,sanPhamBanChayAdapter ;
    RecyclerView rcTopBanChay,rcGoiY;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_home, container, false);
        init();
        getSanPham();
        getSanPhamBanChay();
        return view;

    }
    private void init(){
        rcTopBanChay = view.findViewById(R.id.rcTopBanChay);
        rcGoiY= view.findViewById(R.id.rcGoiY);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        rcTopBanChay.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL );
        rcTopBanChay.addItemDecoration(dividerItemDecoration);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcGoiY.setLayoutManager(gridLayoutManager);

        listSanPham = new ArrayList<>();
        listSanPhamBanChay= new ArrayList<>();

        sanPhamAdapter = new SanPhamAdapter(listSanPham,getActivity());
        sanPhamBanChayAdapter = new SanPhamAdapter(listSanPhamBanChay,getActivity());

        rcTopBanChay.setAdapter(sanPhamBanChayAdapter);
        rcGoiY.setAdapter(sanPhamAdapter);
    }

    private void getSanPham( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sanpham");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    listSanPham.add(sanPham);
                }
                sanPhamAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getSanPhamBanChay( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sanpham");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    if(sanPham.getSaoDanhGia()>4.0){
                        listSanPhamBanChay.add(sanPham);
                    }
                }
                sanPhamBanChayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getListAnh( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("viewanh");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   Anh anh = dataSnapshot.getValue(Anh.class);
                        listAnh.add(anh);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Get Link Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }



}