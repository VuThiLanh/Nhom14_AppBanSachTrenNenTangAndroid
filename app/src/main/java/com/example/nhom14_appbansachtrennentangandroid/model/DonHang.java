package com.example.nhom14_appbansachtrennentangandroid.model;

import java.util.List;

public class DonHang {
    String id;
    ThongTinNhanHang thongTinNhanHang;
    List<GioHang> GioHangList;
    long TongTien, TongTienHang;
    String NgayTao;
    String TrangThai;

    public DonHang(String id, ThongTinNhanHang thongTinNhanHang, List<GioHang> gioHangList, long tongTien, long tongTienHang, String ngayTao, String trangThai) {
        this.id = id;
        this.thongTinNhanHang = thongTinNhanHang;
        GioHangList = gioHangList;
        TongTien = tongTien;
        TongTienHang = tongTienHang;
        NgayTao = ngayTao;
        TrangThai = trangThai;
    }

    public DonHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ThongTinNhanHang getThongTinNhanHang() {
        return thongTinNhanHang;
    }

    public void setThongTinNhanHang(ThongTinNhanHang thongTinNhanHang) {
        this.thongTinNhanHang = thongTinNhanHang;
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
