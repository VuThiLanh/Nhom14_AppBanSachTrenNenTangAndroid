package com.example.nhom14_appbansachtrennentangandroid.model;

import java.util.List;

public class DonHang {
    String id;
    TaiKhoan ThongTinNhanHang;
    List<GioHang> GioHangList;
    long TongTien, TongTienHang;
    String NgayTao;
    String TrangThai;

    public DonHang(String id, TaiKhoan thongTinNhanHang, List<GioHang> gioHangList, long tongTien, long tongTienHang, String ngayTao, String trangThai) {
        this.id = id;
        ThongTinNhanHang = thongTinNhanHang;
        GioHangList = gioHangList;
        TongTien = tongTien;
        TongTienHang = tongTienHang;
        NgayTao = ngayTao;
        TrangThai = trangThai;
    }

    public DonHang() {
    }

    public TaiKhoan getThongTinNhanHang() {
        return ThongTinNhanHang;
    }

    public void setThongTinNhanHang(TaiKhoan thongTinNhanHang) {
        ThongTinNhanHang = thongTinNhanHang;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
