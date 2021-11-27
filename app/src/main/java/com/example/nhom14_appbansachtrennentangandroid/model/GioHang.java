package com.example.nhom14_appbansachtrennentangandroid.model;

public class GioHang {
    String idsp;
    String tenSP;
    String img;
    int dongia;
    int soluong;

    public GioHang(String idsp, String tenSP, String img, int dongia, int soluong) {
        this.idsp = idsp;
        this.tenSP = tenSP;
        this.img = img;
        this.dongia = dongia;
        this.soluong = soluong;
    }
    public GioHang() {
    }
    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getIdsp() {
        return idsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
