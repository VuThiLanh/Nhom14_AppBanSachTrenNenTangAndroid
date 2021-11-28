package com.example.nhom14_appbansachtrennentangandroid.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
<<<<<<< HEAD
=======
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
>>>>>>> main
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom14_appbansachtrennentangandroid.R;
<<<<<<< HEAD
import com.example.nhom14_appbansachtrennentangandroid.View.GioHangActivity;
=======
import com.example.nhom14_appbansachtrennentangandroid.View.ChiTietSPActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.ChinhTriPhapLuatActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.GiaoTrinhActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.GioHangActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.KhoaHocCongNgheKinhTeActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.TamLyTamLinhTonGiaoActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.ThieuNhiActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.TruyenTieuThuyetActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.VanHoaXaHoiLichSuActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.VanHocNgheThuatActivity;
import com.example.nhom14_appbansachtrennentangandroid.adapter.DanhGiaAdapter;
>>>>>>> main
import com.example.nhom14_appbansachtrennentangandroid.adapter.SanPhamAdapter;
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
    SanPhamAdapter sanPhamAdapter,sanPhamBanChayAdapter ;
    RecyclerView rcTopBanChay,rcGoiY;
    ViewFlipper anhquangcao;
    View view;
    ImageView img_GioHang;
    LinearLayout ll_ChinhTri_PhapLuat, ll_KhoaHoc_CN_KT, ll_VanHoc_NT, ll_VanHoa_XH_LS, ll_GiaoTrinh, ll_Truyen_TieuThuyet, ll_TamLy_TamLinh, ll_ThieuNhi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_home, container, false);
        init();
        getSanPham();
        getSanPhamBanChay();
        ChuyenDenGioHang();
        DanhMuc();
        return view;

    }
    private void init(){
        rcTopBanChay = view.findViewById(R.id.rcTopBanChay);
        rcGoiY= view.findViewById(R.id.rcGoiY);
        img_GioHang= view.findViewById(R.id.img_gioHang);
        ll_ChinhTri_PhapLuat=view.findViewById(R.id.ll_ChinhTri_PhapLuat);
        ll_KhoaHoc_CN_KT = view.findViewById(R.id.ll_KhoaHoc_CN_KT);
        ll_VanHoc_NT=view.findViewById(R.id.ll_VanHoc_NT);
        ll_VanHoa_XH_LS = view.findViewById(R.id.ll_VanHoa_XH_LS);
        ll_GiaoTrinh = view.findViewById(R.id.ll_GiaoTrinh);
        ll_Truyen_TieuThuyet = view.findViewById(R.id.ll_Truyen_TieuThuyet);
        ll_TamLy_TamLinh = view.findViewById(R.id.ll_TamLy_TamLinh);
        ll_ThieuNhi = view.findViewById(R.id.ll_ThieuNhi);

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

        int img[] = {R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,R.drawable.poster5};
        for (int i = 0; i < img.length; i++) {
            flipperImage(img[i]);
        }
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
    public void flipperImage(int image) {
        anhquangcao=view.findViewById(R.id.anhquangcao);
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);
        anhquangcao.addView(imageView);
        anhquangcao.setFlipInterval(3000);
        anhquangcao.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getContext().getApplicationContext(),android.R.anim.slide_in_left);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getContext().getApplicationContext(),android.R.anim.slide_out_right);
        anhquangcao.setInAnimation(animation_slide_in);
        anhquangcao.setOutAnimation(animation_slide_out);
    }
    private void ChuyenDenGioHang(){
        img_GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void DanhMuc(){
        ll_ChinhTri_PhapLuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChinhTriPhapLuatActivity.class);
                startActivity(intent);
            }
        });
        ll_KhoaHoc_CN_KT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KhoaHocCongNgheKinhTeActivity.class);
                startActivity(intent);
            }
        });
        ll_VanHoc_NT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VanHocNgheThuatActivity.class);
                startActivity(intent);
            }
        });
        ll_VanHoa_XH_LS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VanHoaXaHoiLichSuActivity.class);
                startActivity(intent);
            }
        });
        ll_GiaoTrinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GiaoTrinhActivity.class);
                startActivity(intent);
            }
        });
        ll_Truyen_TieuThuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TruyenTieuThuyetActivity.class);
                startActivity(intent);
            }
        });
        ll_TamLy_TamLinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TamLyTamLinhTonGiaoActivity.class);
                startActivity(intent);
            }
        });
        ll_ThieuNhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThieuNhiActivity.class);
                startActivity(intent);
            }
        });
    }
}