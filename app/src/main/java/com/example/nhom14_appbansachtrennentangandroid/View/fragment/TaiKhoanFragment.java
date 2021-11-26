package com.example.nhom14_appbansachtrennentangandroid.View.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.View.CapNhatTKActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.ChangePassActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.DangNhap;
import com.example.nhom14_appbansachtrennentangandroid.View.DonHangActivity;
import com.example.nhom14_appbansachtrennentangandroid.View.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
;

public class TaiKhoanFragment extends Fragment {

    View mView;
    LinearLayout tv_capnhattk,tv_thongtindonhang,tv_thongtinshop,tv_trogiup,tv_dangxuat,tv_thaydoimk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        anhxa();
        tv_capnhattk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CapNhatTKActivity.class);
                startActivity(intent);
            }
        });
        tv_thaydoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassActivity.class);
                startActivity(intent);
            }
        });
        tv_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), DangNhap.class));
//                FirebaseAuth.getInstance().signOut();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
            }
        });
        tv_thongtindonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DonHangActivity.class));
            }
        });
        return  mView;

    }
    private void anhxa(){
        tv_capnhattk=mView.findViewById(R.id.tv_capnhattaikhoan);
        tv_thaydoimk=mView.findViewById(R.id.tv_thaydoimk);
        tv_thongtindonhang=mView.findViewById(R.id.tv_thongtindonhang);
        tv_thongtinshop=mView.findViewById(R.id.tv_thongtinshop);
        tv_trogiup=mView.findViewById(R.id.tv_trogiup);
        tv_dangxuat=mView.findViewById(R.id.tv_dangxuat);
    }
}
