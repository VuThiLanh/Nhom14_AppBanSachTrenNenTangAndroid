package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhom14_appbansachtrennentangandroid.R;
import com.example.nhom14_appbansachtrennentangandroid.View.MainActivity;
import com.example.nhom14_appbansachtrennentangandroid.model.GioHang;
import com.example.nhom14_appbansachtrennentangandroid.model.SanPham;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {
    DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    Context context;
    List<GioHang> gioHangList;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @Override
    public int getCount() {
        if(gioHangList != null){
            return gioHangList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return gioHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_giohang, parent,false);
        TextView tvName, tvTangSL,tvGiamSL,tvSoLuong, tvPrice;
        ImageView imgPicture;
        tvName = view.findViewById(R.id.tvNameProduct_GH);
        tvPrice = view.findViewById(R.id.tvPrice_GH);
        tvTangSL = view.findViewById(R.id.tvTangSL_GH);
        tvGiamSL = view.findViewById(R.id.tvGiamSL_GH);
        tvSoLuong = view.findViewById(R.id.tvSoLuong_GH);
        imgPicture = view.findViewById(R.id.imgPictureProduct_GH);
        GioHang gioHang = gioHangList.get(position);
        tvName.setText(gioHang.getTenSP());
        tvPrice.setText(formatPrice.format(gioHang.getDongia()) +"");
        Glide.with(context).load(gioHang.getImg()).error(R.drawable.avatardefault).into(imgPicture);
        tvSoLuong.setText(gioHang.getSoluong()+"");
        return view;
    }
}
