package vn.tuyensinh.hoctiengviet.model.request;

import vn.tuyensinh.hoctiengviet.entity.NgoaiNgu;
import vn.tuyensinh.hoctiengviet.entity.NguoiLienHeKhanCap;
import vn.tuyensinh.hoctiengviet.entity.ThanNhan;
import vn.tuyensinh.hoctiengviet.entity.ThanhTichHocTap;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class SinhVienRegister {
    private Long id;
    private String hoVaTen;
    private Timestamp ngaySinh;
    private Integer gioiTinh;
    private String noiSinh;
    private String quocGia;
    private String soHoChieu;
    private String diaChi;
    private String soDienThoai;
    private String fax;
    private String email;
    private NguoiLienHeKhanCap nguoiLienHeKhanCap;
    private Set<ThanNhan> listThanNhan;
    private ThanhTichHocTap thanhTichHocTap;
    private Set<NgoaiNgu> ngoaiNguList;
    private Long maKhoaHoc;
    private String noiHoc;
    private String heDaoTao;
    private Integer nienKhoa;
    private Integer doiTuongUuTien;
    private  Integer loaiHocBong;

    public SinhVienRegister() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Timestamp getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Timestamp ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getSoHoChieu() {
        return soHoChieu;
    }

    public void setSoHoChieu(String soHoChieu) {
        this.soHoChieu = soHoChieu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NguoiLienHeKhanCap getNguoiLienHeKhanCap() {
        return nguoiLienHeKhanCap;
    }

    public void setNguoiLienHeKhanCap(NguoiLienHeKhanCap nguoiLienHeKhanCap) {
        this.nguoiLienHeKhanCap = nguoiLienHeKhanCap;
    }

    public Set<ThanNhan> getListThanNhan() {
        return listThanNhan;
    }

    public void setListThanNhan(Set<ThanNhan> listThanNhan) {
        this.listThanNhan = listThanNhan;
    }

    public ThanhTichHocTap getThanhTichHocTap() {
        return thanhTichHocTap;
    }

    public void setThanhTichHocTap(ThanhTichHocTap thanhTichHocTap) {
        this.thanhTichHocTap = thanhTichHocTap;
    }

    public Set<NgoaiNgu> getNgoaiNguList() {
        return ngoaiNguList;
    }

    public void setNgoaiNguList(Set<NgoaiNgu> ngoaiNguList) {
        this.ngoaiNguList = ngoaiNguList;
    }

    public Long getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(Long maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getNoiHoc() {
        return noiHoc;
    }

    public void setNoiHoc(String noiHoc) {
        this.noiHoc = noiHoc;
    }

    public String getHeDaoTao() {
        return heDaoTao;
    }

    public void setHeDaoTao(String heDaoTao) {
        this.heDaoTao = heDaoTao;
    }

    public Integer getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(Integer nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public Integer getDoiTuongUuTien() {
        return doiTuongUuTien;
    }

    public void setDoiTuongUuTien(Integer doiTuongUuTien) {
        this.doiTuongUuTien = doiTuongUuTien;
    }

    public Integer getLoaiHocBong() {
        return loaiHocBong;
    }

    public void setLoaiHocBong(Integer loaiHocBong) {
        this.loaiHocBong = loaiHocBong;
    }
}
