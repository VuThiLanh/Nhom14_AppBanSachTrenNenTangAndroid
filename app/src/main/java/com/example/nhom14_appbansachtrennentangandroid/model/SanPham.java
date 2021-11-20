package com.example.nhom14_appbansachtrennentangandroid.model;

public class SanPham {

    String idSp;
    String NgayXB;
    int donGia;
    String img;
    String maDanhMuc;
    String moTa;
    float saoDangGia;
    int slCon;
    String tenSP;
    String tenTacGia;

    public SanPham(String idSp, String ngayXB, int donGia, String img, String maDanhMuc, String moTa, float saoDangGia, int slCon, String tenSP, String tenTacGia) {
        this.idSp = idSp;
        NgayXB = ngayXB;
        this.donGia = donGia;
        this.img = img;
        this.maDanhMuc = maDanhMuc;
        this.moTa = moTa;
        this.saoDangGia = saoDangGia;
        this.slCon = slCon;
        this.tenSP = tenSP;
        this.tenTacGia = tenTacGia;
    }

    public SanPham() {
    }

    public String getIdSp() {
        return idSp;
    }

    public void setIdSp(String idSp) {
        this.idSp = idSp;
    }

    public String getNgayXB() {
        return NgayXB;
    }

    public void setNgayXB(String ngayXB) {
        NgayXB = ngayXB;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getSaoDangGia() {
        return saoDangGia;
    }

    public void setSaoDangGia(float saoDangGia) {
        this.saoDangGia = saoDangGia;
    }

    public int getSlCon() {
        return slCon;
    }

    public void setSlCon(int slCon) {
        this.slCon = slCon;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
}
