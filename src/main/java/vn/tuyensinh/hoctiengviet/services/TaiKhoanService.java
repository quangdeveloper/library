package vn.tuyensinh.hoctiengviet.services;

import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;

import java.util.List;
import java.util.Set;

public interface TaiKhoanService {
    TaiKhoan findByID(Long id);
    TaiKhoan findByTaiKhoan(String tk);
    List<TaiKhoan> findAll();
    TaiKhoan insert(TaiKhoanRequest taiKhoanRequest);
    TaiKhoan update(TaiKhoanRequest taiKhoanRequest);
    void delete(Long id);
    List<String> getRoleNames(Long id);
    Set<Integer> getRoleIds(Long id);
}
