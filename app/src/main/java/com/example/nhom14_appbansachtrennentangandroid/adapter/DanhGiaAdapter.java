package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.model.DanhGia;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.ViewHower> {
    List<DanhGia> danhGiaList;
    Context context;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();


    public DanhGiaAdapter(List<DanhGia> danhGiaList, Context context) {
        this.danhGiaList = danhGiaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHower onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danh_gia, parent, false);
        return new ViewHower(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHower holder, int position) {

        DanhGia danhGia=danhGiaList.get(position);
        if(danhGia==null){
            return;
        }
        holder.tv_noidung.setText(danhGia.getNoidung());

    }

    @Override
    public int getItemCount() {
        if(danhGiaList != null){
            return danhGiaList.size();
        }
        return 0;
    }

    public class ViewHower extends RecyclerView.ViewHolder {
        ImageView img_avt;
        ImageView img_sao1;
        ImageView img_sao2;
        ImageView img_sao3;
        ImageView img_sao4;
        ImageView img_sao5;
        TextView tv_username;
        TextView tv_noidung;
        public ViewHower(@NonNull View itemView) {
            super(itemView);
            img_avt=itemView.findViewById(R.id.img_avt);
            img_sao1=itemView.findViewById(R.id.img_sao1);
            img_sao2=itemView.findViewById(R.id.img_sao2);
            img_sao3=itemView.findViewById(R.id.img_sao3);
            img_sao4=itemView.findViewById(R.id.img_sao4);
            img_sao5=itemView.findViewById(R.id.img_sao5);
            tv_noidung=itemView.findViewById(R.id.tv_noidung);
            tv_username=itemView.findViewById(R.id.tv_username);
        }
    }
}
