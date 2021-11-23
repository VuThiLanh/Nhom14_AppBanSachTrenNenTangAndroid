package com.example.nhom14_appbansachtrennentangandroid.View.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DanhGiaAdapter;
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamAdapter;
import com.example.nhom14_appbansachtrennentangandroid.model.DanhGia;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<SanPham> list= new ArrayList<>();
    SanPhamAdapter adapter ;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_home, container, false);
        takeInf(40);
        return view;

    }
    private void initRecyclerView(View view){
        RecyclerView recycleviewTatCa = view.findViewById(R.id.rcTopBanChay);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recycleviewTatCa.setLayoutManager(layoutManager);
        adapter = new SanPhamAdapter(list,getActivity());
        recycleviewTatCa.setAdapter(adapter);
    }
    private void takeInf( int n){
        int i =0;
        String sp = "sp00" +i+"";
        reference.child("sanpham").child(sp).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                int i=1;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SanPham sanPham= dataSnapshot.getValue(SanPham.class);
                        list.add(sanPham);
                        i++;
                    if(i==n){
                        break;
                    }
                }
                adapter=new SanPhamAdapter(list, getContext());
                RecyclerView rcTopBanChay = view.findViewById(R.id.rcTopBanChay);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                rcTopBanChay.setLayoutManager(layoutManager);
                rcTopBanChay.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}