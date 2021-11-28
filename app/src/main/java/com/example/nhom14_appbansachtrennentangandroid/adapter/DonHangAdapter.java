package com.example.nhom14_appbansachtrennentangandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nhom14_appbansachtrennentangandroid.model.GioHang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.List;

public class DonHangAdapter extends BaseAdapter {
    DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    Context context;
    List<GioHang> gioHangList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    /*public AdapterDatHang(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }*/
    @Override
    public int getCount() {
        if(gioHangList != null) {
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
        return null;
    }
}
