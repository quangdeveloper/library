package vn.tuyensinh.hoctiengviet.services;

import vn.tuyensinh.hoctiengviet.entity.GiangVien;

import java.sql.Timestamp;
import java.util.List;

public interface GiangVienService {
    List<GiangVien> findAll();
    GiangVien findByLectureCode(String code);
    GiangVien findByLectureID(Long gvid);
    void insert(GiangVien gv);
    void update(GiangVien gv);
    void delete(Long id);
    void deleteAll();
    List<GiangVien> findByStartDateAndAdrress(Timestamp fromDate,Timestamp toDate, String address);
}
