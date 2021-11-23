package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class SanPhamAdapter  extends  RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder>{
    List<SanPham> list;

    Context context;
    public  SanPhamAdapter(List<SanPham> list, Context context){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @NotNull
    @Override
    public SanPhamAdapter.SanPhamViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach,parent,false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SanPhamAdapter.SanPhamViewHolder holder, int position) {
        holder.item_ten.setText(list.get(position).getTenSP());
        holder.item_gia.setText(list.get(position).getDonGia()+"đ");
        holder.item_sao.setText(list.get(position).getSaoDanhGia()+"");
        Glide.with(context).load(list.get(position).getImg()).into(holder.item_anh);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    class SanPhamViewHolder extends RecyclerView.ViewHolder{
        TextView item_ten,item_gia,item_sao;
        ImageView item_anh;

        public  SanPhamViewHolder(View view){
            super(view);
            item_ten=view.findViewById(R.id.item_tensach);
            item_gia=view.findViewById(R.id.item_gia);
            item_sao=view.findViewById(R.id.item_sao);
            item_anh=view.findViewById(R.id.item_anhsach);
        }
    }

}
