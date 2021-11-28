package com.example.nhom14_appbansachtrennentangandroid.model;

public class GioHang {
<<<<<<< HEAD
    int sl;
    String id;

    public GioHang(int sl, String id) {
        this.sl = sl;
        this.id = id;
=======
    String idsp;
    String tenSP;
    String img;
    int donGia;
    int soluong;

    public GioHang(String idsp, String tenSP, String img, int donGia, int soluong) {
        this.idsp = idsp;
        this.tenSP = tenSP;
        this.img = img;
        this.donGia = donGia;
        this.soluong = soluong;
>>>>>>> main
    }
    public GioHang() {
    }
    public int getDongia() {
        return donGia;
    }

    public void setDongia(int donGia) {
        this.donGia = donGia;
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

<<<<<<< HEAD
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
=======
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
>>>>>>> main
    }
}
