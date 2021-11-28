package com.example.nhom14_appbansachtrennentangandroid.model;

import java.util.List;

public class DonHang {
    String id;
    String diachi;
    String id_User;
    String ngaysinh;
    String sdt;
    String username;
    List<GioHang> GioHangList;
    long TongTien, TongTienHang;
    String NgayTao;
    String TrangThai;

    /*public DonHang(String id, String diachi, String id_User, String ngaysinh, String sdt, String username, List<GioHang> gioHangList, long tongTien, long tongTienHang, String ngayTao, String trangThai) {
        this.id = id;
        this.diachi = diachi;
        this.id_User = id_User;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.username = username;
        GioHangList = gioHangList;
        TongTien = tongTien;
        TongTienHang = tongTienHang;
        NgayTao = ngayTao;
        TrangThai = trangThai;
    }*/

    public DonHang() {
    }

    public DonHang(String id, List<GioHang> gioHangList, long tongTien, long tongTienHang, String ngayTao, String trangThai) {
        this.id = id;
        GioHangList = gioHangList;
        TongTien = tongTien;
        TongTienHang = tongTienHang;
        NgayTao = ngayTao;
        TrangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GioHang> getGioHangList() {
        return GioHangList;
    }

    public void setGioHangList(List<GioHang> gioHangList) {
        GioHangList = gioHangList;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long tongTien) {
        TongTien = tongTien;
    }

    public long getTongTienHang() {
        return TongTienHang;
    }

    public void setTongTienHang(long tongTienHang) {
        TongTienHang = tongTienHang;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
