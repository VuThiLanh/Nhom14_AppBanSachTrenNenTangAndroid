package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.View.ChiTietDonHangActivity;
import com.example.nhom14_appbansachtrennentangandroid.model.DonHang;
import com.example.nhom14_appbansachtrennentangandroid.model.GioHang;

import java.util.List;

public class DSDonHangAdapter extends RecyclerView.Adapter<DSDonHangAdapter.Holder> {
    List<DonHang> donHangList;
    Context context;

    public DSDonHangAdapter(List<DonHang> donHangList, Context context) {
        this.donHangList = donHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dh_dang_giao, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DonHang donHang=donHangList.get(position);
        GioHang gioHang=donHang.getGioHangList().get(0);
        if(gioHang!=null){
            Glide.with(context).load(gioHang.getImg()).error(R.drawable.ic_baseline_hide_image_24).into(holder.img_anh);
            holder.tv_tenSP.setText(gioHang.getTenSP());
            holder.tv_sl.setText("x"+ gioHang.getSoluong());
            holder.tv_gia.setText(gioHang.getDonGia()+"đ");
        }
        holder.tv_ngay.setText(donHang.getNgayTao());
        holder.tv_tongsl.setText(donHang.getGioHangList().size()+" sản phẩm");
        holder.tv_thanhtien.setText(donHang.getTongTien()+"đ");
        if(donHang.getGioHangList().size()>1){
            holder.tv_xem.setVisibility(View.VISIBLE);
        }
        else {
            holder.tv_xem.setVisibility(View.GONE);
        }
        holder.tv_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChiTietDonHangActivity.class);
                intent.putExtra("id", donHang.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView img_anh;
        TextView tv_tenSP, tv_sl,tv_ngay, tv_gia,tv_xem,tv_tongsl, tv_thanhtien;
        Button btn_danhgia,btn_danhan, btn_mualai, btn_huy;
        RelativeLayout rl_cho, rl_da_huy, rl_da_nhan,rl_dang_giao;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img_anh=itemView.findViewById(R.id.img_anh);
            tv_tenSP=itemView.findViewById(R.id.tv_tenSP);
            tv_sl=itemView.findViewById(R.id.tv_sl);
            tv_thanhtien=itemView.findViewById(R.id.tv_thanhtien);
            tv_gia=itemView.findViewById(R.id.item_gia);
            tv_ngay=itemView.findViewById(R.id.tv_ngay);
            tv_xem=itemView.findViewById(R.id.tv_xem);
            tv_tongsl=itemView.findViewById(R.id.tv_tongsl);
            btn_danhan=itemView.findViewById(R.id.btn_danhan);
            btn_danhgia=itemView.findViewById(R.id.btn_danhgia);
            btn_huy=itemView.findViewById(R.id.btn_huy);
            btn_mualai=itemView.findViewById(R.id.btn_mualai);
            rl_cho=itemView.findViewById(R.id.rl_cho);
            rl_da_huy=itemView.findViewById(R.id.rl_da_huy);
            rl_da_nhan=itemView.findViewById(R.id.rl_da_nhan);
            rl_dang_giao=itemView.findViewById(R.id.rl_dang_giao);
        }
    }
}
