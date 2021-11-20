package com.example.nhom14_appbansachtrennentangandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDangKyBinding;
import com.example.nhom14_appbansachtrennentangandroid.databinding.ActivityDangNhapBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {

    ActivityDangKyBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        binding= DataBindingUtil.setContentView(DangKyActivity.this, R.layout.activity_dang_ky);
        progressDialog = new ProgressDialog(this);
        initListener();
        binding.toolbarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(DangKyActivity.this,DangNhap.class);
                startActivity(intent);
            }
        });
    }
    private  void initListener(){
        binding.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDangKy();
            }
        });
    }
    private  void onClickDangKy(){
        String email = binding.edEmailDangky.getText().toString().trim();
        String password = binding.edPassDangky.getText().toString().trim();
        String tenDN = binding.edTenDNDangky.getText().toString().trim();
        String nhaclai = binding.edNhaclaiMK.getText().toString().trim();
        if (email.equals("") || password.equals("") || tenDN.equals("")|| nhaclai.equals("")){
            AlertDialog ad = new AlertDialog.Builder(DangKyActivity.this).create();
            ad.setTitle("Thông báo");
            String msg = String.format("Vui lòng nhập đầy đủ thông tin!");
            ad.setMessage(msg);
            ad.setIcon(android.R.drawable.ic_dialog_info);
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                }
            });
            ad.show();
        }
        else{
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            if(nhaclai.equals(password)){
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    AlertDialog ad = new AlertDialog.Builder(DangKyActivity.this).create();
                                    ad.setTitle("Thông báo");
                                    String msg = String.format("Đăng ký thành công !");
                                    ad.setMessage(msg);
                                    ad.setIcon(android.R.drawable.ic_dialog_info);
                                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            Intent intent = new Intent(DangKyActivity.this, DangNhap.class);
                                            startActivity(intent);
                                        }
                                    });
                                    ad.show();

                                } else {
                                    AlertDialog ad = new AlertDialog.Builder(DangKyActivity.this).create();
                                    ad.setTitle("Thông báo");
                                    String msg = String.format("Đăng ký thất bại !");
                                    ad.setMessage(msg);
                                    ad.setIcon(android.R.drawable.ic_dialog_info);
                                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                        }
                                    });
                                    ad.show();
                                }
                            }
                        });

            }
            else {
                AlertDialog ad = new AlertDialog.Builder(DangKyActivity.this).create();
                ad.setTitle("Thông báo");
                String msg = String.format("Nhắc lại mật khẩu sai !");
                ad.setMessage(msg);
                ad.setIcon(android.R.drawable.ic_dialog_info);
                ad.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
                ad.show();
            }
        }
    }
}