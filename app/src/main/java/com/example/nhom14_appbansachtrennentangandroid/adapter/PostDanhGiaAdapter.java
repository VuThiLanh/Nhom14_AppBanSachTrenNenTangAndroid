package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.model.GioHang;

import java.util.List;

public class PostDanhGiaAdapter extends RecyclerView.Adapter<PostDanhGiaAdapter.Holder> {
    List<GioHang>gioHangList;
    Context context;

    public PostDanhGiaAdapter(List<GioHang> gioHangList, Context context) {
        this.gioHangList = gioHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_gia, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView img_anh,img_saovang1, img_saovang2, img_saovang3,img_saovang4, img_saovang5;
        TextView tv_tenSp;
        Button btn_dang;
        EditText et_noidung;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img_anh=itemView.findViewById(R.id.img_anh);
            img_saovang1=itemView.findViewById(R.id.img_saovang1);
            img_saovang2=itemView.findViewById(R.id.img_saovang2);
            img_saovang3=itemView.findViewById(R.id.img_saovang3);
            img_saovang4=itemView.findViewById(R.id.img_saovang4);
            img_saovang5=itemView.findViewById(R.id.img_saovang5);
            tv_tenSp=itemView.findViewById(R.id.tv_tenSp);
            btn_dang=itemView.findViewById(R.id.btn_dang);
            et_noidung=itemView.findViewById(R.id.et_noidung);
        }
    }
}
