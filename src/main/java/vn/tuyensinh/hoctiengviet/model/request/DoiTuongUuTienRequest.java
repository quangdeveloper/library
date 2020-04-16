package vn.tuyensinh.hoctiengviet.model.request;

public class DoiTuongUuTienRequest {
    private Integer id;
    private String loaiUuTien;

    public DoiTuongUuTienRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoaiUuTien() {
        return loaiUuTien;
    }

    public void setLoaiUuTien(String loaiUuTien) {
        this.loaiUuTien = loaiUuTien;
    }
}
