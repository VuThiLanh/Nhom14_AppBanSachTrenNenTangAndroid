package com.example.nhom14_appbansachtrennentangandroid.model;

public class GioHang {
    String id;
    int tong;

    public GioHang(String id, int tong) {
        this.id = id;
        this.tong = tong;
    }

    public GioHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }
}
