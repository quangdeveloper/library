package vn.tuyensinh.hoctiengviet.services;

import vn.tuyensinh.hoctiengviet.entity.SinhVienDangKi;
import vn.tuyensinh.hoctiengviet.model.request.SinhVienRegister;

import java.util.List;

public interface SinhVienDangKiService {
    List<SinhVienDangKi> findAll(Integer pageNo,Integer pageSize);
    SinhVienDangKi findByID(Long id);
    SinhVienDangKi findByPassport(String passport);
    void insert(SinhVienRegister sinhVienRegister);
    void delete(Long id);
}
