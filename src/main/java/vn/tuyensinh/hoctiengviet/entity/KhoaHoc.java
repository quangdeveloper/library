package vn.tuyensinh.hoctiengviet.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "khoahoc", uniqueConstraints = {@UniqueConstraint(name = "khoahoc_uk", columnNames = "makhoahoc")})
public class KhoaHoc extends Base{

    @Column(name = "makhoahoc",nullable =  false)
    private String maKhoaHoc;

    @Column(name = "tenkhoahoc",nullable =  false)
    private String tenKhoaHoc;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "noidung",nullable =  false)
    private String noiDung;

    @Column(name = "hocphi",nullable =  false)
    private Double hocPhi;

    @Column(name = "ngaybatdau",nullable =  false)
    private Timestamp ngayBatDau;

    @Column(name = "ngayketthuc",nullable =  false)
    private Timestamp ngayketthuc;

    @Column(name = "trangthai", nullable =  false)
    private Integer trangThai;

    @ManyToMany(mappedBy = "KhoaHocList",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<SinhVienDangKi> sinhVienDangKiList;

    public Timestamp getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Timestamp ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Timestamp getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Timestamp ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public KhoaHoc() {
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Double hocPhi) {
        this.hocPhi = hocPhi;
    }
}
