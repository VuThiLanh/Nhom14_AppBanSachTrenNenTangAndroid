package com.example.nhom14_appbansachtrennentangandroid.View.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamAdapter;
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamDanhMucAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChinhTriPhapLuatFragment extends Fragment {
    private ArrayList<SanPham> listChinhTriPhapLuat;
    SanPhamDanhMucAdapter ChinhTriPhapLuatAdapter;
    RecyclerView rcChinhTriPhapLuat;
    public static ChinhTriPhapLuatFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ChinhTriPhapLuatFragment fragment = new ChinhTriPhapLuatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chinh_tri_phap_luat, container, false);
        rcChinhTriPhapLuat = view.findViewById(R.id.rcChinhTri_PhapLuat);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcChinhTriPhapLuat.setLayoutManager(gridLayoutManager);

        listChinhTriPhapLuat = new ArrayList<>();

        ChinhTriPhapLuatAdapter = new SanPhamDanhMucAdapter(listChinhTriPhapLuat,getActivity());
        rcChinhTriPhapLuat.setAdapter(ChinhTriPhapLuatAdapter);
        getSanPhamBanChay();
        return view;
    }

    private void getSanPhamBanChay( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sanpham");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                    if(sanPham.getMaDanhMuc().equals("dm03")){
                        listChinhTriPhapLuat.add(sanPham);
                    }
                }
                ChinhTriPhapLuatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Get Book Fail!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}