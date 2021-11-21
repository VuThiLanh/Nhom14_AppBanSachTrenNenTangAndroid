package com.example.nhom14_appbansachtrennentangandroid.model;

public class DanhGia {
    String iddanggia;
    String noidung;
    String idSp;
    int sao;
    String id_User;

    public DanhGia(String iddanggia, String noidung, String idSp, int sao, String id_User) {
        this.iddanggia = iddanggia;
        this.noidung = noidung;
        this.idSp = idSp;
        this.sao = sao;
        this.id_User = id_User;
    }

    public DanhGia() {
    }

    public String getIddanggia() {
        return iddanggia;
    }

    public void setIddanggia(String iddanggia) {
        this.iddanggia = iddanggia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getIdSp() {
        return idSp;
    }

    public void setIdSp(String idSp) {
        this.idSp = idSp;
    }

    public int getSao() {
        return sao;
    }

    public void setSao(int sao) {
        this.sao = sao;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }
}
