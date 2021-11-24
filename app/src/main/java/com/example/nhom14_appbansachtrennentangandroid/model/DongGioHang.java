package com.example.nhom14_appbansachtrennentangandroid.model;

public class DongGioHang {
    int sl;
    String id;

    public DongGioHang( int sl, String id) {
        this.sl = sl;
        this.id = id;
    }

    public DongGioHang() {
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
