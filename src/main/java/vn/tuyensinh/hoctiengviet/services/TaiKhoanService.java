package vn.tuyensinh.hoctiengviet.services;

import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface TaiKhoanService {

    List<TaiKhoan> findAll();
    TaiKhoan findByID(Long id);
    TaiKhoan findByTaiKhoan(String account);
    List<TaiKhoan> findByTrangThai(Integer status);
    List<TaiKhoan> findByStatusAndCreatedDate(Integer status, Timestamp fromDate, Timestamp toDate);
    void insertAccount(TaiKhoanRequest taiKhoanRequest);
    void deleteAccount(Long id);
    void updateAccount(TaiKhoanRequest taiKhoanRequest);
    Set<String> getRoleNames(Long id);



}
