package vn.tuyensinh.hoctiengviet.services;

import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.model.request.GiangVienRequest;

import java.sql.Timestamp;
import java.util.List;

public interface GiangVienService {
    List<GiangVien> findAll();
    GiangVien findByLectureCode(String code);
    GiangVien findByLectureID(Long gvid);
    void insert(GiangVienRequest gv);
    void update(GiangVienRequest gv);
    void delete(Long id);
    void deleteAll();
    List<GiangVien> findByStartDateAndAdrress(Timestamp fromDate,Timestamp toDate, String address);
}
